package artatawe.controller;

import artatawe.classes.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.control.ListView;


/**
 * Panel Controller handles all events from the main Panel graphical interface.
 * This Panel is the main stage for the program, providing the bridge between
 * different parts, namely logging in, viewing or editing profile, and viewing
 * artworks on auction.
 */
public class PanelController {
    Profile profile; // Current user
    @FXML
    private Button logout;
    @FXML
    private Button editProfileBtn;
    @FXML
    private Button profileBtn;
    @FXML
    private AnchorPane rootPane1;
    @FXML
    private ImageView smallAvatar;
    @FXML
    private Label title;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private ListView<String> listView;

    //@FXML
    //private ObservableList<String> artView;

    /**
     * When the log out button is clicked, take user back to log in page.
     * @param event clicking the log out button button.
     * @throws Exception if event cannot be handled.
     */
    @FXML
    private void logoutAction(ActionEvent event) throws Exception{
        Browser.saveAll();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/login.fxml"));
        rootPane1.getChildren().setAll(pane);
    }


    /**
     * When the edit profile button is clicked, take user to the edit profile page.
     * @param event clicking the edit profile button.
     * @throws Exception if event cannot be handled.
     */
    @FXML
    private void editAction(ActionEvent event) throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/editprofile.fxml"));
        rootPane1.getChildren().setAll(pane);
    }


    /**
     * Initialise the controller and perform default operations.
     */
    @FXML
    private void initialize() {
        /* Set the profile to the current user. */
        profile = Browser.getCurrentUser();

        /* Display the users avatar, or revert to default if cannot load. */
        String fileLocation = "";
        try {
            fileLocation = "artatawe/images/" + profile.getImageName();
            Image image = new Image(fileLocation);
            smallAvatar.setImage(image);
        } catch (Exception e) {
            System.out.println("Failed to read image: " + fileLocation);
            fileLocation = "artatawe/images/defaultAvatar.png";
            Image image = new Image(fileLocation);
            smallAvatar.setImage(image);
        }

        /* Set the font and centre the title "Artatawe" */
        title.setFont(Font.font("Constantia", 32));
        title.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(title, 0.0);
        AnchorPane.setRightAnchor(title, 0.0);
        title.setAlignment(Pos.CENTER);

        /* Set items for the choice box */
        choiceBox.getItems().add("All");
        choiceBox.getItems().add("Paintings");
        choiceBox.getItems().add("Sculptures");
        choiceBox.setValue("Paintings");
        choiceBox.setValue("All");

        //Let users to click on contents inside the favourite artwork table and show the page of the artwork being clicked on
        ChangeListener<String> auctionClicked = (observable, oldValue, newValue) -> {
            //nothing to do when nothing selected
            if (newValue == null) {
                return;
            }
            try {
                System.out.println(newValue);
                for (Artwork a : ArtworkBrowser.getArtworks()) {
                    if ((a.getTitle()+"\n"+a.getDescription()).equals(newValue)){
                        ArtworkBrowser.setCurrentArtwork(a);
                    }
                }
                artworkAction();

            } catch (Exception e) {
                e.printStackTrace();
            }

            Platform.runLater(() -> {
                listView.getSelectionModel().clearSelection();
            });
        };
        listView.getSelectionModel().selectedItemProperty().addListener(auctionClicked);
    }


    /**
     * When the edit profile button is clicked, take user to the edit profile page.
     * @param event clicking the edit profile button.
     * @throws Exception if event cannot be handled.
     */
    @FXML
    private void showArt(ActionEvent event) throws Exception {
        ObservableList<String> artView = listView.getItems();
        if (choiceBox.getValue().equals("All")) {
            artView.remove(0,artView.size());
            /* List through all artworks and get the titles of each */
            for (Artwork a : ArtworkBrowser.getArtworks()) {
                if (!a.isFinished()) {
                    artView.add(a.getTitle() + "\n" + a.getDescription());
                }
            }
            listView.setItems(artView);

        } else if (choiceBox.getValue().equals("Paintings")) {
            artView.remove(0,artView.size());
            for (Artwork a : ArtworkBrowser.getArtworks()) {
                if (a instanceof Painting) {
                    if (!a.isFinished()) {
                        artView.add(a.getTitle() + "\n" + a.getDescription());
                    }
                }
            }
            listView.setItems(artView);

        } else if (choiceBox.getValue().equals("Sculptures")) {
            artView.remove(0,artView.size());
            for (Artwork a : ArtworkBrowser.getArtworks()) {
                if (a instanceof Sculpture) {
                    if (!a.isFinished()) {
                        artView.add(a.getTitle() + "\n" + a.getDescription());
                    }
                }
            }
            listView.setItems(artView);
        }
    }
    



    /**
     * When the profile button is clicked, take user to the profile page.
     * @param event clicking the profile button.
     * @throws Exception if event cannot be handled.
     */
    @FXML
    private void profileAction(ActionEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/profile.fxml"));
        rootPane1.getChildren().setAll(pane);
    }


    /**
     * When the artwork button is clicked, take user to the artwork page.
     * @throws Exception if event cannot be handled.
     */
    @FXML
    public void artworkAction() throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/Artwork.fxml"));
        rootPane1.getChildren().setAll(pane);
    }
}
