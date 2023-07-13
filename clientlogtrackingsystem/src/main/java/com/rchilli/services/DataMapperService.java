package com.rchilli.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.rchilli.entities.ResumeLogs;

@Service
public class DataMapperService {

	private static final Logger logger = LoggerFactory.getLogger(DataMapperService.class);

	public Context setData(List<ResumeLogs> listOfResumeLogs) {
		Context context = new Context();

		// Prepare the data to be passed to the Thymeleaf template
		Map<String, Object> data = new HashMap<>();
		data.put("listOfResumeLogs", listOfResumeLogs);
		context.setVariables(data);

		// Logging the number of resume logs received
		logger.info("Setting data for Thymeleaf template. Number of resume logs: {}", listOfResumeLogs.size());

		return context;
	}
}
