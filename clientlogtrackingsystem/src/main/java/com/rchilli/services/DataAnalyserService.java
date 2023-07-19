package com.rchilli.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.rchilli.entities.DetailsOfClientReport;
import com.rchilli.entities.EmailDetails;
import com.rchilli.entities.ResumeLogs;
import com.rchilli.serviceImp.EmailServiceImpl;

@Service
public class DataAnalyserService {

	private int totalUsage;
	private int passed;
	private int failed;
	private int pdf;
	private int doc;
	private int jd;
	private int resume;
	private int pageCountGt5;
	private int parsingTimeGt4;
	private String usage;
	@Autowired
	private EmailServiceImpl emailServiceImpl;
	
	@Value("${app.recipients}")
	private List<String> recipientList;
	
	Logger logger=LoggerFactory.getLogger(DataAnalyserService.class);
	
	public DetailsOfClientReport dataProcess(List<ResumeLogs> listOfResumeLogs) {
		totalUsage=listOfResumeLogs.size();
		boolean emailSent=false;
		for(int i=0;i<listOfResumeLogs.size();i++) {
			ResumeLogs e=listOfResumeLogs.get(i);
			if(e.getStatus().equalsIgnoreCase("ok")) {
				passed++;
			}else {
				failed++;
			}
			if(e.getFileExtension().equalsIgnoreCase("pdf")) {
				pdf++;
			}
			if(e.getFileExtension().equalsIgnoreCase("doc")) {
				doc++;
			}
			if(e.getApiType().equalsIgnoreCase("resume")) {
				resume++;
			}
			else {
				jd++;
			}
			if(e.getPageCount()>5) {
				pageCountGt5++;
				if(!emailSent) {
					logger.info("email sending is in process for pageCount greater than 5");
					EmailDetails ed=new EmailDetails();
					ed.setRecipients(recipientList);
					System.out.println(ed.getRecipients().size());
					Context mailContext=new Context();
					mailContext.setVariable("highPageCount", true);
					mailContext.setVariable("userId", e.getUserId());
					mailContext.setVariable("id", e.getId());
					emailServiceImpl.sendSimpleMail(ed, mailContext, "warnEmailTemplate");
					emailSent=true;
				}
				
			}
			if(e.getParsingTime()>4000) {
				parsingTimeGt4++;
				if(!emailSent) {
					logger.info("email sending is in process for processing time greater than 4000s");
					EmailDetails ed=new EmailDetails();
					ed.setRecipients(recipientList);
					System.out.println(ed.getRecipients().size());
					Context mailContext=new Context();
					mailContext.setVariable("highParsingTime", true);
					mailContext.setVariable("userId", e.getUserId());
					mailContext.setVariable("id", e.getId());
					emailServiceImpl.sendSimpleMail(ed, mailContext, "warnEmailTemplate");
					emailSent=true;
				}
			}
		}
		DetailsOfClientReport dcr=new DetailsOfClientReport();
		dcr.setDoc(doc);
		dcr.setFailed(failed);
		dcr.setJd(jd);
		dcr.setParsingTimeGt4(parsingTimeGt4);
		dcr.setPageCountGt5(pageCountGt5);
		dcr.setPassed(passed);
		dcr.setPdf(pdf);
		dcr.setResume(resume);
		dcr.setTotalUsage(totalUsage);
		dcr.setUsage(usage);
		totalUsage=0;
		passed=0;
		failed=0;
		pdf=0;
		doc=0;
		jd=0;
		resume=0;
	    pageCountGt5=0;
		parsingTimeGt4=0;
	    usage=null;
		
	    return dcr;
	}
	public DetailsOfClientReport dataProcess(List<ResumeLogs> listOfResumeLogs,int preUsage) {
		totalUsage=listOfResumeLogs.size();
		boolean emailSent=false;
		for(int i=0;i<listOfResumeLogs.size();i++) {
			ResumeLogs e=listOfResumeLogs.get(i);
			if(e.getStatus().equalsIgnoreCase("ok")) {
				passed++;
			}else {
				failed++;
			}
			if(e.getFileExtension().equalsIgnoreCase("pdf")) {
				pdf++;
			}
			if(e.getFileExtension().equalsIgnoreCase("doc")) {
				doc++;
			}
			if(e.getApiType().equalsIgnoreCase("resume")) {
				resume++;
			}
			else {
				jd++;
			}
			if(e.getPageCount()>5) {
				pageCountGt5++;
				if(!emailSent) {
					logger.info("email sending is in process for pageCount greater than 5");
					EmailDetails ed=new EmailDetails();
					ed.setRecipients(recipientList);
					System.out.println(ed.getRecipients().size());
					Context mailContext=new Context();
					mailContext.setVariable("highPageCount", true);
					mailContext.setVariable("userId", e.getUserId());
					mailContext.setVariable("id", e.getId());
					emailServiceImpl.sendSimpleMail(ed, mailContext, "warnEmailTemplate");
					emailSent=true;
				}
			}
			if(e.getParsingTime()>4000) {
				parsingTimeGt4++;
				if(!emailSent) {
					logger.info("email sending is in process for processing time greater than 4000s");
					EmailDetails ed=new EmailDetails();
					ed.setRecipients(recipientList);
					System.out.println(ed.getRecipients().size());
					Context mailContext=new Context();
					mailContext.setVariable("highParsingTime", true);
					mailContext.setVariable("userId", e.getUserId());
					mailContext.setVariable("id", e.getId());
					emailServiceImpl.sendSimpleMail(ed, mailContext, "warnEmailTemplate");
					emailSent=true;
				}
			}
			
			
		}
		int perInc=0,perDec=0;
        
		if(totalUsage>=preUsage) {
			perInc=(totalUsage-preUsage)*100/preUsage;
		}else {
			perDec=(preUsage-totalUsage)*100/preUsage;
		}
		DetailsOfClientReport dcr=new DetailsOfClientReport();
		dcr.setDoc(doc);
		dcr.setFailed(failed);
		dcr.setJd(jd);
		dcr.setParsingTimeGt4(parsingTimeGt4);
		dcr.setPageCountGt5(pageCountGt5);
		dcr.setPassed(passed);
		dcr.setPdf(pdf);
		dcr.setResume(resume);
		dcr.setTotalUsage(totalUsage);
		totalUsage=0;
		passed=0;
		failed=0;
		pdf=0;
		doc=0;
		jd=0;
		resume=0;
	    pageCountGt5=0;
		parsingTimeGt4=0;
	    usage=null;
		if(perInc>0) {
			usage="+ "+perInc+"";
		}else if(perDec>0) {
			usage="- "+ perDec+"";
		}
		dcr.setUsage(usage);
		perInc=0;
		perDec=0;
		
		return dcr;
	}
}
