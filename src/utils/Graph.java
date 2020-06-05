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
import elements.TreeElement;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

public class Graph {

    private Model model;

    private Group canvas;

    private ZoomableScrollPane scrollPane;

    MouseGestures mouseGestures;

    /**
     * the pane wrapper is necessary or else the scrollpane would always align
     * the top-most and left-most child to the top and left eg when you drag the
     * top child down, the entire scrollpane would move down
     */
    NodeLayer nodeLayer;

    public Graph() {

        this.model = new Model();

        canvas = new Group();
        nodeLayer = new NodeLayer();

        canvas.getChildren().add(nodeLayer);

        mouseGestures = new MouseGestures(this);

        scrollPane = new ZoomableScrollPane(canvas);

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

    }

    public ScrollPane getScrollPane() {
        return this.scrollPane;
    }

    public Pane getNodeLayer() {
        return this.nodeLayer;
    }

    public Model getModel() {
        return model;
    }

    public void beginUpdate() {
    }

    public void endUpdate() {

        // add components to graph pane
        getNodeLayer().getChildren().addAll(model.getAddedEdges());
        for(TreeElement cell : model.getAddedCells())
            getNodeLayer().getChildren().add(cell.getShape());

        // remove components from graph pane
        for(TreeElement cell : model.getRemovedCells())
            getNodeLayer().getChildren().remove(cell.getShape());
        getNodeLayer().getChildren().removeAll(model.getRemovedEdges());

        // enable dragging of cells
        for (TreeElement cell : model.getAddedCells()) {
            mouseGestures.makeDraggable(cell.getShape());
        }

        // every cell must have a parent, if it doesn't, then the graphParent is
        // the parent
        getModel().attachOrphansToGraphParent(model.getAddedCells());

        // remove reference to graphParent
        getModel().disconnectFromGraphParent(model.getRemovedCells());

        // merge added & removed cells with all cells
        getModel().merge();

    }

    public double getScale() {
        return this.scrollPane.getScaleValue();
    }
}
