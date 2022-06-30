package com.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.model.User;
import com.blog.model.UserDetailsImpl;
import com.blog.repositories.UserRepo;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username)  {
		User user = userRepo.findByEmail(username);
		if(user!= null) {
			return new UserDetailsImpl(user);
			//return user;
		}
		throw new UsernameNotFoundException("User name not found");
	}

}
