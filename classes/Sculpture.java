package artatawe.classes;
import java.util.ArrayList;
import java.util.Date;

/**
 * Sculpture
 * Stores attributes specific to sculptures
 * Created: 2017/11/27
 * @author Nicholas Williams
 */
public class Sculpture extends Artwork {
    private int depth;
    private String mainMaterial;
    private ArrayList<String> additionalPhotos;

    /**
     * Constructor for Painting
     * Overloaded constructor takes artworkID as parameter
     * Used for instantiation of sculptures read from disk
     * @param artworkID             the id of the sculpture
     * @param title                 the title given to the sculpture
     * @param description           the description given to the sculpture
     * @param mainPhotoLocation     the location of the main photo
     * @param artistName            the name of the artist
     * @param yearCreated           the year the sculpture was created
     * @param reservePrice          the reserve price of the sculpture
     * @param timeListed            the date and time the sculpture was listed
     * @param seller                the profile for the seller of the sculpture
     * @param totalBids             the maximum amount of bids on a sculpture
     * @param width                 the width of the sculpture
     * @param height                the height of the sculpture
     * @param depth                 the depth of the sculpture
     * @param mainMaterial          the main material of the sculpture
     * @param additionalPhotos      any additional photos of the sculpture
     */
    public Sculpture(int artworkID, String title, String description,
                     String artistName, String yearCreated, int reservePrice,
                     Date timeListed, String seller ,int totalBids, int width, int height,
                     int depth,String mainMaterial, String mainPhotoLocation,ArrayList<String> additionalPhotos) {
        super(artworkID, title, description, mainPhotoLocation, artistName,
                yearCreated, reservePrice, timeListed, seller, totalBids, width, height);
        this.depth = depth;
        this.mainMaterial = mainMaterial;
        this.additionalPhotos = additionalPhotos;
    }


    /**
     * Overloaded constructor doesn't take artworkID as parameter
     * used for instantiation of sculptures read from disk
     *
     * @param title                 the title given to the sculpture
     * @param description           the description given to the sculpture
     * @param mainPhotoLocation     the location of the main photo
     * @param artistName            the name of the artist
     * @param yearCreated           the year the sculpture was created
     * @param reservePrice          the reserve price of the sculpture
     * @param timeListed            the date and time the sculpture was listed
     * @param seller                the profile for the seller of the sculpture
     * @param totalBids             the maximum amount of bids on a sculpture
     * @param width                 the width of the sculpture
     * @param height                the height of the sculpture
     * @param depth                 the depth of the sculpture
     * @param mainMaterial          the main material of the sculpture
     * @param additionalPhotos      any additional photos of the sculpture
     */
    public Sculpture(String title, String description,
                     String artistName, String yearCreated, int reservePrice,
                     Date timeListed, String seller ,int totalBids, int width, int height,
                     int depth,String mainMaterial, String mainPhotoLocation,ArrayList<String> additionalPhotos) {
        super(title, description, mainPhotoLocation, artistName,
                yearCreated, reservePrice, timeListed, seller, totalBids, width, height);
        this.depth = depth;
        this.mainMaterial = mainMaterial;
        this.additionalPhotos = additionalPhotos;
    }


    /**
     * getter for depth
     * @return depth
     */
    public int getDepth() {
        return depth;
    }


    /**
     * getter for mainMaterial
     * @return mainMaterial
     */
    public String getMainMaterial() {
        return mainMaterial;
    }


    /**
     * getter for additionalPhotos
     * @return additionalPhotos
     */
    public ArrayList<String> getAdditionalPhotos() {
        return additionalPhotos;
    }


    /**
     * Convert sculpture to a string suitable for writing to disk.
     * @return sculpture as a string.
     */
    public String toString() {
        /* Use string builder for loop concatenation.*/
        StringBuilder toString = new StringBuilder(
                this.artworkID + "," +
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
                        this.depth + "," +
                        this.mainMaterial + "," +
                        this.mainPhotoLocation + ",");
        for (String addPhotos : this.additionalPhotos) {
            toString.append(addPhotos);
            toString.append("\\");
        }
        return toString.toString();
    }
}


