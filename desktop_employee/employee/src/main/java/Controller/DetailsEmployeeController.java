package Controller;

import Services.EmployeeService;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import Entities.Employee;

public class DetailsEmployeeController {

    Employee e;
    @FXML
    public Text id;
    @FXML
    public Text email;
    @FXML
    public Text nom;

private final EmployeeService es = new EmployeeService();

public void initialize(){



}

    public void setSelectedEmployee(Employee e) {
        this.e = e;
        // Remplir les champs avec les valeurs de l'Achat sélectionné
        if (e != null) {
            id.setText(String.valueOf(e.getId()));
            email.setText(e.getEmail());
            nom.setText(e.getNom());
        }
    }



}
