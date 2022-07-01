package com.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.UserDto;
import com.blog.services.interfaces.UserService;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	
	@PostMapping(value = "/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		return new ResponseEntity<UserDto>(userService.createUser(userDto), HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}")
	public UserDto getUserDetails(@PathVariable("id") Integer  id) {
		return userService.getUserById(id);
	}

	@PutMapping(value = "/{userId}") 
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer userId) {
		return new ResponseEntity<UserDto>(this.userService.updateUser(userDto, userId), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer userId) {
		this.userService.deleteUser(userId);
		return new ResponseEntity(new ApiResponse("User deleted successfuly", true), HttpStatus.OK);
	}

	@GetMapping(value = "/")
	public List<UserDto> getAllUsers() {
		return userService.getAllUsers(0);
	}

}
