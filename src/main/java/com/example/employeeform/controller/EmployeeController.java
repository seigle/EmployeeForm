package com.example.employeeform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.employeeform.entity.Employee;
import com.example.employeeform.repository.EmployeeRepository;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeRepository eRepo;
	
	@GetMapping({"/showEmployees", "/", "/list"})  // The leading slash may need to be removed.
	public ModelAndView showEmployees() {
		ModelAndView modelView = new ModelAndView("list-employees");
		List<Employee> list = eRepo.findAll();
		modelView.addObject("employees", list);
		return modelView;
	}
	
	@GetMapping("/addEmployeeForm")
	public ModelAndView addEmployeeForm() {
		ModelAndView modelView = new ModelAndView("add-employee-form");
		Employee newEmployee = new Employee();
		modelView.addObject("employee", newEmployee);
		
		return modelView;
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute Employee employee) {
		eRepo.save(employee);
		return "redirect:/list";
	}
	
	@GetMapping("/showUpdateForm")
	public ModelAndView showUpdateForm(@RequestParam("employeeId") Long employeeId) {
		ModelAndView modelView = new ModelAndView("add-employee-form");
		Employee employee = eRepo.findById(employeeId).get();
		modelView.addObject("employee", employee);
		return modelView;
	}
	
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam("employeeId") Long employeeId) {
		eRepo.deleteById(employeeId);
		return "redirect:/list";
	}
}
