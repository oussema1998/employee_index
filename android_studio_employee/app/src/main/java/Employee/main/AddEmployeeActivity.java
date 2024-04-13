package Employee.main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import entities.Employee;
import services.EmployeeService;
import services.ValidationService;


public class AddEmployeeActivity extends AppCompatActivity {
    private EditText nameField;

    private  EditText emailField;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        nameField = findViewById(R.id.nameField);
        emailField = findViewById(R.id.emailField);
        submitButton = findViewById(R.id.button);



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
        String ErrorMessage="";
        if (name.isEmpty()){
            ErrorMessage+="Name is required \n";
        }
        if (name.length()>20){
            ErrorMessage+="Name is too long (20 max) \n";
        }
        if (email.isEmpty()){
            ErrorMessage+="Email is required \n";

        }else
        {   if(!ValidationService.isEmailValid(email)){
            ErrorMessage+="Email is invalid \n";
        }}

        if(!ErrorMessage.isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error");
            builder.setMessage(ErrorMessage);
            builder.setPositiveButton("OK", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }else{

            Employee newEmployee = new Employee(name, email);
            EmployeeService employeeService = new EmployeeService();
            employeeService.add(newEmployee, new EmployeeService.EmployeeCallback() {
                @Override
                public void onSuccess(List<Employee> employees) {
                    Toast.makeText(AddEmployeeActivity.this, "Employee added successfully!", Toast.LENGTH_LONG).show();
                    finish();
                }

                @Override
                public void onError(String errorMessage) {
                    Toast.makeText(AddEmployeeActivity.this, "Failed to add employee: " + errorMessage, Toast.LENGTH_LONG).show();
                }
            });
        }

    }
}