package com.bookstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.entities.Book;
import com.bookstore.service.BookDataService;

@RestController
public class BookController {
	
	@Autowired
	BookDataService bookDataService;
	
	@GetMapping("/books")
	public ResponseEntity<List<Book>>gettingAllBooks(){
		List<Book> bookList = bookDataService.getBooks();
		
		if(bookList.size()==0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(bookList));
	}
	
	@PostMapping("/addbook")
	public ResponseEntity<Void> postingBooks(@RequestBody Book book){
		
		try{
            bookDataService.addNewBook(book);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	  }
	}
	
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> gettingBookById(@PathVariable("id") int id){
        Book booki = bookDataService.getBookById(id);

        if(booki == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(booki));
    }
    @GetMapping("/book/{bookName}")
    public ResponseEntity<List<Book>> gettingBookByName(@PathVariable("bookName") String bookName){
    	List<Book> bookN =bookDataService.getBookByName(bookName);
    	
    	if(bookN.size()==0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    	
        return ResponseEntity.of(Optional.of(bookN));
    }
    @GetMapping("/Books/{author}")
    public ResponseEntity<List<Book>> gettingBookByAuthor(@PathVariable("author") String author){
    	List<Book> bookAut =bookDataService.getBookByAuthor(author);
    	
    	if(bookAut.size()==0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(bookAut));
    }

}
