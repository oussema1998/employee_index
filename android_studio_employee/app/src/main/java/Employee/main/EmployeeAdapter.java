package Employee.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import entities.Employee;

public class EmployeeAdapter extends ArrayAdapter<Employee> {
    private Context context;
    private List<Employee> employees;

    public EmployeeAdapter(Context context, List<Employee> employees) {
        super(context, R.layout.employee_item, employees);
        this.context = context;
        this.employees = employees;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.employee_item, parent, false);
        }

        Employee employee = employees.get(position);

        TextView employeeId = convertView.findViewById(R.id.employeeId);
        TextView employeeName = convertView.findViewById(R.id.employeeName);
        TextView employeeEmail = convertView.findViewById(R.id.employeeEmail);
        Button detailsButton = convertView.findViewById(R.id.detailsButton);

        employeeId.setText("ID: " + employee.getId());
        employeeName.setText("Name: " + employee.getNom());
        employeeEmail.setText("Email: " + employee.getEmail());

        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsEmployee.class);
                intent.putExtra("employeeId", employee.getId());
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
