package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	private BookService bookService;

	@GetMapping(value = "/books", produces = {"application/json"})
	public ResponseEntity<?> getAllBooks(@PageableDefault(page = 0, size = 5) Pageable pageable){
		return new ResponseEntity<>(bookService.findAll(pageable), HttpStatus.OK);
	}
}
