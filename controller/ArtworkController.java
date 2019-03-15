package artatawe.controller;

import artatawe.classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.util.ArrayList;

/**
 * Created: 2017/18/27
 * The GUI controller for artwork, displays artwork and differentiates between
 * sculptures and paintings.
 *
 * @author Nicholas Williams
 */
public class ArtworkController {

    Profile currUser; // Profile of logged in user.
    private Artwork artwork;
    @FXML
    private Label title;
    @FXML
    private Label description;
    @FXML
    private Label mainPhotoLocation;
    @FXML
    private Label artistName;
    @FXML
    private Label yearCreated;
    @FXML
    private Label reservePrice;
    @FXML
    private Label timeListed;
    @FXML
    private Label totalBids;
    @FXML
    private Label sellerUsername;
    @FXML
    private Label width;
    @FXML
    private Label height;
    @FXML
    private Label depth;
    @FXML
    private Label mainMaterial;
    @FXML
    private Label additionalPhotos;
    @FXML
    private ImageView art;
    @FXML
    private AnchorPane rootPane3;
    @FXML
    private Button homeBtn;
    @FXML
    private Button bidButton;
    @FXML
    private TextField newBid;
    @FXML
    private Label bidFailed;
    @FXML
    private Button nextImage;
    @FXML
    private int pictureBeingShown;
    @FXML
    private Button markAsFavorite;
    @FXML
    private Boolean isFavoriteUser;
    @FXML
    private Label currBid;

    /**
     * Method to initalize the controller.
     */
    public void initialize() {
        currUser = Browser.getCurrentUser();

        //Instance Painting
        this.artwork = ArtworkBrowser.getCurrentArtwork();
        /*method checks if the artwork being shown is one of the users favorites
         and sets isFavoriteUser appropriately*/
        for (String a : Browser.getCurrentUser().getFavourites()) {
            if (artwork.getSellerUsername().equals(a)) {
                isFavoriteUser = true;
            } else {
                isFavoriteUser = false;
            }
        }
        double currBidValue = BidBrowser.getHighestBid(artwork.getArtworkID());
        currBid.setText("Current bid: " + currBidValue);

        title.setText("Title: " + artwork.getTitle());
        description.setText("Description: " + artwork.getDescription());
        artistName.setText("Artist Name: " + artwork.getArtistName());
        yearCreated.setText("Year Created: " + artwork.getYearCreated());
        reservePrice.setText("Reserve Price £" + artwork.getReservePrice());
        sellerUsername.setText("Being sold by " + artwork.getSellerUsername());
        width.setText("Width: " + artwork.getWidth());
        height.setText("Height: " + artwork.getHeight());
        timeListed.setText("Listed at " + artwork.getTimeListed());
        Image image = new Image(artwork.getMainPhotoLocation());
        art.setImage(image);
        /*if the user is a favorite user attempts to add them to the users
         favorite user list*/
        if (isFavoriteUser = true) {
            Browser.getCurrentUser().addFavourite(ProfileBrowser.getUserByID(
                    artwork.getSellerUsername()));
        }
        //if statement to seperate for specific features for Sculpture
        if (artwork instanceof Sculpture) {
            Sculpture sculpture = (Sculpture) artwork;
            depth.setText("Depth: " + sculpture.getDepth());
            mainMaterial.setText("Main Material: " + sculpture.getMainMaterial());
            nextImage.setVisible(true);
            /*shows the next button which is only relevant for Sculptures
            if statement which changes image each time the nextImage button
            is pressed.*/
            if (pictureBeingShown > 0 && pictureBeingShown <
                    sculpture.getAdditionalPhotos().size() + 1) {
                Image newImage = new Image(nextImageLocation(sculpture,
                        (pictureBeingShown - 1)));
                art.setImage(newImage);
            } else {
                art.setImage(image);
                pictureBeingShown = 0;
            }
        }
    }


    /**
     * Action button to go back to the homepage
     *
     * @param event
     * @throws Exception
     */
    @FXML
    private void homeAction(ActionEvent event) throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource(
                "../gui/panel.fxml"));
        rootPane3.getChildren().setAll(pane);
    }


    /**
     * Method for the nextImage button which adds 1 to the picture being shown
     * and then re initializes the class.
     *
     * @param event
     */
    @FXML
    private void nextImage(ActionEvent event) {
        pictureBeingShown++;
        initialize();
    }


    /**
     * When button is clicked sets isFavoriteUser to true
     *
     * @param event
     */
    @FXML
    private void addFavorite(ActionEvent event) {
        isFavoriteUser = true;
        initialize();
    }


    /**
     * Place bid Button
     * Access the bid the user is trying to place from the text box then attempts
     * to place that bid, displaying
     * different error messages as needed.
     *
     * @param event place bid button clicked.
     */
    @FXML
    private void placeBid(ActionEvent event) {
        String bob;
        bob = newBid.getText();
        if (!(newBid.getText().isEmpty())) {
            Double result = Double.parseDouble(bob);
            /*Bid newbid = new Bid("Donger", null, 23, result);//to be replaced
            with reference to actual user*/
            Bid newbid = new Bid(currUser.getUsername(), null,
                    artwork.getArtworkID(), result);
            int condition = BidBrowser.addBid(newbid);
            if (condition == 1) {
                bidFailed.setText("The Auction is already over");
            } else if (condition == 2) {
                bidFailed.setText("Bid is lower than the reserve price");
            } else if (condition == 3) {
                bidFailed.setText("Bid is lower than the current top bid");
                /*also need one for if the current user is the already the top
                bidder.*/
            }
        } else {
            bidFailed.setText("Enter a bid");
        }
        /* Update bid label */
        double currBidValue = BidBrowser.getHighestBid(artwork.getArtworkID());
        currBid.setText("Current bid: " + currBidValue);
    }


    /**
     * Method to get the next pictures location
     *
     * @param sculpture         currently being viewed.
     * @param pictureBeingShown the picture current on display.
     * @return String for the next images location
     */
    private String nextImageLocation(Sculpture sculpture, int pictureBeingShown) {
        return sculpture.getAdditionalPhotos().get(pictureBeingShown);
    }


}
