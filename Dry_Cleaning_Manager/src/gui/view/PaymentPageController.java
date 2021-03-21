package gui.view;

import gui.Controller.DataManager;
import gui.Controller.GenerateFromData;
import gui.Controller.guiMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainpackage.Client;
import mainpackage.ClientManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class PaymentPageController
{
    public String userName;
    GenerateFromData generator;
    ClientManager clientManager = new ClientManager();
    DataManager dm;
    ResultSet rs;

    @FXML TableView<ClientData> clientTable = new TableView<ClientData>();
    @FXML TableColumn nameColumn = new TableColumn();
    @FXML TableColumn costColumn = new TableColumn();


    @FXML
    public void initData(String userName)
    {
        this.userName = userName;
    }

    @FXML
    public void initialize()
    {
        try
        {
            dm = new DataManager();
            generator = new GenerateFromData(clientManager);

            ObservableList<ClientData> data = FXCollections.observableArrayList();

            rs = dm.getData("clients");

            while(rs.next())
            {
                ClientData cData = new ClientData(rs.getString("name"),rs.getString("activity"),rs.getInt("cost"));
                cData.setId(rs.getInt("id"));
                Client clt = generator.generateClient(rs.getInt("id"),rs.getString("name"),
                                        rs.getString("type"),rs.getString("phoneNumber"));
                cData.setCost(clt.getCost());
                data.addAll(cData);
            }

            nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
            costColumn.setCellValueFactory(new PropertyValueFactory("cost"));

            clientTable.setItems(data);
        }
        catch(SQLException ex)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "An error occurred during initialization.", ButtonType.OK);
            alert.showAndWait();

            ex.printStackTrace();
        }
    }

    @FXML
    public void closeTheBill()
    {
        ClientData cData = clientTable.getSelectionModel().getSelectedItem();
        createPopup(cData.getId(),cData.getName() +"'s cost has been reseted.");

        try
        {
            dm.updateClientData("cost=0 where name like '%" + cData.getName() + "%'");

            dm.updateClothesData("status='delivered', activity='passive' where owner_id=" + cData.getId() + " and activity like '%active%'");

            Date date = new Date();
            String values = "('" + userName + "','" + cData.getName() + "','" + cData.getCost() + "','" + date.toString() + "')";
            dm.addPaymentData(values);
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING, "An error occurred during payment.", ButtonType.OK);
            alert.showAndWait();
        }
        initialize();
    }

    @FXML
    private void AddClothes(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(guiMain.class.getResource("/gui/view/AddClothes.fxml"));
        AnchorPane addClothesPopup = loader.load();

        Scene scene = new Scene(addClothesPopup);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void createPopup(int id,String text)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(guiMain.class.getResource("/gui/view/PayedClothes.fxml"));
            AnchorPane popup = loader.load();
            PayedClothesController popupController = loader.getController();
            popupController.initData(id,text);
            popupController.initialize();

            Scene scene = new Scene(popup);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Payed Clothes");
            stage.show();
        }
        catch(Exception ex)
        {

        }
    }
}
