package gui.view;

import gui.Controller.DataManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;

public class PaymentHistoryPageController
{
    DataManager dm;
    @FXML TableView<PaymentData> paymentTable = new TableView<PaymentData>();
    @FXML TableColumn employeeNameColumn = new TableColumn();
    @FXML TableColumn accountNameColumn = new TableColumn();
    @FXML TableColumn costColumn = new TableColumn();
    @FXML TableColumn dateColumn = new TableColumn();

    @FXML
    public void initialize()
    {
        ObservableList<PaymentData> data = FXCollections.observableArrayList();
        try
        {
            dm = new DataManager();

            ResultSet rs = dm.getData("payments");
            while(rs.next())
            {
                data.add(new PaymentData(rs.getString("employeeName"), rs.getString("accountName"),
                        rs.getInt("cost"), rs.getString("date")));
            }

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        employeeNameColumn.setCellValueFactory(new PropertyValueFactory("employeeName"));
        accountNameColumn.setCellValueFactory(new PropertyValueFactory("accountName"));
        costColumn.setCellValueFactory(new PropertyValueFactory("cost"));
        dateColumn.setCellValueFactory(new PropertyValueFactory("date"));

        paymentTable.setItems(data);
    }
}
