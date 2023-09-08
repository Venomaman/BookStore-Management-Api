package com.bookstore.entities;

import java.util.List;

public class OrderRequest {
	
	private String fullName;
	private String contactNumber;
	
	private List<OrdersQuantity> orderQuantityList;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public List<OrdersQuantity> getOrderQuantityList() {
		return orderQuantityList;
	}

	public void setOrderQuantityList(List<OrdersQuantity> orderQuantityList) {
		this.orderQuantityList = orderQuantityList;
	}
	
	

}
