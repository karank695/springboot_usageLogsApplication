package com.rchilli.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.rchilli.entities.DetailsOfClientReport;
import com.rchilli.entities.EmailDetails;
import com.rchilli.entities.ResumeLogs;
import com.rchilli.repository.ResumeLogsRepository;
import com.rchilli.serviceImp.EmailServiceImpl;

@Service
public class ResumeLogsUserReportService {

	private static final Logger logger = LoggerFactory.getLogger(ResumeLogsUserReportService.class);
	@Value("${app.recipients}")
	private List<String> recipients;

	@Autowired
	private ResumeLogsRepository resumeLogsRepository;

	@Autowired
	private SpringTemplateEngine springTemplateEngine;

	@Autowired
	private DocumentGeneratorService documentGeneratorService;
	
	@Autowired
	private DataAnalyserService dataAnalyserService;

	@Autowired
	private EmailServiceImpl emailServiceImpl;
	
	@Value("${app.recipients}")
	private List<String> recipientsList;
	public DetailsOfClientReport getReport(int userId, String date) {
		List<ResumeLogs> lr=null;
		DetailsOfClientReport dr=null;
	    lr=resumeLogsRepository.findByDateAndUserId(userId, date);
		if(!lr.isEmpty()) {
			logger.info("executin of getReport when list is not empty");
			dr=dataAnalyserService.dataProcess(lr);
			Context context= new Context();
			context.setVariable("DCR", dr);
			context.setVariable("date", date);
			Date cd=new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		    String s = df.format(cd);
		    context.setVariable("currentDate", s);
		    String fileName="dayReport"+cd.getTime();
			String processedHtml=springTemplateEngine.process("dayReportTemplate", context);
			documentGeneratorService.htmlToPdf(processedHtml, fileName);
			EmailDetails ed=new EmailDetails();
			ed.setRecipients(recipients);
			Context mailContext=new Context();
			logger.info("mail sending with attachment is in process");
			emailServiceImpl.sendMailWithAttachment(ed, mailContext, "emailTemplate", fileName);
			
		}
		
		return dr;
	}
	public DetailsOfClientReport getReportByMonthAndYear(int userId, String year,String month) {
		List<ResumeLogs> lr=null;
		DetailsOfClientReport mdr=null;
	    lr=resumeLogsRepository.findByMonthAndYear(userId, year, month);
	    int monthData=Integer.parseInt(month);
	    monthData=monthData-1;
	    String prevMonth=null;
	    if(monthData<10)
	    prevMonth="0"+month;
	    else
	    prevMonth=String.valueOf(monthData);
	    List<ResumeLogs> lr1=resumeLogsRepository.findByMonthAndYear(userId, year, prevMonth);
	    int preUsage=lr1.size();
	    System.out.println(prevMonth);
		if(!lr.isEmpty()) {
			mdr=dataAnalyserService.dataProcess(lr,preUsage);
			Context context= new Context();
			context.setVariable("DCR", mdr);
			Date cd=new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		    String s = df.format(cd);
		    context.setVariable("currentDate", s);
		    String date=""+month+"/"+year;
		    context.setVariable("date", date);
		    String fileName="monthReport"+cd.getTime();
			String processedHtml=springTemplateEngine.process("monthlyReportTemplate", context);
			documentGeneratorService.htmlToPdf(processedHtml, fileName);
			EmailDetails ed=new EmailDetails();
			ed.setRecipients(recipients);
			Context mailContext=new Context();
			logger.info("mail sending with attachment is in process");
			emailServiceImpl.sendMailWithAttachment(ed, mailContext, "emailTemplate", fileName);
			
		}
		
		return mdr;
	}
}
