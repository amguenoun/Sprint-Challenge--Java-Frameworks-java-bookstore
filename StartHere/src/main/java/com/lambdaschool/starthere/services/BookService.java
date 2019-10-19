package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
	List<Book> findAll(Pageable pageable);

	Book findById(long id);

	Book save(Book book);

	Book update(Book book, long id);

	void addBookToAuthor(long bookid, long authorid);

	void delete(long bookid);
}
