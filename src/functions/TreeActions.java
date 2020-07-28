package functions;

import elements.ShapeType;
import elements.TreeElement;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import utils.Graph;
import utils.Layout;
import utils.Model;

public class TreeActions extends VisualActions {
    
    protected Graph graph;
    protected Model model;
    protected Layout layout;
    
    protected static ShapeType type;
    protected static String deleteElementId;
    
    public TreeActions(Graph graph, Layout layout) {
        super();
        
        this.graph = graph;
        this.layout = layout;
    }
    
    public static void getShape(ShapeType shape) {
        type = shape;
    }
    public static void passDelValue(String delId) {
        deleteElementId = delId;
    }
    
    @Override
    public boolean add(String value, Pane pane) {
            // TODO Auto-generated method stub
        model = graph.getModel();

        graph.beginUpdate();

        model.addCell(value, type);

        int cellsSize = model.getAllCells().size();

        if(cellsSize == 0) {
            graph.endUpdate();
            layout.update();

            return true;
        }
        if(cellsSize%2 != 0) 
            cellsSize = (cellsSize-1)/2;
        else 
            cellsSize = (cellsSize/2)-1;

        TreeElement tmpCell = model.getAllCells().get(cellsSize);
        model.addEdge(tmpCell.getCellId(),value);       

        graph.endUpdate();
        layout.update();

        return false;
    }

    @Override
    public boolean delete(Pane pane) {
            // TODO Auto-generated method stub
        model = graph.getModel();
        double x;
        double y;
        
        graph.beginUpdate();
        
        try {
            TreeElement tmpCell = model.getCell(deleteElementId);
            TreeElement lastCell = model.getAllCells().get(model.getAllCells().size()-1);
            
            x = tmpCell.getShape().getLayoutX();
            y = tmpCell.getShape().getLayoutY();
            
            int idx = model.getAllCells().indexOf(tmpCell);
            
            model.getAllCells().remove(lastCell);
            model.getAllCells().add(idx, lastCell);  
            
            TranslateTransition translate = new TranslateTransition();  
            translate.setByX(x - lastCell.getShape().getLayoutX());
            translate.setByY(y - lastCell.getShape().getLayoutY());
            
            translate.setDuration(Duration.seconds(1));  
            translate.setNode(lastCell.getShape());
            translate.play();
            
            translate.setOnFinished(evt -> {
                TranslateTransition newTransition = new TranslateTransition();  
                newTransition.setByX(lastCell.getShape().getLayoutX() - x);
                newTransition.setByY(lastCell.getShape().getLayoutY() - y);
                newTransition.setDuration(Duration.millis(1));  
                newTransition.setNode(lastCell.getShape());
                newTransition.play();
                
                newTransition.setOnFinished(et ->{                 
                    lastCell.getShape().setLayoutX(x); 
                    lastCell.getShape().setLayoutY(y);  

                    model.modEdge(tmpCell.getCellId(), lastCell.getCellId());  //swap with last node               
                    model.delCell(tmpCell);  

                    graph.endUpdate();
                });
                
            });
                
            
        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Delete error");
            error.setContentText("Error occurs. Maybe cell not exist.");
            error.showAndWait();
        }
            return false;
    }

    @Override
    public boolean addByIndex(String value,int idx,Pane pane) {
            return false;
    }


    @Override
    public boolean deleteByIndex(int idx, Pane pane) {
        model = graph.getModel();
        double x;
        double y;
        
        graph.beginUpdate();
        
        try {
            
            TreeElement tmpCell = model.getAllCells().get(idx);
            TreeElement lastCell = model.getAllCells().get(model.getAllCells().size()-1);
            
            x = tmpCell.getShape().getLayoutX();
            y = tmpCell.getShape().getLayoutY();
            
            model.getAllCells().remove(lastCell);
            model.getAllCells().add(idx, lastCell);  
            
            TranslateTransition translate = new TranslateTransition();  
            translate.setByX(x - lastCell.getShape().getLayoutX());
            translate.setByY(y - lastCell.getShape().getLayoutY());
            
            translate.setDuration(Duration.seconds(1));  
            translate.setNode(lastCell.getShape());
            translate.play();
            
            translate.setOnFinished(evt -> {
                TranslateTransition newTransition = new TranslateTransition();  
                newTransition.setByX(lastCell.getShape().getLayoutX() - x);
                newTransition.setByY(lastCell.getShape().getLayoutY() - y);
                newTransition.setDuration(Duration.millis(1));  
                newTransition.setNode(lastCell.getShape());
                newTransition.play();
                
                newTransition.setOnFinished(et ->{                 
                    lastCell.getShape().setLayoutX(x); 
                    lastCell.getShape().setLayoutY(y);  

                    model.modEdge(tmpCell.getCellId(), lastCell.getCellId());  //swap with last node               
                    model.delCell(tmpCell);  

                    graph.endUpdate();

                });
                
            });
                
            
        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Delete error");
            error.setContentText("Error occurs. Maybe cell not exist.");
            error.showAndWait();
        }
        
        return false;
    }

}
