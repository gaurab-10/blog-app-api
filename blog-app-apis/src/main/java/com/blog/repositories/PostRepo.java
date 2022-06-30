package com.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blog.model.Category;
import com.blog.model.Post;
import com.blog.model.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	@Query("Select p from Post p where p.category =?1")
	List<Post> findAllByCategory(Category category);

	
	@Query("Select p from Post p where p.user =?1")
	List<Post> findAllByUser(User user);
	
	@Query("Select p from Post p where p.title like ?1")
	List<Post> findByTitleContaining( String titlePattern);
	
}
