package com.techelevator.model;

public class Weather {

	private String parkCode;
	private int fiveDay;
	private double low;
	private double high;
	private String forecast;
	private String tempFC;

	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}

	public int getFiveDay() {
		return fiveDay;
	}

	public void setFiveDay(int fiveDay) {
		this.fiveDay = fiveDay;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast;
	}

	public String getTempFC() {
		return tempFC;
	}

	public void setTempFC(String tempFC) {
		this.tempFC = tempFC;
	}
	
}