package maths;

/**
 *
 * @author james
 */
public class Ray {
    private final Vector origin;
    private final Vector direction;
    private final double refractiveIndex;
    
    public Ray(Vector origin, Vector direction, double refractiveIndex){
        this.origin = origin;
        this.direction = direction;
        this.refractiveIndex = refractiveIndex;
    }
    
    public Vector getOrigin(){
        return origin;
    }
    
    public Vector getDirection(){
        return direction;
    }
    
    public double getRefractiveIndex(){
        return refractiveIndex;
    }
}
