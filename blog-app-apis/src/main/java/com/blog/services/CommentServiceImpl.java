package com.blog.services;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.exceptions.ResourceNotFoundException;
import com.blog.model.Comment;
import com.blog.model.Post;
import com.blog.model.User;
import com.blog.payloads.ApiResponse;
import com.blog.payloads.CommentDto;
import com.blog.repositories.CommentRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;



@Service
public class CommentServiceImpl implements CommentService {

	private Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CommentRepo commentRepo;

	@Override
	public CommentDto createComment(CommentDto commnetDto, Integer postId, Integer userId) {

		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "post id", postId));
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "user id", userId));
		Comment comment = modelMapper.map(commnetDto, Comment.class);
		comment.setPost(post);
		comment.setUser(user);
		return (toCommentDto(commentRepo.save(comment)));
	}

	@Override
	public ApiResponse deleteComment(Integer commentId) {
		Comment comment = commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("comment", "comment id", commentId));
		commentRepo.delete(comment);
		return new ApiResponse("Comment Deleted successfully", true);

	}

	public CommentDto toCommentDto(Comment comment) {
		return modelMapper.map(comment, CommentDto.class);
	}

	public Comment toComment(CommentDto commentDto) {
		return modelMapper.map(commentDto, Comment.class);
	}

	@Override
	public CommentDto getCommentById(Integer id) {
		Comment comment = commentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("commnet", "comment id", id));
		//	logger.info("comment is:: {}", comment);
		return toCommentDto(comment);
	}

}
