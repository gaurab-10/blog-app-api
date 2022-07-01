package com.blog.payloads;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentDto {

	private int id;

	@NotEmpty
	private String content;

	@NotEmpty
	private UserDto user;

}
