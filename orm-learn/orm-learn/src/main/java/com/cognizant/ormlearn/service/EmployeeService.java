package com.cognizant.ormlearn.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.repository.EmployeeRepository;

import ch.qos.logback.classic.Logger;

@Service
public class EmployeeService {
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@Transactional
	public Employee get(int id) {
		LOGGER.info("Start");
		return employeeRepository.findById(id).get();

	}

	@Transactional
	public void save(Employee employee) {
		LOGGER.info("Start");
		employeeRepository.save(employee);
		LOGGER.info("End");
	}
	
	@Transactional
	public List<Employee> getAllPermanentEmployees(){
		return employeeRepository.getAllPermanentEmployees();
	}
	
	@Transactional
	public double getAverageSalary() {
		return employeeRepository.getAverageSalary();
	}
	
	public double getAverageSalary(int id) {
		return employeeRepository.getAverageSalary(id);
	}
	
	@Transactional
	public List<Employee> getAllEmployeesNative()
	{
		return employeeRepository.getAllEmployeesNative();
	}
}
