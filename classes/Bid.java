package artatawe.classes;

import java.util.Date;

/**
 * Bid defines a single bid placed on an artwork auction. It holds vital information
 * for linking a bid to the user who placed it, the artwork it was placed on,
 * and the value of the bid itself.
 *
 * Created 2017/12/01
 * @author Joel Osborne
 **/
public class Bid {
    private static int currID = 0; // For incrementing new Bid ID's
    private String username;
    private Date bidDate;
    private int artworkID;
    private Double value;
    private int bidID;


    /**
     * Create a new bid.
     *
     * @param username  the ProfileController linked to the bid
     * @param bidDate   the date the bid was made
     * @param artworkID the artwork the bid is being made on
     * @param value     the value of the bid
     **/
    public Bid(String username, Date bidDate, int artworkID, Double value) {
        this.username = username;
        this.bidDate = bidDate;
        this.artworkID = artworkID;
        this.value = value;

        // Increment every new bid with a new ID.
        currID++;
        this.bidID = currID;
    }


    /**
     * Overload constructor used for reading from file and generating new instances.
     *
     * @param bidID the unique identifier for the bid
     * @param username of user placing bid
     * @param bidDate time bid placed.
     * @param artworkID of artwork bid placed on
     * @param value of bid (money).
     */
    public Bid(int bidID, String username, Date bidDate, int artworkID, Double value) {
        this.bidID = bidID;
        this.username = username;
        this.bidDate = bidDate;
        this.artworkID = artworkID;
        this.value = value;
    }


    /**
     * Create a new empty bid.
     */
    public Bid() {
    }


    /**
     * Get the date the bid was placed
     *
     * @return the date.
     */
    public Date getDate() {
        return bidDate;
    }


    /**
     * Get the ID of the artwork the bid is placed on.
     *
     * @return the artworkID
     */
    public int getArtworkID() {
        return artworkID;
    }


    /**
     * Get the value of the bid
     *
     * @return the bids value.
     */
    public Double getValue() {
        return value;
    }


    /**
     * Get the username of the person who placed the bid.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }


    /**
     * Get the ID of the Bid.
     *
     * @return the ID.
     */
    public int getID() {
        return bidID;
    }


    /**
     * Convert a Bid to a string suitable for writing to disk.
     *
     * @return Bid as a string.
     */
    public String toString() {
        /* Format the bidDate to a string in ReadWrites accepted format. */
        String bidDateFormatted = ReadWrite.getFormat().format(bidDate);
        return this.bidID + "," +
                this.username + "," +
                bidDateFormatted + "," +
                this.artworkID + "," +
                this.value;
    }
}