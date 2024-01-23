package tracer;

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

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }
    
    public int getZ() {
        return (int) z;
    }

    public Vector addVector(Vector vector) {
        return new Vector(x + vector.getX(), y + vector.getY(), z + vector.getZ());
    }

    public double magnitude() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }
    
    public Vector scale(double scale){
        return new Vector(scale*x, scale*y, scale*z);
    }
    
    public Vector normalise(double length){
        double mag = magnitude();
        return new Vector(length*(x/mag), length*(y/mag), length*(z/mag));
    }

    public boolean equalTo(Vector vector) {
        return x == vector.getX() && y == vector.getY() && z == vector.getZ();
    }
    
    public double dot(Vector vector){
        return x*vector.getX() + y*vector.getY() + z*vector.getZ();
    }
    
    public double angle(Vector vector){ //returns angle between 2 vector directions in radians
        double dotProduct = dot(vector);
        double productOfMags = magnitude() * vector.magnitude();
        double angle = Math.acos(dotProduct / productOfMags);
        if(Double.isNaN(angle)){
            return 0;
        }else{
            return angle;  
        } 
    }

}
