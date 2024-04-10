package services;

import static android.content.ContentValues.TAG;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import Utils.Constants;

import entities.Employee;

public class EmployeeService {

    private static final String BASE_URL = "http://"+ Constants.MY_IP_ADRESS+":8085/employee/";

    public interface EmployeeCallback {
        void onSuccess(List<Employee> employees);
        void onError(String errorMessage);
    }

    public void getAll(EmployeeCallback callback) {
        new GetAllEmployeesTask(callback).execute();
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
