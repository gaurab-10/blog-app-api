package com.blog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.exceptions.ResourceNotFoundException;
import com.blog.model.Category;
import com.blog.payloads.CategoryDto;
import com.blog.repositories.CategoryRepo;
import com.blog.services.interfaces.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat = this.categoryRepo.save(toCategory(categoryDto));
		return toCategoryDto(cat);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
		Category cat = this.categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
		cat.setTitle(categoryDto.getTitle());
		cat.setCategoryDesc(categoryDto.getCategoryDesc());
		return toCategoryDto(this.categoryRepo.save(cat));
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
		return toCategoryDto(cat);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> list = categories.stream().map(category -> this.toCategoryDto(category))
				.collect(Collectors.toList());
		return list;
	}

	@Override
	public void deleteUser(Integer categoryID) {
		Category cat = this.categoryRepo.findById(categoryID)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryID));
		this.categoryRepo.delete(cat);

	}

	private CategoryDto toCategoryDto(Category cat) {
		return modelMapper.map(cat, CategoryDto.class);

	}

	private Category toCategory(CategoryDto cat) {
		return modelMapper.map(cat, Category.class);

	}


}
