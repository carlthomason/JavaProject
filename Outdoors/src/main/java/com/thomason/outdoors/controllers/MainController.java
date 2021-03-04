package com.thomason.outdoors.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thomason.outdoors.models.Camp;
import com.thomason.outdoors.models.Fish;
import com.thomason.outdoors.models.Hunt;
import com.thomason.outdoors.models.User;
import com.thomason.outdoors.services.CampService;
import com.thomason.outdoors.services.FishService;
import com.thomason.outdoors.services.HuntService;
import com.thomason.outdoors.services.UserService;
import com.thomason.outdoors.validators.UserValidator;

@Controller
public class MainController {
	private final UserService userService;
	private final CampService campService;
	private final FishService fishService;
	private final HuntService huntService;
	private final UserValidator userValidator;
	
	public MainController(UserService userService, UserValidator userValidator, CampService campService, FishService fishService, HuntService huntService){
		this.userService = userService;
		this.userValidator = userValidator;
		this.campService = campService;
		this.fishService = fishService;
		this.huntService = huntService;
	}
//=========================================================================================//	
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
//========================================================================================//
  // Camp Home
  @RequestMapping("/camphome")
	public String campHome(HttpSession session, Model model) {
		// if current user is in session then proceed(if not redirect)
		if (session.getAttribute("userId") != null) {
			// get user from session, return the home page
			Long userId = (Long) session.getAttribute("userId");
			User u = userService.findUserById(userId);
			model.addAttribute("user", u);

			List<Camp> camplist = campService.getAll();
			model.addAttribute("camps", camplist);
			return "campHome.jsp";
		} else {
			return "redirect:/outdoors";
		}
	}
  
  
// Camp create page
	@RequestMapping("/camps")
	public String createCamp(@ModelAttribute("camp") Camp myCamp, Model model, HttpSession session) {
		if (session.getAttribute("userId") != null) {
			
			List<User> allusers = userService.findAll();
			model.addAttribute("users", allusers);

			
			Long userId = (Long) session.getAttribute("userId");
			User u = userService.findUserById(userId);
			model.addAttribute("currentUser", u);
			return "campCreate.jsp";
		} else {
			return "redirect:/";
		}
	}

  
  
  // Camp Create
  @RequestMapping(value = "/camp/new", method = RequestMethod.POST)
	public String createNewCamp(@Valid @ModelAttribute("camps") Camp myCamp, BindingResult result, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		User u = userService.findUserById(userId);
		model.addAttribute("user", u);
		if (result.hasErrors()) {
			List<User> allusers = userService.findAll();
			model.addAttribute("users", allusers);
			return "createCamp.jsp";
		} else {
			Camp newCamp = campService.createCamp(myCamp);
			newCamp.setUser(u);
			campService.updateCamp(newCamp);
			return "redirect:/camphome";
		}
	}
  
  // Camp Show
  //Display a Camp
	@GetMapping("/camp/{id}")
	public String displayCamp(@PathVariable("id") Long campId, Model model, HttpSession session) {
		// find a camp by id
		Camp thisCamp = campService.findCamp(campId);
		model.addAttribute("camp", thisCamp);

		// find a current login user id
		Long userId = (Long) session.getAttribute("userId");
		User u = userService.findUserById(userId);
		model.addAttribute("currentUserId", u.getId());
		return "campShow.jsp";
	}
  
  // Camp Edit Page
	@RequestMapping("/camp/{id}/edit")
	public String displayEditCamp(@PathVariable("id") Long campId, @ModelAttribute("editcamp") Camp myCamp, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		User u = userService.findUserById(userId);

		// get a camp by id
		Camp editing = campService.findCamp(campId);
		if (u.getId() == editing.getUser().getId()) {

			List<User> allUsers = userService.findAll();

			// Getting a camp's creator info back to JSP
			model.addAttribute("user", editing.getUser());
			model.addAttribute("editcamp", editing);
			model.addAttribute("users", allUsers);
			model.addAttribute("id", editing.getId());
			return "campEdit.jsp";
		} else {
			return "redirect:/camphome";
		}
	}
	
   // Update Camp
	@PutMapping("/camp/{id}/edit")
	public String updateCamp(@PathVariable("id") Long campId, @Valid @ModelAttribute("editcamp") Camp myCamp, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<User> allusers = userService.findAll();
			model.addAttribute("users", allusers);
			return "campEdit.jsp";
		} else {
			campService.createCamp(myCamp);
			return "redirect:/camphome";
		}
	}
  
  // Camp Delete
	@DeleteMapping("/camp/{id}/delete")
	public String deleteCamp(@PathVariable("id") Long myId) {
		Camp delcamp = campService.findCamp(myId);
		if (delcamp != null) {
			campService.deleteCamp(myId);
			return "redirect:/camphome";
		} else {
			return "redirect:/camphome";
		}
	}
	 
//=======================================================================================//	
	// Hunt Home
	  @RequestMapping("/huntHome")
		public String huntHome(HttpSession session, Model model) {
			// if current user is in session then proceed(if not redirect)
			if (session.getAttribute("userId") != null) {
				// get user from session, return the home page
				Long userId = (Long) session.getAttribute("userId");
				User u = userService.findUserById(userId);
				model.addAttribute("user", u);

				List<Hunt> huntList = huntService.getAll();
				model.addAttribute("hunts", huntList);
				return "huntHome.jsp";
			} else {
				return "redirect:/outdoors";
			}
		}
	  
	// Hunt create page
		@RequestMapping("/hunt")
		public String createHunt(@ModelAttribute("hunt") Hunt myHunt, Model model, HttpSession session) {
			if (session.getAttribute("userId") != null) {
				
				List<User> allusers = userService.findAll();
				model.addAttribute("users", allusers);

				
				Long userId = (Long) session.getAttribute("userId");
				User u = userService.findUserById(userId);
				model.addAttribute("currentUser", u);
				return "huntCreate.jsp";
			} else {
				return "redirect:/";
			}
		}
	  
	  
	  
	  
	  // Create Hunting site location
	  @RequestMapping(value = "/hunt/new", method = RequestMethod.POST)
		public String createNewHunt(@Valid @ModelAttribute("hunt") Hunt myHunt, BindingResult result, Model model, HttpSession session) {
			Long userId = (Long) session.getAttribute("userId");
			User u = userService.findUserById(userId);
			model.addAttribute("user", u);
			if (result.hasErrors()) {
				List<User> allusers = userService.findAll();
				model.addAttribute("users", allusers);
				return "huntCreate.jsp";
			} else {
				Hunt newHunt = huntService.createHunt(myHunt);
				newHunt.setUser(u);
				huntService.updateHunt(newHunt);
				return "redirect:/huntHome";
			}
		}
	  
	  // Hunt Show
	  //Display a Hunt
		@GetMapping("/hunt/{id}")
		public String displayHunt(@PathVariable("id") Long huntId, Model model, HttpSession session) {
			// find a camp by id
			Hunt thisHunt = huntService.findHunt(huntId);
			model.addAttribute("hunt", thisHunt);

			// find a current login user id
			Long userId = (Long) session.getAttribute("userId");
			User u = userService.findUserById(userId);
			model.addAttribute("currentUserId", u.getId());
			return "huntShow.jsp";
		}
	  
	  // Hunt Edit Page
		@RequestMapping("/hunt/{id}/edit")
		public String displayEditHunt(@PathVariable("id") Long huntId, @ModelAttribute("editHunt") Hunt myHunt, Model model, HttpSession session) {
			Long userId = (Long) session.getAttribute("userId");
			User u = userService.findUserById(userId);

			// get a hunt by id
			Hunt editing = huntService.findHunt(huntId);
			if (u.getId() == editing.getUser().getId()) {

				List<User> allUsers = userService.findAll();

				// Getting a Hunt creator info back to JSP
				model.addAttribute("user", editing.getUser());
				model.addAttribute("editHunt", editing);
				model.addAttribute("users", allUsers);
				model.addAttribute("id", editing.getId());
				return "huntEdit.jsp";
			} else {
				return "redirect:/huntHome";
			}
		}
		
	   // Update Hunt
		@PutMapping("/hunt/{id}/edit")
		public String updateHunt(@PathVariable("id") Long huntId, @Valid @ModelAttribute("editHunt") Hunt myHunt, BindingResult result, Model model) {
			if (result.hasErrors()) {
				List<User> allusers = userService.findAll();
				model.addAttribute("users", allusers);
				return "huntEdit.jsp";
			} else {
				huntService.createHunt(myHunt);
				return "redirect:/huntHome";
			}
		}
	  
	  // Hunt Delete
		@DeleteMapping("/hunt/{id}/delete")
		public String deleteHunt(@PathVariable("id") Long huntId) {
			Hunt delHunt = huntService.findHunt(huntId);
			if (delHunt != null) {
				huntService.deleteHunt(huntId);
				return "redirect:/huntHome";
			} else {
				return "redirect:/huntHome";
			}
		}
//====================================================================================//
		//Fish Home
		  @RequestMapping("/fishHome")
			public String fishHome(HttpSession session, Model model) {
				// if current user is in session then proceed(if not redirect)
				if (session.getAttribute("userId") != null) {
					// get user from session, return the home page
					Long userId = (Long) session.getAttribute("userId");
					User u = userService.findUserById(userId);
					model.addAttribute("user", u);

					List<Fish> fishList = fishService.getAll();
					model.addAttribute("fish", fishList);
					return "fishHome.jsp";
				} else {
					return "redirect:/outdoors";
				}
			}
		  
		// Fish create page
			@RequestMapping("/fish")
			public String createFish(@ModelAttribute("fish") Fish myFish, Model model, HttpSession session) {
				if (session.getAttribute("userId") != null) {
					
					List<User> allusers = userService.findAll();
					model.addAttribute("users", allusers);

					
					Long userId = (Long) session.getAttribute("userId");
					User u = userService.findUserById(userId);
					model.addAttribute("currentUser", u);
					return "fishCreate.jsp";
				} else {
					return "redirect:/";
				}
			}
		  
		  
		  
		  
		  // Create Fishing site location
		  @RequestMapping(value = "/fish/new", method = RequestMethod.POST)
			public String createNewFish(@Valid @ModelAttribute("fish") Fish myFish, BindingResult result, Model model, HttpSession session) {
				Long userId = (Long) session.getAttribute("userId");
				User u = userService.findUserById(userId);
				model.addAttribute("user", u);
				if (result.hasErrors()) {
					List<User> allusers = userService.findAll();
					model.addAttribute("users", allusers);
					return "fishCreate.jsp";
				} else {
					Fish newFish = fishService.createFish(myFish);
					newFish.setUser(u);
					fishService.updateFish(myFish);
					return "redirect:/fishHome";
				}
			}
		  
		  //Fish Show
		  //Display a Fish
			@GetMapping("/fish/{id}")
			public String displayFish(@PathVariable("id") Long fishId, Model model, HttpSession session) {
				// find a camp by id
				Fish thisFish = fishService.findFish(fishId);
				model.addAttribute("fish", thisFish);

				// find a current login user id
				Long userId = (Long) session.getAttribute("userId");
				User u = userService.findUserById(userId);
				model.addAttribute("currentUserId", u.getId());
				return "fishShow.jsp";
			}
		  
		  // Fish Edit Page
			@RequestMapping("/fish/{id}/edit")
			public String displayEditFish(@PathVariable("id") Long fishId, @ModelAttribute("editFish") Fish myFish, Model model, HttpSession session) {
				Long userId = (Long) session.getAttribute("userId");
				User u = userService.findUserById(userId);

				// get a fish by id
				Fish editing = fishService.findFish(fishId);
				if (u.getId() == editing.getUser().getId()) {

					List<User> allUsers = userService.findAll();

					// Getting a fish user info back to JSP
					model.addAttribute("user", editing.getUser());
					model.addAttribute("editFish", editing);
					model.addAttribute("users", allUsers);
					model.addAttribute("id", editing.getId());
					return "fishEdit.jsp";
				} else {
					return "redirect:/fishHome";
				}
			}
			
		   // Update Fish
			@PutMapping("/fish/{id}/edit")
			public String updateFish(@PathVariable("id") Long fishId, @Valid @ModelAttribute("editFish") Fish myFish, BindingResult result, Model model) {
				if (result.hasErrors()) {
					List<User> allusers = userService.findAll();
					model.addAttribute("users", allusers);
					return "fishEdit.jsp";
				} else {
					fishService.createFish(myFish);
					return "redirect:/fishHome";
				}
			}
		  
		  // Fish Delete
			@DeleteMapping("/fish/{id}/delete")
			public String deleteFish(@PathVariable("id") Long fishId) {
				Fish delFish = fishService.findFish(fishId);
				if (delFish != null) {
					fishService.deleteFish(fishId);
					return "redirect:/fishHome";
				} else {
					return "redirect:/fishHome";
				}
			}
}

