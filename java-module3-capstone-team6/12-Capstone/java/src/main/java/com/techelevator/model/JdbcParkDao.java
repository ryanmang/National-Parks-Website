package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcParkDao implements ParkDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcParkDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Park> getAllParksInformation() {
		List<Park> allParks = new ArrayList<Park>();
		String sqlSelectAllParkInfo = "SELECT * FROM park";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllParkInfo);
		while (results.next()) {
			Park park = new Park();
			park.setParkCode(results.getString("parkcode"));
			park.setParkName(results.getString("parkname"));
			park.setState(results.getString("state"));
			park.setAcreage(results.getInt("acreage"));
			park.setElevation(results.getInt("elevationinfeet"));
			park.setMilesTrail(results.getDouble("milesoftrail"));
			park.setNumberSites(results.getInt("numberofcampsites"));
			park.setClimate(results.getString("climate"));
			park.setYearFounded(results.getInt("yearfounded"));
			park.setAnnualVisitorCount(results.getInt("annualvisitorcount"));
			park.setQuote(results.getString("inspirationalquote"));
			park.setQuoteSource(results.getString("inspirationalquotesource"));
			park.setDescription(results.getString("parkdescription"));
			park.setEntryFee(results.getInt("entryfee"));
			park.setNumberSpecies(results.getInt("numberofanimalspecies"));
			allParks.add(park);
		}
		return allParks;
	}

}
