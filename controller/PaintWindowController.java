package artatawe.controller;

import artatawe.classes.Browser;
import artatawe.classes.Profile;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.util.ArrayList;


public class PaintWindowController {

    @FXML
    public AnchorPane rootPane;
    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private TextField brushSize;
    @FXML
    private Label failMsg;


    private double startX;
    private double startY;
    private double endX;
    private double endY;

    private String toolType = "Trace";
    private Profile profile;


    /**
     * Save the drawn image to disk, and set as profile image.
     * @throws Exception if image cannot be written.
     */
    public void saveAction() throws Exception {
        try {
            /* Create new snapshot as size of the canvas. */
            int canvasWidth = (int) canvas.getWidth();
            int canvasHeight = (int) canvas.getHeight();
            WritableImage image = new WritableImage(canvasWidth, canvasHeight);
            canvas.snapshot(null, image);

            /* Render the image ready for writing to disk. */
            RenderedImage renderedImage = SwingFXUtils.fromFXImage(image, null);

            /* Create a new file as the username + Avatar, or overwrite if file
             * already exists. */
            String userName = Browser.getCurrentUser().getUsername();
            String imageName = userName + "Avatar.png";
            String fileLocation = "src/artatawe/images/" + imageName;
            File file = new File(fileLocation);

            /* Write the file to disk. */
            ImageIO.write(renderedImage, "png", file);

            /* Set the image as the profile image. */
            Browser.getCurrentUser().setImageName(imageName);

        } catch (Exception e) {
            failMsg.setText("Failed to save image" + e);
        }
        rootPane.setMaxSize(400, 400);
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/editprofile.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void toolTraceAction() {toolType = "Trace";}

    public void toolLineAction() {toolType = "Line";}


    //Instance ProfileController for testing
    private void setProfiles(Profile profile) {
        this.profile = profile;
    }


    private void setTestProfiles() {
        Profile user = new Profile("abc", "Jackie", "Chan",
                "07447627890", "Under the bridge",
                "ERROR 404 NOT FOUND", "", new ArrayList<>());
        setProfiles(user);
    }



    public void initialize(){
        setTestProfiles();
        GraphicsContext g = canvas.getGraphicsContext2D();


        canvas.setOnMousePressed(e -> {
            startX = e.getX();
            startY = e.getY();


            if(toolType.equals("Trace")) {
                double size = Double.parseDouble(brushSize.getText());
                g.setFill(colorPicker.getValue());
                g.fillOval(startX, startY, size, size);
            }


        });

        canvas.setOnMouseDragged(e -> {
            double size = Double.parseDouble(brushSize.getText());
            double x = e.getX() - size / 2;
            double y = e.getY() - size / 2;


            if(toolType.equals("Trace")) {
                g.setFill(colorPicker.getValue());
                g.fillOval(x, y, size, size);
            }


        });

        canvas.setOnMouseReleased(e -> {
            double size = Double.parseDouble(brushSize.getText());
            endX = e.getX();
            endY = e.getY();


            if(toolType.equals("Line")) {
                g.setLineWidth(size);
                g.setStroke(colorPicker.getValue());
                g.strokeLine(startX, startY, endX, endY);
            } else {
                g.setFill(colorPicker.getValue());
                g.fillOval(endX, endY, size, size);
            }
        });
    }
}