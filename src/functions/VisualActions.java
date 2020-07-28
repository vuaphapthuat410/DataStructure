package functions;

import java.util.LinkedList;

import elements.Element;
import javafx.scene.layout.Pane;

public abstract class VisualActions {
	
	protected LinkedList<Element> ElementsList;
	
	public abstract boolean add(String value,Pane pane);
	
	public abstract boolean delete(Pane pane);
	
	public boolean addByIndex(String value,int index,Pane pane) {
		return false;
	}
	
	public boolean deleteByIndex(int index, Pane pane) {
		return false;
	}
	
	public LinkedList<Element> getElementsList() {
		return ElementsList;
	}

	public void setElementsList(LinkedList<Element> elementsList) {
		ElementsList = elementsList;
	}

}
