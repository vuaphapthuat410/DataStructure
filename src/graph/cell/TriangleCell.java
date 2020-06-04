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
import javafx.scene.shape.Polygon;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class TriangleCell extends Cell {

    public TriangleCell( String id) {
        super( id);

        double width = 50;
        double height = 50;

        Polygon pol = new Polygon( width / 2, 0, width, height, 0, height);
        pol.setStroke(Color.RED);
        pol.setFill(Color.RED);

        Text text = new Text(id);
        text.setFill(Color.WHITE);

        StackPane view = new StackPane();
        view.getChildren().addAll(pol,text);
        setView( view);

    }

}
