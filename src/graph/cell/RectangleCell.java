/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.cell;

/**
 *
 * @author vuaphapthuat410
 */
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class RectangleCell extends Cell {

    public RectangleCell( String id) {
        super( id);

        Rectangle rec = new Rectangle( 50,50);
        rec.setStroke(Color.DODGERBLUE);
        rec.setFill(Color.DODGERBLUE);
        
        Text text = new Text(id);
        text.setFill(Color.WHITE);

        StackPane view = new StackPane();
        view.getChildren().addAll(rec,text);
        setView( view);

    }

}
