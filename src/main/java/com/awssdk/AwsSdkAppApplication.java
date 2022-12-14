package com.awssdk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AwsSdkAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsSdkAppApplication.class, args);
		
		System.out.println("************ The list of buckts ***************");
		
		ListOfBucket listOfBucket = new ListOfBucket();
		listOfBucket.getAllBucket();
				
		System.out.println("*********************List of user ***************************");
				
		ListOfUser listOfUser = new ListOfUser();
		listOfUser.getAllUser();
		
		System.out.println("\n********************** List of security groups ****************");
		
		ListOfSecurityGroup listOfSecurityGroup = new ListOfSecurityGroup();
		listOfSecurityGroup.getAllSecurityGroup();
		
		System.out.println("\n********************** Printing employee detail ****************");
		MyTest myTest = new MyTest();
		myTest.getEmployee();
		
	}
}
