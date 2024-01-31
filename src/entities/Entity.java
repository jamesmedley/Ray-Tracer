package entities;

import java.io.Serializable;
import maths.Ray;
import maths.Vector;
import tracer.Material;

/**
 *
 * @author james
 */
public class Entity implements Serializable {

    protected Vector position;
    protected Material material;

    public Vector getPosition() {
        return position;
    }

    public Material getMaterial() {
        return material;
    }

    public Vector intersectionPoint(Ray ray) {
        return new Vector(0, 0, 0);
    }

    public Vector normalAt(Vector intersectionPoint) {
        return new Vector(0, 0, 0);
    }
}
