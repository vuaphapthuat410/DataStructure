package controllers;

import java.util.ResourceBundle;

import functions.General;
import functions.StackActions;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class StackController implements Initializable {
	StackActions stack = new StackActions();
	ObservableList<Node> arrowList;
	@FXML
	private Button pushButton;
	@FXML
	private TextField valuePushField;
	@FXML
	private Button popButton;
	@FXML
	private TextField peakValue;
	@FXML
	private Pane pnStackVisual;
	
	public void pushIntoStack(ActionEvent e) {
		String value = valuePushField.getText();
//		if(value == "") {
//			General.showAlertWithoutHeaderText("Alert","Text is null!");
//			return;
//		}
		stack.add(value);
		pnStackVisual.getChildren().add(stack.getFirstElement().getShape());
		System.out.println("Value input: " + value);
		valuePushField.clear();
		peakValue.setText(value);
	}
	public void popFromStack(ActionEvent e) {
		valuePushField.clear();
		if(stack.getStack().size() != 0 ) 
			deleteShape(stack.getFirstElement().getShape());
			stack.delete();
			if(stack.getStack().size() == 0 )
				peakValue.clear();
			else peakValue.setText(stack.getFirstElement().getValue().getText());
	}
	
	public void deleteShape(Pane elementShape) {
		pnStackVisual.getChildren().remove(elementShape);
	}
	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub	
	}
	
}
