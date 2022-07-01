package com.blog.repoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.blog.model.Post;
import com.blog.model.User;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class PostRepositoryTest {
	
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	
	//@Test
	@Transactional
	void findPostByIdTest() {
		int id = 15;
		Post post = postRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Post not found on id: "+ id));
		assertEquals("Java", post.getTitle());
		log.info("Post of id {} is --> {}", id, post);
	}

	//@Test
	@Transactional
	void postAndAssociatedCategoryAndAssociatedUser() {
		int id = 15;
		Post post = postRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Post not found on id: "+ id));
		log.info("Posts2 --> {}",post);
		log.info("Category2 --> {}",post.getCategory());
		log.info("User2 --> {}",post.getUser());
		log.info("Roles --> {}",post.getUser().getRoles());
		
	}
	
	//@Test
	@Transactional
	void findPostsByUserTest() {
		int userID = 2;
		User user = userRepo.findById(userID).orElseThrow(() -> new EntityNotFoundException("Post not found on id: "+ userID));
		List<Post> post = postRepo.findAllByUser(user);
		for (Post result : post) {
			log.info("Post by UserID {} -->{}", userID, result);
		}	
	}
	
	@Test
	@DirtiesContext
	void deleteByIdTest() {
		int postID = 16;
		postRepo.deleteById(postID);
		Post post = postRepo.findById(postID).orElseThrow(() -> new EntityNotFoundException("Post not found on id: "+ postID));
		
	}
}
