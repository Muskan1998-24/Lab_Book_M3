
package com.mycompany.app;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SBUMain {
	public static void main(String[] args) {
	 AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
	  //registering configuration class in which configurations are kept
	 Class configurationClass=JavaConfig.class;
     context.register(configurationClass);
     context.refresh();
     //context.refresh->container scans the bean class, get object and then inject dependency
     

     SBU sbu = context.getBean(SBU.class);
     List<Employee>employees=sbu.getEmpList();
     //fetching bean by type
     System.out.println("SBU Details");
     System.out.println("-------------------------------");
     sbu.show();
     //sbu.display();
     
     SBUMain em = new SBUMain();
     SBU sbu1=sbu.getSbuDetails();
     em.printSBUDetails(sbu1);
	}
	
	void printSBUDetails(SBU sbu) {
		System.out.println("sbu details= SBU"+"["+"sbuCode="+sbu.getSbuCode()+","+" "+"sbuHead="+sbu.getSbuHead()+","+" "+"sbuName="+sbu.getSbuName()+"]"+"]");
	}

}