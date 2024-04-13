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
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import Utils.Constants;

import entities.Employee;

public class EmployeeService {

    private static final String BASE_URL = "http://"+ Constants.MY_IP_ADDRESS +":8085/employee/";

    public interface EmployeeCallback {
        void onSuccess(List<Employee> employees);
        void onError(String errorMessage);
    }

    public void getAll(EmployeeCallback callback) {
        new GetAllEmployeesTask(callback).execute();
    }
    public void add(Employee employee, EmployeeCallback callback) {
        new AsyncTask<Employee, Void, String>() {
            @Override
            protected String doInBackground(Employee... employees) {
                try {
                    URL url = new URL(BASE_URL + "add");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setDoOutput(true);

                    Gson gson = new Gson();
                    String jsonInputString = gson.toJson(employees[0]);
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

            @Override
            protected void onPostExecute(String result) {
                if ("Success".equals(result)) {
                    callback.onSuccess(null); // Adapt as necessary
                } else {
                    callback.onError(result);
                }
            }
        }.execute(employee);
    }



    private static class GetAllEmployeesTask extends AsyncTask<Void, Void, List<Employee>> {
        private final EmployeeCallback callback;

        public GetAllEmployeesTask(EmployeeCallback callback) {
            this.callback = callback;
        }

        @Override
        protected List<Employee> doInBackground(Void... voids) {
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

        @Override
        protected void onPostExecute(List<Employee> employees) {
            if (employees != null) {
                callback.onSuccess(employees);
            } else {
                callback.onError("Failed to fetch employees");
            }
        }
    }


}
