package com.blog.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.payloads.CategoryDto;
import com.blog.payloads.UserDto;

//@Service
public interface CategoryService {

	CategoryDto createCategory(CategoryDto category);

	CategoryDto updateCategory(CategoryDto category, Integer CategoryDtoId);

	CategoryDto getCategoryById(Integer categoryId);

	List<CategoryDto> getAllCategory();

	void deleteUser(Integer categoryID);

}
