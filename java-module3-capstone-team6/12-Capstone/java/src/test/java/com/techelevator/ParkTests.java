package com.techelevator;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.model.JdbcParkDao;
import com.techelevator.model.Park;
import com.techelevator.model.ParkDao;

import cucumber.api.java.After;

public class ParkTests {

	private static SingleConnectionDataSource dataSource;
	private ParkDao dao;
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
		dao = new JdbcParkDao(dataSource);
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
	public void testIfParkNameExistsInParkTable() {
		List<Park> parksTest = dao.getAllParksInformation();
		int indexLocation = parksTest.size()-1;
		String actualName = parksTest.get(indexLocation).getParkName();
		String expectedName = "The National Park";
		assertEquals(expectedName, actualName);
	}
}
