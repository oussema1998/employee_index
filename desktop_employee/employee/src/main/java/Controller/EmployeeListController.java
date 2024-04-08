package Controller;

import Entities.Employee;
import Services.EmployeeService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class EmployeeListController {

    @FXML
    private TableView<Employee> employeeTableView;

    @FXML
    private TableColumn<Employee, Integer> idColumn;

    @FXML
    private TableColumn<Employee, String> nameColumn;

    @FXML
    private TableColumn<Employee, String> emailColumn;

    private final EmployeeService employeeService = new EmployeeService();

    public void initialize() {
        // Fetch all employees from the service
        List<Employee> employees = employeeService.getAllEmployees();

        // Convert the list to an observable list
        ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList(employees);

        // Bind the observable list to the TableView
        employeeTableView.setItems(employeeObservableList);

        // Bind the columns to the properties of the Employee class
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
    }
}
