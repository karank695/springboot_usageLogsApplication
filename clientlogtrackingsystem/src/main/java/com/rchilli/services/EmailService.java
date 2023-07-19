package com.rchilli.services;

import org.thymeleaf.context.Context;

import com.rchilli.entities.EmailDetails;

public interface EmailService {

	// Method
    // To send a simple email
    String sendSimpleMail(EmailDetails details,Context context,String TemplateName);
 
    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailDetails details,Context context,String templateName,String fileName);
}
