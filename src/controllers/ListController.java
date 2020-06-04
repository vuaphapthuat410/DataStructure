package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import functions.General;
import functions.LinkedListActions;
import functions.VisualActions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

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

	VisualActions viLinkedList = new LinkedListActions();

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
		
		LinkedListActions temp = (LinkedListActions)viLinkedList;
		pnListVisual.getChildren().addAll(temp.getArrowList());
		for (int i = 0; i < temp.getArrowList().size(); i++) {
			General.FadeInOut(temp.getArrowList().get(i), 1, 0, 0.01);
		}
	}

	public void addItem(ActionEvent e) {
		String value = tfValue.getText();
		viLinkedList.add(value,pnListVisual);
		printList();
		System.out.println("Value input: " + value);
		tfValue.clear();

	}

	public void deleteItem(ActionEvent e) {
		
		viLinkedList.delete(pnListVisual);
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

		viLinkedList.addByIndex(value, index,pnListVisual);

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
		
		viLinkedList.deleteByIndex(index,pnListVisual);

		printList();
		tfIndex.clear();

	}


	public void printList() {
		for (int i = 0; i < viLinkedList.getElementsList().size(); i++) {
			System.out.println(viLinkedList.getElementsList().get(i).getValue().getText());
		}
	}


}
