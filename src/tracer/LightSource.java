package tracer;

/**
 *
 * @author james
 */
public class LightSource{
    private final double intensity;
    private final Vector position;
    
    public LightSource(Vector position, double intensity){
        this.position = position;
        this.intensity = intensity;
    }
    
    public double getIntensity(){
        return intensity;
    }
    
    public Vector getPosition(){
        return position;
    }
    
    public double distanceAttenuation(Vector point){
        // constants to fine tune attenuation effect
        double a = 1;
        double b = 0;
        double c = 0;
        
        double distance = position.addVector(point.scale(-1)).magnitude();
        double attenuation = 1/(a*Math.pow(distance, 2) + b*distance + c);
        return attenuation;
    }
}
