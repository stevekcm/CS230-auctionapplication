package artatawe.controller;


import artatawe.classes.Browser;
import artatawe.classes.Profile;
import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Profile controller handles all user's information
 * The profile page is the stage that allow user to view the information between
 * other user or the user himself
 */
public class ProfileController {

    private Profile profile;
    @FXML
    private Label firstName;
    @FXML
    private Label lastName;
    @FXML
    private Label phoneNumber;
    @FXML
    private Label address;
    @FXML
    private Label postcode;
    @FXML
    private Button editProfileBtn;
    @FXML
    private Button logoutBtn;
    @FXML
    private Button homeBtn;
    @FXML
    private AnchorPane rootPane3;
    @FXML
    private ImageView smallAvatar;
    @FXML
    private ImageView mainAvatar;

    /**
     * When the logout button is clicked, take user back to log in page
     * @param event clicking the log out button
     * @throws Exception if event cannot be handle
     */
    @FXML
    private void logoutAction(ActionEvent event) throws Exception {
        /*Browser.saveAll();*/
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/login.fxml"));
        rootPane3.getChildren().setAll(pane);
    }

    /**
     * When the edit profile button is clicked, take user to edit profile page
     * @param event clicking the edit profile button
     * @throws Exception if event cannot be handle
     */
    @FXML
    private void editProfileAction(ActionEvent event) throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/editprofile.fxml"));
        rootPane3.getChildren().setAll(pane);
    }

    /**
     * Instance profile controller
     * @param profile the profile from Profile class
     */
    public void setProfiles(Profile profile) {
        this.profile = profile;
    }


    /**
     *  Setting Test profiles for testing
     */
    private void setTestProfiles() {
        Profile user = new Profile("abc", "Jackie", "Chan",
                "07447627890", "Under the bridge",
                "ERROR 404 NOT FOUND", "", new ArrayList<>());
        setProfiles(user);
    }

    /**
     *  Initialise the current user and then print the information
     */
    public void initialize() {
        /* Set the profile to the current user. */
        setProfiles(Browser.getCurrentUser());

        firstName.setText("First Name: " + profile.getFirstName());
        lastName.setText("Last Name: " + profile.getLastName());
        phoneNumber.setText("Phone Number: " + profile.getPhoneNumber());
        address.setText("Address: " + profile.getAddress());
        postcode.setText("Postcode: " + profile.getPostCode());

        //Instance Avatar
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
     * When the home button is clicked, take user back to the panel page
     * @param event clicking the home button
     * @throws Exception if event cannot be handled
     */
    @FXML
    private void homeAction(ActionEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/panel.fxml"));
        rootPane3.getChildren().setAll(pane);
    }


    /**
     * Apply new custom drawn image to the profile page.
     * @param fileLocation of new custom drawing.
     */
    public void applyDrawingImage(String fileLocation) {
        Image image = new Image(fileLocation);
        mainAvatar.setImage(image);
        smallAvatar.setImage(image);
    }
}
