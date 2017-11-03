package com.springjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springjpa.model.Customer;
import com.springjpa.repo.CustomerRepository;

@RestController
public class WebController {
	@Autowired
	CustomerRepository repository;
	
	@RequestMapping("/save")
	public String process(){
		repository.save(new Customer("Nalanda", "S"));
		repository.save(new Customer("Akshatha", "D"));
		repository.save(new Customer("Ahaana", "N"));

		return "Done";
	}
	
	
	@RequestMapping("/findall")
	public String findAll(){
		String result = "<html>";
		for(Customer cust : repository.findAll()){
			result += "<div>" + cust.toString() + "</div>";
		}
		
		return result + "</html>";
	}
	
	@RequestMapping("/findbyid")
	public String findById(@RequestParam("id") long id){
		String result = "";
		result = repository.findOne(id).toString();
		return result;
	}
	
	@RequestMapping("/findbylastname")
	public String fetchDataByLastName(@RequestParam("lastname") String lastName){
		String result = "<html>";
		
		for(Customer cust: repository.findByLastName(lastName)){
			result += "<div>" + cust.toString() + "</div>"; 
		}
		
		return result + "</html>";
	}
	@RequestMapping("/del")
	public String del() {
		System.out.println("inside");
		repository.deleteAll();
		return "done";
		
	}
	
	@RequestMapping("/deleteById")
		public String deleteById(@RequestParam("id") long id) {
		try {
			 repository.delete(id);
		}
	catch(EmptyResultDataAccessException e) {
		return "Deletion failed, item does not exist";
	}
	      return "redirect:findall";

		 
	}
}

