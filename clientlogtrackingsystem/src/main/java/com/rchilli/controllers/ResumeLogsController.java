package com.rchilli.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rchilli.entities.ResumeLogs;
import com.rchilli.repository.ResumeLogsRepository;

@RestController
public class ResumeLogsController {

	@Autowired
	private ResumeLogsRepository resumeLogsRepository;

	@GetMapping("/getResumeLogs")
	public List<ResumeLogs> getResumeLogs() {
		return resumeLogsRepository.findByUserId(186);
	}
}
