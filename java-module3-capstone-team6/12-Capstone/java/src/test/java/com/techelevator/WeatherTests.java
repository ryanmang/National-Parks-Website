package com.techelevator;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import com.techelevator.model.JdbcWeatherDao;
import com.techelevator.model.Weather;
import com.techelevator.model.WeatherDao;

public class WeatherTests {

	private static SingleConnectionDataSource dataSource;
	private WeatherDao dao;
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
		dao = new JdbcWeatherDao(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sqlInsertTestPark = "INSERT INTO park (parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies) VALUES ('TNP', 'The National Park', 'Michigan', 40, 100000, 123.0, 5, 'Forest', 2019, 34, 'What in tarnation? Dagnabbit!', 'Bill Bob', 'This is a national park in Michigan', 40, 321)";
		jdbcTemplate.update(sqlInsertTestPark);
		String sqlInsertTestWeather = "INSERT INTO weather (parkcode, fivedayforecastvalue, low, high, forecast) VALUES ('TNP', 1, 15, 25, 'partly cloudy')";
		jdbcTemplate.update(sqlInsertTestWeather);
	}

	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	protected DataSource getDataSource() {
		return dataSource;
	}
	
	@Test
	public void testIfWeatherForecastIsSelectedByParkCodeGetLowHighTemp() {
		String parkCode = "TNP";
		List<Weather> testParkCount = dao.getWeatherInfoByParkCode(parkCode);
		int indexLocation = testParkCount.size()-1;
		double actualLow = testParkCount.get(indexLocation).getLow();
		int expectedLow = 15;
		assertEquals(expectedLow, actualLow, 0);
		double actualHigh = testParkCount.get(indexLocation).getHigh();
		int expectedHigh = 25;
		assertEquals(expectedHigh, actualHigh, 0);
	}
	

}
