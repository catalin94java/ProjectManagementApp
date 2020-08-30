package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;

@Controller
@RequestMapping ("/projects")
public class ProjectController {
	
	@Autowired
	ProjectRepository proRep;
	
	@Autowired
	EmployeeRepository empRepo;

	@GetMapping ("/new")
	public String displayProjectForm(Model model) {
		
	    Project aProject = new Project();
	    List<Employee> employees = empRepo.findAll();
		model.addAttribute("project", aProject);
		model.addAttribute("allEmployees", employees);
		return "projects/new-project";
	}
	
	
	@PostMapping("/save")
	public String createProject(Project project, Model model) {
		
		proRep.save(project);
		

		// use a redirect to prevent duplicate submissions
		return "redirect:/projects";
		
	}
	
	@GetMapping
	public String displayProjects(Model model) {
		
		List<Project> projects = proRep.findAll();
		model.addAttribute("projectsList",projects);
		return"projects/show-projects";
		
	}
	
	
}
