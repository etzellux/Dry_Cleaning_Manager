package gui.view;

import gui.Controller.DataManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.util.Date;

public class EmployeesPageController
{
    DataManager dm;
    @FXML ChoiceBox<String> positionCB = new ChoiceBox<>();
    @FXML TableView<EmployeeData> employeeTable = new TableView<EmployeeData>();
    @FXML TableColumn nameColumn = new TableColumn();
    @FXML TableColumn positionColumn = new TableColumn();
    @FXML TableColumn phoneNumberColumn = new TableColumn();
    @FXML TableColumn salaryColumn = new TableColumn();
    @FXML TableColumn hiringDateColumn = new TableColumn();

    @FXML TextField nameTF = new TextField();
    @FXML TextField phoneNumberTF = new TextField();
    @FXML TextField salaryTF = new TextField();
    @FXML ChoiceBox<String> positionCB2 = new ChoiceBox<>();

    @FXML
    public void initialize()
    {
        try
        {
            dm = new DataManager();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        positionCB.getItems().addAll("All","manager","worker","salesman");
        positionCB.getSelectionModel().select(0);
        positionCB2.getItems().addAll("manager","worker","salesman");
        positionCB2.getSelectionModel().select(0);

        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        positionColumn.setCellValueFactory(new PropertyValueFactory("position"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory("phoneNumber"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory("salary"));
        hiringDateColumn.setCellValueFactory(new PropertyValueFactory("hiringDate"));


    }

    @FXML
    public void showEmployees()
    {
        String positionSelected = positionCB.getValue();

        try
        {
            ObservableList<EmployeeData> data = FXCollections.observableArrayList();
            ResultSet rs;
            if(positionSelected.equals("All"))
            {
                rs = dm.getData("employees");
            }
            else
            {
                rs = dm.getData("employees where position='" + positionSelected + "'");
            }

            while(rs.next())
            {
                data.add(new EmployeeData(rs.getInt("id"),rs.getString("name"),rs.getString("position"),
                                        rs.getString("phoneNumber"),rs.getInt("salary"),
                                        rs.getString("hiringDate")));
            }
            employeeTable.setItems(data);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    public void AddNewEmployee()
    {
        String name = nameTF.getText();
        String phoneNumber = phoneNumberTF.getText();
        String position = positionCB2.getValue();
        int salary = Integer.parseInt(salaryTF.getText());
        Date hiringDate = new Date();

        try
        {
            String values = "('" + name + "','" + position + "','" + phoneNumber + "','" + salary + "','" + hiringDate.toString() + "')";
            dm.addEmployeesData(values);
        }
        catch(Exception ex)
        {

        }
    }

    @FXML
    public void deleteEmployee()
    {
        EmployeeData employeeSelected = employeeTable.getSelectionModel().getSelectedItem();

        try
        {
            dm.deleteData(" employees where id=" + employeeSelected.getId());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
