package artatawe.classes;

import java.text.ParseException;

/**
 * Browser defines the basic building blocks of the browsers, with its subclasses
 * ArtworkBrowser, BidBrowser, and ProfileBrowser specialising in specific
 * filtering methods for their respective types.
 *
 * Created: 2017/11/27
 * @author Robert Hardy
 */
public class Browser {
    protected static Profile currentUser;

    /**
     * Regenerate all Bids, Profiles and Artworks from disk.
     *
     * @throws ParseException if errors reading files during creation.
     */
    public static void makeAll() throws ParseException {
        ProfileBrowser.makeProfiles();
        ArtworkBrowser.makeArtworks();
        BidBrowser.makeBids();
    }


    /**
     * Save all Bids, Profiles and Artworks to disk.
     */
    public static void saveAll() {
        ProfileBrowser.saveProfiles();
        ArtworkBrowser.saveArtworks();
        BidBrowser.saveBids();
    }


    /**
     * Get the current user.
     *
     * @return the current user.
     */
    public static Profile getCurrentUser() {
        return currentUser;
    }


    /**
     * Set the current user to a given user.
     *
     * @param user the user to set.
     */
    public static void setCurrentUser(Profile user) {
        currentUser = user;
    }
}
