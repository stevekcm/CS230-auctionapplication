package artatawe.classes;
import java.util.Date;

/**
 * Painting
 * Stores attributes specific to Painting
 * Created: 2017/11/27
 * @author Nicholas Williams
 */
public class Painting extends Artwork {
    /**
     * Constructor for Painting
     * Overloaded constructor takes artworkID as parameter
     * Used for instantiation of paintings read from disk
     * @param artworkID         the id of the painting
     * @param title             the title given to the painting
     * @param description       the description given to the painting
     * @param artistName        the name of the artist
     * @param yearCreated       the year the painting was created
     * @param reservePrice      the reserve price of the painting
     * @param timeListed        the date and time the painting was listed
     * @param seller            the profile for the seller of the painting
     * @param totalBids         the maximum amount of bids on a painting
     * @param width             the width of the painting
     * @param height            the height of the painting
     * @param mainPhotoLocation the location of the main photo image file.
     */
    public Painting(int artworkID, String title, String description, String mainPhotoLocation,
                    String artistName, String yearCreated, int reservePrice,
                    Date timeListed, String seller,int totalBids, int width, int height) {
        super(artworkID, title, description, mainPhotoLocation, artistName, yearCreated,
                reservePrice, timeListed, seller, totalBids, width, height);
    }


    /**
     * /**
     * Overloaded constructor doesn't take artworkID as a parameter
     * Used for instantiation of new paintings
     *
     * @param title                  the title given to the painting
     * @param description            the description given to the painting
     * @param mainPhotoLocation      the location where the main photo is stored
     * @param artistName             the name of the artist
     * @param yearCreated            the year the painting was created
     * @param reservePrice           the reserve price of the painting
     * @param timeListed             the date and time the painting was listed
     * @param seller                 the profile for the seller of the painting
     * @param totalBids              the maximum amount of bids on a painting
     * @param width                  the width of the painting
     * @param height                 the height of the painting
     */
    public Painting(String title, String description, String mainPhotoLocation,
                    String artistName, String yearCreated, int reservePrice,
                    Date timeListed, String seller,int totalBids, int width, int height) {
        super(title, description, mainPhotoLocation, artistName, yearCreated,
                reservePrice, timeListed, seller, totalBids, width, height);
    }


    /**
     * Covert painting to a string suitable for writing to disk.
     * @return painting as a string.
     *
     */

    public String toString() {
        return this.artworkID + "," +
                this.title + "," +
                this.description + "," +
                this.artistName + "," +
                this.yearCreated + "," +
                this.reservePrice + "," +
                this.timeListed + "," +
                this.sellerUsername + "," +
                this.totalBids + "," +
                this.width + "," +
                this.height + "," +
                this.mainPhotoLocation;
    }
}
