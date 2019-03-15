package artatawe.classes;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Artwork Browser facilitates the filtering and fetching of artworks. Various
 * methods include using ReadWrite to save and load artworks from disk, getting
 * artworks by favourite users, adding artworks to the global list, etc.
 *
 * Created: 2017/11/27
 * @author Robert Hardy
 */
public class ArtworkBrowser extends Browser {
    private static ArrayList<Painting> paintings;
    private static ArrayList<Sculpture> sculptures;
    private static ArrayList<Artwork> artworks;
    protected static Artwork currentArtwork;


    /**
     * Read Paintings and Sculptures from disk and populate the lists.
     * @throws ParseException if error reading line of textfile during creation.
     */
    public static void makeArtworks() throws ParseException {
        paintings = ReadWrite.makePaintings();
        sculptures = ReadWrite.makeSculptures();
        artworks = mergeArtworks();
    }


    /**
     * Save all artworks to disk
     */
    public static void saveArtworks() {
        ReadWrite.fileWriter(paintings, ReadWrite.getPaintingsFile());
        ReadWrite.fileWriter(sculptures, ReadWrite.getSculpturesFile());
    }


    /**
     * Combine paintings and sculptures into a single List.
     *
     * @return the list.
     */
    private static ArrayList<Artwork> mergeArtworks() {
        /* Create an empty list, add all paintings and sculptures to it, then
         * sort it chronologically and return. */
        ArrayList<Artwork> merge = new ArrayList<>();
        merge.addAll(paintings);
        merge.addAll(sculptures);
        return sortChronological(merge);
    }


    /**
     * Add a given artwork to the global artwork lists.
     *
     * @param artwork to add.
     */
    public static void addArtwork(Artwork artwork) {
        /* Check not already added */
        if (!(artworks.contains(artwork))) {
            /* Check type and add to appropriate list, add to all artworks too */
            if (artwork instanceof Painting) {
                paintings.add((Painting) artwork);
            } else {
                sculptures.add((Sculpture) artwork);
            }
            artworks.add(artwork);
            // Sort the lists now new artwork added.
            sortChronological(artworks);
        }
    }


    /**
     * Filter artworks by favourite user. Get a list of all artworks by a users
     * favourite list.
     *
     * @param user the user with the favourite list.
     * @return all artworks by favourite users, or empty list if none.
     */
    public static ArrayList<Artwork> filterFavouriteUsers(Profile user) {
        /* Get the profiles of the favourite users, and get the seller for every
         * artwork, if they match, add to list and return it. */
        ArrayList<Artwork> favArtworks = new ArrayList<>();
        for (String f : user.getFavourites()) {
            for (Artwork a : artworks) {
                if (a.getSellerUsername().equals(f)) {
                    favArtworks.add(a);
                }
            }
        }
        return favArtworks;
    }


    /**
     * Sort a given list of artworks by the date of listing.
     *
     * @param sort the list to be sorted.
     * @return the sorted list, or empty list if no elements.
     */
    private static ArrayList<Artwork> sortChronological(ArrayList<Artwork> sort) {
        // Check if list empty before sorting, return empty list if it is.
        if (sort.size() > 0) {
            Date timeListed;
            timeListed = sort.get(0).getTimeListed();
            int n = sort.size();
            // Bubble sort array by date.
            for (int i = 0; i < n; i++) {
                for (int j = 1; j < n; j++) { // Skip first
                    /* If the date listed of j is after our element, then swap
                     * them over.*/
                    if (sort.get(j).getTimeListed().after(timeListed)) {
                        // Test if i is a painting or a sculpture before placing.
                        if (sort.get(i) instanceof Painting) {
                            Painting tempPainting = (Painting) sort.get(i);
                            sort.set(i, sort.get(j));
                            sort.set(j, tempPainting);
                        } else {
                            Sculpture tempSculpture = (Sculpture) sort.get(i);
                            sort.set(i, sort.get(j));
                            sort.set(j, tempSculpture);
                        }
                    }
                }
            }
        }
        return sort;
    }


    /**
     * Get a list of all Paintings. If no painting, return empty list.
     *
     * @return list of all artworks that are only Paintings.
     */
    public static ArrayList<Painting> filterPaintings() {
        /* Get all artworks and test if it's an instance of a painting, if it is
         * add it to list and return it.*/
        ArrayList<Painting> paintings = new ArrayList<>();
        for (Artwork a : artworks) {
            if (a instanceof Painting) {
                paintings.add((Painting) a);
            }
        }
        return paintings;
    }


    /**
     * Get a list of all Sculptures. If no sculptures, return empty list.
     *
     * @return list of all artworks that are only Sculptures.
     */
    public static ArrayList<Sculpture> filterSculptures() {
        /* Get all artworks and test if it's an instance of a sculpture, if it is
         * add it to list and return it.*/
        ArrayList<Sculpture> sculptures = new ArrayList<>();
        for (Artwork a : artworks) {
            if (a instanceof Sculpture) {
                sculptures.add((Sculpture) a);
            }
        }
        return sculptures;
    }


    /**
     * Get a list of all artworks that have finished their auctions.
     *
     * @return the list.
     */
    public static ArrayList<Artwork> filterFinished() {
        ArrayList<Artwork> finished = new ArrayList<>();
        for (Artwork a : artworks) {
            if (a.getBidsLeft() == 0) {
                finished.add(a);
            }
        }
        return finished;
    }


    /**
     * Filter artworks by a search query.
     *
     * @param query the sequence of characters to filter by.
     * @return list of artworks whose title contains the search query.
     */
    public static ArrayList<Artwork> search(String query) {
        /* Compare the search query against the titles of all artworks, if match
         * then add to list and return it, or empty list if none. */
        ArrayList<Artwork> searchResults = new ArrayList<>();
        for (Artwork a : artworks) {
            String title = a.getTitle();
            if (title.contains(query)) {
                searchResults.add(a);
            }
        }
        return searchResults;
    }


    /**
     * Get the list of artworks.
     *
     * @return the list.
     */
    public static ArrayList<Artwork> getArtworks() {
        return artworks;
    }


    /**
     * Get the artworks that a given user has won.
     *
     * @param user the winner of the artworks
     * @return the artwork they won, or empty list if none.
     */
    public static ArrayList<Artwork> getWonByUser(Profile user) {
        ArrayList<Artwork> won = new ArrayList<>();
        for (Artwork a : artworks) {
            /*See if Artwork still on auction, if not, get the profile of the
            winning bidder and see if matches user.*/
            if (a.getBidsLeft() == 0) {
                Bid highestBid = BidBrowser.getBidByID(a.getHighestBidID());
                String winner = highestBid.getUsername();
                if (winner.equals(user.getUsername())) {
                    won.add(a);
                }
            }
        }
        return won;
    }


    /**
     * Get a list of all artworks on sale by a given user.
     *
     * @param user selling artworks
     * @return artworks sold or on auction, empty list if none.
     */
    public static ArrayList<Artwork> getSoldByUser(Profile user) {
        ArrayList<Artwork> sold = new ArrayList<>();
        for (Artwork a : artworks) {
            String seller = a.getSellerUsername();
            if (seller.equals(user.getUsername())) {
                sold.add(a);
            }
        }
        return sold;
    }


    /**
     * Return a single artwork that matches a given artwork ID.
     *
     * @param artworkID the artwork to search for.
     * @return the artwork matching the ID, or null otherwise.
     */
    public static Artwork getByID(int artworkID) {
        Artwork artwork = new Artwork();
        for (Artwork a : artworks) {
            if (a.getArtworkID() == artworkID) {
                artwork = a;
            }
        }
        return artwork;
    }


    /**
     * Convert every Painting to a string for writing to disk.
     *
     * @return array of all paintings as strings.
     */
    public static ArrayList<String> toStringPaintings() {
        ArrayList<String> toStringAll = new ArrayList<>();
        for (Painting p : paintings) {
            toStringAll.add(p.toString());
        }
        return toStringAll;
    }


    /**
     * Convert every Sculpture to a string for writing to disk.
     *
     * @return array of all paintings as strings.
     */
    public static ArrayList<String> toStringSculptures() {
        ArrayList<String> toStringAll = new ArrayList<>();
        for (Sculpture s : sculptures) {
            toStringAll.add(s.toString());
        }
        return toStringAll;
    }
    public static void setCurrentArtwork(Artwork artwork){
        currentArtwork=artwork;
    }
    public static Artwork getCurrentArtwork() {
        return currentArtwork;
    }
}
