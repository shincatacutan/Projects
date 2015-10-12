package com.uhg.optum.ssmo.esb.twscalendar.domain;

public class JobCode {

	private String jobDescription;
	private String jobCode;
	
	public JobCode(){
		
	}
	public JobCode(String jobDescription, String jobCode) {
		super();
		this.jobDescription = jobDescription;
		this.jobCode = jobCode;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
}
