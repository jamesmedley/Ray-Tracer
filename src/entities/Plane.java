package entities;

import maths.Ray;
import maths.Vector;
import tracer.RGB;

/**
 *
 * @author james
 */
public class Plane extends Entity {

    private final Vector normal;

    public Plane(Vector position, RGB colour, double diffuse, double specular, double transmissive, double emissive, double shininess, Vector normal) {
        this.position = position;
        this.colour = colour;
        this.diffuse = diffuse;
        this.specular = specular;
        this.transmissive = transmissive;
        this.emissive = emissive;
        this.shininess = shininess;
        this.normal = normal;
    }

    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector intersectionPoint(Ray ray) {
        Vector p = ray.getOrigin();
        Vector d = ray.getDirection();
        double numerator = (p.addVector(position.scale(-1))).dot(normal);
        double denominator = d.dot(normal);

        if (denominator != 0) {
            double t = -numerator / denominator;
            if (t > 0) {
                return p.addVector(d.scale(t));
            }
        }
        return null;
    }

    @Override
    public Vector normalAt(Vector intersectionPoint) {
        return normal;
    }

}
