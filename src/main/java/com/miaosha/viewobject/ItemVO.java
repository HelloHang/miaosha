package com.miaosha.viewobject;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


/**
 * Created by dangao on 2/25/2019.
 */
public class ItemVO
{
	private Integer id;

	private String title;

	private BigDecimal price;

	private String description;

	private Integer stock;

	//销量
	private Integer sales;

	private String imgUrl;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public BigDecimal getPrice()
	{
		return price;
	}

	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Integer getStock()
	{
		return stock;
	}

	public void setStock(Integer stock)
	{
		this.stock = stock;
	}

	public Integer getSales()
	{
		return sales;
	}

	public void setSales(Integer sales)
	{
		this.sales = sales;
	}

	public String getImgUrl()
	{
		return imgUrl;
	}

	public void setImgUrl(String imgUrl)
	{
		this.imgUrl = imgUrl;
	}
}
