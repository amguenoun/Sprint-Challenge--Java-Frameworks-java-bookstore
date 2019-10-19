package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Section;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SectionService {
	List<Section> findAll(Pageable pageable);

	Section findById(long id);

	Section save(Section section);

	void delete(long id);
}
