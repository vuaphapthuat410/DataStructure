package elements;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public abstract class Element {
	protected Label value;
	protected Pane shape;
	
	public Label getValue() {
		return value;
	}
	public void setValue(Label value) {
		this.value = value;
	}
	public Pane getShape() {
		return shape;
	}
	public void setShape(Pane shape) {
		this.shape = shape;
	}
	
	
}
