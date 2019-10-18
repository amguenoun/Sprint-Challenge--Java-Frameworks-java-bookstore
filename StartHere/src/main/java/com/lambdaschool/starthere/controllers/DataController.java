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

}
