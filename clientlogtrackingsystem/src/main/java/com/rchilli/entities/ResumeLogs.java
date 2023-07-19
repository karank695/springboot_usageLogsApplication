package com.rchilli.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//resume log entity consists of data related to parsing
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
	private String apiType;

}
