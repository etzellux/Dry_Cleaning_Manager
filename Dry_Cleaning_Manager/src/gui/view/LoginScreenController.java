package gui.view;

import gui.Controller.DataManager;
import gui.Controller.guiMain;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;

public class LoginScreenController
{
    DataManager dm;

    @FXML TextField  userNameTF = new TextField();
    @FXML PasswordField passwordTF = new PasswordField();

    @FXML public void loginButtonPushed()
    {
        String userName = userNameTF.getText();
        String password = passwordTF.getText();
        try
        {
            dm = new DataManager();
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
}
