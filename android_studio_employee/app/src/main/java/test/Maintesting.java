package test;

import entities.Employee;
import services.EmployeeService;

public class Maintesting {
    public static void main(String[] args) {
Employee ex = new Employee("vvvv","vvvv@gmail.com");
        EmployeeService es = new EmployeeService();
        es.add(ex);





    }
}
