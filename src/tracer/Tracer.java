package tracer;

/**
 *
 * @author james
 */
public class Tracer {
    private final Renderer renderer;
    private final Camera camera;
    
    public Tracer(Renderer renderer){
        this.renderer = renderer;
        camera = new Camera();
    }
    
    public Renderer traceImage(Scene scene){
        int[] imageDimensions = renderer.getDimensions();
        for(int i=0; i<imageDimensions[0];i++){
            for (int j = 0; j < imageDimensions[1]; j++) {
                Ray ray = camera.rayForPixel(i, j);
                renderer.paintPixel(new int[]{i,j}, traceRay(ray, scene));
            }
        }
        return renderer;
    }
    
    private RGB traceRay(Ray ray, Scene scene){
        /* 
            1) (intersection point, surface normal vector, entity) <- scene.intersect(ray)
                a) Compute position of first intersection with an object in scene
            
            2) r = -2(ray.getDirection.dot(N))*N + ray.getDirection
            
            3) t = calculate refracted ray  using Snell's law. if total internal reflection dont trace transmissive ray!
        
            4) rgb = shade(point of intersection, surface normal, material properties, ray, scene) 
                                + (entity.getReflectivity()[2])*traceRay(Q, r, scene)
                                + (entity.getReflectivity()[3])*traceRay(Q, t, scene) // K_t should be 1-K_s
        
            5) return rgb
        */
        return rgb;
    }
    
    private RGB shade(Vector intersection, Vector surfaceNormal, Entities entity, Ray ray, Scene scene){
        /*
            I = entity.getEmissive();
            for each light:
                attenuation = light.distanceAttenuation(intersection) * shadowAttenuation(intersection, scene)
                L = light.getDirection(intersection)
                I = I + scene.ambient + attenuation*(entity.getDiffuse() + entity.getSpecular())
                
            return I;
        */
       
               
    }
    
    private int shadowAttenuation(Vector point, Scene scene){
        /*
        
            if ray intersects another object before reaching the light source:
                return 0
            else:   
                return 1
        
        */
    }
}
