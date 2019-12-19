package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcSurveyDao implements SurveyDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcSurveyDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Park> getCountOfParksAndParkInfo() {
		List<Park> allParks = new ArrayList<>();

		String sqlGetAllSurveys = "SELECT COUNT (A.parkcode) AS park_count, B.parkcode, B.parkname, B.annualvisitorcount,"
				+ " B.entryfee FROM survey_result A INNER JOIN park B ON A.parkcode=B.parkcode GROUP BY A.parkcode,"
				+ " B.parkname, B.annualvisitorcount, B.entryfee, B.parkcode ORDER BY park_count DESC, B.parkname ASC LIMIT 5";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllSurveys);
		while (results.next()) {
			Park park = new Park();
			park.setParkCount(results.getInt("park_count"));
			park.setParkCode(results.getString("parkcode"));
			park.setParkName(results.getString("parkname"));
			park.setAnnualVisitorCount(results.getInt("annualvisitorcount"));
			park.setEntryFee(results.getInt("entryfee"));
			allParks.add(park);
		}
		return allParks;

	}

	@Override
	public void saveSubmittedSurveyInfo(Survey survey) {

		String sqlToSaveSurvey = "INSERT INTO survey_result (parkcode, emailaddress, state, activitylevel)"
				+ " VALUES(?,?,?,?)";
		jdbcTemplate.update(sqlToSaveSurvey, survey.getParkCode(), survey.getEmail(), survey.getState(),
				survey.getActivityLevel());

	}

}
