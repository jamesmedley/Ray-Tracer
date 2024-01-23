package entities;

import tracer.RGB;
import tracer.Vector;

/**
 *
 * @author james
 */
public class Plane extends Entities{
    private final Vector normal;
    
    public Plane(Vector position, Vector normal, RGB colour){
        setPosition(position);
        this.normal = normal;
        setColour(colour);
    }
    
    public Vector getNormal(){
        return normal;
    }
    
}
