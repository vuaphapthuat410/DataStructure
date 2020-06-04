package controllers;

import java.util.ResourceBundle;

import functions.StackActions;
import functions.VisualActions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class StackController implements Initializable {
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

	VisualActions viStack = new StackActions();

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	
	public void pushIntoStack(ActionEvent e) {
		String value = valuePushField.getText();
		System.out.println("Value input: " + value);
		valuePushField.clear();
		if(viStack.add(value, pnStackVisual))
			peakValue.setText(value);
	}

	public void popFromStack(ActionEvent e) {
		valuePushField.clear();
		
		viStack.delete(pnStackVisual);
		if (viStack.getElementsList().size() == 0)
			peakValue.clear();
		else
			peakValue.setText(((StackActions)viStack).getFirstElement().getValue().getText());
	}

}
