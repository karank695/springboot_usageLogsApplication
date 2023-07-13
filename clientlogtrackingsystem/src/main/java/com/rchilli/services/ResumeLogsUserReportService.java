package com.rchilli.services;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rchilli.entities.ResumeLogs;
import com.rchilli.repository.ResumeLogsRepository;

@Service
public class ResumeLogsUserReportService {
	@Autowired
	private ResumeLogsRepository resumeLogsRepository;

	public List<ResumeLogs> getReport(List<Integer> userIds, String parseDate) {
		List<ResumeLogs> results = resumeLogsRepository.findByUserIds(userIds);
		List<ResumeLogs> userResumeLogs = new ArrayList<>();
		for (int i = 0; i < results.size(); i++) {
			String d = results.get(i).getParseDate().toString();
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			String formattedDate = d.formatted(myFormatObj);
			if (formattedDate.substring(0, 10).equalsIgnoreCase(parseDate.substring(0, 10))) {
				ResumeLogs resumeLogs = results.get(i);
				userResumeLogs.add(resumeLogs);
			}
		}
		return userResumeLogs;

	}
}
