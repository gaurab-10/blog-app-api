package com.blog;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blog.model.Post;
import com.blog.repositories.PostRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class PostRepositoryTest {
	
	
	@Autowired
	private PostRepo postRepo;

	@Test
	@Transactional
	void postAndAssociatedCategoryAndAssociatedUser() {
		int id = 15;
		Post post = postRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found on id: "+ id));
		log.info("Posts --> {}",post);
		log.info("Category --> {}",post.getCategory());
		log.info("User --> {}",post.getUser());
		log.info("Roles --> {}",post.getUser().getRoles());
		
	}
}
