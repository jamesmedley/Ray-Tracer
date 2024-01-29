package tracer;

import maths.Vector;

/**
 *
 * @author james
 */
public class Material {

    private RGB colour;
    private Vector ambient;
    private Vector diffuse;
    private Vector specular;
    private Vector emissive;
    private double shininess;

    public Material(RGB colour, Vector ambient, Vector diffuse, Vector specular, Vector emissive, double shininess) {
        this.colour = colour;
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
        this.emissive = emissive;
        this.shininess = shininess;
    }

    public RGB getColour() {
        return colour;
    }

    public void setColour(RGB colour) {
        this.colour = colour;
    }

    public Vector getAmbient() {
        return ambient;
    }

    public void setAmbient(Vector ambient) {
        this.ambient = ambient;
    }

    public Vector getDiffuse() {
        return diffuse;
    }

    public void setDiffuse(Vector diffuse) {
        this.diffuse = diffuse;
    }

    public Vector getSpecular() {
        return specular;
    }

    public void setSpecular(Vector specular) {
        this.specular = specular;
    }

    public Vector getEmissive() {
        return emissive;
    }

    public void setEmissive(Vector emissive) {
        this.emissive = emissive;
    }

    public double getShininess() {
        return shininess;
    }

    public void setShininess(double shininess) {
        this.shininess = shininess;
    }

}
