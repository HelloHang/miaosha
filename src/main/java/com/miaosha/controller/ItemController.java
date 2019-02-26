package com.miaosha.controller;

import com.miaosha.error.BusinessException;
import com.miaosha.model.ItemModel;
import com.miaosha.response.CommonReturnType;
import com.miaosha.service.ItemService;
import com.miaosha.viewobject.ItemVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by dangao on 2/25/2019.
 */
@Controller
@RequestMapping("/item")
public class ItemController extends BaseController
{

	@Autowired
	private ItemService itemService;

	@PostMapping(value = "/create",consumes = {CONTENT_TYPE_FORMED})
	@ResponseBody
	public CommonReturnType createItem(@RequestParam(name = "title") String title,
		  @RequestParam(name = "description") String description,
		  @RequestParam(name = "price") BigDecimal price,
		  @RequestParam(name = "stock") Integer stock,
		  @RequestParam(name = "imgUrl") String imgUrl) throws Exception
	{
		ItemModel itemModel = new ItemModel();
		itemModel.setTitle(title);
		itemModel.setStock(stock);
		itemModel.setPrice(price);
		itemModel.setDescription(description);
		itemModel.setImgUrl(imgUrl);

		ItemModel itemModelReturn = itemService.createItem(itemModel);
		ItemVO itemVO = this.convertVOFromModle(itemModelReturn);
		return CommonReturnType.create(itemVO);
	}

	@GetMapping(value = "/get")
	@ResponseBody
	public CommonReturnType getItem(@RequestParam(name = "id") Integer id)
	{
		ItemModel itemModel = itemService.getItemById(id);
		ItemVO itemVO = this.convertVOFromModle(itemModel);
		return CommonReturnType.create(itemVO);
	}

	@GetMapping(value = "/list")
	@ResponseBody
	public CommonReturnType listItem()
	{
		List<ItemModel> modelList = itemService.listItem();

		List<ItemVO> itemVOList = modelList.stream().map(itemModel -> this.convertVOFromModle(itemModel))
			  .collect(Collectors.toList());
		return CommonReturnType.create(itemVOList);
	}

	private ItemVO convertVOFromModle(ItemModel itemModel)
	{
		if(itemModel == null)
		{
			return null;
		}
		ItemVO itemVO = new ItemVO();
		BeanUtils.copyProperties(itemModel, itemVO);
		return itemVO;
	}
}
