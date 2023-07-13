package com.rchilli.entities;

// This class contains data related to monthly report for different client 
public class MonthlyDetailsOfClientReport {

	// Total number of parsed resume in a given month
	private int numberOfParsedResume;
	// Total number of passed resume in ATS
	private int numberOfResumePassedATS;

	public MonthlyDetailsOfClientReport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MonthlyDetailsOfClientReport(int numberOfParsedResume, int numberOfResumePassedATS) {
		super();
		this.numberOfParsedResume = numberOfParsedResume;
		this.numberOfResumePassedATS = numberOfResumePassedATS;
	}

	public int getNumberOfParsedResume() {
		return numberOfParsedResume;
	}

	public void setNumberOfParsedResume(int numberOfParsedResume) {
		this.numberOfParsedResume = numberOfParsedResume;
	}

	public int getNumberOfResumePassedATS() {
		return numberOfResumePassedATS;
	}

	public void setNumberOfResumePassedATS(int numberOfResumePassedATS) {
		this.numberOfResumePassedATS = numberOfResumePassedATS;
	}

	@Override
	public String toString() {
		return "MonthlyDetailsOfClientReport [numberOfParsedResume=" + numberOfParsedResume
				+ ", numberOfResumePassedATS=" + numberOfResumePassedATS + "]";
	}

}
