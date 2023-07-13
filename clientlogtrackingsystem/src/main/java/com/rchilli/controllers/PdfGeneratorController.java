package com.rchilli.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import com.rchilli.entities.ResumeLogs;
import com.rchilli.services.DataMapperService;
import com.rchilli.services.DocumentGeneratorService;
import com.rchilli.services.ResumeLogsUserReportService;

@RestController
public class PdfGeneratorController {

	private static final Logger logger = LoggerFactory.getLogger(PdfGeneratorController.class);

	@Autowired
	private ResumeLogsUserReportService resumeLogsUserReportService;

	@Autowired
	private SpringTemplateEngine springTemplateEngine;

	@Autowired
	private DataMapperService dataMapper;

	@Autowired
	private DocumentGeneratorService documentGeneratorService;

	@GetMapping(value = "/reportPdf")
	public String generateDocument(@RequestParam("userId") int[] user_Ids, // Request parameter for user IDs
			@RequestParam("parseDate") String parseDate // Request parameter for parse date
	) {
		List<Integer> userIds = new ArrayList<>();

		// Convert int[] to List<Integer>
		for (int i = 0; i < user_Ids.length; i++) {
			userIds.add(user_Ids[i]);
		}

		// Retrieve report data based on user IDs and parse date
		List<ResumeLogs> ls = resumeLogsUserReportService.getReport(userIds, parseDate);

		String finalHtml = null;

		// Map data to Thymeleaf context
		Context dataContext = dataMapper.setData(ls);

		// Process Thymeleaf template to generate HTML
		finalHtml = springTemplateEngine.process("report", dataContext);

		// Log the generated HTML
		logger.info("Generated HTML: {}", finalHtml);
		Date date = new Date();
		String nameOfFile = "report" + date.getTime();
		// Convert HTML to PDF using a document generator service
		documentGeneratorService.htmlToPdf(finalHtml, nameOfFile);

		return "success";
	}
}
