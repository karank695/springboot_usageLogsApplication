package com.rchilli;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.rchilli.entities.ResumeLogs;
import com.rchilli.repository.ResumeLogsRepository;

@SpringBootApplication
public class ClientlogtrackingsystemApplication {

	private static final Logger logger = LoggerFactory.getLogger(ClientlogtrackingsystemApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ClientlogtrackingsystemApplication.class, args);
		ResumeLogsRepository resumeLogsRepository = context.getBean(ResumeLogsRepository.class);
		// Fetch resume logs by user ID
//		int userId = 186;
//		List<ResumeLogs> resumeLogsList = resumeLogsRepository.findByMonthAndYear(186, "2022", "04");

		// Logging the retrieved resume logs
//		logger.info("Resume logs for user ID {}: {}", userId);

//		 Print the retrieved resume logs
//		resumeLogsList.forEach(e -> System.out.println(e));
	}
}
