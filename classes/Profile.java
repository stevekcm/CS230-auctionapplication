package artatawe.classes;

import java.util.ArrayList;

/**
 * artatawe.classes.ProfileController
 * Created: 2017/11/27
 * @author Joshua Bray
 */
public class Profile {
    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String postcode;
    private String imageName;
    private ArrayList<String> favourites;


    /**
     * Create a new Profile.
     * @param username the profile's displayed name.
     * @param firstName the first name of the profile's user.
     * @param lastName the surname of the profile's user.
     * @param phoneNumber the profile user's personal phone number.
     * @param address the user's home location.
     * @param postcode the postcode of the profile user's home address.
     * @param favourites the user's favourite users.
     * @param imageName the name of the image in the images folder.
     */
    public Profile(String username, String firstName, String lastName,
                   String phoneNumber, String address, String postcode,
                   String imageName, ArrayList<String> favourites) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.postcode = postcode;
        this.imageName = imageName;
        this.favourites = favourites;
    }


    /**
     * Create a new empty Profile
     * Used by Profile Browser.
     */
    public Profile() {
    }


    /**
     * Add a user to the profiles favourite list.
     * @param user to add as favourite.
     */
    public void addFavourite(Profile user) {
        /*Check if use is already a favourite before adding duplicate*/
        if(!(favourites.contains(user.getUsername()))) {
            this.favourites.add(user.getUsername());
        }
    }


    /**
     * Remove a user from the favourite list.
     * @param user to remove.
     */
    public void removeFavourite(Profile user) {
        if (this.favourites.contains(user)) {
            this.favourites.remove(user);
        }
    }


    /**
     * Get the list of favourite users.
     * @return favourites.
     */
    /*public ArrayList<Profile> getFavouriteUsers() {*/
    public ArrayList<String> getFavourites() {
        return favourites;
    }


    /**
     *
     * @return a string containing the user's username
     */
    public String getUsername() {
        return username;
    }


    /**
     *
     * @return a string containing the user's first name
     */
    public String getFirstName() {
        return firstName;
    }


    /**
     *
     * @return a string containing the user's last name
     */
    public String getLastName() {
        return lastName;
    }


    /**
     *
     * @return a string containing the user's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }


    /**
     *
     * @return a string containing the user's address
     */
    public String getAddress() {
        return address;
    }


    /**
     *
     * @return a string containing the user's postcode
     */
    public String getPostCode() {
        return postcode;
    }


    /**
     * Convert a profile object into a string suitable for writing to disk.
     * @return profile as a string.
     */
    public String toString() {
        /* Use string builder for loop concatenation.*/
        StringBuilder toString = new StringBuilder(
                this.username + "," +
                this.firstName + "," +
                this.lastName + "," +
                this.phoneNumber + "," +
                this.address + "," +
                this.postcode + "," +
                this.imageName+  ",");
        for (String fav : this.favourites) {
            toString.append(fav);
            toString.append("\\");
        }
        return toString.toString();
    }


    /**
     * Set the file name of the current Profile image.
     * @param fileName to set.
     */
    public void setImageName(String fileName) {
        this.imageName = fileName;
    }


    /**
     * Get the image name of the profile picture.
     * @return image name.
     */
    public String getImageName() {
        return imageName;
    }
}