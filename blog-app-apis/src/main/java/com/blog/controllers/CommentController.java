package com.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.CommentDto;
import com.blog.services.CommentService;

@RestController
@RequestMapping(value = "api/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/create/post/{postId}/user/{userId}")
	public ResponseEntity<?> createComment(@RequestBody CommentDto commentDto, @PathVariable("postId") Integer postId,
			@PathVariable("userId") Integer userId) {

		CommentDto savedComment = commentService.createComment(commentDto, postId, userId);
		return new ResponseEntity<CommentDto>(savedComment, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{commentId}")
	public ResponseEntity<?> deleteComment(@RequestBody CommentDto commentDto,
			@PathVariable("commentId") Integer commentId) {

		ApiResponse apiResponse = commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CommentDto> findCommentById(@PathVariable("id") int id) {
		return new ResponseEntity<CommentDto>(commentService.getCommentById(id), HttpStatus.OK);
	}

}
