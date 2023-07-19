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
public class ResumeLogsUserReportByDayController {
	@Autowired
	private ResumeLogsUserReportService resumeLogsMonthlyUserReportService;

	@GetMapping("/report")
	
	public Object getReport(@RequestParam("userId") int userId, @RequestParam("date") String date,ModelAndView mav) {
		DetailsOfClientReport dcr=null;
		dcr = resumeLogsMonthlyUserReportService.getReport(userId, date);
		if(dcr==null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("data not found in details of Client report in controller");
		}
		
		mav.addObject("DCR", dcr);
		mav.addObject("date", date);
		Date cd=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    String s = df.format(cd);
		mav.addObject("currentDate", s);
		mav.setViewName("dayReportView");
		dcr=null;
		return mav;

	}
}
