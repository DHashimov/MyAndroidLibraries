package com.springmvc.testserver.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.testserver.dao.UserService;
import com.springmvc.testserver.domain.User;

@Controller
public class SpringServiceController {

	UserService userService = new UserService();

	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.GET)
	public @ResponseBody
	User getUser(@PathVariable("id") int id) {
		User user = userService.getUserById(id);
		return user;
	}

	@RequestMapping(
			value = "/welcome",
			method = RequestMethod.GET)
	public ModelAndView helloWorld() {

		System.out.println("Hello The Project is Working");
		String message = "Hello The Project is Working";
		return new ModelAndView("welcome", "message", message);
	}

	// @RequestMapping(
	// value = "/getallusers",
	// method = RequestMethod.GET,
	// headers = "Accept=application/json")
	// public List<User> getAllUsers() {
	// List<User> users = userService.getAllUsers();
	// return users;
	// }

	@RequestMapping(
			value = "/getallusers",
			method = RequestMethod.GET)
	public @ResponseBody
	List<User> getAllUsers() {
		List<User> emps = userService.getAllUsers();
		return emps;
	}

}
