package tracer;

/**
 *
 * @author james
 */
public class LightSource{
    final double intensity;
    final Vector position;
    
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
}
