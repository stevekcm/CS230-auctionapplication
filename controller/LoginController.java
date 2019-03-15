package artatawe.controller;

import artatawe.classes.Browser;
import artatawe.classes.Profile;

import artatawe.classes.ProfileBrowser;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Login Controller handles all events of login, verify the username from the database
 *
 * @author Steve Kwok Chun Man
 */
public class LoginController {

    @FXML
    public AnchorPane rootPane;
    @FXML
    private ArrayList<Profile> profile;
    @FXML
    private Button loginBtn;
    @FXML
    private TextField username;
    @FXML
    private Label loginMessage;
    @FXML
    private Button registerBtn;
    @FXML
    private Label epic;

    /**
     * When the login button is clicked, verify the username and pass the instance of current user to ProfileBroswer
     * @param event clicking the login button
     * @throws Exception if event cannot be handled
     */
    @FXML
    private void loginAction(ActionEvent event) throws Exception {
        /* Test if the entered username matches a known profile within the system.*/
        String currUser = username.getText();
        if (ProfileBrowser.testUserID(currUser)) {
            /* Get the profile and set it as the current user, proceed to next screen, */
            Profile currProfile = ProfileBrowser.getUserByID(currUser);
            Browser.setCurrentUser(currProfile);
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/panel.fxml"));
            rootPane.getChildren().setAll(pane);
        }
        loginMessage.setText("Invalid Username.");
    }

    /**
     * Set the profile from the profilebroswer
     */
    private void setProfiles() {
        this.profile = ProfileBrowser.getProfiles();
    }

    /*
        * Initalise the profile
     */
    public void initialize() {
         setProfiles();
    }

    /*
        * When the register button is clicked, take user to the register page
     */
    @FXML
    private void registerAction(ActionEvent event) throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/register.fxml"));
        rootPane.getChildren().setAll(pane);
    }

}
