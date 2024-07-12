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

public class UpdateEmployeeActivity extends AppCompatActivity {

    private EditText nameField;
    private EditText emailField;
    private Button updateButton;
    private ImageButton retourButton;
    private Employee currentEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_employee);

        nameField = findViewById(R.id.nameField);
        emailField = findViewById(R.id.emailField);
        updateButton = findViewById(R.id.updateButton);
        retourButton = findViewById(R.id.retourBtn);

        retourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        int employeeId = getIntent().getIntExtra("employeeId", -1);
        if (employeeId != -1) {
            new FetchEmployeeTask().execute(employeeId);
        }

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateEmployee();
            }
        });
    }

    private void updateEmployee() {
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
            currentEmployee.setNom(name);
            currentEmployee.setEmail(email);
            UpdateEmployeeTask task = new UpdateEmployeeTask();
            task.execute(currentEmployee);
        }
    }

    private class FetchEmployeeTask extends AsyncTask<Integer, Void, Employee> {

        @Override
        protected Employee doInBackground(Integer... ids) {
            EmployeeService employeeService = new EmployeeService();
            return employeeService.getEmployeeById(ids[0]);
        }

        @Override
        protected void onPostExecute(Employee employee) {
            if (employee != null) {
                currentEmployee = employee;
                nameField.setText(employee.getNom());
                emailField.setText(employee.getEmail());
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateEmployeeActivity.this);
                builder.setTitle("Error");
                builder.setMessage("Failed to load employee details");
                builder.setPositiveButton("OK", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }

    private class UpdateEmployeeTask extends AsyncTask<Employee, Void, String> {

        @Override
        protected String doInBackground(Employee... employees) {
            EmployeeService employeeService = new EmployeeService();
            return employeeService.update(employees[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            if ("Success".equals(result)) {
                finish();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateEmployeeActivity.this);
                builder.setTitle("Error");
                builder.setMessage("Failed to update employee: " + result);
                builder.setPositiveButton("OK", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }
}
