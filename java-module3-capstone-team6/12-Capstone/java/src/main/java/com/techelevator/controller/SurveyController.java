package com.techelevator.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.techelevator.model.SurveyDao;

@Controller
public class SurveyController {

	@Autowired
	private SurveyDao surveyDao; 
	@Autowired
	private ParkDao parkDao;

	@RequestMapping(path = "/Survey", method = RequestMethod.GET)
	public String displaySurvey(Model modelHolder, ModelMap model) {
		if (!modelHolder.containsAttribute("survey")) {
			modelHolder.addAttribute("survey", new Survey());
		}
		return "Survey";
	}

	@RequestMapping(path = "/Survey", method = RequestMethod.POST)
	public String submitSurveyForm(@Valid @ModelAttribute Survey survey, BindingResult result,
			RedirectAttributes flash) {
		if (result.hasErrors()) {
			flash.addFlashAttribute("survey", survey);
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "survey", result);
			return "redirect:/Survey";
		}
		surveyDao.saveSubmittedSurveyInfo(survey);
		return "redirect:/Favorites";
	}

	@RequestMapping(path = "/Favorites", method = RequestMethod.GET)
	public String showFavorites(ModelMap model) {
		/* model.put("park", getParkByCode(parkcode)); */
		model.put("parks", surveyDao.getCountOfParksAndParkInfo());
		return "Favorites";
	}
	
//	private Park getParkByCode(String parkCode) {
//		for (Park p : parkDao.getAllParksInformation()) {
//			if (p.getParkCode().contentEquals(parkCode)) {
//				return p;
//			}
//		}
//		return null;
//	}
	
}
