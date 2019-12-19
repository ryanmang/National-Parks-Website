package com.techelevator.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class Survey {


	private Long surveyId;
	
	
	@NotBlank(message="Please select the park you want to review")
	private String parkCode;
	
	@Email(message="You must enter a valid email address")
	@NotBlank(message="Email is required")
	private String email;
	
	
	@NotBlank(message="Please select your state")
	private String state;
	
	@NotNull(message="Please select an activity level")
	private String activityLevel;
	
	public Long getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getActivityLevel() {
		return activityLevel;
	}
	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}
	

}
 
