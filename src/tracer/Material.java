package tracer;

import java.io.Serializable;
import maths.Vector;

/**
 *
 * @author james
 */
public class Material implements Serializable {

    private final RGB colour;
    private final Vector ambient;
    private final Vector diffuse;
    private final Vector specular;
    private final double transmissive;
    private final Vector emissive;
    private final double shininess;
    private final double roughness;
    private final double refractiveIndex;

    public Material(RGB colour, Vector ambient, Vector diffuse, Vector specular, double transmissive, Vector emissive, double shininess, double roughness, double refractiveIndex) {
        this.colour = colour;
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
        this.transmissive = transmissive;
        this.emissive = emissive;
        this.shininess = shininess;
        this.roughness = roughness;
        this.refractiveIndex = refractiveIndex;
    }

    public RGB getColour() {
        return colour;
    }

    public Vector getAmbient() {
        return ambient;
    }

    public Vector getDiffuse() {
        return diffuse;
    }

    public Vector getSpecular() {
        return specular;
    }

    public double getTransmissive() {
        return transmissive;
    }

    public Vector getEmissive() {
        return emissive;
    }

    public double getShininess() {
        return shininess;
    }
    
    public double getRoughness() {
        return roughness;
    }

    public double getRefractiveIndex() {
        return refractiveIndex;
    }
}
