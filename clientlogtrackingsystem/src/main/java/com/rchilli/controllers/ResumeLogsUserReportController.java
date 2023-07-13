package com.rchilli.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.rchilli.entities.ResumeLogs;
import com.rchilli.services.ResumeLogsUserReportService;

@Controller
public class ResumeLogsUserReportController {
	private static Logger logger = LoggerFactory.getLogger(ResumeLogsUserReportController.class);
	@Autowired
	private ResumeLogsUserReportService resumeLogsUserReportService;

	@GetMapping("/resumeLogs")
	public String getReport(@RequestParam("userId") int[] user_Ids, @RequestParam("parseDate") String parseDate,
			Model m) throws ParseException {
		logger.info("data retrieved for different userIds");
		List<Integer> userIds = new ArrayList<>();
		for (int i = 0; i < user_Ids.length; i++) {
			userIds.add(user_Ids[i]);
		}
		List<ResumeLogs> ls = resumeLogsUserReportService.getReport(userIds, parseDate);
		m.addAttribute("listOfResumeLogs", ls);
		return "report";
	}
}
