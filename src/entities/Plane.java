package entities;

import tracer.RGB;
import tracer.Ray;
import tracer.Vector;

/**
 *
 * @author james
 */
public class Plane extends Entity{
    private final Vector normal;
    
    public Plane(Vector position, Vector diffuse, Vector specular, double transmissive, RGB emissive, double shininess, Vector normal){
        this.position = position;
        this.diffuse = diffuse;
        this.specular = specular;
        this.transmissive = transmissive;
        this.emissive = emissive;
        this.shininess = shininess;
        this.normal = normal;
    }
    
    public Vector getNormal(){
        return normal;
    }
    
    @Override public Vector intersectionPoint(Ray ray){
        Vector p = ray.getOrigin();
        Vector d = ray.getDirection();
        double numerator = (p.addVector(position.scale(-1))).dot(normal);
        double denominator = d.dot(normal);

        if (denominator != 0) {
            double t = -numerator / denominator;
            if(t>0){
                return p.addVector(d.scale(t));
            }    
        }
        return null;
    }
    
    @Override public Vector normalAt(Vector intersectionPoint){
        return normal;
    }
    
}
