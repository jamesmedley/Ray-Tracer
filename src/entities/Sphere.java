package entities;

import tracer.RGB;
import tracer.Vector;

/**
 *
 * @author james
 */
public class Sphere extends Entities{
    private final double radius;
    
    public Sphere(Vector position,  RGB colour, double[] reflectivity, double shininess, double radius){
        this.position = position;
        this.colour = colour;
        this.reflectivity = reflectivity;
        this.shininess = shininess;
        this.radius = radius;
    }
    
    public double getRadius(){
        return radius;
    }
    
}
