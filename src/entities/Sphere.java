package entities;

import tracer.RGB;
import tracer.Vector;

/**
 *
 * @author james
 */
public class Sphere extends Entities{
    private final double radius;
    
    public Sphere(Vector position, double radius, RGB colour){
        setPosition(position);
        this.radius = radius;
        setColour(colour);
    }
    
    public double getRadius(){
        return radius;
    }
    
}
