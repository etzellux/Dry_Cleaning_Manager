package gui.view;

import gui.Controller.guiMain;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainpackage.Client.*;
import javafx.fxml.FXMLLoader;
import gui.Controller.DataManager;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ClientsPageController
{
    DataManager dm;
    ResultSet rs;

    @FXML TableView<ClientData> clientTable = new TableView<ClientData>();
    @FXML TableColumn nameColumn = new TableColumn();
    @FXML TableColumn statusColumn = new TableColumn();
    @FXML Label infoLabel = new Label();
    @FXML TableView<ClothesData> wardrobeTable = new TableView<ClothesData>();
    @FXML TableColumn w_typeColumn = new TableColumn();
    @FXML TableColumn w_bodySizeColumn = new TableColumn();
    @FXML TableColumn w_statusColumn = new TableColumn();
    @FXML TableColumn w_colorTypeColumn = new TableColumn();
    @FXML TableColumn w_arrDateColumn = new TableColumn();
    @FXML TableColumn w_activityColumn = new TableColumn();

    @FXML
    public void initialize()
    {
        try
        {
            dm = new DataManager();

            ObservableList<ClientData> data = FXCollections.observableArrayList();

            rs = dm.getData("clients");

            while(rs.next())
            {
                data.addAll(new ClientData(rs.getString("name"),rs.getString("activity")));
                //System.out.println(rs.getString("name") + rs.getString("activity"));
            }


            nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
            statusColumn.setCellValueFactory(new PropertyValueFactory("activity"));

            w_typeColumn.setCellValueFactory(new PropertyValueFactory("type"));
            w_bodySizeColumn.setCellValueFactory(new PropertyValueFactory("bodySize"));
            w_statusColumn.setCellValueFactory(new PropertyValueFactory("status"));
            w_colorTypeColumn.setCellValueFactory(new PropertyValueFactory("colorType"));
            w_arrDateColumn.setCellValueFactory(new PropertyValueFactory("arrDate"));
            w_activityColumn.setCellValueFactory(new PropertyValueFactory("activity"));

            clientTable.setItems(data);
            //clientTable.getColumns().addAll(nameColumn,statusColumn);
            clientTable.setEditable(true);
            nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

            wardrobeTable.setEditable(true);
            ObservableList<String> typeList = FXCollections.observableArrayList("jean","pant","dress","sweat","hoodie","t-shirt","shirt","suit","mont","portmanto");
            w_typeColumn.setCellFactory(ChoiceBoxTableCell.forTableColumn(typeList));
            ObservableList<String> bodySizeList = FXCollections.observableArrayList("XS","S","M","L","XL","XXL","XXXL");
            w_bodySizeColumn.setCellFactory(ChoiceBoxTableCell.forTableColumn(bodySizeList));
            ObservableList<String> statusList = FXCollections.observableArrayList("dirty","cleaned","cleaned-ironed","delivered");
            w_statusColumn.setCellFactory(ChoiceBoxTableCell.forTableColumn(statusList));
            ObservableList<String> colorTypeList = FXCollections.observableArrayList("white","black","colorful");
            w_colorTypeColumn.setCellFactory(ChoiceBoxTableCell.forTableColumn(colorTypeList));



        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    public void AddClient(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(guiMain.class.getResource("/gui/view/AddClient.fxml"));
        AnchorPane addClientPopup = loader.load();

        Scene scene = new Scene(addClientPopup);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void showClientInfo()
    {
        try
        {
            int ownerId = 0;
            String labelText = "";
            ClientData selectedClient = clientTable.getSelectionModel().getSelectedItem();
            ResultSet rs = dm.getData("clients where name like " + "'%" + selectedClient.getName() +"%'");
            if(rs.next())
            {
                ownerId = rs.getInt("id");
                labelText = "Name: " + rs.getString("name") + "\n"
                            +"Type: " + rs.getString("type") + "\n"
                            +"Signing Date: " + rs.getString("signingDate") + "\n"
                            + "Cost: " + rs.getInt("cost") + "\n"
                            + "Phone Number: " + rs.getString("phoneNumber");
            }
            infoLabel.setText(labelText);
            ObservableList<ClothesData> data = FXCollections.observableArrayList();
            ResultSet wardrobeRS = dm.getData("clothes where owner_id=" + ownerId);

            while(wardrobeRS.next())
            {
                data.addAll(new ClothesData(wardrobeRS.getInt("id"),wardrobeRS.getString("type"),wardrobeRS.getString("bodySize"),
                                            wardrobeRS.getString("status"),wardrobeRS.getString("colorType"),
                                            wardrobeRS.getString("arrDate"),wardrobeRS.getString("activity")));
            }

            wardrobeTable.setItems(data);

        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    public void updateNameCell(TableColumn.CellEditEvent edittedCell)
    {
        ClientData clientSelected = clientTable.getSelectionModel().getSelectedItem();
        String oldName = clientSelected.getName();
        clientSelected.setName(edittedCell.getNewValue().toString());
        try
        {
            dm.updateClientData("name='" + clientSelected.getName() + "' where name like '%" + oldName + "%'");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    public void updateTypeCell(TableColumn.CellEditEvent edittedCell)
    {
        ClothesData clothesSelected = wardrobeTable.getSelectionModel().getSelectedItem();
        clothesSelected.setType(edittedCell.getNewValue().toString());
        try
        {
            dm.updateClothesData("type='" + clothesSelected.getType() + "' where id=" + clothesSelected.getId());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    public void updateBodySizeCell(TableColumn.CellEditEvent edittedCell)
    {
        ClothesData clothesSelected = wardrobeTable.getSelectionModel().getSelectedItem();
        clothesSelected.setBodySize(edittedCell.getNewValue().toString());
        try
        {
            dm.updateClothesData("bodySize='" + clothesSelected.getBodySize() + "' where id=" + clothesSelected.getId());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    public void updateStatusCell(TableColumn.CellEditEvent edittedCell)
    {
        ClothesData clothesSelected = wardrobeTable.getSelectionModel().getSelectedItem();
        clothesSelected.setStatus(edittedCell.getNewValue().toString());
        try
        {
            dm.updateClothesData("status='" + clothesSelected.getStatus() + "' where id=" + clothesSelected.getId());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    public void updateColorTypeCell(TableColumn.CellEditEvent edittedCell)
    {
        ClothesData clothesSelected = wardrobeTable.getSelectionModel().getSelectedItem();
        clothesSelected.setColorType(edittedCell.getNewValue().toString());
        try
        {
            dm.updateClothesData("colorType='" + clothesSelected.getColorType() + "' where id=" + clothesSelected.getId());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    public void deleteClothes()
    {
        ClothesData clothesSelected = wardrobeTable.getSelectionModel().getSelectedItem();

        try
        {
            dm.deleteData(" clothes where id=" + clothesSelected.getId());
            showClientInfo();
        }
        catch(Exception ex)
        {

        }
    }
}
