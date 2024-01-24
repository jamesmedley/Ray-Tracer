package tracer;

import entities.Entities;
import entities.Plane;
import entities.Sphere;
import java.util.ArrayList;

/**
 *
 * @author james
 */
public class Scene {
    private final ArrayList<Entities> entities = new ArrayList();
    private final double ambient;
    
    public Scene(double ambient){
        this.ambient = ambient;
    }
    
    public double getAmbient(){
        return ambient;
    }
    
    public ArrayList getEntities(){
        return entities;
    }
    
    public void addSphere(Vector position,RGB colour, double[] reflectivity, double shininess, double radius){
        entities.add(new Sphere(position, colour, reflectivity, shininess, radius));
    }
    
    public void addPlane(Vector position,RGB colour, double[] reflectivity, double shininess, Vector normal){
        entities.add(new Plane(position, colour, reflectivity, shininess, normal));
    }
}
