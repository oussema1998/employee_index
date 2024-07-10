package Employee.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import entities.Employee;
import services.EmployeeService;

public class MainActivity extends AppCompatActivity {
    private ListView employeeListView;
    private EmployeeAdapter adapter;

    private EmployeeService employeeService;

    private Button Addbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        employeeListView = findViewById(R.id.employeeListView);
        Addbtn = findViewById(R.id.addbtn);
        Addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEmployeeActivity.class);
                startActivity(intent);
            }
        });

        employeeService = new EmployeeService();
        new GetAllEmployeesTask().execute();
    }

    private class GetAllEmployeesTask extends AsyncTask<Void, Void, List<Employee>> {
        @Override
        protected List<Employee> doInBackground(Void... voids) {
            return employeeService.getAll();
        }

        @Override
        protected void onPostExecute(List<Employee> employees) {
            if (employees != null) {
                adapter = new EmployeeAdapter(MainActivity.this, employees);
                employeeListView.setAdapter(adapter);
            } else {
                Toast.makeText(MainActivity.this, "Failed to fetch employees", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
