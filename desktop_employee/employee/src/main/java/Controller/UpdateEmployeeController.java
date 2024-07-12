package Controller;

import Entities.Employee;
import Services.EmployeeService;
import Services.ValidatingService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateEmployeeController {
    @FXML
    public TextField EmailField;
    @FXML
    public TextField NomField;

    private Employee selectedEmployee;
    private final EmployeeService es = new EmployeeService();

    private EmployeeListController ec = new EmployeeListController();

    public void setSelectedEmployee(Employee employee) {
        this.selectedEmployee = employee;
        NomField.setText(employee.getNom());
        EmailField.setText(employee.getEmail());
    }

    public void setEmployeeListController(EmployeeListController ec) {
        this.ec = ec;
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (NomField.getText().isEmpty() ) {
            errorMessage += "Nom field is empty!\n";
        }
        if (NomField.getLength() > 20) {
            errorMessage += "Nom field length must be less than 20 !\n";
        }

        if (EmailField.getText().isEmpty()) {
            errorMessage += "Email field is empty!\n";
        } else {
            if (!ValidatingService.isEmailValid(EmailField.getText())) {
                errorMessage += "Email is invalid!\n";
            }
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            showAlertDialog(Alert.AlertType.ERROR, "Error", "Validation Error", errorMessage);
            return false;
        }
    }

    @FXML
    public void updateEmployee() {
        if (isInputValid()) {
            selectedEmployee.setNom(NomField.getText());
            selectedEmployee.setEmail(EmailField.getText());
            EmployeeService.updateEmployee(selectedEmployee);
            Stage stage = (Stage) NomField.getScene().getWindow();
            ec.refreshListEmployee();
            stage.close();
        }
    }

    private void showAlertDialog(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
