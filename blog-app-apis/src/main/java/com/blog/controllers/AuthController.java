package com.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blog.exceptions.ApiException;
import com.blog.payloads.JwtRequest;
import com.blog.payloads.JwtResponse;
import com.blog.payloads.UserDto;
import com.blog.services.interfaces.UserService;
import com.blog.utils.JwtUtil;

@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService customUserDetailsService;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;

	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

		System.out.println(jwtRequest);

		try {
			// we passed the user_name and password to authenticate the user.
			authenticateUser(jwtRequest.getUsername(), jwtRequest.getPassword());
			UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
			String token = this.jwtUtil.generateToken(userDetails);
			System.out.println("JWT::" + token);
			return ResponseEntity.ok(new JwtResponse(token));

		} catch (UsernameNotFoundException e) {
			throw new Exception("Username not found::" + e.getMessage());
		}

	}

	public void authenticateUser(String username, String password) throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER DISABLED: " + e.getMessage());
		}

		catch (BadCredentialsException e) {
			throw new ApiException("User name or password not valid");
		}

		catch (Exception e) {
			throw new ApiException("Exception occured " + e.getMessage());
		}
	}

	@PostMapping(value="/registerUser")
	public ResponseEntity<?> registerUser(@RequestBody UserDto userDto){
		
		return new ResponseEntity<UserDto>(	userService.registerUser(userDto), HttpStatus.CREATED);
	}

}