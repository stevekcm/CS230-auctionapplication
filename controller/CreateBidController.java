package artatawe.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;


public class CreateBidController {
    @FXML
    private ChoiceBox<String> artType;
    @FXML
    private TextField title;
    @FXML
    private TextField description;
    @FXML
    private TextField artistName;
    @FXML
    private TextField yearCreated;
    @FXML
    private TextField reservePrice;
    @FXML
    private ImageView artworkPic;
    @FXML
    private Button uploadArtBtn;
    @FXML
    private Button submitBtn;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label titleLabel;
    @FXML
    private Label descrLabel;
    @FXML
    private Label artistNameLabel;
    @FXML
    private Label yearCreatedLable;
    @FXML
    private Label priceLabel;
    @FXML
    private Label notification;

    private boolean isVaild;

    @FXML
    public void homeAction(ActionEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/panel.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    public void uploadAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("IMAGE files (*.jpg)", "*.jpg");
        fileChooser.setTitle("Select Image to upload");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(null);
        /*TO DO, Write to imageview AND submitAction*/
    }

    @FXML
    public void submitAction(ActionEvent event) {
        isVaild = true;

        artType.getItems().addAll("Paintings" , "Sculptures");

        if(title.getText() != null && !title.getText().isEmpty()){
            titleLabel.setTextFill(Color.BLACK);
            /* TO DO , waiting for read & write */
        } else {
            isVaild = false;
            notification.setText("Please fill in all the information correctly !");
            titleLabel.setTextFill(Color.RED);
        }
        if(description.getText() != null && !description.getText().isEmpty()){
            descrLabel.setTextFill(Color.BLACK);
            /* TO DO , waiting for read & write */
        } else {
            isVaild = false;
            notification.setText("Please fill in all the information correctly !");
            descrLabel.setTextFill(Color.RED);
        }

        if(artistName.getText() != null && !artistName.getText().isEmpty()){
            artistNameLabel.setTextFill(Color.BLACK);
            /* TO DO , waiting for read & write */
        } else {
            isVaild = false;
            notification.setText("Please fill in all the information correctly !");
            artistNameLabel.setTextFill(Color.RED);
        }
        if(yearCreated.getText() != null && !yearCreated.getText().isEmpty()){
            yearCreatedLable.setTextFill(Color.BLACK);
            /* TO DO , waiting for read & write */
        } else {
            isVaild = false;
            notification.setText("Please fill in all the information correctly !");
            yearCreatedLable.setTextFill(Color.RED);
        }
        if(reservePrice.getText() != null && !reservePrice.getText().isEmpty()){
            priceLabel.setTextFill(Color.BLACK);
            /* TO DO , waiting for read & write */
        } else {
            isVaild = false;
            notification.setText("Please fill in all the information correctly !");
            priceLabel.setTextFill(Color.RED);
        }
        /*TO DO*/
        if(isVaild){

        }
    }
}
