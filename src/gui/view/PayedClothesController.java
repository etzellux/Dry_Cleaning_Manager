package gui.view;

import gui.Controller.DataManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.ResultSet;

public class PayedClothesController
{
    public int owner_id = 0;
    @FXML TableView<ClothesData> wardrobeTable = new TableView<ClothesData>();
    @FXML TableColumn w_typeColumn = new TableColumn();
    @FXML TableColumn w_bodySizeColumn = new TableColumn();
    @FXML TableColumn w_statusColumn = new TableColumn();
    @FXML TableColumn w_colorTypeColumn = new TableColumn();
    @FXML TableColumn w_arrDateColumn = new TableColumn();
    @FXML Label label = new Label("");

    @FXML
    public void initialize()
    {
        try
        {
            DataManager dm = new DataManager();

            ObservableList<ClothesData> data = FXCollections.observableArrayList();

            ResultSet wardrobeRS = dm.getData("clothes where owner_id="+ owner_id + " and activity like '%active%' ");

            while(wardrobeRS.next())
            {
                data.addAll(new ClothesData(wardrobeRS.getInt("id"),wardrobeRS.getString("type"),wardrobeRS.getString("bodySize"),
                        wardrobeRS.getString("status"),wardrobeRS.getString("colorType"),
                        wardrobeRS.getString("arrDate"),wardrobeRS.getString("activity")));
            }
            w_typeColumn.setCellValueFactory(new PropertyValueFactory("type"));
            w_bodySizeColumn.setCellValueFactory(new PropertyValueFactory("bodySize"));
            w_statusColumn.setCellValueFactory(new PropertyValueFactory("status"));
            w_colorTypeColumn.setCellValueFactory(new PropertyValueFactory("colorType"));
            w_arrDateColumn.setCellValueFactory(new PropertyValueFactory("arrDate"));

            wardrobeTable.setItems(data);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    public void initData(int id,String text)
    {
        owner_id = id;
        label.setText(text);
    }

    @FXML
    public void closeWindow()
    {
        Stage stage = (Stage) wardrobeTable.getScene().getWindow();
        stage.close();
    }
}
