package tracer;

import entities.Entity;
import entities.Plane;
import entities.Sphere;
import java.util.ArrayList;

/**
 *
 * @author james
 */
public class Scene {
    private final ArrayList<Entity> entities = new ArrayList();
    private final ArrayList<LightSource> lights = new ArrayList();
    private final RGB ambient;
    
    public Scene(RGB ambient){
        this.ambient = ambient;
    }
    
    public RGB getAmbient(){
        return ambient;
    }
    
    public ArrayList getEntities(){
        return entities;
    }
    
    public ArrayList getLights(){
        return lights;
    }
    
    public void addSphere(Vector position,  Vector diffuse, Vector specular, double transmissive, double emissive, double shininess, double radius){
        entities.add(new Sphere(position, diffuse, specular, transmissive, emissive, shininess, radius));
    }
    
    public void addPlane(Vector position, Vector diffuse, Vector specular, double transmissive, double emissive, double shininess, Vector normal){
        entities.add(new Plane(position, diffuse, specular, transmissive, emissive, shininess, normal));
    }
    
    public void addLight(Vector position, RGB intensityDiffuse, RGB intensitySpecular){
        lights.add(new LightSource(position, intensityDiffuse, intensitySpecular));
    }
    
    public Intersection intersect(Ray ray){
        Intersection intersection = null;
        double closest = Double.POSITIVE_INFINITY;
        for(Entity entity: entities){
            Vector intersectionPoint = entity.intersectionPoint(ray);
            if(intersectionPoint!=null){
                double distance = intersectionPoint.addVector(ray.getOrigin().scale(-1)).magnitude();
                if(distance<closest){
                    closest = distance;
                    Vector normal = entity.normalAt(intersectionPoint);
                    intersection = new Intersection(intersectionPoint, normal, entity);
                }                
            }  
        }
        //System.out.println("----------------------------------");
        return intersection;
    }
    
    
}
