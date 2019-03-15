package artatawe.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.IOException;

public class RegisterController {
    @FXML
    private TextField username;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField address;
    @FXML
    private TextField postcode;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label notification;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label phoneNumberLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label postcodeLabel;

    private boolean isVaild;

    @FXML
    private void registerAction(ActionEvent event) throws IOException {
        isVaild = true;
        if(username.getText() != null && !username.getText().isEmpty()){
            userNameLabel.setTextFill(Color.BLACK);
            /* TO DO , waiting for read & write */
        } else {
            isVaild = false;
            notification.setText("Please fill in all the information correctly !");
            userNameLabel.setTextFill(Color.RED);
        }
        if(firstName.getText() != null && !firstName.getText().isEmpty()){
            firstNameLabel.setTextFill(Color.BLACK);
            /* TO DO , waiting for read & write */
        } else {
            isVaild = false;
            notification.setText("Please fill in all the information correctly !");
            firstNameLabel.setTextFill(Color.RED);
        }
        if(lastName.getText() != null && !lastName.getText().isEmpty()){
            lastNameLabel.setTextFill(Color.BLACK);
            /* TO DO , waiting for read & write */
        } else {
            isVaild = false;
            notification.setText("Please fill in all the information correctly !");
            lastNameLabel.setTextFill(Color.RED);
        }

        if(phoneNumber.getText() != null && !phoneNumber.getText().isEmpty()){
            phoneNumberLabel.setTextFill(Color.BLACK);
            /* TO DO , waiting for read & write */
        } else {
            isVaild = false;
            notification.setText("Please fill in all the information correctly !");
            phoneNumberLabel.setTextFill(Color.RED);
        }
        if(address.getText() != null && !address.getText().isEmpty()){
            addressLabel.setTextFill(Color.BLACK);
            /* TO DO , waiting for read & write */
        } else {
            isVaild = false;
            notification.setText("Please fill in all the information correctly !");
            addressLabel.setTextFill(Color.RED);
        }
        if(postcode.getText() != null && !postcode.getText().isEmpty()){
            postcodeLabel.setTextFill(Color.BLACK);
            /* TO DO , waiting for read & write */
        } else {
            isVaild = false;
            notification.setText("Please fill in all the information correctly !");
            postcodeLabel.setTextFill(Color.RED);
        }

        // Changing stage after successfully registered
        if(isVaild) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/login.fxml"));
            rootPane.getChildren().setAll(pane);
        }
    }

    @FXML
    private void backAction(ActionEvent event) throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/login.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}
