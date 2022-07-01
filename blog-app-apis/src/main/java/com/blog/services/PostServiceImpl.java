package com.blog.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.exceptions.ResourceNotFoundException;
import com.blog.model.Category;
import com.blog.model.Post;
import com.blog.model.User;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.repositories.CategoryRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;
import com.blog.services.interfaces.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "user id", userId));
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "category id", categoryId));
		Post post = toPost(postDto);
		post.setImage("default.png");
		post.setUser(user);
		post.setCategory(category);
		return toPostDto(postRepo.save(post));
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postID) {
		Post post = postRepo.findById(postID)
				.orElseThrow(() -> new ResourceNotFoundException("post", "postID", postID));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImage(postDto.getImage());
		System.out.print("Saved image name is" + post.getImage());
		return toPostDto(postRepo.save(post));
	}

	@Override
	public PostDto getPostById(Integer postID) {
		Post post = postRepo.findById(postID)
				.orElseThrow(() -> new ResourceNotFoundException("post", "postID", postID));
		return toPostDto(post);
	}

	@Override
	public PostResponse getAllPost(int pageNumber, int pageSize, String sortBy, String sortDir) {
		sortDir = sortDir.substring(0, 3);
		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		PageRequest pageReq = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> posts = postRepo.findAll(pageReq);
		List<PostDto> list = posts.stream().map(post -> this.toPostDto(post)).collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(list);
		postResponse.setPageNumber(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setLastPages(posts.isLast());
		return postResponse;
	}

	@Override
	public void deletePost(Integer postID) {
		Post post = postRepo.findById(postID)
				.orElseThrow(() -> new ResourceNotFoundException("post", "postID", postID));
		postRepo.delete(post);

	}

	@Override
	public List<PostDto> getPostsByCategory(int categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
		List<Post> posts = postRepo.findAllByCategory(category);
		List<PostDto> list = new ArrayList<>();
		return posts.stream().map((post) -> toPostDto(post)).collect(Collectors.toList());
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = postRepo.findByTitleContaining("%" + keyword + "%");
		return posts.stream().map((post) -> toPostDto(post)).collect(Collectors.toList());
	}

	@Override
	public List<PostDto> getPostsByUser(int userID) {
		User user = userRepo.findById(userID)
				.orElseThrow(() -> new ResourceNotFoundException("user", "user id", userID));
		List<Post> posts = postRepo.findAllByUser(user);
		return posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

	}

	private Post toPost(PostDto postDto) {
		return modelMapper.map(postDto, Post.class);
	}

	private PostDto toPostDto(Post post) {
		return modelMapper.map(post, PostDto.class);
	}

}
