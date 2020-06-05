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
import utils.*;
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
        
      
        layout = new TreeLayout(graph);
        layout.execute();
        
        viTree = new TreeActions(graph, layout); 
    }    
    
    

    @FXML
    private void addItem(ActionEvent event) {
        String value = tfValue.getText();
        
        TreeActions.getShape(optsShape.getValue()); //set shape
        viTree.add(value, paneView);
        
        tfValue.clear();
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
