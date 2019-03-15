package artatawe.classes;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Arrays;

/**
 * ReadWrite provides all the functionality needed for writing the system data
 * to text files, and reading the data back again.
 *
 * Created: 2017/11/27
 * @author Edward Bramworth and Robert Hardy
 */
public class ReadWrite {
    private static final String BIDS_FILE = "Bids.txt";
    private static final String PAINTINGS_FILE = "Artworks\\Paintings.txt";
    private static final String SCULPTURES_FILE = "Artworks\\Sculptures.txt";
    private static final String PROFILES_FILE = "Profiles.txt";
    private static final String DATA_PATH = "src\\artatawe\\data\\";
    //defining the format for the date type
    private static DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


    /**
     * Method reads each line from a specified file
     * Returns an ArrayList of String where each string represents a record
     *
     * @param fileName location of file to read.
     * @return list of lines to be read.
     * @throws FileNotFoundException if file cannot be found.
     */
    private static ArrayList<String> readFile(String fileName) throws FileNotFoundException {

        ArrayList<String> rows = new ArrayList<>();
        File file = new File(fileName);
        Scanner s = new Scanner(System.in);

        try {
            s = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found! " + file);
            System.exit(0); // Stop program.
        }

        while (s.hasNextLine()) {
            rows.add(s.nextLine());
        }

        return rows;
    }

    /**
     * Method takes in an ArrayList of any object type, calls the toString method on each object
     * Another ArrayList is populated with the toString values of the objects, which are then written to file
     *
     * @param objects      to be written to disk.
     * @param fileLocation of file to write to.
     */
    public static void fileWriter(ArrayList<?> objects, String fileLocation) {
        ArrayList<String> rows = new ArrayList<>();

        for (Object object : objects) {
            rows.add(object.toString());
        }

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileLocation));
            for (int i = 0; i < rows.size(); i++) {
                out.write(rows.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {
            System.out.println("Cannot write to " + fileLocation);
        }
    }


    /**
     * Get the date/time format.
     *
     * @return format.
     */
    public static DateFormat getFormat() {
        return format;
    }


    /**
     * Generate bid instances from read file.
     *
     * @return list of generated bids.
     * @throws ParseException if element cannot be read.
     */
    public static ArrayList<Bid> makeBids() throws ParseException {
        String filePath = DATA_PATH + BIDS_FILE;
        ArrayList<String> rows = new ArrayList<>();
        ArrayList<Bid> bids = new ArrayList<>();

        try {
            rows = readFile(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (String row : rows) {
            String[] parts = row.split(",");
            int bidID = 0;
            String username = "";
            Date bidDate = null;
            int artworkID = 0;
            Double value = 0.0;

            for (int i = 0; i < parts.length; i++) {
                switch (i) {
                    case 0:
                        bidID = Integer.parseInt(parts[i]);
                        break;

                    case 1:
                        username = parts[i];
                        break;

                    case 2:
                        bidDate = format.parse(parts[i]);
                        break;

                    case 3:
                        artworkID = Integer.parseInt(parts[i]);
                        break;

                    case 4:
                        value = Double.parseDouble(parts[i]);
                        break;

                    default:
                        System.out.println("Incorrect number of attributes in record");
                }
            }

            bids.add(new Bid(bidID, username, bidDate, artworkID, value));

        }
        return bids;

    }


    /**
     * Generate painting instances from read file.
     *
     * @return list of generated paintings.
     * @throws ParseException if element cannot be read.
     */
    public static ArrayList<Painting> makePaintings() throws ParseException {
        String filePath = DATA_PATH + PAINTINGS_FILE;
        ArrayList<String> rows = new ArrayList<>();
        ArrayList<Painting> paintings = new ArrayList<>();

        try {
            rows = readFile(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        for (String row : rows) {
            String[] parts = row.split(",");
            int artworkID = 0;
            String title = "";
            String description = "";
            String artistName = "";
            String yearCreated = "";
            int reservePrice = 0;
            Date timeListed = null;
            String seller = "";
            int totalBids = 0;
            int width = 0;
            int height = 0;
            String mainPhotoLocation = "";

            for (int i = 0; i < parts.length; i++) {
                switch (i) {
                    case 0:
                        artworkID = Integer.parseInt(parts[i]);
                        break;

                    case 1:
                        title = parts[i];
                        break;

                    case 2:
                        description = parts[i];
                        break;

                    case 3:
                        artistName = parts[i];
                        break;

                    case 4:
                        yearCreated = parts[i];
                        break;

                    case 5:
                        reservePrice = Integer.parseInt(parts[i]);
                        break;

                    case 6:
                        timeListed = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss").parse(parts[i]);
                        break;

                    case 7:
                        seller = parts[i];
                        break;

                    case 8:
                        totalBids = Integer.parseInt(parts[i]);
                        break;

                    case 9:
                        width = Integer.parseInt(parts[i]);
                        break;

                    case 10:
                        height = Integer.parseInt(parts[i]);
                        break;

                    case 11:
                        mainPhotoLocation = parts[i];

                    default:
                        System.out.println("Incorrect number of attributes in record");

                }
            }

            paintings.add(new Painting(artworkID, title, description, mainPhotoLocation, artistName,
                    yearCreated, reservePrice, timeListed, seller, totalBids, width, height));


        }
        return paintings;
    }


    /**
     * Generate sculpture instances from read file.
     *
     * @return list of generated sculptures.
     * @throws ParseException if element cannot be read.
     */
    public static ArrayList<Sculpture> makeSculptures() throws ParseException {
        String filePath = DATA_PATH + SCULPTURES_FILE;
        ArrayList<String> rows = new ArrayList<>();
        ArrayList<Sculpture> sculptures = new ArrayList<>();

        try {
            rows = readFile(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (String row : rows) {
            String[] parts = row.split(",");
            int artworkID = 0;
            String title = "";
            String description = "";
            String artistName = "";
            String yearCreated = "";
            int reservePrice = 0;
            Date timeListed = null;
            String seller = "";
            int totalBids = 0;
            int width = 0;
            int height = 0;
            int depth = 0;
            String mainMaterial = "";
            String mainPhotoLocation = "";
            String additionalPhotosLine = "";

            for (int i = 0; i < parts.length; i++) {
                switch (i) {
                    case 0:
                        artworkID = Integer.parseInt(parts[i]);
                        break;

                    case 1:
                        title = parts[i];
                        break;

                    case 2:
                        description = parts[i];
                        break;

                    case 3:
                        artistName = parts[i];
                        break;

                    case 4:
                        yearCreated = parts[i];
                        break;

                    case 5:
                        reservePrice = Integer.parseInt(parts[i]);
                        break;

                    case 6:
                        timeListed = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss").parse(parts[i]);
                        break;

                    case 7:
                        seller = parts[i];
                        break;

                    case 8:
                        totalBids = Integer.parseInt(parts[i]);
                        break;

                    case 9:
                        width = Integer.parseInt(parts[i]);
                        break;

                    case 10:
                        height = Integer.parseInt(parts[i]);
                        break;

                    case 11:
                        depth = Integer.parseInt(parts[i]);
                        break;

                    case 12:
                        mainMaterial = parts[i];
                        break;

                    case 13:
                        mainPhotoLocation = parts[i];
                        break;

                    case 14:
                        additionalPhotosLine = parts[i];

                    default:
                        System.out.println("Incorrect number of attributes in record");
                }

            }
            ArrayList<String> additionalPhotos = new ArrayList<>(Arrays.asList(additionalPhotosLine.split("\\\\")));

            sculptures.add(new Sculpture(artworkID, title, description, artistName, yearCreated, reservePrice, timeListed,
                    seller, totalBids, width, height, depth, mainMaterial, mainPhotoLocation, additionalPhotos));
        }

        return sculptures;
    }


    /**
     * Generate profile instances from read file.
     *
     * @return list of generated profiles.
     */
    public static ArrayList<Profile> makeProfiles() {
        String filePath = DATA_PATH + PROFILES_FILE;
        ArrayList<Profile> profiles = new ArrayList<>();
        ArrayList<String> rows = new ArrayList<>();

        try {
            rows = readFile(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File Not Found");
        }

        for (String row : rows) {
            String[] parts = row.split(",");
            String username = "";
            String firstName = "";
            String lastName = "";
            String phoneNumber = "";
            String address = "";
            String postcode = "";
            String imageLocation = "";
            String favouriteUserLine = "";

            for (int i = 0; i < parts.length; i++) {
                switch (i) {
                    case 0:
                        username = (parts[i]);
                        break;

                    case 1:
                        firstName = parts[i];
                        break;

                    case 2:
                        lastName = parts[i];
                        break;

                    case 3:
                        phoneNumber = parts[i];
                        break;

                    case 4:
                        address = parts[i];
                        break;

                    case 5:
                        postcode = parts[i];
                        break;

                    case 6:
                        imageLocation = parts[i];
                        break;

                    case 7:
                        favouriteUserLine = parts[i];
                        break;

                    default:
                        System.out.println("Incorrect number of attributes in record");

                }
            }

            ArrayList<String> favouriteUsers = new ArrayList<>(Arrays.asList(favouriteUserLine.split("\\\\")));
            profiles.add(new Profile(username, firstName, lastName, phoneNumber,
                    address, postcode, imageLocation, favouriteUsers));
        }

        return profiles;
    }


    /**
     * Getter used in ProfileBrowser
     *
     * @return The file location of the profiles file
     */
    public static String getProfilesFile() {
        return DATA_PATH + PROFILES_FILE;
    }

    /**
     * Getter used in ArtworkBrowser
     *
     * @return The file location of the sculptures file
     */
    public static String getSculpturesFile() {
        return DATA_PATH + SCULPTURES_FILE;
    }

    /**
     * Getter used in ArtworkBrowser
     *
     * @return The file location of the paintings file
     */
    public static String getPaintingsFile() {
        return DATA_PATH + PAINTINGS_FILE;
    }

    /**
     * Getter used in BidBrowser
     *
     * @return The file location of the bids file
     */
    public static String getBidsFile() {
        return DATA_PATH + BIDS_FILE;
    }
}
