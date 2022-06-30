package com.blog.payloads;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostDto {
	
	private int id;

	private String title;

	private String content;

	private String image;
	
	private UserDto user;
	
	private LocalDateTime createdTime;

	private CategoryDto category;
	
	//@JsonIgnore --> If used then it will not load the comments of the particular post
	private List<CommentDto> comments = new ArrayList<>();
	

}
