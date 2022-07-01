package com.blog.repoTest;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blog.model.Category;
import com.blog.repositories.CategoryRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class CategoryRepositoryTest {
	
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Test
	@Transactional
	void getCategoryByIdTest() {
		int id = 5;
		Category category = categoryRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found on id: "+ id));
		log.info("Category --> {}",category);
		
	}
}
