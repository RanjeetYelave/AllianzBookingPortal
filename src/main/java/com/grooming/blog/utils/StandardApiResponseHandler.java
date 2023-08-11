package com.grooming.blog.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StandardApiResponseHandler {
	private String ApiResponse;
	private boolean status;
}
