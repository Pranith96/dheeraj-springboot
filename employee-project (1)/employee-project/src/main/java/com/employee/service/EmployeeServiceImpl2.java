package com.employee.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.entity.Status;
import com.employee.exceptions.EmployeeNotFoundException;
import com.employee.repository.EmployeeRepository;

@Transactional
@Service(value = "service2")
@Profile(value = { "dev", "local", "qa", "prod" })
public class EmployeeServiceImpl2 implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public String addEmployee(Employee employee) {
		employee.setStatus(Status.ACTIVE);
		Employee response = employeeRepository.save(employee);
		if (response == null) {
			return "data not saved";
		}
		return "data saved successfully";
	}

	@Override
	public List<Employee> getEmployees() {
		List<Employee> response = employeeRepository.findAll();
		if (response == null || response.isEmpty()) {
			throw new EmployeeNotFoundException("Data is empty");
		}
		return response;
	}

	@Override
	public EmployeeDto getEmploye(Integer employeeId) {
		Optional<Employee> response = employeeRepository.findById(employeeId);
		if (!response.isPresent()) {
			throw new EmployeeNotFoundException("Data is not found");
		}

		EmployeeDto dto = new EmployeeDto();
		dto.setEmailId(response.get().getEmailId());
		dto.setEmployeeId(response.get().getEmployeeId());
		dto.setMobileNumber(response.get().getMobileNumber());
		dto.setName(response.get().getName());
		return dto;
	}

	@Override
	public List<Employee> getEmployeByName(String name) {
		// List<Employee> response = employeeRepository.findByName(name);
		List<Employee> response = employeeRepository.getByName(name);
		if (response == null || response.isEmpty()) {
			throw new EmployeeNotFoundException("Data is empty");
		}
		return response;
	}

	@Override
	public Employee getEmployeByLogin(String login, String password) {
		// Optional<Employee> response =
		// employeeRepository.findByLoginIdAndPassword(login, password);
		Optional<Employee> response = employeeRepository.getByLoginIdAndPassword(login, password);
		if (!response.isPresent()) {
			throw new EmployeeNotFoundException("Data is not found");
		}
		return response.get();
	}

	@Transactional
	@Override
	public String updateEmployeeName(String name, Integer employeeId) {
		employeeRepository.updateEmployeeNameById(name, employeeId);
		return "Updated successfully";
	}

	@Override
	public String deleteEmployee(Integer employeeId) {
		Optional<Employee> response = employeeRepository.findById(employeeId);
		if (!response.isPresent()) {
			throw new EmployeeNotFoundException("Data is not found");
		}
		employeeRepository.delete(response.get());
		return "Deleted successfully";
	}

//	@Override
//	public String deleteEmployee(Integer employeeId) {
//		employeeRepository.deleteById(employeeId);
//		return "Deleted successfully";
//	}
}
