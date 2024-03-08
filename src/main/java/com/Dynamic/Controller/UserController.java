package com.Dynamic.Controller;


import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Dynamic.Dao.UserRegistrationRepository;
import com.Dynamic.Entity.PartnerRegistration;
import com.Dynamic.Entity.UserRegistration;
import com.Dynamic.service.PartnerRegistationService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	 	@Autowired
		private PartnerRegistationService partnerRegistration;
	 
	 	@Autowired
	 	private UserRegistrationRepository userRegistrationRepository;
	 	
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

	 
	 	//profile
		
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
		//Listofservices
		@RequestMapping("/ListOfPartner")
		public String Partner(Model m,Principal p) {
	        List<PartnerRegistration> listOfPartners = partnerRegistration.getAllPartners();
	        m.addAttribute("ListOfPartner", listOfPartners);  
	
	        return "ListOfPartner";
		}
		
		@RequestMapping("booking")
		public String Booking(Model model) {
			return "booking";
		}
		
		@RequestMapping("/team")
		public String team(Model model) {
			return "team";
		}
		@RequestMapping("/logout")
		public String login() {
			return "redirect:/index";
		}
	
}
