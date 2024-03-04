package com.Dynamic.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Dynamic.Dao.PartnerRepository;
import com.Dynamic.Entity.Contact;
import com.Dynamic.Entity.PartnerRegistration;
import com.Dynamic.service.PartnerRegistationService;
import com.Dynamic.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	 @Autowired
	private UserService userService;
	 @Autowired
	private PartnerRegistationService partnerRegistration;
	 
	 private PartnerRepository partnerRepository;
	 
//	 @ModelAttribute
//	 public PartnerRegistration GetPartner(Principal p, Model m) {
//		 String email=p.getName();
//		 PartnerRegistration partnerRegistration = partnerRepository.findByEmail(email);
//		m.addAttribute("partnerRegistration", partnerRegistration);
//		return partnerRegistration;
//	 }
	 
	
	@RequestMapping(value = {"","/","home"})
	public String displayHome(Model model) {
		model.addAttribute("UserName"," Harendra Ranjan");
		return "index";
	}
	@RequestMapping("service")
	public String Service(Model model) {
		return "service";
	}
	
	@RequestMapping("Listofservices")
	public String menu(Model model) {
		return "Listofservices";
	}
	
	
	@RequestMapping("booking")
	public String Booking(Model model) {
		return "booking";
	}
	
	@RequestMapping("team")
	public String team(Model model) {
		return "team";
	}
	
	
	@RequestMapping("about")
	public String About(Model model) {
		return "about";
	}
	@RequestMapping("testimonial")
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
			session.setAttribute("msg", "This email is already exit");
		}else {
			Contact c=userService.saveUser(contact);
			if(c!=null) {
				session.setAttribute("msg", "Thank for contacting! I will contact you as soon as posible");
			}else {
				session.setAttribute("msg", "Contact is not created successfully");
			}
		}
		
		return "redirect:/contact";
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
		
		return "redirect:/registartion";
	}
	@RequestMapping("/ListOfPartner")
	public String Partner(Model m,Principal p) {

        // Fetch the list of partners from your service
        List<PartnerRegistration> listOfPartners = partnerRegistration.getAllPartners();
        m.addAttribute("ListOfPartner", listOfPartners);  // Add the list to the model

        return "ListOfPartner";
	}
	
}
