package com.blog.payloads;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class RoleDto {
	
	private int  id;
	@NotEmpty
	private String name;

}
