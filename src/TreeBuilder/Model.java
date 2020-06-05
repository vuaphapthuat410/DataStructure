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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import elements.TreeElement;
import elements.ShapeType;

public class Model {

    TreeElement graphParent;

    List<TreeElement> allCells;
    List<TreeElement> addedCells;
    List<TreeElement> removedCells;

    List<Edge> allEdges;
    List<Edge> addedEdges;
    List<Edge> removedEdges;

    Map<String,TreeElement> cellMap; // <id,cell>

    public Model() {

         graphParent = new TreeElement( "_ROOT_");

         // clear model, create lists
         clear();
    }

    public void clear() {

        allCells = new ArrayList<>();
        addedCells = new ArrayList<>();
        removedCells = new ArrayList<>();

        allEdges = new ArrayList<>();
        addedEdges = new ArrayList<>();
        removedEdges = new ArrayList<>();

        cellMap = new HashMap<>(); // <id,cell>

    }

    public void clearAddedLists() {
        addedCells.clear();
        addedEdges.clear();
    }

    public List<TreeElement> getAddedCells() {
        return addedCells;
    }

    public List<TreeElement> getRemovedCells() {
        return removedCells;
    }

    public List<TreeElement> getAllCells() {
        return allCells;
    }

    public List<Edge> getAddedEdges() {
        return addedEdges;
    }

    public List<Edge> getRemovedEdges() {
        return removedEdges;
    }

    public List<Edge> getAllEdges() {
        return allEdges;
    }

    public void addCell(String id, ShapeType type) {

        try {
            TreeElement node = new TreeElement(id, type);
            addCell(node);
        } catch (Exception e) {
            throw e;
        }   
    }

    private void addCell( TreeElement cell) {

        addedCells.add(cell);

        cellMap.put( cell.getCellId(), cell);

    }
    
    public void delCell(TreeElement cell) {
        
        removedCells.add(cell);
        
        cellMap.remove(cell.getCellId());
                
    }
    
    public TreeElement getCell(String id) {
        return cellMap.get(id);
    }
    
    public void addEdge( String sourceId, String targetId) {

        TreeElement sourceCell = cellMap.get( sourceId);
        TreeElement targetCell = cellMap.get( targetId);

        Edge edge = new Edge( sourceCell, targetCell);

        addedEdges.add( edge);

    }
    
    public void modEdge(String sourceId, String targetId) {
        TreeElement sCell = cellMap.get(sourceId);
        TreeElement tCell = cellMap.get(targetId);
        
        for(Edge edge : allEdges) {
            if(edge.getSource().equals(tCell) || edge.getTarget().equals(tCell))
                removedEdges.add(edge);
        }
        
        for(Edge edge : allEdges) {
            if(edge.getSource().equals(sCell)) {
                edge.changeSource(tCell);
            }
            else if(edge.getTarget().equals(sCell)) {
                edge.changeTarget(tCell);
            }      
        }
    }  

    /**
     * Attach all cells which don't have a parent to graphParent 
     * @param cellList
     */
    public void attachOrphansToGraphParent( List<TreeElement> cellList) {

        for( TreeElement cell: cellList) {
            if( cell.getCellParents().size() == 0) {
                graphParent.addCellChild( cell);
            }
        }

    }

    /**
     * Remove the graphParent reference if it is set
     * @param cellList
     */
    public void disconnectFromGraphParent( List<TreeElement> cellList) {

        for( TreeElement cell: cellList) {
            graphParent.removeCellChild( cell);
        }
    }

    public void merge() {

        // cells
        allCells.addAll( addedCells);
        allCells.removeAll( removedCells);

        addedCells.clear();
        removedCells.clear();

        // edges
        allEdges.addAll( addedEdges);
        allEdges.removeAll( removedEdges);

        addedEdges.clear();
        removedEdges.clear();

    }
}