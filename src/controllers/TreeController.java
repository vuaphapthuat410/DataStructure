/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import graph.cell.Cell;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import graph.cell.CellType;
import graph.model.Model;
import graph.graph.Graph;
import graph.graph.Layout;
import graph.graph.TreeLayout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
        

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
    private ComboBox<CellType> optsShape;// choose shape
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        graph = new Graph();
        paneView.setCenter(graph.getScrollPane());
        
        optsShape.getItems().addAll(CellType.values());
        optsShape.setValue(CellType.RECTANGLE); //choose random
        
        addGraphComponents(graph);
        layout = new TreeLayout(graph);
        layout.execute();
        
    }    
    
    private void addGraphComponents(Graph graph) {

        model = graph.getModel();

        graph.beginUpdate();

        model.addCell("Cell A", CellType.TRIANGLE);
        model.addCell("Cell B", CellType.RECTANGLE);
        model.addCell("Cell C", CellType.RECTANGLE);
        model.addCell("Cell D", CellType.TRIANGLE);
        model.addCell("Cell E", CellType.RECTANGLE);
        model.addCell("Cell F", CellType.TRIANGLE);
        model.addCell("Cell G", CellType.RECTANGLE);

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
        model = graph.getModel();
        
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
        model.addEdge(tmpCell.getCellId(),tfValue.getText());
        
        
        graph.endUpdate();
        layout.update();
    }

    @FXML
    private void addByIdx(ActionEvent event) {
        model = graph.getModel();
        
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
        model.addEdge(tmpCell.getCellId(),tfValue.getText());
        
        graph.endUpdate();
        layout.update();
    }

    @FXML
    private void delItem(ActionEvent event) {
        model = graph.getModel();
        double x;
        double y;
        
        graph.beginUpdate();
        
        try {
            Cell tmpCell = model.getCell(tfValue.getText());
            x = tmpCell.getLayoutX();
            y = tmpCell.getLayoutY();
            System.out.println(x + "" + y );
            
            int idx = model.getAllCells().indexOf(tmpCell);
            model.modEdge(tmpCell.getCellId());
            model.delCell(tmpCell);
            
            tmpCell = model.getAllCells().get(model.getAllCells().size()-1);
            model.getAllCells().remove(tmpCell);
            model.getAllCells().add(idx, tmpCell);
            
            TranslateTransition translate = new TranslateTransition();  
            translate.setToX(x - tmpCell.getLayoutX());
            translate.setToY(y - tmpCell.getLayoutY());
            translate.setDuration(Duration.millis(1000));  
            translate.setNode(tmpCell);
            translate.play();
            
            tmpCell.setTranslateX(x); //dunno why but it fix the error while moving using translate
            tmpCell.setTranslateY(y);
            
        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Delete error");
            error.setContentText("Error occurs. Maybe cell not exist.");
            error.showAndWait();
        }  
        
        graph.endUpdate();
        
    }

    @FXML
    private void delByIdx(ActionEvent event) {
        model = graph.getModel();
        double x;
        double y;
        
        graph.beginUpdate();
        
        try {
            String index = tfIndex.getText();
            int idx = Integer.parseInt(index);
            Cell tmpCell = model.getAllCells().get(idx);
            
            x = tmpCell.getLayoutX();
            y = tmpCell.getLayoutY();
            
            
            model.modEdge(tmpCell.getCellId());
            model.delCell(tmpCell);
            
            tmpCell = model.getAllCells().get(model.getAllCells().size()-1);
            model.getAllCells().remove(tmpCell);
            model.getAllCells().add(idx, tmpCell);
            
            TranslateTransition translate = new TranslateTransition();  
            translate.setToX(x - tmpCell.getLayoutX());
            translate.setToY(y - tmpCell.getLayoutY());
            translate.setDuration(Duration.millis(1000));  
            translate.setNode(tmpCell);
            translate.play();
            
            tmpCell.setTranslateX(x); //dunno why but it fix the error while moving using translate
            tmpCell.setTranslateY(y);
            
        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Delete error");
            error.setContentText("Error occurs. Maybe cell not exist.");
            error.showAndWait();
        }  
        
        graph.endUpdate();
    }
    
}
