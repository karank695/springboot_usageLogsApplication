package com.rchilli.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rchilli.entities.EmailDetails;

//This is test controller for confirmation of sending mail
@RestController
public class EmailController {

	@GetMapping("/sendMail")
	public String sendMail() {
		EmailDetails ed = new EmailDetails();
		ArrayList<String> emails = new ArrayList<>();
		emails.add("krn0862@gmail.com");
		emails.add("krn0861@gmail.com");
		ed.setRecipients(emails);
		return "email Sent";
	}
}
