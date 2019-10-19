package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.services.BookService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	private BookService bookService;

	@ApiOperation(value = "Returns all Authors",
			response = Book.class,
			responseContainer = "List")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Book List Found",  responseContainer = "List"),
			@ApiResponse(code = 404, message = "Book List Not Found")})
	@ApiImplicitParams({@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"),
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
					value = "Sorting criteria in the format: property(,asc|desc). " + "Default sort order is ascending. " + "Multiple sort criteria are supported.")})
	@GetMapping(value = "/books", produces = {"application/json"})
	public ResponseEntity<?> getAllBooks(@PageableDefault(page = 0, size = 5) Pageable pageable){
		return new ResponseEntity<>(bookService.findAll(pageable), HttpStatus.OK);
	}

	@GetMapping(value = "/book/{id}", produces = {"application/json"})
	public ResponseEntity<?> getBookById(@PathVariable long id){
		return new ResponseEntity<>(bookService.findById(id), HttpStatus.OK);
	}

	@PostMapping(value = "/author", consumes = {"application/json"})
	public ResponseEntity<?> createBook(@Valid @RequestBody Book book){
		bookService.save(book);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
