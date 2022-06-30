package com.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.CategoryDto;
import com.blog.services.CategoryService;

@RestController
@RequestMapping(value = "/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	 
	@GetMapping(value = "/")
	public ResponseEntity<List<CategoryDto>> getCategories() {
		return new ResponseEntity<List<CategoryDto>>(categoryService.getAllCategory(), HttpStatus.OK);
	}

	@PostMapping(value = "/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto category) {
		return new ResponseEntity<CategoryDto>(categoryService.createCategory(category), HttpStatus.CREATED);
	}
	

	@PutMapping(value = "/{id}")
	public CategoryDto updateCategory(@RequestBody CategoryDto category, @PathVariable("id") int id) {
		return categoryService.updateCategory(category, id);
	}

	@GetMapping(value = "/{catID}")
	public CategoryDto getOneCategory(@PathVariable("catID") int catID) {
		return categoryService.getCategoryById(catID);
	}

	@DeleteMapping(value = "/{catID}")
	public void deleteCategory(@PathVariable("catID") int catID) {
		categoryService.deleteUser(catID);
	}

}
