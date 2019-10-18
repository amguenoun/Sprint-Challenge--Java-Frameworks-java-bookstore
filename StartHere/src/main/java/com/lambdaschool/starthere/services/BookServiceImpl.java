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

	@Override
	public Book findById(long id) {
		return bookRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book Id: " + id + " not found."));
	}

	@Override
	public Book save(Book book) {
		Book newBook = new Book();

		newBook.setBooktitle(book.getBooktitle());
		newBook.setISBN(book.getISBN());
		newBook.setCopy(book.getCopy());
		newBook.setSection(book.getSection());

		if(book.getAuthors().size() > 0){
			for(Author a: book.getAuthors()){
				newBook.getAuthors().add(a);
			}
		}

		return bookRepo.save(newBook);
	}

	@Transactional
	@Override
	public Book update(Book book, long id) {
		Book currentBook = bookRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book Id: " + id + " not found."));

		if(book.getAuthors() != null){
			currentBook.setAuthors(book.getAuthors());
		}

		if(book.getBooktitle() != null){
			currentBook.setBooktitle(book.getBooktitle());
		}

		if(book.getCopy() > 0){
			currentBook.setCopy(book.getCopy());
		}

		if(book.getISBN() != null){
			currentBook.setISBN(book.getISBN());
		}

		if(book.getSection() != null){
			currentBook.setSection(book.getSection());
		}

		if(book.getAuthors().size() > 0){
			for(Author a : book.getAuthors()){
				currentBook.getAuthors().add(a);
			}
		}

		return bookRepo.save(currentBook);
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
		bookRepo.deleteFromWrote(bookid);
		bookRepo.deleteById(bookid);
	}
}
