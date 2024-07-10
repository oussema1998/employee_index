package Employee.main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import entities.Employee;
import services.EmployeeService;
import services.ValidationService;

public class AddEmployeeActivity extends AppCompatActivity {

    private EditText nameField;
    private EditText emailField;
    private Button submitButton;
    private ImageButton zz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        nameField = findViewById(R.id.nameField);
        emailField = findViewById(R.id.emailField);
        submitButton = findViewById(R.id.button);
        zz = findViewById(R.id.retourBtn);

        zz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEmployee();
            }
        });
    }

    private void addEmployee() {
        String name = nameField.getText().toString().trim();
        String email = emailField.getText().toString().trim();
        String errorMessage = "";

        if (name.isEmpty()) {
            errorMessage += "Name is required \n";
        }
        if (name.length() > 20) {
            errorMessage += "Name is too long (20 max) \n";
        }
        if (email.isEmpty()) {
            errorMessage += "Email is required \n";
        } else if (!ValidationService.isEmailValid(email)) {
            errorMessage += "Email is invalid \n";
        }

        if (!errorMessage.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error");
            builder.setMessage(errorMessage);
            builder.setPositiveButton("OK", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            Employee newEmployee = new Employee(name, email);
            AddEmployeeTask task = new AddEmployeeTask();
            task.execute(newEmployee);
        }
    }

    private class AddEmployeeTask extends AsyncTask<Employee, Void, String> {

        @Override
        protected String doInBackground(Employee... employees) {
            EmployeeService employeeService = new EmployeeService();
            return employeeService.add(employees[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            if ("Success".equals(result)) {
                // Employee added successfully
                finish();
            } else {
                // Error adding employee
                AlertDialog.Builder builder = new AlertDialog.Builder(AddEmployeeActivity.this);
                builder.setTitle("Error");
                builder.setMessage("Failed to add employee: " + result);
                builder.setPositiveButton("OK", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }
}
