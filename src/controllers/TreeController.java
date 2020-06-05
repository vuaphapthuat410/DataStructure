/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.animation.Animation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
        
import TreeBuilder.*;
import elements.TreeElement;
import elements.ShapeType;
import functions.TreeActions;
import functions.VisualActions;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class TreeController implements Initializable {

    @FXML
    private TextField tfValue;
    @FXML
    private TextField tfIndex;
    @FXML
    private ComboBox<ShapeType> optsShape;// choose shape
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnAddByIdx;
    @FXML
    private Button btnDel;
    @FXML
    private Button btnDelByIdx;
    @FXML
    private BorderPane paneView;

    /**
     * Initializes the controller class.
     */
    
    protected Graph graph;
    protected Model model;
    protected Layout layout;
    
    private VisualActions viTree;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        graph = new Graph();
        paneView.setCenter(graph.getScrollPane());
        
        optsShape.getItems().addAll(ShapeType.values());
        optsShape.setValue(ShapeType.RECTANGLE); //choose random
        
        addGraphComponents(graph);
        layout = new TreeLayout(graph);
        layout.execute();
        
        viTree = new TreeActions(graph, layout); 
    }    
    
    private void addGraphComponents(Graph graph) {

        model = graph.getModel();

        graph.beginUpdate();

        model.addCell("Cell A", ShapeType.RECTANGLE);
        model.addCell("Cell B", ShapeType.RECTANGLE);
        model.addCell("Cell C", ShapeType.RECTANGLE);
        model.addCell("Cell D", ShapeType.TRIANGLE);
        model.addCell("Cell E", ShapeType.RECTANGLE);
        model.addCell("Cell F", ShapeType.TRIANGLE);
        model.addCell("Cell G", ShapeType.RECTANGLE);

        model.addEdge("Cell A", "Cell B");
        model.addEdge("Cell A", "Cell C");
        model.addEdge("Cell B", "Cell D");
        model.addEdge("Cell B", "Cell E");
        model.addEdge("Cell C", "Cell F");
        model.addEdge("Cell C", "Cell G");

        graph.endUpdate();

    }

    @FXML
    private void addItem(ActionEvent event) {
        String value = tfValue.getText();
        
        TreeActions.getShape(optsShape.getValue()); //set shape
        viTree.add(value, paneView);
        
        tfValue.clear();
    }

    @FXML
    private void addByIdx(ActionEvent event) {
        /*model = graph.getModel();
        double x1 = 0, x2 = 0;
        double y1 = 0, y2 = 0;
        
        graph.beginUpdate();
        
        model.addCell(tfValue.getText(), optsShape.getValue());
        
        int cellsSize = model.getAllCells().size();
        
        if(cellsSize == 0) {
            graph.endUpdate();
            layout.execute();
            return;
        }
        if(cellsSize%2 != 0) 
            cellsSize = (cellsSize-1)/2;
        else 
            cellsSize = (cellsSize/2)-1;
        
        Cell tmpCell = model.getAllCells().get(cellsSize);
        Cell swpCell = model.getAllCells().get(Integer.parseInt(tfIndex.getText()));
        
        model.addEdge(tmpCell.getCellId(),swpCell.getCellId());
        
        graph.endUpdate();
        */
        tfValue.clear();
        tfIndex.clear();
    }

    @FXML
    private void delItem(ActionEvent event) {
          TreeActions.passDelValue(tfValue.getText());
          viTree.delete(paneView);
           
          tfValue.clear();               
    }

    @FXML
    private void delByIdx(ActionEvent event) {
        int idx = 0;
        
        try {
                idx = Integer.parseInt(tfIndex.getText());
        } catch (NumberFormatException e) {
                // TODO: handle exception
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Parsing error");
                error.setContentText("Error occurs. Input is not an integer.");
                error.showAndWait();
                return;
        }
        
        viTree.deleteByIndex(idx, paneView);
        
        tfValue.clear();
        tfIndex.clear();
    }
    
}
