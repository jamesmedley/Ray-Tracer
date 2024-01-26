package tracer;

import entities.Entity;
import java.util.ArrayList;

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
        Intersection intersection = scene.intersect(ray);
      //  System.out.println(intersection.getIntersection().getX() + ", " +intersection.getIntersection().getY() + ", " +intersection.getIntersection().getZ());
        RGB I = shade(intersection, ray, scene);
       // System.out.println(I.toString());
        //Vector intersectionPoint = intersection.getIntersection();
        //Vector normal = intersection.getSurfaceNormal();
        //Entity entity = intersection.getEntity();
        //Vector rayDirection = ray.getDirection();
        //Vector R = rayDirection.addVector(normal.scale(2*(rayDirection.dot(normal))));
        //Vector T = new Vector(0,0,0);
                // I = I.add(traceRay(new Ray(intersectionPoint, R), scene).scale(entity.getSpecular()))
                // .add(traceRay(new Ray(intersectionPoint, T), scene).scale(entity.getTransmissive())); 
                                
        return I;
        
    }
    
    private RGB shade(Intersection intersection, Ray ray, Scene scene){
        RGB I = scene.getAmbient();
        if(intersection == null){
            return I;
        }
        Entity entity = intersection.getEntity();
        I = I.multiply(entity.getDiffuse());
       // System.out.println(I.toString());
        Vector intersectionPoint = intersection.getIntersection();
        Vector normal = intersection.getSurfaceNormal();
        ArrayList<LightSource> lights = scene.getLights();
        RGB diffuse = new RGB(0,0,0);
        RGB specular = new RGB(0,0,0);
       // System.out.println("----------------------------------------------------------");
      //  System.out.println(intersectionPoint);
        for(LightSource light:lights){
          //  System.out.println(light.getPosition());
            RGB intensityDiffuse = light.getIntensityDiffuse();
            RGB intensitySpecular = light.getIntensitySpecular();
            
            Vector L = light.getPosition().addVector(intersectionPoint.scale(-1)).normalise(1);
            Vector V = ray.getDirection().normalise(-1);
            Vector H = V.addVector(L).normalise(1);
            
            double NdotL = normal.dot(L);
            double NdotH = normal.dot(H);
            
            double NdotH_alpha = Math.pow(NdotH, entity.getShininess());
            
            double attenuation = light.distanceAttenuation(intersectionPoint)*shadowAttenuation(light, L, intersectionPoint, scene);
            
            diffuse = diffuse.add(intensityDiffuse.scale(NdotL * attenuation));
            specular = specular.add(intensitySpecular.scale(NdotH_alpha * attenuation));
        }
        I = I.add(diffuse.multiply(entity.getDiffuse())).add(specular.multiply(entity.getSpecular()));
        
        return I;
    }
    
    private int shadowAttenuation(LightSource light, Vector lightDirection, Vector intersectionPoint, Scene scene){
        Ray ray = new Ray(intersectionPoint.addVector(lightDirection.scale(0.001)), lightDirection);
        Intersection intersection = scene.intersect(ray);
        if(intersection == null){
            return 1;
        }
        double distanceToLight = light.getPosition().addVector(intersectionPoint.scale(-1)).magnitude();
        double distanceToIntersection = intersection.getIntersection().addVector(intersectionPoint.scale(-1)).magnitude();
        if(distanceToIntersection <= distanceToLight){
            return 0;
        }else{
            return 1;
        }
    }
}
