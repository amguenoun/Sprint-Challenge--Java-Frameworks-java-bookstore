package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
public class Book extends Auditable {
	@ApiModelProperty(name = "bookid", value = "primary key for book", required = true, example= "1")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long bookid;

	@ApiModelProperty(name = "booktitle", value = "Title of Book", required = true)
	@Column(nullable = false)
	private String booktitle;

	@ApiModelProperty(name = "ISBN", value = "Book ISBN", required = true)
	@Column(nullable = false, unique = true)
	private String ISBN;

	@ApiModelProperty(name = "copy", value = "Copyright Year of Book")
	private int copy;

	@ManyToMany(mappedBy = "books")
	@JsonIgnoreProperties("books")
	private List<Author> authors = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "sectionid")
	@JsonIgnoreProperties("books")
	private Section section;

	public Book() {
	}

	public Book(String booktitle, String ISBN, int copy, Section section) {
		this.booktitle = booktitle;
		this.ISBN = ISBN;
		this.copy = copy;
		this.section = section;
	}

	public long getBookid() {
		return bookid;
	}

	public void setBookid(long bookid) {
		this.bookid = bookid;
	}

	public String getBooktitle() {
		return booktitle;
	}

	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	public int getCopy() {
		return copy;
	}

	public void setCopy(int copy) {
		this.copy = copy;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	@Override
	public String toString() {
		return "Book{" + "bookid=" + bookid + ", booktitle='" + booktitle + '\'' + ", ISBN='" + ISBN + '\'' + ", copy=" + copy + ", authors=" + authors + ", section=" + section + '}';
	}
}

