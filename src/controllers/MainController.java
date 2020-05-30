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

	static Node blendMode() {
//		Create a pane to group
		Pane g1 = new Pane();
		g1.setStyle("-fx-background-color: #000000");
		g1.setMinWidth(206);
		g1.setMinHeight(106);
		g1.setStyle("-fx-border-color: #000000");
		g1.setStyle("-fx-border-width: 3 3 3 3");

		StackPane data = new StackPane();
		data.setLayoutX(50);
		data.setLayoutY(50);
		data.setStyle("-fx-background-color: #000000");

		Text dataText = new Text("123");

		Rectangle c1 = new Rectangle(0, 0, 100, 100);
		c1.setFill(Color.AQUA);
		data.getChildren().addAll(c1, dataText);
		data.setLayoutX(3);
		data.setLayoutY(3);
//		data.setStyle("-fx-border-color: #000000");
//		data.setStyle("-fx-border-width: 3 0 3 3");

//		c1.setBlendMode(BlendMode.MULTIPLY);
		StackPane pointer = new StackPane();
		pointer.setLayoutX(150);
		pointer.setLayoutY(50);

		Text pointerText = new Text("Alo pointer");

		Rectangle rec = new Rectangle(0, 0, 100, 100);

		rec.setFill(Color.BLUEVIOLET);
		pointer.getChildren().addAll(rec, pointerText);

		g1.getChildren().addAll(data, pointer);

		return g1;
	}

}
