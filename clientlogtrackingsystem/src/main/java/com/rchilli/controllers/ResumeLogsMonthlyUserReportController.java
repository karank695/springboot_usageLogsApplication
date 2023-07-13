package com.rchilli.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rchilli.entities.ResumeLogs;
import com.rchilli.services.ResumeLogsMonthlyUserReportService;

@Controller
public class ResumeLogsMonthlyUserReportController {
	@Autowired
	private ResumeLogsMonthlyUserReportService resumeLogsMonthlyUserReportService;

	@GetMapping("/monthly/report")
	public String getReport(@RequestParam("userId") int userId, @RequestParam("monthData") String monthData,
			@RequestParam("yearData") String yearData, Model m) {
		List<ResumeLogs> ls = resumeLogsMonthlyUserReportService.getReport(userId, monthData, yearData);
		m.addAttribute("listOfResumeLogs", ls);
		return "monthlyReport";
	}
}
