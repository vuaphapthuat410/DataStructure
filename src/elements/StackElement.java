package elements;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class StackElement extends Element {
	public StackElement(String value) {
		super();
		this.value = new Label();
		this.value.setText(value);
		this.value.setStyle("-fx-text-fill: red");
	 
		StackPane shapeElement = new StackPane(this.value);
		shapeElement.setPrefHeight(43);
		shapeElement.setPrefWidth(141);
			
		shapeElement.setStyle("-fx-border-color: green");
		
		this.shape = shapeElement;
	}
}
