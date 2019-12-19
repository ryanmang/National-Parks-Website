package com.techelevator.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.model.Park;
import com.techelevator.model.ParkDao;
import com.techelevator.model.Survey;
import com.techelevator.model.Weather;
import com.techelevator.model.WeatherDao;

@Controller
public class ParkController {

	@Autowired
	private ParkDao parkDao;

	@Autowired
	private WeatherDao weatherDao;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String displayParksHomePage(ModelMap model) {
		List<Park> parks = parkDao.getAllParksInformation();
		model.put("parkMap", parks);
		return "Parks";
	}

	@RequestMapping(path = "/ParkDetail", method = RequestMethod.GET)
	public String goToParkDetailsPage(@RequestParam String parkcode, ModelMap map) {
		map.put("park", getParkByCode(parkcode));
		map.put("weathers", weatherDao.getWeatherInfoByParkCode(parkcode));
		return "ParkDetail";
	}

	@RequestMapping(path = "/ParkDetail", method = RequestMethod.POST)
	public String changeTemperatureFC(HttpSession session, @RequestParam String tempFC, @RequestParam String parkCode) {
		if (tempFC.equals("Celsius")) {
			Boolean celcius = true;
			session.setAttribute("celcius", celcius);
		} else {
			Boolean celcius = false;
			session.setAttribute("celcius", celcius);
		}
		return "redirect:/ParkDetail?parkcode=" + parkCode;
	}

	private Park getParkByCode(String parkcode) {
		for (Park p : parkDao.getAllParksInformation()) {
			if (p.getParkCode().contentEquals(parkcode)) {
				return p;
			}
		}
		return null;
	}
}