package tracer;

import java.io.Serializable;
import maths.Vector;

/**
 *
 * @author james
 */
public class LightSource implements Serializable {

    private final Vector position;
    private final RGB colour;
    private final double intensity;

    public LightSource(Vector position, RGB colour, double intensity) {
        this.position = position;
        this.colour = colour;
        this.intensity = intensity;
    }

    public double getIntensity() {
        return intensity;
    }

    public RGB getColour() {
        return colour;
    }

    public Vector getPosition() {
        return position;
    }

    public double distanceAttenuation(Vector point) {
        // constants to fine tune attenuation effect
        double a = 0.000005;
        double b = 0;
        double c = 0;

        double distance = position.addVector(point.scale(-1)).magnitude();
        double attenuation = 1 / (a * Math.pow(distance, 2) + b * distance + c);
        return attenuation;

    }
}
