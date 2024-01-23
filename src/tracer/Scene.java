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
    final private ArrayList<Entities> entities = new ArrayList();

    public ArrayList getEntities(){
        return entities;
    }
    
    public void addSphere(Vector position, double radius, RGB colour){
        entities.add(new Sphere(position, radius, colour));
    }
    
    public void addPlane(Vector position, Vector normal, RGB colour){
        entities.add(new Plane(position, normal, colour));
    }
}
