package com.employee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employee.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// List<Employee> findByName(String name);

//	Optional<Employee> findByLoginIdAndPassword(String login, String password);

	// @Query("select e from Employee e where e.name=:name")
	// List<Employee> getByName(String name);

	// @Query("select e from Employee e where e.name=?1")
	// List<Employee> getByName(String name);

	@Query(value = "select * from employee_table where employee_name=:name", nativeQuery = true)
	List<Employee> getByName(String name);

	// @Query("select e from Employee e where e.loginId=:login and
	// e.password=:password")
	// Optional<Employee> getByLoginIdAndPassword(String login, String password);

	// @Query("select e from Employee e where e.loginId=?1 and e.password=?2")
	// Optional<Employee> getByLoginIdAndPassword(String login, String password);

	@Query(value = "select * from employee_table where login_id=:login and password=:password", nativeQuery = true)
	Optional<Employee> getByLoginIdAndPassword(String login, String password);

	@Modifying
	@Query("update Employee e set e.name=:name where e.employeeId=:employeeId")
	void updateEmployeeNameById(String name, Integer employeeId);
}
