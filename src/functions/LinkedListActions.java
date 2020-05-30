package functions;

import java.util.LinkedList;

import elements.Element;
import elements.creators.ListElementCreator;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class LinkedListActions implements ListActions{
	final int MAX_LINKED_LIST_ITEMS = 8;
	private LinkedList<Element> ElementsList;
	
	public LinkedListActions() {
		super();
		ElementsList =new LinkedList<Element>();
	}

	public LinkedList<Element> getElementsList() {
		return ElementsList;
	}

	public void setElementsList(LinkedList<Element> elementsList) {
		ElementsList = elementsList;
	}

	@Override
	public void add(String value) {
		// TODO Auto-generated method stub
//		Create new element
		if(ElementsList.size() >= MAX_LINKED_LIST_ITEMS) {
			System.out.println("Can't add more node");
			return;
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
			
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		ElementsList.remove();
		Pane temp;
		for(int i=0;i<ElementsList.size();i++) {
			temp = ElementsList.get(i).getShape();
			System.out.println("Layout Y of element " + i + " = " + temp.getLayoutY() );
			System.out.println("Layout X of element after move " + i + " = " + temp.getLayoutX() );
			if(i<3) {
				goUpDown(temp,- 90);
			}
			if(i == 3) {
				goHorizontal(temp, -302);
			}
			if(i>3) {
				goUpDown(temp, 90);
			}
		
		}
		
	}
	
	@Override
	public void addByIndex(String value,int index) {
		// TODO Auto-generated method stub
		if(ElementsList.size() >= MAX_LINKED_LIST_ITEMS) {
			System.out.println("Can't add more node");
			return;
		}
		Element node = ListElementCreator.createElement(value);
		Pane temp;
		for(int i=index;i<ElementsList.size();i++) {
			temp = ElementsList.get(i).getShape();
			if(i<3) {
				goUpDown(temp, 90);
			}else if(i==3) {
				goHorizontal(temp, 302);
			}else if(i>3) {
				goUpDown(temp, -90);
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
	}

	@Override
	public void deleteByIndex(int index) {
		// TODO Auto-generated method stub
		ElementsList.remove(index);
		Pane temp;
		for(int i=index;i<ElementsList.size();i++) {
			temp = ElementsList.get(i).getShape();
			System.out.println("Layout Y of element " + i + " = " + temp.getLayoutY() );
			System.out.println("Layout X of element after move " + i + " = " + temp.getLayoutX() );
			if(i<3) {
				goUpDown(temp,- 90);
			}
			if(i == 3) {
				goHorizontal(temp, -302);
			}
			if(i>3) {
				goUpDown(temp, 90);
			}
		
		}
		
	}
	
	public void goUpDown(Pane pane, int y) {
		TranslateTransition way = new TranslateTransition();
		way.setDuration(Duration.seconds(1));
		way.setByY(y);
		way.setNode(pane);
		way.play();
	
	}
	
	public void goHorizontal(Pane pane, int x) {
		TranslateTransition way = new TranslateTransition();
		way.setDuration(Duration.seconds(1));
		way.setByX(x);
		way.setNode(pane);
		way.play();

	}
}
