package com.bookstore.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.configuration.JwtRequestFilter;
import com.bookstore.entities.Book;
import com.bookstore.entities.Order;
import com.bookstore.entities.OrderRequest;
import com.bookstore.entities.OrdersQuantity;
import com.bookstore.entities.User;
import com.bookstore.repositories.BookRepository;
import com.bookstore.repositories.OrderRepository;
import com.bookstore.repositories.UserRepository;

@Service
public class OrderService {
	
	private static final String ORDER_PLACED = "Placed";
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Order> getAllOrder() {
		List<Order> order = new ArrayList<>();
		orderRepository.findAll().forEach(x -> order.add(x));
		return order;
	}
	
	public List<Order> getOrderDetails() {
		String currentUser=JwtRequestFilter.CURRENT_USER;
		User user= userRepository.findFirstByEmail(currentUser);
		
		return orderRepository.findByUser(user);
	}
	
	public void placeOrder(OrderRequest orderRequest) {
		List<OrdersQuantity> productQuantityList = orderRequest.getOrderQuantityList();
		
		for(OrdersQuantity o: productQuantityList) {
			Book book= bookRepository.findById(o.getBookId()).get();
			
			String currentUser=JwtRequestFilter.CURRENT_USER;
			User user= userRepository.findFirstByEmail(currentUser);
			Order order = new Order(
					orderRequest.getFullName(),
					orderRequest.getContactNumber(),
					ORDER_PLACED,
					book.getPrice()*o.getQuantity(),
					book,
					user
					);
			orderRepository.save(order);
		}
		
		
	}

}
