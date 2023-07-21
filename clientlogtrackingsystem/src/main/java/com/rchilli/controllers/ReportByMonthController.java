package com.rchilli.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rchilli.entities.DetailsOfClientReport;
import com.rchilli.services.LogsUserReportService;

@Controller
public class ReportByMonthController {
	@Autowired
	private LogsUserReportService resumeLogsMonthlyUserReportService;

	@GetMapping("/monthly/report")
	public Object getReport(@RequestParam("userId") int[] userIds, @RequestParam("year") String year,@RequestParam("month") String month,ModelAndView mav) {
		if(userIds.length==1) {
			int userId=userIds[0];
			DetailsOfClientReport dcr=null;
			dcr = resumeLogsMonthlyUserReportService.getReport(userId, year, month);
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
		else {
			List<Integer> userIdList=new ArrayList<>();
			for(int i=0;i<userIds.length;i++) {
				userIdList.add(userIds[i]);
			}
			List<DetailsOfClientReport> DCRList=null;
			DCRList = resumeLogsMonthlyUserReportService.getReport(userIdList, year, month);
			if(DCRList==null) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("data not found in the List of Client report in controller");
			}
			Date cd=new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		    String s = df.format(cd);
		    mav.addObject("currentDate", s);
		    String date=""+month+"/"+year;
		    mav.addObject("date", date);
			mav.addObject("DCRList", DCRList);
			mav.setViewName("mulUserIdView");
			return mav;
		}
			
	}
}
