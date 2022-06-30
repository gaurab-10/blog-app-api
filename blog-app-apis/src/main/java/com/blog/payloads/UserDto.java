package com.blog.payloads;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

	@NotNull
	private int id;

	@NotEmpty
	@Size(min = 4, message = "Username must be min of 4 characters")
	private String name;

	@Email(message = "Email Address is not valid !!!")
	private String email;

	@NotEmpty // blank and not null check
	@Size(min = 5, max = 10, message = "Password must be of min  3 characters")
	private String password;

	@NotEmpty
	private String about;

	// private List<CommentDto> comments = new ArrayList<>();
	
	private List<RoleDto> roles = new ArrayList<RoleDto>();


	
	
}
