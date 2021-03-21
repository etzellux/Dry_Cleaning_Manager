package gui.view;

import gui.Controller.DataManager;
import gui.Controller.guiMain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;

public class MainPageController
{
    String userName;
    DataManager dm;
    @FXML TableView<ClothesData> wardrobeTable = new TableView<ClothesData>();
    @FXML TableColumn w_typeColumn = new TableColumn();
    @FXML TableColumn w_statusColumn = new TableColumn();

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

            ObservableList<ClothesData> data = FXCollections.observableArrayList();
            ResultSet wardrobeRS = dm.getData("clothes where status <> 'delivered' and activity='active'");

            while(wardrobeRS.next())
            {
                data.add(new ClothesData(wardrobeRS.getInt("id"),wardrobeRS.getString("type"),wardrobeRS.getString("bodySize"),
                        wardrobeRS.getString("status"),wardrobeRS.getString("colorType"),
                        wardrobeRS.getString("arrDate"),wardrobeRS.getString("activity")));
            }

            w_typeColumn.setCellValueFactory(new PropertyValueFactory("type"));
            w_statusColumn.setCellValueFactory(new PropertyValueFactory("status"));

            wardrobeTable.setItems(data);
        }
        catch(Exception ex)
        {

        }
    }

    @FXML
    private void goToClientsPage(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(guiMain.class.getResource("/gui/view/ClientsPage.fxml"));
        AnchorPane clientsPage = loader.load();

        Scene scene = new Scene(clientsPage);

        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
    }

    @FXML
    private void AddClient(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(guiMain.class.getResource("/gui/view/AddClient.fxml"));
        AnchorPane addClientPopup = loader.load();

        Scene scene = new Scene(addClientPopup);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Add New Client");
        stage.show();
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
        stage.setTitle("Add New Clothes");
        stage.show();
    }

    @FXML
    private void goToPaymentPage(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(guiMain.class.getResource("/gui/view/PaymentPage.fxml"));
        AnchorPane paymentPage = loader.load();
        PaymentPageController pController = loader.getController();
        pController.initData("admin");

        Scene scene = new Scene(paymentPage);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Payment");
        stage.show();
    }

    @FXML
    public void createPopup(String text) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(guiMain.class.getResource("/gui/view/popup.fxml"));
        AnchorPane popup = loader.load();
        popupController popupController = loader.getController();
        popupController.label.setText(text);

        Scene scene = new Scene(popup);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Error");
        stage.show();
    }
}
