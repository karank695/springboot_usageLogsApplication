package com.rchilli.services;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.rchilli.entities.EmailDetails;
import com.rchilli.entities.MonthlyDetailsOfClientReport;
import com.rchilli.entities.ResumeLogs;
import com.rchilli.repository.ResumeLogsRepository;
import com.rchilli.serviceImp.EmailServiceImpl;

@Service
public class ResumeLogsMonthlyUserReportService {

	private static final Logger logger = LoggerFactory.getLogger(ResumeLogsMonthlyUserReportService.class);
	@Value("${app.recipients}")
	private List<String> recipients;

	@Autowired
	private ResumeLogsRepository resumeLogsRepository;

	@Autowired
	private EmailServiceImpl emailServiceImpl;

	@Autowired
	private SpringTemplateEngine springTemplateEngine;

	@Autowired
	private DataMapperService dataMapper;

	@Autowired
	private DocumentGeneratorService documentGeneratorService;

	public List<ResumeLogs> getReport(int userId, String monthData, String yearData) {
		List<ResumeLogs> results = resumeLogsRepository.findByUserId(userId);
		int totalCount = 0;
		int passed = 0;

		for (int i = 0; i < results.size(); i++) {
			String date = results.get(i).getParseDate().toString();
			String month = date.substring(5, 7);
			String year = date.substring(0, 4);
			int pageCount = results.get(i).getPageCount();
			int parsingTime = results.get(i).getParsingTime();
			if (results.get(i).getStatus().equalsIgnoreCase("ok") && month.equalsIgnoreCase(monthData)
					&& year.equalsIgnoreCase(yearData)) {
				passed++;
			}

			if (month.equalsIgnoreCase(monthData) && year.equalsIgnoreCase(yearData)) {
				totalCount++;
			}
			//checking condition for trigger mail when page count will be greater than 5
			if (pageCount > 5) {
				Boolean highPageCount = true;
				Context ct = new Context();
				ct.setVariable("id", results.get(i).getId());
				ct.setVariable("userId", results.get(i).getUserId());
				ct.setVariable("highPageCount", highPageCount);
				EmailDetails ed = new EmailDetails();
				ed.setRecipients(recipients);
				emailServiceImpl.sendSimpleMail(ed, ct, "warnEmailTemplate");
				logger.info("Email sent for page count greater than 5");
				break;
			}

			//checking condition for trigger main when parsing time will be greater than 3s
			if ((parsingTime / 1000) > 3) {
				Boolean highParsingTime = true;
				Context ct = new Context();
				ct.setVariable("id", results.get(i).getId());
				ct.setVariable("userId", results.get(i).getUserId());
				ct.setVariable("highParsingTime", highParsingTime);
				EmailDetails ed = new EmailDetails();
				ed.setRecipients(recipients);
				emailServiceImpl.sendSimpleMail(ed, ct, "warnEmailTemplate");
				logger.info("Email sent for parsing time greater than 3s");
				break;
			}
		}

		// Create object for class MonthlyDetailsOfClientReport
		MonthlyDetailsOfClientReport monthlyDetailsOfClientReport = new MonthlyDetailsOfClientReport();
		monthlyDetailsOfClientReport.setNumberOfParsedResume(totalCount);
		monthlyDetailsOfClientReport.setNumberOfResumePassedATS(passed);
		logger.info("Total count: {}, Passed: {}", totalCount, passed);

		Context context = new Context();
		context.setVariable("MDetailsOfClientReport", monthlyDetailsOfClientReport);

		if (passed < totalCount / 2) {
			EmailDetails ed = new EmailDetails();
			List<String> emails = new LinkedList<>();
			emails.add("krn0862@gmail.com");
			emails.add("krn0861@gmail.com");
			ed.setRecipients(emails);

			emailServiceImpl.sendSimpleMail(ed, context, "emailTemplate");
		}

		String finalHtml = null;
		Context dataContext = dataMapper.setData(results);
		finalHtml = springTemplateEngine.process("report", dataContext);

		Date date = new Date();
		String nameOfFile = "report" + date.getTime();
		documentGeneratorService.htmlToPdf(finalHtml, nameOfFile);

		return results;
	}
}
