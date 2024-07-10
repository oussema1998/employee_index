package Employee.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
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

        es = new EmployeeService();
        int employeeId = getIntent().getIntExtra("employeeId", 35);

        employeeIdTextView.setText(String.valueOf(employeeId));

        new GetEmployeeTask().execute(employeeId);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ferme l'activité
                finish();
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
                // Si l'employé n'est pas trouvé, vous pouvez afficher un message d'erreur ou prendre une autre action
                Toast.makeText(DetailsEmployee.this, "Employee not found", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
