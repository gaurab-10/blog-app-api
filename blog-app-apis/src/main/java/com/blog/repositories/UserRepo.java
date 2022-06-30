package com.blog.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.blog.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	public Page<User> findAll(Pageable pageable);

	public User findByEmail(String email);

}
