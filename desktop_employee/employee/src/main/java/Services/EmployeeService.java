package Services;

import Entities.Employee;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


public class EmployeeService {

    private static final String BASE_URL = "http://localhost:8085/employee/";

    public static List<Employee> getAllEmployees() {
        HttpClient client = HttpClient.newHttpClient();
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
}