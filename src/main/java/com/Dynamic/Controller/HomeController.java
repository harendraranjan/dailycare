package com.Dynamic.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Dynamic.Dao.OrderBookingRepository;
import com.Dynamic.Dao.PartnerRepository;
import com.Dynamic.Entity.Contact;
import com.Dynamic.Entity.OrderBooking;
import com.Dynamic.Entity.PartnerRegistration;
import com.Dynamic.Entity.UserRegistration;
import com.Dynamic.service.OrderBookingService;
import com.Dynamic.service.PartnerRegistationService;
import com.Dynamic.service.UserRegistrationServise;
import com.Dynamic.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	 @Autowired
	private UserService userService;
	 @Autowired
	private PartnerRegistationService partnerRegistration;
	 
	 @Autowired
	private UserRegistrationServise userRegistrationServise;
		
		
	 
	 @RequestMapping("/error/access-denied")
	 public String accessDenied() {
	     return "error/access-denied";
	 }
	
	@RequestMapping(value = {"","/","home"})
	public String displayHome(Model model) {
		model.addAttribute("UserName"," Harendra Ranjan");
		return "index";
	}
	
	
	@RequestMapping("/about")
	public String About(Model model) {
		return "about";
	}
	@RequestMapping("/testimonial")
	public String testimonial() {
		return "testimonial";
	}
	
	
	//Contact 
	@RequestMapping("contact")
	public String Contact() {
		return "contact";
	}
	//save contact form
	@PostMapping("SaveContact")
	public String SaveContact( @ModelAttribute Contact contact,HttpSession session,Model m) {
		boolean f = userService.existEmailChack(contact.getEmail());
		if(f) {
			session.setAttribute("msg", "I have already recive your message i will contact you as soon as possible");
		}else {
			Contact c=userService.saveUser(contact);
			if(c!=null) {
				session.setAttribute("msg", "Thank for contacting! I will contact you as soon as posible");
			}else {
				session.setAttribute("msg", "Contact is not created successfully");
			}
		}
		
		return "redirect:/thankyou";
	}
	
	//registration of user
	@RequestMapping("/registartion")
	public String PartnerRegistraion() {
		return "registartion";
	}
	
	
	//save registration form
	@PostMapping("/SaveRegistartion")
	public String SaveRegistraion(@ModelAttribute PartnerRegistration partner,HttpSession session) {
		
		boolean f=partnerRegistration.existEmailCheck(partner.getEmail());
		if(f) {
			session.setAttribute("msg", "This email is already exist");
		}else {
			PartnerRegistration savePartnerRegistration = partnerRegistration.savePartnerRegistration(partner);
			if(savePartnerRegistration!=null) {
				session.setAttribute("msg","Your registration have done successfully" );
			}else {
				session.setAttribute("msg", "Your registration haven't done successfully");
			}
		}
		
		return "redirect:/SaveRegistartion";
	}
	
	
	
	@RequestMapping("/UserRegistration")
	public String UserRegistraion() {
		return "UserRegistration";
	}
	
	@PostMapping("/SaveUserRegistraion")
	public String SaveUserRegistraion( @ModelAttribute UserRegistration userRegistration, HttpSession session, Model m) {
		boolean f = userRegistrationServise.existEmailChack(userRegistration.getEmail());
		if(f) {
			session.setAttribute("msg", "This email is already exist");
		}else {
			UserRegistration saveUserRegistration = userRegistrationServise.saveUserRegistration(userRegistration);
			System.out.println(userRegistration);
			if(saveUserRegistration!=null) {
				session.setAttribute("msg","Your registration have done successfully" );
			}else {
				session.setAttribute("msg", "Your registration haven't done successfully");
			
			}
		}
		System.out.println("User Registration: " + userRegistration);
		return "redirect:/UserRegistration";
	}
	@RequestMapping("/thankyou")
	public String thankyou() {
		return "thankyou";
	}
	
}
