package com.techelevator;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.JdbcParkDao;
import com.techelevator.model.JdbcSurveyDao;
import com.techelevator.model.Park;
import com.techelevator.model.ParkDao;
import com.techelevator.model.Survey;
import com.techelevator.model.SurveyDao;

public class SurveyTests {

	private static SingleConnectionDataSource dataSource;
	private SurveyDao dao;
	private JdbcTemplate jdbcTemplate;
	
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/npgeek");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
	}

	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}
	
	@Before
	public void setUp() {
		dao = new JdbcSurveyDao(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sqlInsertTestPark = "INSERT INTO park (parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies) VALUES ('TNP', 'The National Park', 'Michigan', 40, 100000, 123.0, 5, 'Forest', 2019, 34, 'What in tarnation? Dagnabbit!', 'Bill Bob', 'This is a national park in Michigan', 40, 321)";
		jdbcTemplate.update(sqlInsertTestPark);
		
	}

	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	protected DataSource getDataSource() {
		return dataSource;
	}
	
	@Test
	public void testIfSqlCodePullsInfoFromDatabase() {
		List<Park> parkCount = dao.getCountOfParksAndParkInfo();

		String actualName = parkCount.get(0).getParkCode();
		String expectedName = "ENP";
		// Test runs using our database
		assertEquals(expectedName, actualName);
	}
	
	@Test
	public void testIfSurveyFormSubmitsDataToDataBase() {
		Survey survey = new Survey();
		survey.setParkCode("TNP");
		survey.setEmail("bill@bob.com");
		survey.setState("Alabama");
		survey.setActivityLevel("Inactive");
		
		dao.saveSubmittedSurveyInfo(survey);
		
		List<Survey> allSurveys = new ArrayList<Survey>();
		String sqlSearchForSubmittedSurvey = "SELECT * FROM survey_result";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForSubmittedSurvey);
		while (results.next()) {
			Survey surveys = new Survey();
			surveys.setParkCode(results.getString("parkcode"));
			surveys.setEmail(results.getString("emailaddress"));
			surveys.setState(results.getString("state"));
			surveys.setActivityLevel(results.getString("activitylevel"));
			allSurveys.add(surveys);
		}
		int indexLocation = allSurveys.size()-1;
		String actualName = allSurveys.get(indexLocation).getEmail();
		String expectedName = "bill@bob.com";
		assertEquals(expectedName, actualName);
		
	}
}
