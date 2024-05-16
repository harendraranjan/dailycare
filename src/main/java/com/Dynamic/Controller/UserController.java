package com.Dynamic.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Dynamic.Dao.UserRegistrationRepository;
import com.Dynamic.Entity.OrderBooking;
import com.Dynamic.Entity.JoinUs;
import com.Dynamic.Entity.UserRegistration;
import com.Dynamic.service.OrderBookingService;
import com.Dynamic.service.JoinUsService;
import com.Dynamic.service.UserRegistrationServise;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRegistrationServise userRegistrationServise;

	@Autowired
	private JoinUsService partnerRegistration;

	@Autowired
	private UserRegistrationRepository userRegistrationRepository;

	@Autowired
	private OrderBookingService orderBookingService;

	@ModelAttribute
	public UserRegistration getUser(Principal p, Model m) {
		String name = p.getName();
		UserRegistration userRegistration = userRegistrationRepository.findByEmail(name);

		if (userRegistration != null) {
			System.out.println("User found: " + userRegistration.getName());
		} else {
			System.out.println("User not found for email: " + name);
		}

		m.addAttribute("userRegistration", userRegistration);
		return userRegistration;
	}

	// profile

	@RequestMapping("profile")
	public String profile() {
		return "profile";
	}

	@RequestMapping("/service")
	public String Service() {
		return "service";
	}

	@RequestMapping("/Listofservices")
	public String menu(Model model) {
		return "Listofservices";
	}

	// Listofservices
	@RequestMapping("/ListOfPartner")
	public String Partner(Model m, Principal p) {
		List<JoinUs> listOfPartners = partnerRegistration.getAllPartners();
		m.addAttribute("ListOfPartner", listOfPartners);

		return "ListOfPartner";
	}

	@RequestMapping("booking")
	public String Booking(Model model) {
		return "booking";
	}

	@PostMapping("/SaveOrderBooking")
	public String SaveOrderBooking(@ModelAttribute OrderBooking orderBooking, HttpSession session) {
		orderBookingService.saveOrderBooking(orderBooking);
		System.out.println(orderBooking);
		return "redirect:/thankyou";
	}

	@RequestMapping("/team")
	public String team(Model model) {
		return "team";
	}

	@RequestMapping("/logout")
	public String login() {
		return "redirect:/index";
	}

	@RequestMapping(value = { "joinus", "JoinUs", "joinUs", "Joinus" })
	public String JoinUs() {
		return "JoinUs";
	}

	// show all user
	@RequestMapping("/showUser")
	public String showUser(Model m, Principal p) {
		List<JoinUs> listOfPartners = partnerRegistration.getAllPartners();
		m.addAttribute("ListOfPartner", listOfPartners);
		return "showUser";
	}

	@RequestMapping("/editUser/{id}")
	public String editUser(@PathVariable int id, Model model) {
		JoinUs user = partnerRegistration.getPartnerById(id);

		if (user != null) {

			model.addAttribute("user", user);
			return "editUser";
		} else {

			return "redirect:/user/showUser";
		}
	}

	@PostMapping("/editUser")
	public String saveEditedUser(@ModelAttribute JoinUs user, HttpSession session) {
		boolean success = partnerRegistration.updatePartner(user);

		if (success) {
			session.setAttribute("msg", "User information updated successfully");
		} else {
			session.setAttribute("msg", "Failed to update user information");
		}

		return "redirect:/user/showUser";
	}

	// Delete User
	@GetMapping("/deletUser/{id}")
	public String deletNote(@PathVariable int id, HttpSession session) {
		boolean f = partnerRegistration.deletePartner(id);

		if (f) {
			session.setAttribute("msg", "Delete successfully completed");
		} else {
			session.setAttribute("msg", "Registration not done successfully");
		}

		return "redirect:/user/showUser";
	}

	// save registration form
	@PostMapping("/SaveJoinUs")
	public String SaveRegistraion(@ModelAttribute JoinUs partner, HttpSession session) {

		boolean f = partnerRegistration.existEmailCheck(partner.getEmail());
		if (f) {
			session.setAttribute("msg", "This email is already exist");
		} else {
			JoinUs savePartnerRegistration = partnerRegistration.saveJoinUs(partner);
			if (savePartnerRegistration != null) {
				session.setAttribute("msg", "Your registration have done successfully");
			} else {
				session.setAttribute("msg", "Your registration haven't done successfully");
			}
		}

		return "redirect:/user/JoinUs";
	}
}
