package elements;

import javafx.scene.layout.Pane;

public abstract class Element {
	private String value;
	private Pane shape;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Pane getShape() {
		return shape;
	}
	public void setShape(Pane shape) {
		this.shape = shape;
	}
	
	
}
