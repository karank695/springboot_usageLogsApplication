package com.rchilli.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rchilli.entities.DetailsOfClientReport;
import com.rchilli.services.ResumeLogsUserReportService;

@Controller
public class ResumeLogsUserReportByMonthController {
	@Autowired
	private ResumeLogsUserReportService resumeLogsMonthlyUserReportService;

	@GetMapping("/monthly/report")
	
	public Object getReport(@RequestParam("userId") int userId, @RequestParam("year") String year,@RequestParam("month") String month,ModelAndView mav) {
		DetailsOfClientReport dcr=null;
		dcr = resumeLogsMonthlyUserReportService.getReportByMonthAndYear(userId, year, month);
		if(dcr==null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("data not found in details of Client report in controller");
		}
		Date cd=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    String s = df.format(cd);
	    mav.addObject("currentDate", s);
	    String date=""+month+"/"+year;
	    mav.addObject("date", date);
		mav.addObject("DCR", dcr);
		mav.setViewName("monthlyReportView");
		return mav;

	}
}
