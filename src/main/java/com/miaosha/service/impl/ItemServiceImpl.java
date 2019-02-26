package com.miaosha.service.impl;

import com.miaosha.dao.ItemDOMapper;
import com.miaosha.dao.ItemStockDOMapper;
import com.miaosha.dataobject.ItemDO;
import com.miaosha.dataobject.ItemStockDO;
import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBussinessError;
import com.miaosha.model.ItemModel;
import com.miaosha.service.ItemService;
import com.miaosha.validator.ValidationResult;
import com.miaosha.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by dangao on 2/25/2019.
 */
@Service
public class ItemServiceImpl implements ItemService
{
	@Autowired
	private ItemDOMapper itemDOMapper;

	@Autowired
	private ItemStockDOMapper itemStockDOMapper;

	@Autowired
	private ValidatorImpl validator;

	@Override
	@Transactional
	public ItemModel createItem(ItemModel itemModel) throws BusinessException
	{
		ValidationResult result = validator.validate(itemModel);
		if(result.isHasErrors())
		{
			throw new BusinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR, result.getErrorMessage());
		}
		ItemDO itemDO = this.convertItemDOFromItemModel(itemModel);
		itemDOMapper.insertSelective(itemDO);
		itemModel.setId(itemDO.getId());

		ItemStockDO itemStockDO = this.convertItemStockDOFromItemModel(itemModel);
		itemStockDOMapper.insertSelective(itemStockDO);
		return this.getItemById(itemModel.getId());
	}

	@Override
	public List<ItemModel> listItem()
	{
	List<ItemDO> itemDOList = itemDOMapper.listItem();
	return itemDOList.stream().map(itemDO ->
	{
		ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());
		return this.convertItemModelFromDO(itemDO, itemStockDO);
	}).collect(Collectors.toList());
}

	@Override
	public ItemModel getItemById(Integer id)
	{
		ItemDO itemDO = itemDOMapper.selectByPrimaryKey(id);
		if(itemDO == null)
		{
			return null;
		}

		ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(id);
		ItemModel itemModel = this.convertItemModelFromDO(itemDO, itemStockDO);
		return itemModel;
	}

	private ItemModel convertItemModelFromDO(ItemDO itemDO, ItemStockDO itemStockDO)
	{
		ItemModel itemModel = new ItemModel();
		BeanUtils.copyProperties(itemDO, itemModel);
		itemModel.setPrice(new BigDecimal(itemDO.getPrice()));
		itemModel.setStock(itemStockDO.getStock());
		return itemModel;
	}

	private ItemDO convertItemDOFromItemModel(ItemModel itemModel)
	{
		if(itemModel == null)
		{
			return null;
		}
		ItemDO itemDO = new ItemDO();
		BeanUtils.copyProperties(itemModel, itemDO);
		itemDO.setPrice(itemModel.getPrice().doubleValue());
		return itemDO;
	}

	private ItemStockDO convertItemStockDOFromItemModel(ItemModel itemModel)
	{
		if(itemModel == null)
		{
			return null;
		}

		ItemStockDO itemStockDO = new ItemStockDO();
		itemStockDO.setItemId(itemModel.getId());
		itemStockDO.setStock(itemModel.getStock());
		return itemStockDO;
	}
}
