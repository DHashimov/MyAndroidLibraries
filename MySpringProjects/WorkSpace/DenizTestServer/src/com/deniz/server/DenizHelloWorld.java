package com.deniz.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.deniz.server.controller.EmpRestURIConstants;
import com.deniz.server.json.Employee;

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

	@RequestMapping(
			value = "/submitJSON",
			method = RequestMethod.GET)
	public MappingJackson2JsonView returnJSON() {
		return null;

	}

	// Map to store employees, ideally we should use database
	Map<Integer, Employee> empData = new HashMap<Integer, Employee>();

	@RequestMapping(
			value = EmpRestURIConstants.DUMMY_EMP,
			method = RequestMethod.GET)
	public @ResponseBody
	Employee getDummyEmployee() {
		Employee emp = new Employee();
		emp.setId(9999);
		emp.setName("Dummy");
		emp.setCreatedDate(new Date());
		empData.put(9999, emp);
		return emp;
	}

	@RequestMapping(
			value = EmpRestURIConstants.GET_EMP,
			method = RequestMethod.GET)
	public @ResponseBody
	Employee getEmployee(@PathVariable("id") int empId) {

		return empData.get(empId);
	}

	@RequestMapping(
			value = EmpRestURIConstants.GET_ALL_EMP,
			method = RequestMethod.GET)
	public @ResponseBody
	List<Employee> getAllEmployees() {
		List<Employee> emps = new ArrayList<Employee>();
		Set<Integer> empIdKeys = empData.keySet();
		for (Integer i : empIdKeys) {
			emps.add(empData.get(i));
		}
		return emps;
	}

	@RequestMapping(
			value = EmpRestURIConstants.CREATE_EMP,
			method = RequestMethod.POST)
	public @ResponseBody
	Employee createEmployee(@Valid @ModelAttribute Employee emp,
							BindingResult result) {
		String name = emp.getName();

		emp.setCreatedDate(new Date());
		empData.put(emp.getId(), emp);
		return emp;
	}

	@RequestMapping(
			value = EmpRestURIConstants.DELETE_EMP,
			method = RequestMethod.PUT)
	public @ResponseBody
	Employee deleteEmployee(@PathVariable("id") int empId) {
		Employee emp = empData.get(empId);
		empData.remove(empId);
		return emp;
	}

}
