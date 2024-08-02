package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {

	@Autowired
	private UserService userService;

	@RequestMapping("/home")
	public String home(Model model) {
		System.out.println("Home page handler");
		model.addAttribute("name", "Substring techs");
		model.addAttribute("age", "twenty-three");
		return "home";
	}

	// about section
	@RequestMapping("/about")
	public String aboutPage(Model model) {
		System.out.println("About page loading");
		model.addAttribute("isLogin", true);
		return "about";
	}

	// services
	@RequestMapping("/services")
	public String servicesPage() {
		System.out.println("Services page loading");
		return "services";
	}

	@GetMapping("/contact")
	public String contact() {
		return new String("contact");
	}

	@GetMapping("/login")
	public String login() {
		return new String("login");
	}

	@GetMapping("/register")
	public String register(Model model) {
		UserForm userForm = new UserForm();
		model.addAttribute("userForm", userForm);
		return "register";
	}

	// processing register
	@RequestMapping(value = "/do-register", method = RequestMethod.POST)
	public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult,
			HttpSession session) {
		System.out.println("Process Registration");
		// fetch the form data
		// Userform class
		// validate form data
		if (rBindingResult.hasErrors())
			return "register";
		// save to database
		// userservice
		/// UserForm ---> User
		// User user = User.builder()
		// .name(userForm.getName())
		// .email(userForm.getEmail())
		// .password(userForm.getPassword())
		// .about(userForm.getAbout())
		// .phoneNumber(userForm.getPhoneNumber())
		// .profilePic("https://tse1.mm.bing.net/th?id=OIP.dp0_W14dfG8nECs8vmS5OwHaHa&pid=Api&P=0&h=220")
		// .build();
		User user = new User();
		user.setName(userForm.getName());
		user.setEmail(userForm.getEmail());
		user.setPassword(userForm.getPassword());
		user.setAbout(userForm.getAbout());
		user.setPhoneNumber(userForm.getPhoneNumber());
		user.setProfilePic("https://tse1.mm.bing.net/th?id=OIP.dp0_W14dfG8nECs8vmS5OwHaHa&pid=Api&P=0&h=220");
		User savedUser = userService.saveUser(user);
		// message ="Registration Successful"
		System.out.println("User is saved");
		// show saved message after the user is saved

		Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();

		session.setAttribute("message", message);
		// redirect to login page
		return "redirect:/register";
	}
}
