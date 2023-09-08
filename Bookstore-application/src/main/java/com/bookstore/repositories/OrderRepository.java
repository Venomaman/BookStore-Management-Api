package com.bookstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.entities.Order;
import com.bookstore.entities.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	public List<Order> findByUser(User user);

}
