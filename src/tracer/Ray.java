package tracer;

/**
 *
 * @author james
 */
public class Ray {
    private final Vector origin;
    private final Vector direction;
    
    public Ray(Vector origin, Vector direction){
        this.origin = origin;
        this.direction = direction;
    }
    
    public Vector getOrigin(){
        return origin;
    }
    
    public Vector getDirection(){
        return direction;
    }
}
