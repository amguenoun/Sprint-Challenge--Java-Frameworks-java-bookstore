package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.services.AuthorService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/authors")
public class AuthorController {
	@Autowired
	private AuthorService authorService;

	@ApiOperation(value = "Returns all Authors",
			response = Author.class,
			responseContainer = "List")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Author List Found",  responseContainer = "List"),
			@ApiResponse(code = 404, message = "Author List Not Found")})
	@ApiImplicitParams({@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"),
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
					value = "Sorting criteria in the format: property(,asc|desc). " + "Default sort order is ascending. " + "Multiple sort criteria are supported.")})
	@GetMapping(value = "/authors", produces = {"application/json"})
	public ResponseEntity<?> getAllAuthors(@PageableDefault(page = 0, size = 5) Pageable pageable){
		return new ResponseEntity<>(authorService.findAll(pageable), HttpStatus.OK);
	}

	@GetMapping(value = "/author/{id}", produces = {"application/json"})
	public ResponseEntity<?> getAuthorById(@PathVariable long id){
		return new ResponseEntity<>(authorService.findById(id), HttpStatus.OK);
	}

	@DeleteMapping("/author/{id}")
	public ResponseEntity<?> deleteAuthor(@PathVariable long id){
		authorService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(value = "/author", consumes = {"application/json"})
	public ResponseEntity<?> createAuthor(@Valid @RequestBody Author author){
		authorService.save(author);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
