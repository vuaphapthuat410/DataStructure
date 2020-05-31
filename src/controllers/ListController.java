package controllers;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import functions.General;
import functions.LinkedListActions;
import functions.ListActions;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class ListController implements Initializable {

	@FXML
	private TextField tfValue;

	@FXML
	private TextField tfIndex;

	@FXML
	private Button btnAddItem;

	@FXML
	private Button btnAddByIndex;

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnDelByIndex;

	@FXML
	private Pane pnListVisual;

	@FXML
	private Group arrowD4;

	@FXML
	private Group arrowD6;

	@FXML
	private Group arrowD5;

	@FXML
	private Group arrowD7;

	@FXML
	private Group arrowD1;

	@FXML
	private Group arrowD2;

	@FXML
	private Group arrowD3;

	LinkedListActions linkedList = new LinkedListActions();

	ObservableList<Node> arrowList;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		arrowD1.setVisible(false);
		arrowD2.setVisible(false);
		arrowD3.setVisible(false);
		arrowD4.setVisible(false);
		arrowD5.setVisible(false);
		arrowD6.setVisible(false);
		arrowD7.setVisible(false);

		arrowList = FXCollections.observableArrayList();
		arrowList.addAll(arrowD1, arrowD2, arrowD3, arrowD4, arrowD5, arrowD6, arrowD7);
		
		
		pnListVisual.getChildren().addAll(linkedList.getArrowList());
		for (int i = 0; i < linkedList.getArrowList().size(); i++) {
			General.FadeInOut(linkedList.getArrowList().get(i), 1, 0, 0.01);
		}
	}

	public void addItem(ActionEvent e) {
		String value = tfValue.getText();
		linkedList.add(value);
		pnListVisual.getChildren().add(linkedList.getElementsList().getLast().getShape());
		
		printList();
		System.out.println("Value input: " + value);
		tfValue.clear();

	}

	public void deleteItem(ActionEvent e) {
		if(linkedList.getElementsList().size() != 0 )
			deleteShape(linkedList.getElementsList().getFirst().getShape());
		linkedList.delete();
		printList();
	}

	public void addItemByIndex(ActionEvent e) {
		String value = tfValue.getText();
		int index = 0;
		try {
			index = Integer.parseInt(tfIndex.getText());
		} catch (Exception e2) {
			// TODO: handle exception
			General.showAlertWithoutHeaderText("Alert", "Index must be an integer.");
			return;
		}

		linkedList.addByIndex(value, index);
		if(index < linkedList.getElementsList().size())
			pnListVisual.getChildren().add(linkedList.getElementsList().get(index).getShape());

		printList();
		tfValue.clear();
		tfIndex.clear();
	}

	public void deleteItemByIndex(ActionEvent e) {
		int index = 0;
		try {
			index = Integer.parseInt(tfIndex.getText());
		} catch (Exception e2) {
			// TODO: handle exception
			General.showAlertWithoutHeaderText("Alert", "Index must be an integer.");
			return;
		}
		if(linkedList.getElementsList().size() != 0 && linkedList.getElementsList().size() > index)
			deleteShape(linkedList.getElementsList().get(index).getShape());
		linkedList.deleteByIndex(index);

		printList();
		tfIndex.clear();

	}

	public void deleteShape(Pane elementShape) {
		pnListVisual.getChildren().remove(elementShape);
	}

	public void printList() {
		for (int i = 0; i < linkedList.getElementsList().size(); i++) {
			System.out.println(linkedList.getElementsList().get(i).getValue().getText());
		}
	}


}
