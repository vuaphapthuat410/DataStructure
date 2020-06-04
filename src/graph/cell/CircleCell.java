/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.cell;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author vuaphapthuat410
 */
public class CircleCell extends Cell {
    public CircleCell( String id) {
        super( id);

        double width = 50;
        double height = 50;

        Circle  cir = new Circle(25);
        cir.setStroke(Color.YELLOWGREEN);
        cir.setFill(Color.YELLOWGREEN);

        Text text = new Text(id);
        text.setFill(Color.WHITE);

        StackPane view = new StackPane();
        view.getChildren().addAll(cir,text);
        setView( view);

    }
}
