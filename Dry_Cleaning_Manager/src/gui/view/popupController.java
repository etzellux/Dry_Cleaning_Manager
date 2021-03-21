package gui.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class popupController {
    @FXML Label label = new Label();
    public String labelText = "";
    @FXML
    public void initialize()
    {
        label.setText(labelText);
    }

    public void closeWindow()
    {
        Stage stage = (Stage) label.getScene().getWindow();
        stage.close();
    }
}
