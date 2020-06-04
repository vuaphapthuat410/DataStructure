package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class MainController implements Initializable {

	@FXML
    private Button btnStack;

    @FXML
    private Button btnList;

    @FXML
    private Button btnTree;

    @FXML
    private Button btnAbout;

    @FXML
    private StackPane mainView;

    @FXML
    private Pane pnAbout;

    @FXML
    private Pane pnApp;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		pnAbout.toFront();
	}

	public void goAbout(ActionEvent e) {

		pnAbout.toFront();

	}

	public void goLinkList(ActionEvent e) throws IOException {

		Pane listPane = FXMLLoader.load(getClass().getResource("../views/LinkedListUI.fxml"));
		mainView.getChildren().add(listPane);

	}

}
