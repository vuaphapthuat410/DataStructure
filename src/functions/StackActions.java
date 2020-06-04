package functions;

import java.util.Stack;

import elements.Element;
import elements.creators.StackElementCreator;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class StackActions implements Actions{
	
	private Stack<Element> stack;
	private ObservableList<Node> arrowList;
	
	public StackActions() {
		super();
		stack = new Stack<Element>();
	}
	
	public Stack<Element> getStack() {
		return stack;
	}

	public void setStack(Stack<Element> stack) {
		this.stack = stack;
	}

	public ObservableList<Node> getArrowList() {
		return arrowList;
	}

	public void setArrowList(ObservableList<Node> arrowList) {
		this.arrowList = arrowList;
	}

	
	@Override
	public void add(String value) {
		// TODO Auto-generated method stub
		if(stack.size() >= General.MAX_STACK_SIZE) {
			General.showAlertWithoutHeaderText("Alert", "You can only create " + General.MAX_STACK_SIZE + " elements");
			return;
		}
		Element node = StackElementCreator.createElement(value);
		stack.push(node);

		node.getShape().setLayoutX(500);
		// node.getShape().setLayoutY(General.MAX_STACK_SIZE*42 - stack.size()*42);
		node.getShape().setLayoutY(100);
		General.goUpDown(node.getShape(), General.MAX_STACK_SIZE*42 - stack.size()*42);
	}

		@Override
	public void delete() {
		// TODO Auto-generated method stub
		if(stack.size() == 0) {
			General.showAlertWithoutHeaderText("Alert", "Stack is empty");
			return;
		}
		else {
			Element node = stack.peek();
			General.goUpDown(node.getShape(), General.MAX_STACK_SIZE*42 );
			stack.pop();
		}		
	}
	
	private void goDown(Pane pane, int y) {
		TranslateTransition way = new TranslateTransition();
		way.setDuration(Duration.seconds(1));
		way.setByY(y);
		way.setNode(pane);
		way.play();
		way.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	stack.pop();
            }
        });
	}
		
	public Element getFirstElement() {
		// TODO Auto-generated method stub
		if(stack.size() == 0) {
			General.showAlertWithoutHeaderText("Alert", "Stack is empty");
			return null;
		}
		else{ 
			Element peak = stack.peek();
			return peak;
		}
	}
	
}
