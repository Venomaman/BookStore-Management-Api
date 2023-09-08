package com.bookstore.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name ="books")
@AllArgsConstructor
public class Book{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 Long id;
	
	 String bookName; 
	
	 String discription;
	
	 String author;
	  
	 Double price;

   
	

	public Book(Long id, String bookName, String discription, String author, Double price) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.discription = discription;
		this.author = author;
		this.price = price;
	}


	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}
	
			
}
