package net.springboot.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.springboot.employeemanagement.model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}