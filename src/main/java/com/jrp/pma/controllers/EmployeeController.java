package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.entities.Employee;


@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository empRep;
	
	
	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		
		Employee emp = new Employee();
		model.addAttribute("employee",emp );
		
		return "employees/new-employee"; 
	}
	
	@PostMapping("/save")
	public String createEmployee (Employee employee, Model model) {
		
		empRep.save(employee);
		
		return"redirect:/employee/new";
		
	}
	
	@GetMapping
	public String displayEmployees(Model model) {
		
		List<Employee> employees = empRep.findAll();
		model.addAttribute("employeesList",employees);
		return"employees/show-employees";
		
	}
	

}
