package services;

import static android.content.ContentValues.TAG;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import Utils.Constants;
import entities.Employee;

public class EmployeeService {

    private static final String BASE_URL = "http://" + Constants.MY_IP_ADDRESS + ":8085/employee/";

    public List<Employee> getAll() {
        try {
            URL url = new URL(BASE_URL + "getAll");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            Gson gson = new Gson();
            Employee[] employees = gson.fromJson(response.toString(), Employee[].class);
            return Arrays.asList(employees);
        } catch (Exception e) {
            Log.e("EmployeeService", "Error fetching employees", e);
            Log.e(TAG, "Error fetching employees", e);
            return null;
        }
    }

    public String add(Employee employee) {
        try {
            URL url = new URL(BASE_URL + "add");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            Gson gson = new Gson();
            String jsonInputString = gson.toJson(employee);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return "Success";
            } else {
                return "Error: " + responseCode;
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public Employee getEmployeeById(int id) {
        try {
            URL url = new URL(BASE_URL + "get/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            Gson gson = new Gson();
            return gson.fromJson(response.toString(), Employee.class);
        } catch (Exception e) {
            Log.e("EmployeeService", "Error fetching employee by ID", e);
            return null;
        }
    }

    public boolean deleteEmployeeById(int id) {
        try {
            URL url = new URL(BASE_URL + "delete/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return true;
            } else {
                Log.e("EmployeeService", "Failed to delete employee. Response code: " + responseCode);
                return false;
            }
        } catch (Exception e) {
            Log.e("EmployeeService", "Error deleting employee by ID", e);
            return false;
        }
    }



}
