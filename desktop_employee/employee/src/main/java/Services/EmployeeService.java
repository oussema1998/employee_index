package Services;

import Entities.Employee;
import com.google.gson.Gson;

import java.io.Console;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


public class EmployeeService {

    private static final String BASE_URL = "http://localhost:8085/employee/";
    private static HttpClient client = HttpClient.newHttpClient();
    public static List<Employee> getAllEmployees() {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "getAll"))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                // Convert JSON response to list of Employee objects
                Gson gson = new Gson();
                Employee[] employeeArray = gson.fromJson(response.body(), Employee[].class);
                List<Employee> employeeList = new ArrayList<>(Arrays.asList(employeeArray));
                return employeeList;
            } else {
                System.out.println("Error: " + response.statusCode() + " " + response.body());
                return new ArrayList<>(); // Return an empty list in case of error
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Return an empty list in case of exception
        }
    }

    public static void addEmployee(Employee e) {
        Gson gson = new Gson();
        String requestBody = gson.toJson(e);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "add"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static Employee getEmployeeById(int id) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "get/" + id))
                .GET()  // Explicitly use GET request
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                Gson gson = new Gson();
                return gson.fromJson(response.body(), Employee.class);
            } else {
                System.out.println("Failed to get employee: " + response.statusCode() + " " + response.body());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error when trying to get employee by ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static void deleteEmployeeById(int id) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "delete/" + id))
                .DELETE()  // Utilisation explicite de la méthode DELETE
                .build();

        try {
            HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
            if (response.statusCode() == 200) {
                System.out.println("Employee deleted successfully");
            } else {
                System.out.println("Failed to delete employee: " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("Error when trying to delete employee by ID: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void updateEmployee(Employee employee) {
        Gson gson = new Gson();
        String requestBody = gson.toJson(employee);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "update"))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                System.out.println("Employee updated successfully");
            } else {
                System.out.println("Failed to update employee: " + response.statusCode() + " " + response.body());
            }
        } catch (Exception e) {
            System.out.println("Error when trying to update employee: " + e.getMessage());
            e.printStackTrace();
        }
    }



}