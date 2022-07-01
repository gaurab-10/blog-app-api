package com.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.model.Comment;
import com.blog.model.Post;
import com.blog.model.User;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

	List<Comment> findByUser(User user);

	List<Comment> findByPost(Post post);

	
	
}
