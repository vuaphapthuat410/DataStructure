/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author vuaphapthuat410
 */
import javafx.scene.Group;
import javafx.scene.shape.Line;

import elements.TreeElement;

public class Edge extends Group {

    protected TreeElement source;
    protected TreeElement target;

    Line line;

    public Edge(TreeElement source, TreeElement target) {

        this.source = source;
        this.target = target;

        source.addCellChild(target);
        target.addCellParent(source);

        line = new Line();

        line.startXProperty().bind( source.getShape().layoutXProperty().add(source.getShape().getBoundsInParent().getWidth() / 2.0));
        line.startYProperty().bind( source.getShape().layoutYProperty().add(source.getShape().getBoundsInParent().getHeight() / 2.0));

        line.endXProperty().bind( target.getShape().layoutXProperty().add( target.getShape().getBoundsInParent().getWidth() / 2.0));
        line.endYProperty().bind( target.getShape().layoutYProperty().add( target.getShape().getBoundsInParent().getHeight() / 2.0));

        getChildren().add(line);

    }

    public TreeElement getSource() {
        return source;
    }

    public TreeElement getTarget() {
        return target;
    }
    
    public void changeSource(TreeElement source) {
        this.target.getCellParents().remove(this.source);
        this.source.getCellChildren().remove(this.target);
        
        this.source = source;
        this.source.addCellChild(this.target);
        this.target.addCellParent(source);     
        
        line.startXProperty().bind( source.getShape().layoutXProperty().add(source.getShape().getBoundsInParent().getWidth() / 2.0));
        line.startYProperty().bind( source.getShape().layoutYProperty().add(source.getShape().getBoundsInParent().getHeight() / 2.0));
        
    }
    
    public void changeTarget(TreeElement target) {
        this.target.getCellParents().remove(this.source);
        this.source.getCellChildren().remove(this.target);
        this.target = target;
        this.source.addCellChild(this.target);
        this.target.addCellParent(this.source); 
        
        line.endXProperty().bind( target.getShape().layoutXProperty().add( target.getShape().getBoundsInParent().getWidth() / 2.0));
        line.endYProperty().bind( target.getShape().layoutYProperty().add( target.getShape().getBoundsInParent().getHeight() / 2.0));
        
    }

}
