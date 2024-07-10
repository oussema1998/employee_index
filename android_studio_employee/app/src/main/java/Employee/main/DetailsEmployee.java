package Employee.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import entities.Employee;
import services.EmployeeService;

public class DetailsEmployee extends AppCompatActivity {

    private ImageButton back;
    private EmployeeService es;

    private TextView employeeIdTextView, employeeNameTextView, employeeEmailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_employee);

        this.back = findViewById(R.id.backBtn);
        employeeIdTextView = findViewById(R.id.idd);
        employeeNameTextView = findViewById(R.id.nom);
        employeeEmailTextView = findViewById(R.id.email);
        Button deleteButton = findViewById(R.id.deletebtn);

        es = new EmployeeService();
        int employeeId = getIntent().getIntExtra("employeeId", 35);

        employeeIdTextView.setText(String.valueOf(employeeId));

        new GetEmployeeTask().execute(employeeId);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DeleteEmployeeTask().execute(employeeId);
            }
        });
    }

    private class GetEmployeeTask extends AsyncTask<Integer, Void, Employee> {

        @Override
        protected Employee doInBackground(Integer... integers) {
            int employeeId = integers[0];
            return es.getEmployeeById(employeeId);
        }

        @Override
        protected void onPostExecute(Employee employee) {
            if (employee != null) {
                employeeNameTextView.setText(employee.getNom());
                employeeEmailTextView.setText(employee.getEmail());
            } else {
                Toast.makeText(DetailsEmployee.this, "Employee not found", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class DeleteEmployeeTask extends AsyncTask<Integer, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Integer... integers) {
            int employeeId = integers[0];
            return es.deleteEmployeeById(employeeId);
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                Toast.makeText(DetailsEmployee.this, "Employee deleted", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(DetailsEmployee.this, "Failed to delete employee", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
