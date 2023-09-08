package com.bookstore.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Long orderId;
	private String orderName;
	private String contactNumber;
	private String orderStatus;
	private Double orderAmount;
	
	@OneToOne
	private Book book;
	@OneToOne
	private User user;
	
	
	public Order(String orderName, String contactNumber, String orderStatus, Double orderAmount,
			Book book, User user) {
		super();
		this.orderName = orderName;
		this.contactNumber = contactNumber;
		this.orderStatus = orderStatus;
		this.orderAmount = orderAmount;
		this.book = book;
		this.user = user;
	}
	
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	

}
