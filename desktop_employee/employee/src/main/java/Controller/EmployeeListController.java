package Controller;

import Entities.Employee;
import Services.EmployeeService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class EmployeeListController {

    public Button detailsButton;
    @FXML
    private TableView<Employee> employeeTableView;

    @FXML
    private TableColumn<Employee, Integer> idColumn;

    @FXML
    private TableColumn<Employee, String> nameColumn;

    @FXML
    private TableColumn<Employee, String> emailColumn;

    private final EmployeeService employeeService = new EmployeeService();
    ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList();

    public void initialize() {
        // Fetch all employees from the service
        List<Employee> employees = employeeService.getAllEmployees();

        // Convert the list to an observable list
        employeeObservableList = FXCollections.observableArrayList(employees);

        // Bind the observable list to the TableView
        employeeTableView.setItems(employeeObservableList);

        // Bind the columns to the properties of the Employee class
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        detailsButton.setDisable(true);
        employeeTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            detailsButton.setDisable(newSelection == null);
        });
    }
@FXML
    private void handleDetailsButtonAction(ActionEvent event) {
        Employee selectedEmployee = employeeTableView.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Employee/details-employee.fxml"));
                Parent root = loader.load();

                DetailsEmployeeController controller = loader.getController();
                controller.setSelectedEmployee(selectedEmployee);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Employee Details");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }}

    @FXML
    public void GoAddEmploye(ActionEvent event)  {
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Employee/add-employee.fxml"));
        Parent root = loader.load();

        // Créer une nouvelle scène avec la racine chargée
        Scene scene = new Scene(root);

        // Créer un nouveau stage pour la scène
        Stage stage = new Stage();
        stage.setTitle("Ajouter un achat");
        stage.setScene(scene);
        AddEmployeeController controller = loader.getController();

        controller.SetIndexController(this);

        // Afficher la fenêtre d'ajout d'achat
        stage.showAndWait();}catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshListEmployee(){
        employeeObservableList.clear();
        employeeObservableList.addAll(employeeService.getAllEmployees());
    }

    public void refreshTableView(ActionEvent event) {
        refreshListEmployee();
    }


}
