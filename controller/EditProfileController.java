package artatawe.controller;

import artatawe.classes.Browser;
import artatawe.classes.Profile;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import javafx.scene.image.ImageView;
import java.io.File;

/**
 * EditProfileController provides a stage for user to edit the user information included some features
 * such as upload, draw and built-in avatars .
 *
 * @author Steve Kwok Chun Man
 */
public class EditProfileController {
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
    private Button logoutBtn;
    @FXML
    private Button homeBtn;
    @FXML
    private Button submitBtn;
    @FXML
    private Button favArtworkBtn;
    @FXML
    private Button favUserBtn;
    @FXML
    private Button uploadAvaBtn;
    @FXML
    private Button paintAvaBtn;
    @FXML
    private AnchorPane rootPane2;
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
    @FXML
    private Label notification;
    @FXML
    private ImageView mainAvatar;
    @FXML
    private ImageView smallAvatar;

    private boolean isVaild;
    private Profile profile;

    /**
     * When the home button is clicked, take user back to the panel page
     * @param event clicking the home button
     * @throws Exception if event cannot be handled
     */
    @FXML
    private void homeAction(ActionEvent event) throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/panel.fxml"));
        rootPane2.getChildren().setAll(pane);
    }

    /**
     * When the submit button is clicked, verify the information and write in database
     * @param event clicking the submit button
     */
    @FXML
    private void summitAction(ActionEvent event) {
        isVaild = true;
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
        /*TO DO*/
        if(isVaild){

        }
    }

    /**
     * When the paint avatar button is clicked, take user to the paint page where user able to draw his own avatar
     * @param event Clicking the paint avatar button
     * @throws Exception if event cannot be handled
     */
    @FXML
    private void paintAction(ActionEvent event) throws Exception {
        rootPane2.setMaxSize(300, 400);
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/PaintWindow.fxml"));
        rootPane2.getChildren().setAll(pane);
    }

    /**
     * When the upload button is clicked, open a window for user to choose his own avatar and then write in to database
     * @param event Clicking the upload button
     */
    @FXML
    private void uploadAction(ActionEvent event) {
        // OPEN the window for uploading
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("IMAGE files (*.jpg)", "*.jpg");
        fileChooser.setTitle("Select Image to upload");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(null);
        /*TO DO, Write to profile*/
    }

    /**
     * Initialise the current user and get the avatar from the database
     */
    @FXML
    private void initialize() {
        /* Set the profile to current user */
        profile = Browser.getCurrentUser();

        // Instance Avatar, Somehow not working =**(
        //Image image = new Image("images/defaultAvatar.png");
        String fileLocation = "";
        try {
            fileLocation = "artatawe/images/" + profile.getImageName();
            Image image = new Image(fileLocation);
            mainAvatar.setImage(image);
            smallAvatar.setImage(image);
        } catch (Exception e) {
            System.out.println("Failed to read image: " + fileLocation);
            fileLocation = "artatawe/images/defaultAvatar.png";
            Image image = new Image(fileLocation);
            mainAvatar.setImage(image);
            smallAvatar.setImage(image);
        }
    }

    /**
     * When the favourite user button is clicked, take user to favourite user page
     * @param event Clicking the favourite user button
     * @throws Exception if event cannot be handled
     */
    @FXML
    private void favUserAction(ActionEvent event) throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/favuser.fxml"));
        rootPane2.getChildren().setAll(pane);
    }

    /**
     * When the favourite artwork button is clicked, take user to favourite artwork page
     * @param event Clicking the favourite artwork button
     * @throws Exception if event cannot be handled
     */
    @FXML
    private void favArtworkAction(ActionEvent event) throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/favartwork.fxml"));
        rootPane2.getChildren().setAll(pane);
    }

    /**
     * When the built-in avatar button is clicked, pop out a window to let user choose the favourite avatar
     * @param event Clicking the built-in avatar button
     * @throws Exception if event cannot be handled
     */
    @FXML
    private void builtInAction(ActionEvent event) throws Exception {
        //Stage primaryStage = new Stage();
        Parent parent = FXMLLoader.load(getClass().getResource("../gui/BuiltInAvatar.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Built-In Avatar");
        stage.show();
    }

    /**
     * When the remove avatar button is clicked, remove the current avatar
     * @param event Clicking the remove button
     * @throws Exception if event cannot be handled
     */
    @FXML
    private void removeAction(ActionEvent event) throws Exception {
        mainAvatar.setImage(null);
        smallAvatar.setImage(null);

    }
}
