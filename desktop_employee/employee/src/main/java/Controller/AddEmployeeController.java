package Controller;

import Entities.Employee;
import Services.ValidatingService;
import Services.EmployeeService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddEmployeeController {
    @FXML
    public TextField EmailField;
    @FXML
    public TextField NomField;

    private final EmployeeService es = new EmployeeService();


    private EmployeeListController ec = new EmployeeListController();

    //_________________________

public void SetIndexController (EmployeeListController ecc){this.ec = ecc;}

    @FXML
    public void initialize(){SetIndexController(ec);}



    private boolean isInputValid() {
        String errorMessage = "";

        if (NomField.getText().isEmpty() ) {
            errorMessage += "Nom field is empty!\n";
        }
        if (NomField.getLength()>20)
        {
            errorMessage += "Nom field length must be less than 20 !\n";
        }

        if(EmailField.getText().isEmpty()){errorMessage += "Email field is empty!\n";

        }else{
            if (!ValidatingService.isEmailValid(EmailField.getText())) {
                errorMessage += "Email is invalid!\n";
            }
        }




        if (errorMessage.isEmpty()) {
            return true;
        } else {
         showAlertDialog(Alert.AlertType.ERROR, "Error", "Failed to open new RendezVous window", errorMessage);
            return false;
        }
    }
    @FXML
    public void AddEmployee() {

        if(isInputValid()){
    String nom = NomField.getText();

    String email = EmailField.getText();
        Employee ee = new Employee(nom,email);
        System.out.println(ee+"\n");
        EmployeeService.addEmployee(ee);
        Stage stage = (Stage) NomField.getScene().getWindow();
        ec.refreshListEmployee();
        stage.close();
        }}

    private void showAlertDialog(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }



}
