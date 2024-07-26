package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PageController {

	@RequestMapping("/home")
	public String home(Model model) {
		System.out.println("Home page handler");
		model.addAttribute("name","Substring techs");
		model.addAttribute("age", "twenty-three");
		return "home";
	}

	//about section
	@RequestMapping("/about")
	public String aboutPage(Model model) {
		System.out.println("About page loading");
		model.addAttribute("isLogin", true);
		return "about";
	}

	//services
	@RequestMapping("/services")
	public String servicesPage() {
		System.out.println("Services page loading");
		return "services";
	}
	
}
