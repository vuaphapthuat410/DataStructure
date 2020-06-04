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
	 
		GridPane shapeElement = new GridPane();
		shapeElement.setPrefHeight(43);
		shapeElement.setPrefWidth(141);
	
		StackPane dataPane = new StackPane(this.value);
//		dataPane.setStyle("-fx-border-color: yellow");
		dataPane.setPrefHeight(41);
		dataPane.setPrefWidth(141);
		
		shapeElement.add(dataPane, 0, 0,1,1);
		shapeElement.setStyle("-fx-border-color: green");
		
		this.shape = shapeElement;
	}
}
