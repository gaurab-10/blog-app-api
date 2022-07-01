package com.blog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.exceptions.ResourceNotFoundException;
import com.blog.model.Role;
import com.blog.model.User;
import com.blog.payloads.UserDto;
import com.blog.repositories.RoleRepo;
import com.blog.repositories.UserRepo;
import com.blog.services.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private UserRepo userRepo;

	@Autowired
	public UserServiceImpl(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDto createUser(UserDto userdto) {
		// convert userdto to user
		// save
		// convert user to userdto
		// return userdto
		userdto.setPassword(passwordEncoder.encode(userdto.getPassword()));
		User savedUser = userRepo.save(dtoToUser(userdto));
		return userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		return userToDto(userRepo.save(user));
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers(int pageNo) {
		PageRequest pageRequest = PageRequest.of(pageNo, 3, Sort.by("name"));
		Page<User> page = this.userRepo.findAll(pageRequest);
		return page.getContent().stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
	}

	@Override
	public void deleteUser(Integer userID) {
		User user = this.userRepo.findById(userID)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", userID));
		this.userRepo.delete(user);
	}

	private User dtoToUser(UserDto userdto) {

		User user = this.modelMapper.map(userdto, User.class);

//		User user = new User();
//		user.setId(userdto.getId());
//		user.setName(userdto.getName());
//		user.setEmail(userdto.getEmail());
//		user.setPassword(userdto.getPassword());
//		user.setAbout(userdto.getAbout());
		return user;

	}

	private UserDto userToDto(User user) {

		UserDto userdto = this.modelMapper.map(user, UserDto.class);

//		userdto.setId(user.getId());
//		userdto.setName(user.getName());
//		userdto.setEmail(user.getEmail());
//		userdto.setPassword(user.getPassword());
//		userdto.setAbout(user.getAbout());
		return userdto;
	}

	@Override
	public UserDto registerUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role role1 = roleRepo.getReferenceById(5001);
		Role role = roleRepo.getReferenceById(5002);
		// user.getRoles().add(role);

		user.setRole(role1);
		user.setRole(role);
		return modelMapper.map(this.userRepo.save(user), UserDto.class);
	}

}
