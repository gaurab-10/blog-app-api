package com.blog.repoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blog.model.User;
import com.blog.repositories.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class UserRepositoryTest {
	
	
	@Autowired
	private UserRepo userRepo;
	
	
	@Test
	@Transactional
	void findUserByIdTest() {
		int id = 1;
		User user = userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found on id: "+ id));
		assertEquals("gaurab1@gmail.com", user.getEmail());
		
	}

	@Test
	@Transactional
	void userWithAssociatedRoles() {
		int id = 2;
		User user = userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found on id: "+ id));
		log.info("Role of UserId {} is --> {}",id, user.getRoles());	
		log.info("Comments done by UserId {} is --> {}",id, user.getComments());
	}
	
	@Test
	@Transactional
	void userWithAssociatedComments() {
		int id = 2;
		User user = userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found on id: "+ id));
		assertTrue( user.getComments().isEmpty());
		log.info("Comments done by UserId {} is --> {}",id, user.getComments());
	}
	
	@Test
	@Transactional
	void userWithAssociatedPosts() {
		int id = 2;
		User user = userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found on id: "+ id));
		log.info("Comments done by UserId {} is --> {}",id, user.getRoles());
	}



}
