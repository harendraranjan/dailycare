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
import com.Dynamic.Entity.JoinUs;
import com.Dynamic.Entity.UserRegistration;
import com.Dynamic.service.JoinUsService;
import com.Dynamic.service.UserRegistrationServise;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private JoinUsService partnerRegistration;

	@Autowired
	private UserRegistrationRepository userRegistrationRepository;
	
	@Autowired
	private UserRegistrationServise userRegistrationService;


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

	@RequestMapping(value = { "admin_profile", "/", "" })
	public String profile(Model m, Principal p) {
		List<UserRegistration> allUser = userRegistrationRepository.findAll();
		m.addAttribute("allUser", allUser);
		return "admin_profile";
	}

	// show all user
	@RequestMapping("/showPartner")
	public String showUser(Model m, Principal p) {
		List<JoinUs> listOfPartners = partnerRegistration.getAllPartners();
		m.addAttribute("ListOfPartner", listOfPartners);
		return "showPartner";
	}

	@PostMapping("/editPartner")
	public String saveEditedUser(@ModelAttribute JoinUs user, HttpSession session) {
		boolean success = partnerRegistration.updatePartner(user);

		if (success) {
			session.setAttribute("msg", "User information updated successfully");
		} else {
			session.setAttribute("msg", "Failed to update user information");
		}

		return "redirect:/admin/showPartner";
	}

	@RequestMapping("/editPartner/{id}")
	public String editUser(@PathVariable int id, Model model) {
		JoinUs Partner = partnerRegistration.getPartnerById(id);

		if (Partner != null) {
			model.addAttribute("Partner", Partner);
			return "editPartner";
		} else {
			return "redirect:/admin/showPartner";
		}
	}

	// Delete Partner
	@GetMapping("/deletePartner/{id}")
	public String deletPartner(@PathVariable int id, HttpSession session) {
		boolean f = partnerRegistration.deletePartner(id);

		if (f) {
			session.setAttribute("msg", "Delete successfully completed");
		} else {
			session.setAttribute("msg", "Registration not done successfully");
		}

		return "redirect://showPartner";
	}

	// Delete User
	@GetMapping("/deleteUser/{id}")
	public String deletUser(@PathVariable int id, HttpSession session) {
		boolean f = userRegistrationService.deleteUser(id);

		if (f) {
			session.setAttribute("msg", "Delete successfully completed");
		} else {
			session.setAttribute("msg", "Registration not done successfully");
		}

		return "redirect:/admin/admin_profile";
	}
}
