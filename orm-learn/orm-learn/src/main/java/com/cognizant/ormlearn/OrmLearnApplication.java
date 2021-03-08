package com.cognizant.ormlearn;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.service.CountryService;
//import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@SpringBootApplication
public class OrmLearnApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

	private static CountryService countryService;
	private static EmployeeService employeeService;
	private static DepartmentService departmentService;
	private static SkillService skillService;

	private static void testGetAllCountries() {
		LOGGER.info("Start");
		List<Country> countries = countryService.getAllCountries();
		LOGGER.debug("countries={}", countries);
		LOGGER.info("End");
	}

	private static void getAllCountriesTest() {

		LOGGER.info("Start");

		Country country = null;
		try {
			country = countryService.findCountryByCode("IN");
		} catch (CountryNotFoundException e) {
			LOGGER.debug(e.getMessage());
		}

		LOGGER.debug("Country:{}", country);

		LOGGER.info("End");

	}

	private static void testAddCountry() {
		LOGGER.info("Start");

		Country country = new Country();
		country.setCode("ZE");
		country.setName("Zee");

		countryService.addCountry(country);
		try {
			countryService.findCountryByCode("ZE");
		} catch (CountryNotFoundException e) {
			LOGGER.debug(e.getMessage());
		}

		LOGGER.debug("Country:{}", country);

		LOGGER.info("End");

	}

	private static void updateCountryTest() {
		LOGGER.info("Start");

		try {
			countryService.updateCountry("ZE", "Zero");
		} catch (CountryNotFoundException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		LOGGER.info("End");

	}

	private static void deleteCountryTest() {
		LOGGER.info("Start");
		countryService.deleteCountry("ZE");
		LOGGER.info("End");

	}

	private static void testFindByNameContaining() {
		LOGGER.info("Start");

		List<Country> countries = countryService.findByNameContaining("ou");
		for (Country c : countries) {
			System.out.println(c.getCode() + " " + c.getName());
		}
		LOGGER.info("End");

	}

	private static void testFindByNameStartingWith() {
		LOGGER.info("Start");

		List<Country> countries = countryService.findByNameStartingWith("Z");
		for (Country c : countries) {
			System.out.println(c.getCode() + " " + c.getName());
		}
		LOGGER.info("End");

	}

	private static Date parseDate(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	private static void testAddEmployee() {
		LOGGER.info("start");
		Employee employee = new Employee("John", 25000.00, true, parseDate("1979-04-19"));
		Department department = new Department("Analyst Trainee");
		employee.setDepartment(department);
		employeeService.save(employee);
		LOGGER.info("end");
	}

	private static void testGetEmployee() {
		LOGGER.info("Start");
		Employee employee = employeeService.get(1);
		LOGGER.debug("Employee:{}", employee);
		LOGGER.debug("Department:{}", employee.getDepartment());
		LOGGER.debug("Skills:{}", employee.getSkillList());
		LOGGER.info("End");
	}

	private static void testUpdateEmployee() {
		LOGGER.info("start");
		int employeeId = 1;
		Employee employee = employeeService.get(employeeId);
		employee.setDepartment(new Department("Trainer"));
		employeeService.save(employee);
		LOGGER.debug("Employee={}", employee);
		LOGGER.debug("Department={}", employee.getDepartment());
		LOGGER.info("end");
	}

	private static void testGetDepartment() {
		LOGGER.info("Start");
		int departmentId = 2;
		Department department = departmentService.get(departmentId);
		LOGGER.debug("Department={}", department);
		LOGGER.debug("Employees={}", department.getEmployeeList());
		LOGGER.info("end");
	}

	private static void testAddSkillToEmployee() {
		LOGGER.info("start");
		int employeeId = 1;
		Employee employee = employeeService.get(employeeId);
		Set<Skill> skills = new HashSet<>();
		Skill skill = new Skill("JAVA");
		Skill skill2 = new Skill("MySQL");
		skills.add(skill);
		skills.add(skill2);
		skillService.save(skill);
		skillService.save(skill2);
		employee.setSkillList(skills);
		employeeService.save(employee);
		LOGGER.info("end");
	}

	public static void testGetAllPermanentEmployees() {
		LOGGER.info("Start");
		List<Employee> employees = employeeService.getAllPermanentEmployees();
		LOGGER.debug("Permanent Employees:{}", employees);
		employees.forEach(e -> LOGGER.debug("Skills:{}", e.getSkillList()));
		LOGGER.info("End");
	}

	public static void testGetAverageSalary() {
		LOGGER.info("Start");
		double averageSalary = employeeService.getAverageSalary();
		LOGGER.debug("Average ", averageSalary);
		LOGGER.info("End");
	}

	public static void testGetAverageSalaryByDepartmentId() {
		LOGGER.info("Start");
		double averageSalary = employeeService.getAverageSalary(1);
		LOGGER.debug("Average ", averageSalary);
		LOGGER.info("End");
	}

	public static void testGetAllEmployeesNative() {
		LOGGER.info("Start");
		List<Employee> employees = employeeService.getAllEmployeesNative();
		LOGGER.debug("Employees: {}", employees);
		LOGGER.info("End");
	}

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
		countryService = context.getBean(CountryService.class);
		employeeService = context.getBean(EmployeeService.class);
		departmentService = context.getBean(DepartmentService.class);
		skillService = context.getBean(SkillService.class);
		LOGGER.info("Inside main");
		// testGetAllCountries();
		// getAllCountriesTest();
		// testAddCountry();
		// updateCountryTest();
		// deleteCountryTest();
		// testFindByNameContaining();
		// testFindByNameStartingWith();
		// testAddEmployee();
		// testGetEmployee();
		// testUpdateEmployee();
		// testGetDepartment();
		// testAddSkillToEmployee();
		// testGetAllPermanentEmployees();
		// testGetAverageSalary();
		// testGetAverageSalaryByDepartmentId();
			testGetAllEmployeesNative();
	}

}
