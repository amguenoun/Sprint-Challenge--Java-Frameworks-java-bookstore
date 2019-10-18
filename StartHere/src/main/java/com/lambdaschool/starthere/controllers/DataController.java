package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.services.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

	@ApiOperation(value = "Updates A Book")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Book Updated"),
			@ApiResponse(code = 404, message = "Book Not Found")})
	@PutMapping(value = "/books/{id}", consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<?> updateBook(@ApiParam(value = "Body of Request", required = true) @RequestBody Book book,
										@ApiParam(value = "Book id", required = true) @PathVariable long id){
		bookService.update(book, id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "Assigns Book to Author")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Connection Created"),
			@ApiResponse(code = 404, message = "Book or Author Not Found")})
	@PostMapping(value = "/books/{bookid}/authors/{authorid}")
	public ResponseEntity<?> AssignBookToAuthor(@ApiParam(value = "Book id", required = true) @PathVariable long bookid,
												@ApiParam(value = "Author id", required = true) @PathVariable long authorid){
		bookService.addBookToAuthor(bookid,authorid);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@ApiOperation(value = "Deletes A Book")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Book Deleted"),
			@ApiResponse(code = 404, message = "Book Not Found")})
	@DeleteMapping(value = "/books/{id}")
	public ResponseEntity<?> deleteBook(@ApiParam(value = "Book id", required = true) @PathVariable long id){
		bookService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
