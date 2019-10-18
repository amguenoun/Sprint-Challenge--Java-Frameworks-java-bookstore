package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.models.Section;
import com.lambdaschool.starthere.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("sectionService")
public class SectionServiceImpl implements SectionService {
	@Autowired
	private SectionRepository sectionRepo;

	@Override
	public List<Section> findAll(Pageable pageable) {
		List<Section> sections = new ArrayList<>();
		sectionRepo.findAll(pageable).iterator().forEachRemaining(sections::add);
		return sections;
	}

	@Override
	public Section findById(long id) {
		return sectionRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Section Id: " + id + " not found."));
	}

	@Override
	public Section save(Section section) {
		Section newSection = new Section();

		newSection.setSectionname(section.getSectionname());

		if(section.getBooks().size() > 0){
			for(Book b : section.getBooks()){
				newSection.getBooks().add(b);
			}
		}

		return sectionRepo.save(newSection);
	}

	@Override
	public void delete(long id) {
		sectionRepo.deleteById(id);
	}
}
