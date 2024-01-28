package entities;

import tracer.RGB;
import maths.Ray;
import maths.Vector;

/**
 *
 * @author james
 */
public class Entity {

    protected Vector position;
    protected RGB colour;
    protected double diffuse;
    protected double specular;
    protected double transmissive;  // t should be 1-s
    protected double emissive;
    protected double shininess;

    public Vector getPosition() {
        return position;
    }

    public RGB getColour() {
        return colour;
    }

    public double getDiffuse() {
        return diffuse;
    }

    public double getSpecular() {
        return specular;
    }

    public double getTransmissive() {
        return transmissive;
    }

    public double getEmissive() {
        return emissive;
    }

    public double getShininess() {
        return shininess;
    }

    public Vector intersectionPoint(Ray ray) {
        return new Vector(0, 0, 0);
    }

    public Vector normalAt(Vector intersectionPoint) {
        return new Vector(0, 0, 0);
    }
}
