package gui.view;

import gui.Controller.DataManager;
import gui.Controller.guiMain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.sql.ResultSet;

public class LoginScreenController
{
    DataManager dm;

    @FXML TextField  userNameTF = new TextField();
    @FXML PasswordField passwordTF = new PasswordField();
    @FXML Circle circle = new Circle();

    @FXML
    public void initialize()
    {

        try
        {
            dm = new DataManager();
            databaseConnectionControl();

        }
        catch(Exception ex)
        {

        }
    }

    @FXML public void loginButtonPushed()
    {
        String userName = userNameTF.getText();
        String password = passwordTF.getText();
        try
        {

            ResultSet rs = dm.getData("users");

            boolean control = false;

            while(rs.next())
            {
                if(userName.equals(rs.getString("userName")) && password.equals(rs.getString("password")))
                {
                    control = true;
                }
            }
            if(control)
            {
                loadMainLayout(userName);
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Wrong username or password", ButtonType.OK);
                alert.showAndWait();
            }

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING, "No connection to the MySQL server.", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    public void loadMainLayout(String userName) throws IOException
    {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(guiMain.class.getResource("/gui/view/MainLayout.fxml"));
        BorderPane mainLayout = loader.load();
        MainLayoutController mainC = loader.getController();
        mainC.initData(userName);

        Scene scene = new Scene(mainLayout);
        Stage primaryStage = (Stage) userNameTF.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dry Cleaning Manager");
    }
    @FXML
    public void databaseConnectionControl() throws IOException
    {
        circle.setFill(Color.GREEN);
    }
}
