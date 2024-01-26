package entities;

import tracer.RGB;
import tracer.Ray;
import tracer.Vector;

/**
 *
 * @author james
 */
public class Entity {
    protected Vector position;
    protected Vector diffuse;
    protected Vector specular;
    protected double transmissive;  // t should be 1-s
    protected RGB emissive;
    protected double shininess;
    
    public Vector getPosition(){
        return position;
    }
    
    public Vector getDiffuse(){
        return diffuse;
    }
    
    public Vector getSpecular(){
        return specular;
    }
    
    public double getTransmissive(){
        return transmissive;
    }
    
    public RGB getEmissive(){
        return emissive;
    }
    
    public double getShininess(){
        return shininess;
    }
    
    public Vector intersectionPoint(Ray ray){
        return new Vector(0,0,0);
    }
    
    public Vector normalAt(Vector intersectionPoint){
        return new Vector(0,0,0);
    }
}
