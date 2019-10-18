package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Book;

import java.util.List;

public interface BookService {
	List<Book> findAll();

	Book update(Book book);

	void addBookToAuthor(long bookid, long authorid);

	void delete(long bookid);
}
