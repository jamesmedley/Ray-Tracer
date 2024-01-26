package tracer;

/**
 *
 * @author james
 */
public class LightSource{
    private final RGB intensityDiffuse;
    private final RGB intensitySpecular;
    private final Vector position;
    
    public LightSource(Vector position, RGB intensityDiffuse, RGB intensitySpecular){
        this.position = position;
        this.intensityDiffuse = intensityDiffuse;
        this.intensitySpecular = intensitySpecular;
    }

    public RGB getIntensityDiffuse(){
        return intensityDiffuse;
    }
    
    public RGB getIntensitySpecular(){
        return intensitySpecular;
    }
    
    public Vector getPosition(){
        return position;
    }
    
    public double distanceAttenuation(Vector point){
        // constants to fine tune attenuation effect
        double a = 0.000005;
        double b = 0;
        double c = 0;
        
        double distance = position.addVector(point.scale(-1)).magnitude();
        double attenuation = 1/(a*Math.pow(distance, 2) + b*distance + c);
        return attenuation;
        
    }
}
