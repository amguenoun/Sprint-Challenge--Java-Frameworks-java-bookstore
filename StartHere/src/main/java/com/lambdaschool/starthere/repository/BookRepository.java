package com.lambdaschool.starthere.repository;

import com.lambdaschool.starthere.models.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO wrote (bookid, authorid) VALUES (:bookid, :authorid)", nativeQuery = true)
	void connectBookToAuthor(long bookid, long authorid);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM wrote WHERE bookid = :bookid", nativeQuery = true)
	void deleteFromWrote(long bookid);

}
