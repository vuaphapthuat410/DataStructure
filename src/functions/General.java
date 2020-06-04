package functions;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class General {
	public static final int MAX_LINKED_LIST_ITEMS = 8;
	public static final int MAX_STACK_SIZE = 8;
	
	public static void showAlertWithoutHeaderText(String title,String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);

		// Header Text: null
		alert.setHeaderText(null);
		alert.setContentText(content);

		alert.showAndWait();
	}
	
	public static void FadeInOut(Node node, int inValue, int outValue, double duration) {
		FadeTransition fade = new FadeTransition(Duration.seconds(duration), node);
		fade.setFromValue(inValue);
		fade.setToValue(outValue);
		fade.play();
	}
	
	public static void goUpDown(Pane pane, int y) {
		TranslateTransition way = new TranslateTransition();
		way.setDuration(Duration.seconds(1));
		way.setByY(y);
		way.setNode(pane);
		way.play();
	
	}
	
	public static void goHorizontal(Pane pane, int x) {
		TranslateTransition way = new TranslateTransition();
		way.setDuration(Duration.seconds(1));
		way.setByX(x);
		way.setNode(pane);
		way.play();

	}
}