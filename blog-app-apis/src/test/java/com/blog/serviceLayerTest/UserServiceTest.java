package com.blog.serviceLayerTest;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.blog.repositories.UserRepo;
import com.blog.services.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserServiceTest {

	@Mock
	private UserRepo userRepo;

	
	private UserServiceImpl userService;
	
	@BeforeEach
	void setUp() {
		this.userService = new UserServiceImpl(this.userRepo);
	}

	@Test
	void getAllUsers() {
		userService.getAllUsers(0);
		verify(userRepo).findAll();
	}

}
