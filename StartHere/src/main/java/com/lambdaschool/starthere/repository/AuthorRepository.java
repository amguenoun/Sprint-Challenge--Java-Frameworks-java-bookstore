package com.lambdaschool.starthere.repository;

import com.lambdaschool.starthere.models.Author;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM wrote WHERE authorid = :authorid", nativeQuery = true)
	void deleteFromWrote(long authorid);
}
