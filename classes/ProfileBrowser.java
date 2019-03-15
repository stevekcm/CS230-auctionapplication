package artatawe.classes;

import java.util.ArrayList;

/**
 * Profile Browser provides functionality for looking at and searching for
 * profiles. It has many methods for filtering profiles, adding profiles, and
 * using ReadWrite to write and read profiles from disk.
 *
 * Created: 2017/11/27
 * @author Robert Hardy
 */
public class ProfileBrowser extends Browser {
    private static ArrayList<Profile> profiles;
    /**
     * Read profiles from disk and populate the Profiles list.
     */
    public static void makeProfiles() {
        profiles = ReadWrite.makeProfiles();
    }


    /**
     * Write profiles to disk.
     */
    public static void saveProfiles() {
        ReadWrite.fileWriter(profiles, ReadWrite.getProfilesFile());
    }


    /**
     * Add a given profile to the global profiles list.
     * @param profile to add.
     */
    public static void addProfile(Profile profile) {
        /* Check if profile already added */
        if (!(profiles.contains(profile))) {
            profiles.add(profile);
        }
    }


    /**
     * For any given user, get the profiles of their favourite users.
     * @param user the user to whom the favourites belong.
     * @return list of favourite users.
     */
    public static ArrayList<Profile> filterFavourites(Profile user) {
        /* Check every profiles username to see if matches favourites username,
        * if it does, add it to list and return.*/
        ArrayList<Profile> favourites = new ArrayList<>();
        for (Profile p : profiles) {
            for (String s : user.getFavourites()) {
                if (s.equals(p.getUsername())) {
                    favourites.add(p);
                }
            }
        }
        return favourites;
    }


    /**
     * Get all profiles.
     * @return list of every profile.
     */
    public static ArrayList<Profile> getProfiles() {
        return profiles;
    }


    /**
     * Get a profile by any given username.
     * @param username to search for.
     * @return the profile matching the username, or empty profile if none.
     */
    public static Profile getUserByID(String username) {
        Profile profile = new Profile();
        for (Profile p : profiles) {
            if (p.getUsername().equals(username)) {
                profile = p;
            }
        }
        return profile;
    }


    /**
     * Test if a user exists by username.
     * @param username to test for.
     * @return true if user exists, else false.
     */
    public static boolean testUserID(String username) {
        for (Profile p : profiles) {
            if (p.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Convert every Profile to a string for writing to disk.
     * @return array of all profiles as strings.
     */
    public static ArrayList<String> toStringAll() {
        ArrayList<String> toStringAll = new ArrayList<>();
        for (Profile p : profiles) {
            toStringAll.add(p.toString());
        }
        return toStringAll;
    }
}
