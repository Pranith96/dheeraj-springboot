package com.employee.service;

import java.util.List;

import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;

public interface EmployeeService {

	String addEmployee(Employee employee);

	List<Employee> getEmployees();

	EmployeeDto getEmploye(Integer employeeId);

	List<Employee> getEmployeByName(String name);

	Employee getEmployeByLogin(String login, String password);

	String updateEmployeeName(String name, Integer employeeId);

	String deleteEmployee(Integer employeeId);

}
