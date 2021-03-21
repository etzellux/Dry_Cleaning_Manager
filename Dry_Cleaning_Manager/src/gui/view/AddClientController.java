package gui.view;

import gui.Controller.DataManager;
import gui.Controller.guiMain;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainpackage.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class AddClientController
{
    DataManager dm;
    @FXML TextField nameTF = new TextField();
    @FXML TextField phoneNumberTF = new TextField();
    @FXML ChoiceBox<String> typeCB = new ChoiceBox<>();

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
            Alert alert = new Alert(Alert.AlertType.WARNING, "Database connection failed.", ButtonType.OK);
            alert.showAndWait();
        }
        typeCB.getItems().addAll("individual","corporate");
        typeCB.getSelectionModel().select(0);
    }

    public void addButtonPushed(ActionEvent event)
    {
        String name = nameTF.getText();
        String phoneNumber = phoneNumberTF.getText();
        String type = typeCB.getValue();

        Client client = new Client(name,type,phoneNumber);
        client.setActivity(1);

        String values = "('" + client.getName() + "','" + client.getType()
                        + "','" + client.getSigningDate() + "','" + client.getCost()
                        + "','" + client.getActivity() + "','" + client.getPhoneNumber()
                        + "','" + client.getClothesNum() + "')";
        try
        {
            dm.addClientData(values);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING, "An error occurred while adding a new client", ButtonType.OK);
            alert.showAndWait();
        }
        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        primaryStage.close();
    }


}
