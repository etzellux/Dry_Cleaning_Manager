package gui.view;

import gui.Controller.guiMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Stack;

public class MainLayoutController
{
    public String userName;
    public Stack<String> addressHistory = new Stack<String>();
    @FXML BorderPane mainLayout = new BorderPane();


    @FXML
    public void initData(String userName)
    {
        this.userName = userName;
    }

    @FXML
    private void loadClientsPage(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(guiMain.class.getResource("/gui/view/ClientsPage.fxml"));
        AnchorPane clientsPage = loader.load();

        mainLayout.setCenter(clientsPage);
        addressHistory.push("ClientsPage.fxml");
    }

    @FXML
    public void loadMainPage() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(guiMain.class.getResource("/gui/view/MainPage.fxml"));
        AnchorPane mainPage = loader.load();
        MainPageController mainC = loader.getController();
        mainC.initData(userName);

        mainLayout.setCenter(mainPage);

        addressHistory.push("MainPage.fxml");
    }

    @FXML
    public void loadPaymentHistoryPage() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(guiMain.class.getResource("/gui/view/PaymentHistoryPage.fxml"));
        AnchorPane mainPage = loader.load();

        mainLayout.setCenter(mainPage);

        addressHistory.push("PaymentHistoryPage.fxml");
    }

    @FXML
    public void loadEmployeesPage() throws IOException
    {
        if(userName.equals("admin"))
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(guiMain.class.getResource("/gui/view/EmployeesPage.fxml"));
            AnchorPane employeesPage = loader.load();

            mainLayout.setCenter(employeesPage);

            addressHistory.push("EmployeesPage.fxml");
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Administrator permission needed.", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    public void initialize() throws IOException
    {
        loadMainPage();
    }

    public void goBack() throws IOException
    {
        System.out.println(addressHistory);
        if(addressHistory.size() != 0)
        {
            FXMLLoader loader = new FXMLLoader();
            if(addressHistory.size() > 1)
            {
                addressHistory.pop();
            }
            loader.setLocation(guiMain.class.getResource("/gui/view/" + addressHistory.pop()));
            AnchorPane backPage = loader.load();

            mainLayout.setCenter(backPage);
        }
    }
}