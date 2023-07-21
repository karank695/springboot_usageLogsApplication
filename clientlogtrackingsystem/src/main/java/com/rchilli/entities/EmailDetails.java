package com.rchilli.entities;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//Details of property required for sending mail
//recipients
//msgBody
//subject
@ConfigurationProperties(prefix = "app")
public class EmailDetails {

	private List<String> recipients;
	private String msgBody;
	private String subject;

	public void AddRecipient(String email) {
		recipients.add(email);
	}

	public void removeRecipient(String email) {
		int index = recipients.indexOf(email);
		recipients.remove(index);
	}

}
