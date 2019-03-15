package artatawe.controller;

import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Drawing Avatar Controller
 * Created: 2017/11/27
 */
public class DrawAvatarController implements Initializable {

	
    @FXML
    private Pane pane;

    @FXML
    private SwingNode swingNode;

    @FXML
    private AnchorPane rootPane3;

	
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    createAndSetSwingDrawingPanel(swingNode);
	}

    public void createAndSetSwingDrawingPanel(final SwingNode swingNode) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
				JPanel panel = new JPanel();
                /*final Drawing drawPad = new Drawing("");
				panel.add(drawPad);*/
                swingNode.setContent(panel);
            }
        });
    }
}