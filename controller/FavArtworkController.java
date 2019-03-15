package artatawe.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class FavArtworkController {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private void homeAction(ActionEvent event) throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/panel.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void editProfileAction(ActionEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/editprofile.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void favUserAction(ActionEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/favuser.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}
