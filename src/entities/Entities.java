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
    protected double diffuse;
    protected double specular;
    protected double transmissive;
    protected double emissive;
    protected double shininess;
    
    public Vector getPosition(){
        return position;
    }
    
    public RGB getColour(){
        return colour;
    }
    
    public double getDiffuse(){
        return diffuse;
    }
    
    public double getSpecular(){
        return specular;
    }
    
    public double getTransmissive(){
        return transmissive;
    }
    
    public double getEmissive(){
        return emissive;
    }
    
    public double getShininess(){
        return shininess;
    }
}
