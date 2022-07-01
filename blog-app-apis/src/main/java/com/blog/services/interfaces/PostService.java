package com.blog.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;

@Service
public interface PostService {

	PostDto createPost(PostDto user, Integer userId, Integer categoryId);

	PostDto updatePost(PostDto user, Integer postID);

	PostDto getPostById(Integer postID);

	PostResponse getAllPost(int pageNO, int pageSize, String sortBy, String sortDir);
	
	List<PostDto> getPostsByCategory(int categoryId);
	
	//List<Post> getPostsByCategory(int categoryId);


	List<PostDto> getPostsByUser(int userID);

	void deletePost(Integer postID);

	List<PostDto> searchPosts(String keyword);

}
