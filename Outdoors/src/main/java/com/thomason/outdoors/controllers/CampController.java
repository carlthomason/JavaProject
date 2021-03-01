package com.thomason.outdoors.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thomason.outdoors.models.User;
import com.thomason.outdoors.models.camp.Camp;
import com.thomason.outdoors.services.CampService;
import com.thomason.outdoors.services.UserService;

public class CampController {
	private final CampService campService;
	private final UserService userService;
	
	
	public CampController(CampService campService, UserService userService) {
		this.campService = campService;
		this.userService = userService;
	}
	
	 @RequestMapping("/Camp/home")
		public String homepage(HttpSession session, Model model) {
			// if current user is in session then proceed(if not redirect)
			if (session.getAttribute("userId") != null) {
				// get user from session, return the home page
				Long userId = (Long) session.getAttribute("userId");
				User u = userService.findUserById(userId);
				model.addAttribute("user", u);

				List<Camp> camplist = campService.getAll();
				model.addAttribute("camps", camplist);
				return "/Camp/home.jsp";
			} else {
				return "redirect:/";
			}
		}
}
