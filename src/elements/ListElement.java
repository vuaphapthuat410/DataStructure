package elements;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class ListElement extends Element{

	public ListElement(String value) {
		super();
		// TODO Auto-generated constructor stub
		this.value = new Label();
		this.value.setText(value);
		this.value.setStyle("-fx-text-fill: #ffffff");
		
		GridPane shapeElement = new GridPane();
		shapeElement.setPrefHeight(43);
		shapeElement.setPrefWidth(141);
		
		StackPane dataPane = new StackPane(this.value);
		dataPane.setStyle("-fx-background-color:  #ff6a00");
		dataPane.setPrefHeight(41);
		dataPane.setPrefWidth(88);
		
		AnchorPane pointerPane = new AnchorPane();
		pointerPane.setPrefHeight(41);
		pointerPane.setPrefWidth(51);
		pointerPane.setStyle("-fx-background-color:  #8510e6");
		
		shapeElement.add(dataPane, 0, 0,1,1);
		shapeElement.add(pointerPane, 1, 0, 1, 1 );
		shapeElement.setStyle("-fx-border-color: black");
		
		this.shape = shapeElement;
	}

}
