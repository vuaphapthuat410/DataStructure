/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

/**
 *
 * @author vuaphapthuat410
 */
public enum ShapeType {
    RECTANGLE("RECTANGLE"),
    TRIANGLE("TRIANGLE"),
    CIRCLE("CIRCLE");
    
    private String name;
    
    private ShapeType(String theType) {
        this.name = theType;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
