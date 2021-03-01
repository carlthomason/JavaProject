package com.thomason.outdoors.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thomason.outdoors.models.User;
import com.thomason.outdoors.services.UserService;
import com.thomason.outdoors.validators.UserValidator;

@Controller
public class UserController {
	private final UserService userService;
	private final UserValidator userValidator;
	
	public UserController(UserService userService, UserValidator userValidator) {
		this.userService = userService;
		this.userValidator = userValidator;
	}
	// Registration and Login Page
	@RequestMapping("/")
    public String registerForm(@ModelAttribute("user") User user) {
        return "login.jsp";
    }
    
	// Register
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, Model model) {
        userValidator.validate(user, result);
        if(result.hasErrors()) {
            return "login.jsp";
        }
        boolean isDuplicate = userService.duplicateUser(user.getEmail());
		if(isDuplicate) {
			model.addAttribute("error", "Email already in use! Please try again with a different email address!");
			return "login.jsp";
		}
        User u = userService.registerUser(user);
        session.setAttribute("userId", u.getId());
        return "redirect:/outdoors";
    }
    
    // Login
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
    	boolean isAuthenticated = userService.authenticateUser(email, password);
		if(isAuthenticated) {
			User user = userService.findByEmail(email);
			session.setAttribute("userId", user.getId());
			return "redirect:/outdoors";
		} else {
			redirectAttributes.addFlashAttribute("error",  "Invalid Credentials");
            model.addAttribute("error", "Not authenticated");
            return "redirect:/";
        }
    }

  // Logout  
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
		return "redirect:/";
    }
       
  @RequestMapping("/outdoors")
	public String homepage(HttpSession session, Model model) {
		// if current user is in session then proceed(if not redirect)
		if (session.getAttribute("userId") != null) {
			// get user from session, return the home page
			Long userId = (Long) session.getAttribute("userId");
			User u = userService.findUserById(userId);
			model.addAttribute("user", u);
			return "main.jsp";
		} else {
			return "redirect:/";
		}
	}
}

