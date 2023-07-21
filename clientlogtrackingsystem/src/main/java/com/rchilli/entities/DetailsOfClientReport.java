package com.rchilli.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DetailsOfClientReport {
	private int userId;
	private int totalUsage;
	private int passed;
	private int failed;
	private int pdf;
	private int doc;
	private int jd;
	private int Resume;
	private int pageCountGt5;
	private int parsingTimeGt4;
	private String usage;

}
