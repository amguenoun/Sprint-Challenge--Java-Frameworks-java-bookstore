package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.AuthorRepository;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepository bookRepo;

	@Autowired
	private AuthorRepository authorRepo;

	@Override
	public List<Book> findAll(Pageable pageable) {
		List<Book> books = new ArrayList<>();
		bookRepo.findAll(pageable).iterator().forEachRemaining(books::add);
		return books;
	}

	@Transactional
	@Override
	public Book update(Book book) {
		Book newBook = new Book();

		if(book.getAuthors() != null){
			newBook.setAuthors(book.getAuthors());
		}

		if(book.getBooktitle() != null){
			newBook.setBooktitle(book.getBooktitle());
		}

		if(book.getCopy() > 0){
			newBook.setCopy(book.getCopy());
		}

		if(book.getISBN() != null){
			newBook.setISBN(book.getISBN());
		}

		if(book.getSection() != null){
			newBook.setSection(book.getSection());
		}

		if(book.getAuthors().size() > 0){
			for(Author a : book.getAuthors()){
				newBook.getAuthors().add(a);
			}
		}

		return bookRepo.save(newBook);
	}

	@Transactional
	@Override
	public void addBookToAuthor(long bookid, long authorid) {
		bookRepo.findById(bookid).orElseThrow(() -> new ResourceNotFoundException("Book Id: " + bookid + " not found."));
		authorRepo.findById(authorid).orElseThrow(() -> new ResourceNotFoundException("Author Id: " + authorid + " not found."));

		bookRepo.connectBookToAuthor(bookid, authorid);
	}

	@Transactional
	@Override
	public void delete(long bookid) {
		bookRepo.findById(bookid).orElseThrow(() -> new ResourceNotFoundException("Book Id: " + bookid + " not found."));
		bookRepo.deleteById(bookid);
	}
}
