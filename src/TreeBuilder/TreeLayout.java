/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TreeBuilder;

/**
 *
 * @author vuaphapthuat410
 */
import elements.TreeElement;
import java.util.List;
import java.util.Random;
import static java.lang.Math.floor;
import static java.lang.Math.pow;

public class TreeLayout extends Layout {

    Graph graph;

    Random rnd = new Random();

    public TreeLayout(Graph graph) {

        this.graph = graph;

    }
    
    public int calHeight(List<TreeElement> cells) {
        int i = cells.size();
        int j = 0;
        
        while(i > 0) {
            i = i - (int) floor(pow(2,j));
            j++;          
        }
        
        return j;
    }
    
    @Override
    public void execute() {
        List<TreeElement> cells = graph.getModel().getAllCells();
        
        int i, j=0;
        int limit = 0;
        int tmpId;
        double x;
        double y;
        
        for (i=0;i<cells.size();i++) {            
            if(i==0) {
                 x = 362.5;
                 y = 27;
            }
            else {
                if(i%2 != 0) {
                    tmpId = (i-1)/2;
                     x = cells.get(tmpId).getShape().getLayoutX() - (16/(pow(2,j)))*50; //j run from 0
                     y = cells.get(tmpId).getShape().getLayoutY() + 200;
                }
                else {
                    tmpId = (i/2)-1;
                     x = cells.get(tmpId).getShape().getLayoutX() + (16/(pow(2,j)))*50;
                     y = cells.get(tmpId).getShape().getLayoutY() + 200;
                }
            } 
            
            if(i == limit) {
                j++;
                limit = limit + (int) floor(pow(2,j)); //take max index of each layer //convert to int, never lost precision because it just int exponent
            }
            
            cells.get(i).getShape().relocate(x, y);
        }
    }
    
    @Override
    public void update() {
        List<TreeElement> cells = graph.getModel().getAllCells();
        
        int i = cells.size() - 1;
        int j = calHeight(cells);
        int parentId = 0;
        
        double x;
        double y;
            
            if(i==0) {
                 x = 362.5;
                 y = 27;
            }
            else {
                if(i%2 != 0) {
                    parentId = (i-1)/2;
                     x = cells.get(parentId).getShape().getLayoutX() - (32/(pow(2,j)))*50; // j return start from 1
                     y = cells.get(parentId).getShape().getLayoutY() + 200;
                }
                else {
                    parentId = (i/2)-1;
                     x = cells.get(parentId).getShape().getLayoutX() + (32/(pow(2,j)))*50;
                     y = cells.get(parentId).getShape().getLayoutY() + 200;
                }
            } 
            
            cells.get(i).getShape().relocate(x, y);
            cells.get(i).getShape().toFront();
            cells.get(parentId).getShape().toFront();
    }
}
