package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.service.EmployeeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Qualifier(value = "service2")
	@Autowired
	EmployeeService employeeService;

	@PostMapping("/save")
	@ApiOperation(value = "Employee account Creation API",
    notes = "Please provide all the info for Employee account creation",
    response = Employee.class)
	public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
		String response = employeeService.addEmployee(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/get-all")
	public ResponseEntity<List<Employee>> getEmployees(){
		List<Employee> response = employeeService.getEmployees();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/get/{employeeId}")
	@ApiOperation(value = "Employee Fetch Details Creation API",
    notes = "Please provide employeeId for Employee details fetching",
    response = Employee.class)
	public ResponseEntity<EmployeeDto> getEmployeById(@PathVariable("employeeId") Integer employeeId){
		EmployeeDto response = employeeService.getEmploye(employeeId);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/get/id")
	@ApiOperation(value = "Employee Fetch Details Creation API",
    notes = "Please provide employeeId for Employee details fetching",
    response = Employee.class)
	public ResponseEntity<EmployeeDto> getEmployee(@RequestParam("employeeId") Integer employeeId){
		EmployeeDto response = employeeService.getEmploye(employeeId);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/get/name/{name}")
	@ApiOperation(value = "Employee Fetch Details Creation API",
    notes = "Please provide employee name for Employee details fetching",
    response = Employee.class)
	public ResponseEntity<List<Employee>> getEmployeByName(@PathVariable("name") String name){
		List<Employee> response = employeeService.getEmployeByName(name);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/get/login/{login}/{password}")
	public ResponseEntity<Employee> getEmployeeByLogin(@PathVariable("login") String login, @PathVariable("password") String password){
		Employee response = employeeService.getEmployeByLogin(login,password);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PutMapping("/update/{name}/{employeeId}")
	public ResponseEntity<String> updateEmployee(@PathVariable("name") String name,@PathVariable("employeeId") Integer employeeId){
		String response = employeeService.updateEmployeeName(name,employeeId);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping("/delete/{employeeId}")
	public ResponseEntity<String> deleteEmployeById(@PathVariable("employeeId") Integer employeeId){
		String response = employeeService.deleteEmployee(employeeId);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
