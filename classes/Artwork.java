package artatawe.classes;

import java.util.Date;

/**
 * Artwork
 * Sets information for Artwork on creation, stores the information and provides
 * it on request. An artwork is also its own auction, providing information on
 * the auction status. Artwork has two subclasses, Painting and Sculpture, which
 * provide specialisation for specific artwork types.
 * Created: 2017/11/27
 *
 * @author Nicholas Williams
 */
public class Artwork {
    protected static int currID = 0;
    protected int artworkID;
    protected String title;
    protected String description;
    protected String mainPhotoLocation;
    protected String artistName;
    protected String yearCreated;
    protected double reservePrice;
    protected Date timeListed;
    protected int bidsLeft;
    protected int totalBids;
    protected String sellerUsername;
    protected double width;
    protected double height;
    protected int highestBidID;
    protected boolean finished;


    /**
     * Constructor for artatawe.classes.Artwork Class
     * This constructor will be called when an artwork is read from a file
     *
     * @param artworkID         the id of the artwork
     * @param title             the title given to the artwork
     * @param description       the description given to the artwork
     * @param mainPhotoLocation the file location of the main photo for the artwork
     * @param artistName        the name of the artist
     * @param yearCreated       the year the artwork was created
     * @param reservePrice      the reserve price of the artwork
     * @param timeListed        the date and time the artwork was listed
     * @param sellerUsername    the profile for the seller of the artwork
     * @param totalBids         the amount of bids allowed on the artwork
     * @param width             the width of the product
     * @param height            the height of the product
     */
    protected Artwork(int artworkID, String title, String description,
                      String mainPhotoLocation, String artistName, String yearCreated,
                      int reservePrice, Date timeListed, String sellerUsername,
                      int totalBids, int width, int height) {
        this.artworkID = artworkID;
        this.title = title;
        this.description = description;
        this.mainPhotoLocation = mainPhotoLocation;
        this.artistName = artistName;
        this.yearCreated = yearCreated;
        this.reservePrice = reservePrice;
        this.timeListed = timeListed;
        this.totalBids = totalBids;
        this.sellerUsername = sellerUsername;
        this.width = width;
        this.height = height;
        this.bidsLeft = totalBids;
        this.finished = false;
    }


    /**
     * Overloaded constructor will be called when a new artwork is created
     * @param title             the title given to the artwork
     * @param description       the description given to the artwork
     * @param mainPhotoLocation the file location of the main photo for the artwork
     * @param artistName        the name of the artist
     * @param yearCreated       the year the artwork was created
     * @param reservePrice      the reserve price of the artwork
     * @param timeListed        the date and time the artwork was listed
     * @param sellerUsername    the profile for the seller of the artwork
     * @param totalBids         the amount of bids allowed on the artwork
     * @param width             the width of the product
     * @param height            the height of the product
     */
    protected Artwork(String title, String description, String mainPhotoLocation,
                      String artistName, String yearCreated, int reservePrice,
                      Date timeListed, String sellerUsername, int totalBids,
                      int width, int height) {
        this.title = title;
        this.description = description;
        this.mainPhotoLocation = mainPhotoLocation;
        this.artistName = artistName;
        this.yearCreated = yearCreated;
        this.reservePrice = reservePrice;
        this.timeListed = timeListed;
        this.totalBids = totalBids;
        this.sellerUsername = sellerUsername;
        this.width = width;
        this.height = height;
        this.bidsLeft = totalBids;
        this.finished = false;

        /* incrementing static variable indicating what the value if the next
        artworkID will be */
        currID++;
        this.artworkID = currID;
    }


    /**
     * Create a new empty Artwork
     */
    protected Artwork() {

    }


    /**
     * Get the artworks ID
     *
     * @return artworkID
     */
    public int getArtworkID() {
        return artworkID;
    }


    /**
     * Get the artworks title
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }


    /**
     * Get the total bids allowed on the artwork.
     *
     * @return the total bids.
     */
    public int getTotalBids() {
        return totalBids;
    }


    /**
     * Get the artworks description
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }


    /**
     * Get the artworks main photo location
     *
     * @return mainPhotoLocation
     */
    public String getMainPhotoLocation() {
        return mainPhotoLocation;
    }


    /**
     * Get the artists name
     *
     * @return artistName
     */
    public String getArtistName() {
        return artistName;
    }


    /**
     * Get the year the artwork was created
     *
     * @return yearCreated
     */
    public String getYearCreated() {
        return yearCreated;
    }


    /**
     * Get the auctions reserve price
     *
     * @return reservePrice
     */
    public double getReservePrice() {
        return reservePrice;
    }


    /**
     * Get the time elapsed since the artwork went on auction
     *
     * @return timeListed
     */
    public Date getTimeListed() {
        return timeListed;
    }


    /**
     * Get the sellers username
     *
     * @return seller username
     */
    public String getSellerUsername() {
        return sellerUsername;
    }


    /**
     * Get the width of the artwork piece
     *
     * @return width
     */
    public double getWidth() {
        return width;
    }


    /**
     * Get the height of the artwork piece
     *
     * @return height
     */
    public double getHeight() {
        return height;
    }


    /**
     * Get the current highest bid on the auction
     *
     * @return highest bid
     */
    public int getHighestBidID() {
        return highestBidID;
    }


    /**
     * Set the Bid ID of the highest bid.
     *
     * @param highestBidID to set.
     */
    public void setHighestBidID(int highestBidID) {
        this.highestBidID = highestBidID;
    }


    /**
     * Get the remaining bids left on the auction.
     *
     * @return bids left.
     */
    public int getBidsLeft() {
        return bidsLeft;
    }


    /**
     * Set the remaining bids on the auction.
     *
     * @param bidsLeft to set.
     */
    public void setBidsLeft(int bidsLeft) {
        this.bidsLeft = bidsLeft;
    }


    /**
     * If bid added, reduced number of bids left counter.
     */
    public void reduceBidsLeft() {
        if (this.bidsLeft > 1) {
            this.bidsLeft -= 1;
        } else {
            this.finished = true;
            this.bidsLeft -= 1;
        }
    }

    /**
     * Test if artwork auction finished.
     * @return true if it has, false otherwise.
     */
    public boolean isFinished(){return finished;}
}