package functions;

import java.util.LinkedList;

import elements.Arrow;
import elements.Element;
import elements.creators.ListElementCreator;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class LinkedListActions implements ListActions{
	
	private LinkedList<Element> ElementsList;
	private ObservableList<Node> arrowList;



	public LinkedListActions() {
		super();
		ElementsList = new LinkedList<Element>();
		arrowList = FXCollections.observableArrayList();
		Arrow arrowD1 = new Arrow(243, 137, 243, 184, 10);
		Arrow arrowD2 = new Arrow(243, 227, 243, 274, 10);
		Arrow arrowD3 = new Arrow(243, 317, 243, 364, 10);
		Arrow arrowD4 = new Arrow(269, 386, 430, 386, 10);
		Arrow arrowD5 = new Arrow(545, 364, 545, 317, 10);
		Arrow arrowD6 = new Arrow(545, 274, 545, 227, 10);
		Arrow arrowD7 = new Arrow(545, 184, 545, 137, 10);
		arrowList.addAll(arrowD1, arrowD2, arrowD3, arrowD4, arrowD5, arrowD6, arrowD7);
	}

	public LinkedList<Element> getElementsList() {
		return ElementsList;
	}

	public void setElementsList(LinkedList<Element> elementsList) {
		ElementsList = elementsList;
	}
	
	public ObservableList<Node> getArrowList() {
		return arrowList;
	}

	public void setArrowList(ObservableList<Node> arrowList) {
		this.arrowList = arrowList;
	}

	
	@Override
	public boolean add(String value) {
		// TODO Auto-generated method stub
//		Create new element
		if(ElementsList.size() >= General.MAX_LINKED_LIST_ITEMS) {
			General.showAlertWithoutHeaderText("Alert", "You can only create 8 elements");
			return false;
		}
		Element node = ListElementCreator.createElement(value);
		ElementsList.addLast(node);
		
//		Set layout for new element
		int size = ElementsList.size();
		if(size <=4) {
			node.getShape().setLayoutX(128);
			node.getShape().setLayoutY(94+(size%5-1)*90);
		}else {
			node.getShape().setLayoutX(430);
			node.getShape().setLayoutY(94+(3-size%5)*90);
		}
		
		int location = ElementsList.size();
		if (location > 1)
			General.FadeInOut(arrowList.get(location - 2), 0, 1, 1);
		return true;	
	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		if(ElementsList.size() == 0) {
			General.showAlertWithoutHeaderText("Alert", "Linked list is empty");
			return false;
		}
		if (ElementsList.size() > 1) {
			for (int i = ElementsList.size() - 1; i > 0; i--) {
//				arrowList.get(i).setVisible(false);
				General.FadeInOut(arrowList.get(i - 1), 1, 0, 1);
			}
		}
		
		ElementsList.remove();
		Pane temp;
		for(int i=0;i<ElementsList.size();i++) {
			temp = ElementsList.get(i).getShape();
			System.out.println("Layout Y of element " + i + " = " + temp.getLayoutY() );
			System.out.println("Layout X of element after move " + i + " = " + temp.getLayoutX() );
			if(i<3) {
				General.goUpDown(temp,- 90);
			}
			if(i == 3) {
				General.goHorizontal(temp, -302);
			}
			if(i>3) {
				General.goUpDown(temp, 90);
			}
		
		}
		
		if (ElementsList.size() > 0) {
			for (int i = 1; i < ElementsList.size(); i++) {
//				arrowList.get(i).setVisible(false);
				General.FadeInOut(arrowList.get(i - 1), 0, 1, 2.5);
			}
		}
		return true;
	}
	
	@Override
	public boolean addByIndex(String value,int index) {
		// TODO Auto-generated method stub
		if(ElementsList.size() >= General.MAX_LINKED_LIST_ITEMS) {
			General.showAlertWithoutHeaderText("Alert", "You can only create 8 elements");
			return false;
		}else if(index >= ElementsList.size()) {
			General.showAlertWithoutHeaderText("Alert", "Index out of range");
			return false;
		}
		
		// Fade out arrow after location of added item
		for (int i = index; i < ElementsList.size(); i++) {
//					arrowList.get(i).setVisible(false);
			if(i!=0)
				General.FadeInOut(arrowList.get(i-1), 1, 0, 1);
		}

		Element node = ListElementCreator.createElement(value);
		Pane temp;
		for(int i=index;i<ElementsList.size();i++) {
			temp = ElementsList.get(i).getShape();
			if(i<3) {
				General.goUpDown(temp, 90);
			}else if(i==3) {
				General.goHorizontal(temp, 302);
			}else if(i>3) {
				General.goUpDown(temp, -90);
			}
		}
		ElementsList.add(index, node);
		int location = index + 1;
		if(location <=4) {
			node.getShape().setLayoutX(128);
			node.getShape().setLayoutY(94+(location%5-1)*90);
		}else {
			node.getShape().setLayoutX(430);
			node.getShape().setLayoutY(94+(3-location%5)*90);
		}

		// Fade in arrow after location of added item
		for (int i = index; i < ElementsList.size(); i++) {
//			arrowList.get(i).setVisible(false);
			if(i!=0)
				General.FadeInOut(arrowList.get(i-1), 0, 1, 2.5);
		}
		return true;
	}

	@Override
	public boolean deleteByIndex(int index) {
		// TODO Auto-generated method stub
		if(ElementsList.size() == 0) {
			General.showAlertWithoutHeaderText("Alert", "Linked list is empty");
			return false;
		}else if(ElementsList.size() <= index) {
			General.showAlertWithoutHeaderText("Alert", "Index out of range");
			return false;
		}else{
			for (int i = ElementsList.size() - 1; i >= index; i--) {
//				arrowList.get(i).setVisible(false);
				if(i != 0)
					General.FadeInOut(arrowList.get(i - 1), 1, 0, 1);
			}
		}
		ElementsList.remove(index);
		Pane temp;
		for(int i=index;i<ElementsList.size();i++) {
			temp = ElementsList.get(i).getShape();
			System.out.println("Layout Y of element " + i + " = " + temp.getLayoutY() );
			System.out.println("Layout X of element after move " + i + " = " + temp.getLayoutX() );
			if(i<3) {
				General.goUpDown(temp,- 90);
			}
			if(i == 3) {
				General.goHorizontal(temp, -302);
			}
			if(i>3) {
				General.goUpDown(temp, 90);
			}
		
		}
		
		for (int i = index; i < ElementsList.size(); i++) {
//			arrowList.get(i).setVisible(false);
			if(i != 0)
				General.FadeInOut(arrowList.get(i - 1), 0, 1, 2.5);
		}
		return true;
		
	}
	
	
}
