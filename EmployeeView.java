package com.ideas2it.employee.view;

import java.sql.Date;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import com.ideas2it.employee.controller.EmployeeController;

/**
 * EmployeeView class represents the employee details
 * @author Dipesh
 * @created 23-02-2021
 */
public class EmployeeView {
    Scanner scanner = new Scanner(System.in);
    EmployeeController employeeController = new EmployeeController();

    /**
     * createEmployee takes input from user and sends the value 
     * to the EmployeeController
     */
    public void createEmployee() {
        		
        System.out.println("\nEnter Employee Name: ");
        String name = scanner.nextLine();
        System.out.println("\nEnter Employee Email: ");
        String email = scanner.nextLine();
        System.out.println("\nEnter Employee PhoneNumber: ");
        String phoneNumber = scanner.nextLine();
        System.out.println("\nEnter Employee Salary: ");
        long salary = Long.parseLong(scanner.nextLine());
        System.out.println("\nEnter Employee DateOfJoin (yyyy-mm-dd): ");
        Date dateOfJoin = Date.valueOf(scanner.nextLine());
        int id = employeeController.createEmployee(name, email, 
                phoneNumber, salary, dateOfJoin);
        if (0 != id) {
            System.out.println("\nCreation successfull\n");
            this.displayEmployee(id);
        }
    }

    /**
     * displayEmployee displays employee details that has been created 
     * @param employeeId Its an id an employee
     */
    public void displayEmployee(int employeeId) {
        List<Object> employee = 
                employeeController.displaySpecificEmployee(employeeId);
        System.out.println("\nEmployee Details:");
        System.out.print("\nId => " + employee.get(0) + "\nEmployeeName => "
                + employee.get(1) + "\nEmail => " +employee.get(2)
                + "\nPhoneNumber => ");
        System.out.print(employee.get(3) + "\nSalary => " + employee.get(4));
        System.out.println("\nDateOfJoin => " + employee.get(5));						

    }
	
    /**
     * displayAllEmployee calls printEmployeeDetails 
     * to print all employee details to the user 
     */
    public void displayAllEmployee() {
        System.out.println("\nDisplaying All Employee\n");
        List<Object> employeeList = employeeController.displayAllEmployee();
        int index = 0; 
        System.out.println(" Id  EmployeeName   Email   PhoneNumber  Salary"
                +"  DateOfJoin");  		
        int size = employeeList.size();
        while (index < size) {
            List<String> employeeDetails = (List<String>)(Object)employeeList.get(index);
            String[] employee = (employeeDetails.toString().replace("[","")
                    .replace("]","")).split(",");
            this.printEmployeeDetails(employee);
            index++;
        }
    }
	
    /**
     * printEmployeeDetails prints all employee details to the user 
     * @param employee It is an employeedetails string to print
     */
    public void printEmployeeDetails(String[] employee) {
        System.out.print(employee[0] +"    "+ employee[1]+"    "
                + employee[2] +"   " + employee[3] +"   ");
        System.out.println( employee[4] +"   "+ employee[5]);		
    }

    /**
     * displaySpecificEmployee displays a specific employee by taking
     * user input id and sends value to EmployeeController
     */
    public void displaySpecificEmployee() {
        System.out.println("\nEnter Employee Id to display employee details: ");
        int employeeId = Integer.parseInt(scanner.nextLine());
        List<Object> employee = 
                employeeController.displaySpecificEmployee(employeeId);
        if(null == employee) {
            System.out.println("\nNot a valid employee  ");         
        } else {			
            System.out.println("\nEmployee Details:\n ");
            System.out.print("\nId => " + employee.get(0) + "\nEmployeeName => "
                    + employee.get(1) + "\nEmail => " +employee.get(2)
                    + "\nPhoneNumber => ");
            System.out.print(employee.get(3) + "\nSalary => " + employee.get(4));
            System.out.println("\nDateOfJoin => " + employee.get(5));
        }		
    }		
	
    /**
     * updateEmployee takes a specific employee id from the user,
     * sends value to EmployeeController to update the value of employee
     */
    public void updateEmployee() {
        System.out.println("\nEnter Employee Id to update: ");
        int employeeId = Integer.parseInt(scanner.nextLine());
        System.out.println("\nEnter Employee's UpdatedName: ");
        String name = scanner.nextLine();
        System.out.println("\nEnter Employee's UpdatedEmail: ");
        String email = scanner.nextLine();
        System.out.println("\nEnter Employee's UpdatedPhoneNumber: ");
        String phoneNumber = scanner.nextLine();
        System.out.println("\nEnter Employee's UpdatedSalary: ");
        long salary = Long.parseLong(scanner.nextLine());
        System.out.println("\nEnter Employee's UpdatedDateOfJoin"
                + "(yyyy-mm-dd): ");
        Date dateOfJoin = Date.valueOf(scanner.nextLine());
        if (employeeController.updateEmployee(employeeId, name, email,		
                                              phoneNumber, salary,
                                              dateOfJoin)) {
            System.out.println("\n Update Successfull");
            this.displayEmployee(employeeId);
        } else {
            System.out.println("\n Update unSuccessfull");    
        }
    }
	
    /**
     * deleteEmployee takes a specific employee id from the user,
     * sends value to EmployeeController to delete that particular employee
     */
    public void deleteEmployee() {		
        System.out.print("\nEnter the employee id to delete: ");		
        int deleteId = Integer.parseInt(scanner.nextLine());
        if (employeeController.deleteEmployee(deleteId)) {
            System.out.println("\nDeletion successfull");
        } else {
            System.out.println("\nDeletion unsuccessfull");
        }
    }    

    /**
     * getOption takes a option values from the user,
     * and calls specific methods to that option
     */
    public void getOption() {
        int option ;
 
        String optionDetails = "\n<===========>\n\nEmployee Management\n"
                +"1.Create\n2.DisplayAll\n3.Display\n4.Update "
                +"\n5.Delete \n6.Exit\n";
		String valueOption = "Enter your option: " ; 
			
        do {
            // To loop infintely till the value is 6 and it exits 
            System.out.println(optionDetails);
            System.out.print(valueOption);
            option = Integer.parseInt(scanner.nextLine());
            switch (option) {
            case 1:          
                this.createEmployee();
                break;
				
            case 2:
                this.displayAllEmployee();
                break;
				
            case 3:
                this.displaySpecificEmployee();
                break;
				
            case 4:
                this.updateEmployee();
                break;

            case 5:
                this.deleteEmployee();
                break;
				
            case 6:
                // Exit case
                System.out.println("\nThank you");
                break;

            default:
                System.out.println("\nInvalid Input");
                break;
            }
        } while (6 != option);
    }		
}
