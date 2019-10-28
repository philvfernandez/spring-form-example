package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormController {
	@Autowired
	CustomerRepo repo;
	
	@RequestMapping("/")
	public String edureka(Model model)
	{
		model.addAttribute("customers", new Customers());
		return "edureka";
	}
	
	@RequestMapping("/details")
	public String details(Customers customers) {
		repo.save(customers);
		return "edureka";
	}
	
	@RequestMapping("/getdetails")
	public String getdetails(Model model,Customers customers) {
	
		model.addAttribute("customers",customers);
		return "ViewCustomers";
	}
	
	
	@PostMapping(value = "/getdetails")
    public ModelAndView getdetails(@RequestParam int cid,Model model)
	//public String getdetails(@RequestParam int cid,Model model)
    {
		ModelAndView mv = new ModelAndView("Retrive");
		Customers customers = repo.findById(cid).orElse(null);
	    mv.addObject(customers);
	    
	    // model.addAttribute("customers",customers);
	    
	    return mv;
	    // return "Retrive";
    }
}
