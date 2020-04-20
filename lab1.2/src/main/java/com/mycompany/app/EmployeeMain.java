
package com.mycompany.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class EmployeeMain {
	public static void main(String[] args) {
	 AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
	  //registering configuration class in which configurations are kept
	 Class configurationClass=JavaConfig.class;
     context.register(configurationClass);
     context.refresh();
     //context.refresh->container scans the bean class, get object and then inject dependency
     

     Employee employee = context.getBean(Employee.class);
     //fetching bean by type
     System.out.println("Employee Details");
     System.out.println("-------------------------------");
     employee.display();
     
     EmployeeMain em = new EmployeeMain();
     SBU sbu1=employee.getSbuDetails();
     em.printSBUDetails(sbu1);
	}
	
	void printSBUDetails(SBU sbu) {
		System.out.println("sbu details= SBU"+"["+"sbuCode="+sbu.getSbuId()+","+" "+"sbuHead="+sbu.getSbuHead()+","+" "+"sbuName="+sbu.getSbuName()+"]"+"]");
	}

}