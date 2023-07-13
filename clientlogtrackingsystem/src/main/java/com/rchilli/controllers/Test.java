package com.rchilli.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rchilli.entities.TestObject;

@Controller
public class Test {

	@GetMapping("/test")
	public String test(Model m) {
		TestObject to=new TestObject();
		to.setAge(1);
		to.setName("Karan");
		
		m.addAttribute("to",to);
		return "test";
	}
}
