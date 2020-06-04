/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.line;

/**
 *
 * @author vuaphapthuat410
 */
import javafx.scene.Group;
import javafx.scene.shape.Line;

import graph.cell.Cell;

public class Edge extends Group {

    protected Cell source;
    protected Cell target;

    Line line;

    public Edge(Cell source, Cell target) {

        this.source = source;
        this.target = target;

        source.addCellChild(target);
        target.addCellParent(source);

        line = new Line();

        line.startXProperty().bind( source.layoutXProperty().add(source.getBoundsInParent().getWidth() / 2.0));
        line.startYProperty().bind( source.layoutYProperty().add(source.getBoundsInParent().getHeight() / 2.0));

        line.endXProperty().bind( target.layoutXProperty().add( target.getBoundsInParent().getWidth() / 2.0));
        line.endYProperty().bind( target.layoutYProperty().add( target.getBoundsInParent().getHeight() / 2.0));

        getChildren().add(line);

    }

    public Cell getSource() {
        return source;
    }

    public Cell getTarget() {
        return target;
    }
    
    public void changeSource(Cell source) {
        this.target.getCellParents().remove(this.source);
        this.source.getCellChildren().remove(this.target);
        this.source = source;
        this.source.addCellChild(this.target);
        this.target.addCellParent(source);     
    }
    
    public void changeTarget(Cell target) {
        this.target.getCellParents().remove(this.source);
        this.source.getCellChildren().remove(this.target);
        this.target = target;
        this.source.addCellChild(this.target);
        this.target.addCellParent(this.source);   
    }

}
