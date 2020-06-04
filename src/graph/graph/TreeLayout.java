/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.graph;

/**
 *
 * @author vuaphapthuat410
 */
import java.util.List;
import java.util.Random;
import graph.cell.Cell;
import static java.lang.Math.floor;
import static java.lang.Math.pow;

public class TreeLayout extends Layout {

    Graph graph;

    Random rnd = new Random();

    public TreeLayout(Graph graph) {

        this.graph = graph;

    }
    
    public int calHeight(List<Cell> cells) {
        int i = cells.size();
        int j = 0;
        while(i > 0) {
            j++;
            i = i - (int) floor(pow(2,j));
        }
        
        return j;
    }
    
    @Override
    public void execute() {
        List<Cell> cells = graph.getModel().getAllCells();
        
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
                     x = cells.get(tmpId).getLayoutX() - (16/(pow(2,j)))*50;
                     y = cells.get(tmpId).getLayoutY() + 200;
                }
                else {
                    tmpId = (i/2)-1;
                     x = cells.get(tmpId).getLayoutX() + (16/(pow(2,j)))*50;
                     y = cells.get(tmpId).getLayoutY() + 200;
                }
            } 
            
            if(i == limit) {
                j++;
                limit = limit + (int) floor(pow(2,j)); //take max index of each layer //convert to int, never lost precision because it just int exponent
            }
            
            cells.get(i).relocate(x, y);
        }
    }
    
    @Override
    public void update() {
        List<Cell> cells = graph.getModel().getAllCells();
        
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
                     x = cells.get(parentId).getLayoutX() - (16/(pow(2,j)))*50;
                     y = cells.get(parentId).getLayoutY() + 200;
                }
                else {
                    parentId = (i/2)-1;
                     x = cells.get(parentId).getLayoutX() + (16/(pow(2,j)))*50;
                     y = cells.get(parentId).getLayoutY() + 200;
                }
            } 
            
            cells.get(i).relocate(x, y);
            cells.get(i).toFront();
            cells.get(parentId).toFront();
    }
}
