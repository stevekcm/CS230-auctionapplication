package artatawe.classes;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Bid Browser facilitates the filtering and fetching of bids. Various
 * methods include using ReadWrite to save and load bids from disk, getting
 * bids by favourite users, adding bids to the global list, etc.
 *
 * Created: 2017/11/27
 * @author Robert Hardy
 */
public class BidBrowser extends Browser {
    private static ArrayList<Bid> bids;


    /**
     * Read Bids from disk and populate the Bids list.
     * @throws ParseException if error making bids from line in text file.
     */
    public static void makeBids() throws ParseException {
        bids = ReadWrite.makeBids();
    }


    /**
     * Write bids to disk.
     */
    public static void saveBids() {
        ReadWrite.fileWriter(bids, ReadWrite.getBidsFile());
    }


    /**
     * Filter bids by a username. Get list of bids placed by a user.
     *
     * @param user the user to filter by.
     * @return the bids placed by that user, or empty list if none.
     */
    public static ArrayList<Bid> filterUsername(Profile user) {
        /* Get user name of profile and compare it against the username of all
         * bids placed. If match, then add to list and return it.*/
        ArrayList<Bid> bidsPlaced = new ArrayList<>();
        String username = user.getUsername();
        for (Bid b : bids) {
            String currentUsername = b.getUsername();
            if (currentUsername.equals(username)) {
                bidsPlaced.add(b);
            }
        }
        return bidsPlaced;
    }


    /**
     * Get a bid by a given bid ID.
     *
     * @param ID to search.
     * @return the bid, or empty bid if not found.
     */
    public static Bid getBidByID(int ID) {
        Bid bid = new Bid();
        for (Bid b : bids) {
            if (b.getID() == ID) {
                bid = b;
            }
        }
        return bid;
    }


    /**
     * Filter bids by artwork ID. Get list of bids placed on any artwork.
     *
     * @param artwork to filter by.
     * @return the bids placed on that artwork, or empty list if none.
     */
    public static ArrayList<Bid> filterArtwork(Artwork artwork) {
        /* Get the id of artwork, and compare it against the id of all bids
         * placed, if match, add to list and return it.*/
        ArrayList<Bid> artworkBids = new ArrayList<>();
        int artworkID = artwork.getArtworkID();
        for (Bid b : bids) {
            int currentID = b.getArtworkID();
            if (currentID == artworkID) {
                artworkBids.add(b);
            }
        }
        return artworkBids;
    }


    /**
     * Get the highest bid on a given artwork by artwork ID.
     *
     * @param artworkID to get the highest bid of.
     * @return the highest bid.
     */
    public static Double getHighestBid(int artworkID) {
        Double max = 0.0;
        // Get all bids on an artwork, compare each for the greatest value.
        for (Bid b : bids) {
            if (b.getArtworkID() == artworkID) {
                if (b.getValue() > max) {
                    max = b.getValue();
                }
            }
        }
        return max;
    }


    /**
     * Method to add bid, checks to ensure that the bid is valid
     *
     * @param bid to be added
     * @return 0, 1, 2, or 3, depending on error or if OK.
     */
    public static int addBid(Bid bid) {
        Artwork artwork = ArtworkBrowser.getByID(bid.getArtworkID());
        Double bidValue = bid.getValue();
        int bidsLeft = artwork.getBidsLeft();

        /* Check artwork still on auction */
        if (!artwork.isFinished()) {
            /* Check bid is higher than reserve */
            if (!(bidValue <= artwork.getReservePrice())) {
                /* Finally, check bid higher than current bid */
                if (bidValue > getHighestBid(artwork.getArtworkID())) {
                    /*Add the bid and update artwork parameters to reflect new values.*/
                    bids.add(bid);
                    artwork.setHighestBidID(bid.getID());
                    artwork.reduceBidsLeft();
                } else {
                    return 3; // "ERROR: You must bid higher than the current bid!"
                }
            } else {
                return 2; // "ERROR: You must bid higher than the reserve price!"
            }
        } else {
            return 1; // "ERROR: Auction has ended!"
        }

        return 0; // No problems.
    }


    /**
     * Convert every Bid to a string for writing to disk.
     *
     * @return array of all bids as strings.
     */
    public static ArrayList<String> toStringAll() {
        ArrayList<String> toStringAll = new ArrayList<>();
        for (Bid b : bids) {
            toStringAll.add(b.toString());
        }
        return toStringAll;
    }

}
