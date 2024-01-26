package entities;

import tracer.RGB;
import tracer.Ray;
import tracer.Vector;

/**
 *
 * @author james
 */
public class Sphere extends Entity {

    private final double radius;

    public Sphere(Vector position, Vector diffuse, Vector specular, double transmissive, RGB emissive, double shininess, double radius) {
        this.position = position;
        this.diffuse = diffuse;
        this.specular = specular;
        this.transmissive = transmissive;
        this.emissive = emissive;
        this.shininess = shininess;
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public Vector intersectionPoint(Ray ray) {
        double i = ray.getOrigin().getX();
        double j = ray.getOrigin().getY();
        double k = ray.getOrigin().getZ();
        double a = ray.getDirection().getX();
        double b = ray.getDirection().getY();
        double c = ray.getDirection().getZ();
        double d = position.getX();
        double e = position.getY();
        double f = position.getZ();
        double A = Math.pow(a, 2) + Math.pow(b, 2) + Math.pow(c, 2);
        double B = 2 * (a * (i - d) + b * (j - e) + c * (k - f));
        double C = (Math.pow(i, 2) + Math.pow(j, 2) + Math.pow(k, 2)) + (Math.pow(d, 2) + Math.pow(e, 2) + Math.pow(f, 2)) - 2 * (i * d + j * e + k * f) - Math.pow(radius, 2);
        double discriminant = Math.pow(B, 2) - (4 * a * c);
        if (discriminant < 0) {
            return null;
        }
        double[] solutions = solveQuadratic(A, B, C);
        double distance = Math.min(solutions[0], solutions[1]);
        if(distance < 0){
            return null;
        }else{
            return new Vector(i + distance * a, j + distance * b, k + distance * c);
        }
        
        
    }

    @Override
    public Vector normalAt(Vector intersectionPoint) {
        return intersectionPoint.addVector(position.scale(-1)).normalise(1);
    }

    private double[] solveQuadratic(double a, double b, double c) {
        double posSolution = (-1*b + Math.sqrt(Math.pow(b, 2) - (4 * a * c))) / (2 * a);
        double negSolution = (-1*b - Math.sqrt(Math.pow(b, 2) - (4 * a * c))) / (2 * a);
        return new double[]{posSolution, negSolution};
    }

}
