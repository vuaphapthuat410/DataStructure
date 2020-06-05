package elements;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class TreeElement extends Element{
    
    List<TreeElement> children = new ArrayList<>();
    List<TreeElement> parents = new ArrayList<>();

    Node view;
    
    public TreeElement (String cellId) {
        this.value = new Label(cellId);
    }
    
    public TreeElement(String cellId, ShapeType type) {
        super();
        
        this.value = new Label(cellId);
        this.shape = new Pane();
        
        double width = 50;
        double height = 50;
        
        Shape shp;
        switch (type) {

        case RECTANGLE:
            shp = new Rectangle( 50,50);
            shp.setStroke(Color.DODGERBLUE);
            shp.setFill(Color.DODGERBLUE);
            break;

        case TRIANGLE:
            shp = new Polygon( width / 2, 0, width, height, 0, height);
            shp.setStroke(Color.RED);
            shp.setFill(Color.RED);
            break;
        case CIRCLE:
            shp  = new Circle(25);
            shp.setStroke(Color.YELLOWGREEN);
            shp.setFill(Color.YELLOWGREEN);
            break;

        default:
            throw new UnsupportedOperationException("Unsupported type: " + type);
        }

        Text text = new Text(cellId);
        text.setFill(Color.WHITE);

        StackPane view = new StackPane();
        view.getChildren().addAll(shp,text);
        setView( view);
      
    }

    public void addCellChild(TreeElement cell) {
        children.add(cell);
    }

    public List<TreeElement> getCellChildren() {
        return children;
    }

    public void addCellParent(TreeElement cell) {
        parents.add(cell);
    }

    public List<TreeElement> getCellParents() {
        return parents;
    }

    public void removeCellChild(TreeElement cell) {
        children.remove(cell);
    }

    public void setView(Node view) {

        this.view = view;
        getShape().getChildren().add(view);
    }

    public Node getView() {
        return this.view;
    }

    public String getCellId() {
        return this.value.getText();
    }          
        
}
