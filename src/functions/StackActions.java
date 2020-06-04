package functions;

import java.util.LinkedList;

import elements.Element;
import elements.creators.StackElementCreator;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class StackActions extends VisualActions {

	public StackActions() {
		super();
		ElementsList = new LinkedList<Element>();
	}

	@Override
	public boolean add(String value, Pane pane) {
		// TODO Auto-generated method stub
		if (ElementsList.size() >= General.MAX_STACK_SIZE) {
//			General.showAlertWithoutHeaderText("Alert", "You can only create " + General.MAX_STACK_SIZE + " elements");
			Element overflow = StackElementCreator.createElement("OVERFLOW!!!");
			overflow.getShape().setLayoutX(291);
			overflow.getShape().setLayoutY(53);
			TranslateTransition wayHorizontal = new TranslateTransition(Duration.seconds(1), overflow.getShape());
			wayHorizontal.setByX(180);
			wayHorizontal.play();
			wayHorizontal.setOnFinished(e ->{
				TranslateTransition wayDown = new TranslateTransition(Duration.seconds(1), overflow.getShape());
				wayDown.setByY(180);
				wayDown.play();
				wayDown.setOnFinished(evt ->{
					General.deleteShape(overflow.getShape(), pane);
				});
			});
			pane.getChildren().add(overflow.getShape());
			
			return false;
		}
		Element node = StackElementCreator.createElement(value);
		ElementsList.push(node);

		node.getShape().setLayoutX(291);
		// node.getShape().setLayoutY(General.MAX_STACK_SIZE*42 - stack.size()*42);
		node.getShape().setLayoutY(53);
		General.goUpDown(node.getShape(), (General.MAX_STACK_SIZE + 1) * 43 - ElementsList.size() * 43);
		pane.getChildren().add(node.getShape());
		
		return true;
	}

	@Override
	public boolean delete(Pane pane) {
		// TODO Auto-generated method stub
		if (ElementsList.size() == 0) {
			General.showAlertWithoutHeaderText("Alert", "Stack is empty");
			return false;
		} else {
			Element node = ElementsList.peek();
			goUp(node.getShape(), -10,pane);
			return true;
		}
	}

	private void goUp(Pane pane, int y,Pane parentPane) {
		TranslateTransition way = new TranslateTransition();
		way.setDuration(Duration.seconds(1));
		way.setToY(y);
		way.setNode(pane);
		way.play();
		way.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				ElementsList.pop();
				General.deleteShape(pane, parentPane);
			}
		});
	}

	public Element getFirstElement() {
		// TODO Auto-generated method stub
		if (ElementsList.size() == 0) {
			General.showAlertWithoutHeaderText("Alert", "Stack is empty");
			return null;
		} else {
			Element peak = ElementsList.peek();
			return peak;
		}
	}

}
