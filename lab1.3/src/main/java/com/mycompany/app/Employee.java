package com.mycompany.app;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Component;


@Component
public class Employee 
{
	
	private int employeeId;
	private String employeeName;
	private double salary;
	
	public int getEmployeeId() 
	{
		return employeeId;
	}
	public void setEmployeeId(int employeeId)
	{
		this.employeeId = employeeId;
	}
	
	public String getEmployeeName() 
	{
		return employeeName;
	}
	public void setEmployeeName(String employeeName)
	{
		this.employeeName = employeeName;
	}
	
	public double getSalary() 
	{
		return salary;
	}
	public void setSalary(double salary) 
	{
		this.salary = salary;
	}
	
	
	public void display() {
		System.out.println("Id :"+employeeId+" "+"Name:"+employeeName+" "+"Salary:"+salary);
	}
	
	
	
}


