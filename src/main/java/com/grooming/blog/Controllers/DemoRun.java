package com.grooming.blog.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DemoRun {

	@GetMapping("hi/")
	ResponseEntity<String> demoRun() {
		String string = "Hi";
		return new ResponseEntity<String>(string, HttpStatus.OK);
	}

}
