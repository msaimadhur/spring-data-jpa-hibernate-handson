package com.cognizant.ormlearn.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "em_id")
	private int id;
	@Column(name = "em_name")
	private String name;
	@Column(name = "em_salary")
	private double salary;
	@Column(name = "em_permanent")
	private boolean permanent;
	@Column(name = "em_date_of_birth")
	private Date dateOfBirth;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "em_dp_id")
	private Department department;
	@ManyToMany()
	@JoinTable(name = "employee_skill", joinColumns = @JoinColumn(name = "es_em_id"), inverseJoinColumns = @JoinColumn(name = "es_sk_id"))
	private Set<Skill> skillList;

	public Set<Skill> getSkillList() {
		return skillList;
	}

	public void setSkillList(Set<Skill> skillList) {
		this.skillList = skillList;
	}

	public Employee(String name, double salary, boolean permanent, Date dateOfBirth) {
		super();
		this.name = name;
		this.salary = salary;
		this.permanent = permanent;
		this.dateOfBirth = dateOfBirth;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public boolean isPermanent() {
		return permanent;
	}

	public void setPermanent(boolean permanent) {
		this.permanent = permanent;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", permanent=" + permanent
				+ ", dateOfBirth=" + dateOfBirth + ", department=" + department + "]";
	}

	public Employee() {
		super();
	}

}
