package com.miaosha.service;

import com.miaosha.error.BusinessException;
import com.miaosha.model.ItemModel;

import java.util.List;


/**
 * Created by dangao on 2/25/2019.
 */
public interface ItemService
{
	ItemModel createItem(ItemModel itemModel) throws BusinessException;

	List<ItemModel> listItem();

	ItemModel getItemById(Integer id);
}
