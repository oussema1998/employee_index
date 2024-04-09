package Employee.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import entities.Employee;
import services.EmployeeService;

public class MainActivity extends AppCompatActivity  implements EmployeeService.EmployeeCallback{ private ListView employeeListView;
    private ArrayAdapter<String> adapter;

    private EmployeeService employeeService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        employeeListView = findViewById(R.id.employeeListView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        employeeListView.setAdapter(adapter);

        // Initialize EmployeeService
        employeeService = new EmployeeService();
        // Fetch the list of employees
        employeeService.getAll(this);
    }

    public void onSuccess(List<Employee> employees) {
        for (Employee employee : employees) {
            adapter.add("ID: " + employee.getId());
            adapter.add("Nom: " + employee.getNom()); // Ajouter les noms des employés à l'adaptateur
            adapter.add("Email: " + employee.getEmail());
            adapter.add("");
        }
    }


    public void onError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}