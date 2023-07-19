package com.rchilli.serviceImp;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.rchilli.entities.EmailDetails;
import com.rchilli.services.EmailService;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private TemplateEngine templateEngine;

	@Value("${spring.mail.username}")
	private String sender;

	private Logger logger=LoggerFactory.getLogger(EmailService.class);
	@Override
	public String sendSimpleMail(EmailDetails details, Context context, String templateName) {
		try {
			if (details.getRecipients().isEmpty()) {
				throw new IllegalStateException("NO recipients specified");
			}

			// Create a MimeMessage for sending emails
			MimeMessage message = javaMailSender.createMimeMessage();

			// Enable multipart mode and set the message properties
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(sender);
			helper.setTo(details.getRecipients().toArray(new String[0]));
			helper.setSubject("Sample Email");
			// Process the Thymeleaf template to generate HTML content
			String htmlContent = templateEngine.process(templateName, context);

			// Set the HTML content of the email
			helper.setText(htmlContent, true);
			ClassPathResource resource=new ClassPathResource("/static/images/warning.png");
			helper.addInline("warn",resource);
			logger.info("message has been sent");
			

			// Send the email using the JavaMailSender
			
			javaMailSender.send(message);
		} catch (Exception e) {
			return "Error while sending mail";
		}

		return "Email sent successfully";
	}

	@Override
	public String sendMailWithAttachment(EmailDetails details, Context context,String templateName,String fileName) {
		// TODO: Implement sending mail with attachments
		try {
			if (details.getRecipients().isEmpty()) {
				throw new IllegalStateException("NO recipients specified");
			}

			// Create a MimeMessage for sending emails
			MimeMessage message = javaMailSender.createMimeMessage();

			// Enable multipart mode and set the message properties
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(sender);
			helper.setTo(details.getRecipients().toArray(new String[0]));
			helper.setSubject("Your report");
			// Process the Thymeleaf template to generate HTML content
			String htmlContent = templateEngine.process(templateName, context);

			// Set the HTML content of the email
			helper.setText(htmlContent, true);
			String filename="C:\\Users\\karan\\Pictures\\pdfSpringBoot\\"+fileName+".pdf";
			File f=new File(filename);
			FileSystemResource file=new FileSystemResource(f);
			helper.addAttachment(file.getFilename(), file);
			// Send the email using the JavaMailSender
			
			javaMailSender.send(message);
			logger.info("mail sent with attachment");
		} catch (Exception e) {
			e.printStackTrace();
			return "Error while sending mail";
		}

		return "Email sent successfully";
	
	}

}
