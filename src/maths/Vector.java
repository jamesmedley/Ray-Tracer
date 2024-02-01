package maths;

import java.io.Serializable;

/**
 *
 * @author james
 */
public class Vector implements Serializable {

    private final double x;
    private final double y;
    private final double z;

    public Vector(double x, double y, double z) { //cartesian
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Vector addVector(Vector vector) {
        return new Vector(x + vector.getX(), y + vector.getY(), z + vector.getZ());
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Vector scale(double scale) {
        return new Vector(scale * x, scale * y, scale * z);
    }

    public Vector xScale(Vector vector) {
        return new Vector(x * vector.x, y, z);
    }

    public Vector yScale(Vector vector) {
        return new Vector(x, y * vector.y, z);
    }

    public Vector elementwiseMultiply(Vector vector) {
        return new Vector(x * vector.x, y * vector.y, z * vector.z);
    }

    public Vector normalise(double length) {
        double magnitude = magnitude();
        return new Vector(x / magnitude, y / magnitude, z / magnitude).scale(length);
    }

    public boolean equalTo(Vector vector) {
        return x == vector.getX() && y == vector.getY() && z == vector.getZ();
    }

    public double dot(Vector vector) {
        return x * vector.getX() + y * vector.getY() + z * vector.getZ();
    }

    public Vector cross(Vector vector) {
        double i = this.y * vector.z - this.z * vector.y;
        double j = this.z * vector.x - this.x * vector.z;
        double k = this.x * vector.y - this.y * vector.x;

        return new Vector(i, j, k);
    }

    @Override
    public String toString() {
        return x + ", " + y + ", " + z;
    }

}
