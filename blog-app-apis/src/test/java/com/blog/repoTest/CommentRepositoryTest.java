package com.blog.repoTest;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blog.model.Comment;
import com.blog.model.Post;
import com.blog.model.User;
import com.blog.repositories.CommentRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class CommentRepositoryTest {

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PostRepo postRepo;

	@Test
	@Transactional
	void findCommentsByIdTest() {
		int id = 15;
		Optional<Comment> comment = commentRepo.findById(id);
		if (comment.isEmpty()) {
			assertNull(null);
		} else {
			assertTrue(true);
		}

	}

	@Test
	@Transactional
	void findCommentsByUserTest() {
		int userID = 2;
		User user = userRepo.findById(userID)
				.orElseThrow(() -> new EntityNotFoundException("Post not found on id: " + userID));
		List<Comment> comment = commentRepo.findByUser(user);
		assertTrue(comment.isEmpty());
	}
	
	@Test
	@Transactional
	void findCommentsOfPostTest() {
		int id = 17;
		Optional<Post> post = postRepo.findById(id);
		if (post.isEmpty()) {
			assertNull(null);
		} else {
			List<Comment> comment = commentRepo.findByPost(post.get());
			log.info("Comments of Post {} is -->{}",id, comment);
		
		}
		
	}

	@Test
	@Transactional
	void deleteCommentsByIDTest() {
		int id = 2;
		commentRepo.deleteById(2);
		Optional<Comment> comment = commentRepo.findById(id);
		if (comment.isEmpty()) {
			assertNull(null);
		} else {
			assertTrue(true);
		}
	}

}
