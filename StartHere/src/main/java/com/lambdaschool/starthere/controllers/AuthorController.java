package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController {
	@Autowired
	private AuthorService authorService;

	@GetMapping(value = "/authors", produces = {"application/json"})
	public ResponseEntity<?> getAllAuthors(@PageableDefault(page = 0, size = 5) Pageable pageable){
		return new ResponseEntity<>(authorService.findAll(pageable), HttpStatus.OK);
	}
}
