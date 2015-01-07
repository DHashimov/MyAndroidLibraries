package com.deniz.server;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DenizHelloWorld {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// disallows binding of specified fields
		// binder.setDisallowedFields(new String[] { "studentMobile" });

		// Register specific format for date binder
		// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy*****MM*****dd");
		// binder.registerCustomEditor(Date.class, "studentDOB", new CustomDateEditor(dateFormat, false));

		// using a custom editor
		binder.registerCustomEditor(String.class, "studentName", new StudentNameEditor());

	}

	@RequestMapping("/welcome/{countryName}/{userName}")
	public ModelAndView helloWorld(@PathVariable Map<String, String> pathVars) {

		String name = pathVars.get("userName");
		String country = pathVars.get("countryName");

		String message = "Hello " + name + " From " + country;
		return new ModelAndView("welcome", "message", message);
	}

	@RequestMapping(
			value = "/admissionForm.html",
			method = RequestMethod.GET)
	public ModelAndView getAdmissionForm() {
		ModelAndView modelAndView = new ModelAndView("AdmissionForm");
		return modelAndView;
	}

	// Add that header to both JSP files so it's not needed to be written twice
	@ModelAttribute
	public void addingCommonObject(Model modelAndView) {
		modelAndView.addAttribute("headerMessage", "Deniz First MVC Spring Project");
	}

	@RequestMapping(
			value = "/submitAdmissionForm.html",
			method = RequestMethod.POST)
	public ModelAndView submitAdmissionForm(@Valid @ModelAttribute("student1") Student student1,
											BindingResult result) {

		if (result.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("AdmissionForm");
			return modelAndView;
		}

		ModelAndView modelAndView = new ModelAndView("AdmissionSuccess");
		return modelAndView;
	}

}
