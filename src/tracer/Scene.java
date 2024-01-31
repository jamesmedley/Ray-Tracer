package tracer;

import maths.Intersection;
import maths.Ray;
import maths.Vector;
import entities.Entity;
import entities.Plane;
import entities.Sphere;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author james
 */
public class Scene implements Serializable {

    private final ArrayList<Entity> entities;
    private final ArrayList<LightSource> lights;
    private final RGB ambient;

    public Scene(RGB ambient) {
        this.lights = new ArrayList();
        this.entities = new ArrayList();
        this.ambient = ambient;
    }

    public RGB getAmbient() {
        return ambient;
    }

    public ArrayList getEntities() {
        return entities;
    }

    public ArrayList getLights() {
        return lights;
    }

    public void addSphere(Vector position, Material material, double radius) {
        entities.add(new Sphere(position, material, radius));
    }

    public void addPlane(Vector position, Material material, Vector normal) {
        entities.add(new Plane(position, material, normal));
    }

    public void addLight(Vector position, RGB colour, double intensity) {
        lights.add(new LightSource(position, colour, intensity));
    }

    public Intersection intersect(Ray ray) {
        Intersection intersection = null;
        double closest = Double.POSITIVE_INFINITY;
        for (Entity entity : entities) {
            Vector intersectionPoint = entity.intersectionPoint(ray);
            if (intersectionPoint != null) {
                double distance = intersectionPoint.addVector(ray.getOrigin().scale(-1)).magnitude();
                if (distance < closest) {
                    closest = distance;
                    Vector normal = entity.normalAt(intersectionPoint);
                    intersection = new Intersection(intersectionPoint, normal, entity);
                }
            }
        }
        return intersection;
    }

}
