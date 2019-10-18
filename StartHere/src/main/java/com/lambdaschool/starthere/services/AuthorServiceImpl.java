package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.Book;
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

	@Override
	public Author findById(long id) {
		return authorRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author " + id + " Not Found"));
	}

	@Override
	public Author save(Author author) {
		Author newAuthor = new Author();
		newAuthor.setFirstname(author.getFirstname());
		newAuthor.setLastname(author.getLastname());

		if(author.getBooks().size()>0){
			for(Book b : author.getBooks()){
				newAuthor.getBooks().add(b);
			}
		}

		return authorRepo.save(newAuthor);
	}

	@Override
	public void delete(long id) {
		authorRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author " + id + " Not Found"));
		authorRepo.deleteFromWrote(id);
		authorRepo.deleteById(id);
	}
}
