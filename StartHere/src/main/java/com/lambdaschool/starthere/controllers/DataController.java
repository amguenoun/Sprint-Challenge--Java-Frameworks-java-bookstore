package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/data")
public class DataController {
	@Autowired
	private BookService bookService;

	@PutMapping(value = "/books/{id}", consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<?> updateBook(@RequestBody Book book, @PathVariable long id){
		bookService.update(book, id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(value = "/books/{bookid}/authors/{authorid}")
	public ResponseEntity<?> AssignBookToAuthor(@PathVariable long bookid, @PathVariable long authorid){
		bookService.addBookToAuthor(bookid,authorid);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/books/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable long id){
		bookService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
