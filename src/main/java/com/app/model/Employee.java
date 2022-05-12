package com.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class Employee {

	@Id
	private Integer id;
	private String name;
	private int age;
	public Employee() {
		super();
	}
	public Employee(Integer id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	public void printName() {
		System.out.println("In emp class : "+name);
	}
	
	@Override
	public int hashCode() {
		final int prime=31;
		int result=1;
		result=prime*result+((name==null)?0:name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if(this==obj)
			return false;
		if(obj==null)
			return false;
		if(getClass()!=obj.getClass())
			return false;
		Employee other=(Employee)obj;
		if(name==null) {
			if(other.name!=null)
				return false;
		}else if(!name.equals(other.name))
			return false;
		return true;
	}
}
