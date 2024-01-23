package entities;

import tracer.RGB;
import tracer.Vector;

/**
 *
 * @author james
 */
public class Entities {
    protected Vector position;
    protected RGB colour;
    protected double[] reflectivity; // [ambient, diffuse, specular]
    protected double shininess;
    
    public Vector getPosition(){
        return position;
    }
    
    public RGB getColour(){
        return colour;
    }
    
    public double[] getReflectivity(){
        return reflectivity;
    }
    
    public double getShininess(){
        return shininess;
    }
}
