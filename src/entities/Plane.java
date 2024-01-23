package entities;

import tracer.RGB;
import tracer.Vector;

/**
 *
 * @author james
 */
public class Plane extends Entities{
    private final Vector normal;
    
    public Plane(Vector position,  RGB colour, double[] reflectivity, double shininess, Vector normal){
        this.position = position;
        this.colour = colour;
        this.reflectivity = reflectivity;
        this.shininess = shininess;
        this.normal = normal;
    }
    
    public Vector getNormal(){
        return normal;
    }
    
}
