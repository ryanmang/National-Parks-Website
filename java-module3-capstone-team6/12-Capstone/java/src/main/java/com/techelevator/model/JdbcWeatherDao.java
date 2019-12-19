package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcWeatherDao implements WeatherDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcWeatherDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Weather> getWeatherInfoByParkCode(String parkCode) {
		List<Weather> weatherInfo = new ArrayList<Weather>();
		String sqlSelectAllParkInfo = "SELECT * FROM weather WHERE parkcode = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllParkInfo, parkCode);
		while (results.next()) {
			Weather weather = new Weather();
			weather.setParkCode(results.getString("parkcode"));
			weather.setFiveDay(results.getInt("fivedayforecastvalue"));
			weather.setLow(results.getInt("low"));
			weather.setHigh(results.getInt("high"));
			weather.setForecast(results.getString("forecast"));
			weatherInfo.add(weather);
		}
		return weatherInfo;
	}

}
