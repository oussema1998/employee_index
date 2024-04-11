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
}