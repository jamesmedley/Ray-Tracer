package maths;

/**
 *
 * @author james
 */
public class Vector {

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

    public Vector pointMultiply(Vector vector) {
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

    public double angle(Vector vector) { //returns angle between 2 vector directions in radians
        double dotProduct = dot(vector);
        double productOfMags = magnitude() * vector.magnitude();
        double angle = Math.acos(dotProduct / productOfMags);
        if (Double.isNaN(angle)) {
            return 0;
        } else {
            return angle;
        }
    }

    @Override
    public String toString() {
        return x + ", " + y + ", " + z;
    }

}
