package entities;

import tracer.RGB;
import tracer.Vector;

/**
 *
 * @author james
 */
public class Entities {
    private Vector position;
    private RGB colour;
    
    public Vector getPosition(){
        return position;
    }
    
    public RGB getColour(){
        return colour;
    }
    
    public void setPosition(Vector position){
        this.position = position;
    }
    
    public void setColour(RGB colour){
        this.colour = colour;
    }
}
