package com.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDto {

	private Integer id;
	@NotEmpty
	@Size(min = 4, message = "Min size must be 4")
	private String title;
	@NotEmpty
	@Size(min = 14, message = "Min size must be 14")
	private String categoryDesc;
}
