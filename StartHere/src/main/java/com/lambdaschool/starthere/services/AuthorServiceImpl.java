package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("authorService")
public class AuthorServiceImpl implements AuthorService {
	@Autowired
	private AuthorRepository authorRepo;

	@Override
	public List<Author> findAll(Pageable pageable) {
		List<Author> authors = new ArrayList<>();
		authorRepo.findAll(pageable).iterator().forEachRemaining(authors::add);
		return authors;
	}
}
