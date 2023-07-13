package com.rchilli.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

//resume log entity consists of data related to parsing
@Entity
public class ResumeLogs {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int userId;
	private String fileName;
	private String subUserId;
	@Temporal(TemporalType.TIMESTAMP)
	private Date parseDate;
	private String status;
	private String fileExtension;
	private int pageCount;
	private int parsingTime;

	// default constructor
	public ResumeLogs() {
		super();
		// TODO Auto-generated constructor stub
	}

	// getters and setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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
		return "ResumeLogs [id=" + id + ", userId=" + userId + ", fileName=" + fileName + ", subUserId=" + subUserId
				+ ", parseDate=" + parseDate + ", status=" + status + ", fileExtension=" + fileExtension
				+ ", pageCount=" + pageCount + ", parsingTime=" + parsingTime + "]";
	}

}
