package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Author;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorService {
	List<Author> findAll(Pageable pageable);

	Author findById(long id);

	Author save(Author author);

	void delete(long id);
}
