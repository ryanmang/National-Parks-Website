package com.techelevator.model;

import java.util.List;

public interface SurveyDao {

	public List<Park> getCountOfParksAndParkInfo();
	public void saveSubmittedSurveyInfo(Survey survey);
		
	}

