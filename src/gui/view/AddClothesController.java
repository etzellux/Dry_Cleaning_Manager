package gui.view;

import gui.Controller.guiMain;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainpackage.Clothes;
import gui.Controller.DataManager;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddClothesController
{
    DataManager dm;
    @FXML ChoiceBox<String> ownerCB = new ChoiceBox<>();
    @FXML ChoiceBox<String> typeCB = new ChoiceBox<>();
    @FXML ChoiceBox<String> bodySizeCB = new ChoiceBox<>();
    @FXML ChoiceBox<String> statusCB = new ChoiceBox<>();
    @FXML ChoiceBox<String> colorTypeCB = new ChoiceBox<>();

    @FXML
    public void initialize()
    {
        try
        {
            dm = new DataManager();

            ResultSet rs = dm.getData("clients");

            while(rs.next())
            {
                ownerCB.getItems().addAll(rs.getString("name"));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            createPopup("An error occurred during initialization. \nPlease try again later.");
        }
        ownerCB.getSelectionModel().select(0);
        typeCB.getItems().addAll("jean","pant","dress","sweat","hoodie","t-shirt","shirt","suit","mont","portmanto");
        typeCB.getSelectionModel().select(0);
        bodySizeCB.getItems().addAll("XS","S","M","L","XL","XXL","XXXL");
        bodySizeCB.getSelectionModel().select(0);
        statusCB.getItems().addAll("dirty","cleaned","cleaned-ironed","delivered");
        statusCB.getSelectionModel().select(0);
        colorTypeCB.getItems().addAll("white","black","colorful");
        colorTypeCB.getSelectionModel().select(0);


    }
    public void addNewClothes()
    {
        ResultSet owner;
        int ownerId = 0;
        try
        {
            owner = dm.getData("clients where name like" + "'%" + ownerCB.getValue() + "%'");
            if(owner.next())
            {
                ownerId = owner.getInt("id");
            }
            Clothes clt = new Clothes(typeCB.getValue(),bodySizeCB.getValue(),statusCB.getValue(),colorTypeCB.getValue());

            String values = "('" + ownerId + "','" + clt.getType()
                    + "','" + clt.getBodySize() + "','" + clt.getStatus()
                    + "','" + clt.getColorType() + "','" + clt.getArrDate()
                    + "','" + clt.getActivity() + "')";

            dm.addClothesData(values);
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            createPopup("An error occurred while adding a clothes. \n Please try again.");
        }
    }

    @FXML
    public void createPopup(String text)
    {
        try
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
        catch(Exception ex)
        {

        }
    }
}
