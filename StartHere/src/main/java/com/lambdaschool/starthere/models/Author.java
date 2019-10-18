package com.lambdaschool.starthere.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
public class Author extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long authorid;

	private String lastname;

	private String firstname;

	@ManyToMany
	@JoinTable(name = "wrote", joinColumns = {@JoinColumn(name = "authorid")},
			inverseJoinColumns = {@JoinColumn(name = "bookid")})
	@JsonIgnoreProperties("authors")
	private List<Book> books = new ArrayList<>();

	public Author() {
	}

	public Author(String lastname, String firstname) {
		this.lastname = lastname;
		this.firstname = firstname;
	}

	public long getAuthorid() {
		return authorid;
	}

	public void setAuthorid(long authorid) {
		this.authorid = authorid;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Author{" + "authorid=" + authorid + ", lastname='" + lastname + '\'' + ", firstname='" + firstname + '\'' + ", books=" + books + '}';
	}
}