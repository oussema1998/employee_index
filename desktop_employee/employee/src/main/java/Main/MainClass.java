package Main;

import Services.EmployeeService;
import Entities.Employee;

import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        // Call the getAllEmployees method from EmployeeService
        List<Employee> employees = EmployeeService.getAllEmployees();

        // Display the list of employees in the terminal
        if (!employees.isEmpty()) {
            System.out.println("List of employees:");
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        } else {
            System.out.println("No employees found.");
        }
    }
}
