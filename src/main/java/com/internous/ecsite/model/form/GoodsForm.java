package com.internous.ecsite.model.form;

import java.io.Serializable;

public class GoodsForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String goodName;
	private long price;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getGoodsName() {
		return goodName;
	}
	public void setGoodsName(String goodName) {
		this.goodName = goodName;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	
}
