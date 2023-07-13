package com.rchilli.entities;

import java.util.Date;

//Entity for detailsOfClientReport for single resume parsing
public class DetailsOfClientReport {
	private String subUserId;
	private Date parseDate;
	private String status;
	private String fileExtension;
	private int pageCount;
	private int parsingTime;

	public DetailsOfClientReport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DetailsOfClientReport(String subUserId, Date parseDate, String status, String fileExtension, int pageCount,
			int parsingTime) {
		super();
		this.subUserId = subUserId;
		this.parseDate = parseDate;
		this.status = status;
		this.fileExtension = fileExtension;
		this.pageCount = pageCount;
		this.parsingTime = parsingTime;
	}

	public String getSubUserId() {
		return subUserId;
	}

	public void setSubUserId(String subUserId) {
		this.subUserId = subUserId;
	}

	public Date getParseDate() {
		return parseDate;
	}

	public void setParseDate(Date parseDate) {
		this.parseDate = parseDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getParsingTime() {
		return parsingTime;
	}

	public void setParsingTime(int parsingTime) {
		this.parsingTime = parsingTime;
	}

	@Override
	public String toString() {
		return "DetailsOfClientReport [subUserId=" + subUserId + ", parseDate=" + parseDate + ", status=" + status
				+ ", fileExtension=" + fileExtension + ", pageCount=" + pageCount + ", parsingTime=" + parsingTime
				+ "]";
	}

}
