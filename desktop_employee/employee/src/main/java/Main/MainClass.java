package Main;

import Services.EmployeeService;
import Entities.Employee;

import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        // Call the getAllEmployees method from EmployeeService
      EmployeeService es = new EmployeeService();

      Employee e = new Employee(34,"xxx","rata@gmail.Com");

      es.updateEmployee(e);
    }
}
