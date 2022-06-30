package com.blog.payloads;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiResponse {
	
	private String message;
	private boolean success;
	public ApiResponse(String message, boolean success) {
		super();
		System.out.println("This is the ApiResponse class");
		this.message = message;
		this.success = success;
	}
	

}
