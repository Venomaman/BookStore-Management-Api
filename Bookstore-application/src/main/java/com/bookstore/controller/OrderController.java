package com.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.entities.Order;
import com.bookstore.entities.OrderRequest;
import com.bookstore.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/placeOrder")
	public void placeOrder(@RequestBody OrderRequest orderRequest) {
		orderService.placeOrder(orderRequest);
	}
	
	@GetMapping("/getOrder")
	public List<Order> getOrderHistory() {
		return orderService.getOrderDetails();
		
	}
	@GetMapping("/orderHistory")
	public List<Order> getAllOrder() {
		return orderService.getAllOrder();
		
	}

}
