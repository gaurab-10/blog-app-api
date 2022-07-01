package com.blog.services.interfaces;

import org.springframework.stereotype.Service;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.CommentDto;

@Service
public interface CommentService {


	ApiResponse deleteComment(Integer commentId);
	CommentDto createComment(CommentDto commnetDto, Integer postId, Integer userId);
	
	CommentDto getCommentById(Integer id);
}
