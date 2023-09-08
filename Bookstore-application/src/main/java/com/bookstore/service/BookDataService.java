package com.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entities.Book;
import com.bookstore.repositories.BookRepository;

@Service
public class BookDataService {
	
	@Autowired
	BookRepository bookRepository;
	
	//get All books
	public List<Book> getBooks(){
		return bookRepository.findAll();
	}
	
	//add books
	public void addNewBook(Book book) {
		bookRepository.save(book);
	}
	
	//get specific book by book_id
	public Book getBookById(int id) {
		try {
			return bookRepository.findById(id);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//get book by bookName
	public List<Book> getBookByName(String bookName) {
		try {
			return bookRepository.findBybookName(bookName);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	//get books by Author
	public List<Book> getBookByAuthor(String author) {
		try {
			return bookRepository.findByAuthor(author);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
