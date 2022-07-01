package com.blog.services.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.blog.payloads.UserDto;

@Service
public interface UserService {

	UserDto registerUser(UserDto userDto);

	UserDto createUser(UserDto user);

	UserDto updateUser(UserDto user, Integer userId);

	UserDto getUserById(Integer userId);

	List<UserDto> getAllUsers(int page);

	void deleteUser(Integer userID);

}
