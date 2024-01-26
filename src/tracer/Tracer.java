package tracer;

import entities.Entity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author james
 */
public class Tracer {
    private final Renderer renderer;
    private final Camera camera;
    private final double DELTA = 0.0001;
    
    public Tracer(Renderer renderer){
        this.renderer = renderer;
        camera = new Camera();
    }
    
    public Renderer traceImage(Scene scene, int samplesPerPixel){
        int[] imageDimensions = renderer.getDimensions();
        Random random = new Random(); // for anti-aliasing
        for(int i=0; i<imageDimensions[0];i++){
            for (int j = 0; j < imageDimensions[1]; j++) {
                RGB pixelColor = new RGB(0,0,0);

            // Apply multiple samples per pixel for anti-aliasing
                for (int s = 0; s < samplesPerPixel; s++) {
                    // Generate random offsets for each sample
                    double offsetX = random.nextDouble();
                    double offsetY = random.nextDouble();

                    // Use the offset to create a jittered ray
                    Ray ray = camera.rayForPixel(i + offsetX, j + offsetY);

                    // Accumulate color for each sample
                    pixelColor = pixelColor.addNoLimit(traceRay(ray, scene, 10));
                }
                pixelColor = pixelColor.divide(samplesPerPixel);
                renderer.paintPixel(new int[]{i,j}, pixelColor);
            }
        }
        return renderer;
    }
    
    private RGB traceRay(Ray ray, Scene scene, int depth){
        Intersection intersection = scene.intersect(ray);
        if (depth < 1 || intersection == null) {
            // Stopping condition: return a default color or handle termination
            return new RGB(0, 0, 0); // Assuming RGB is a class with appropriate constructor
        }
        RGB I = shade(intersection, ray, scene);
        Vector intersectionPoint = intersection.getIntersection();
        Vector normal = intersection.getSurfaceNormal();
        Entity entity = intersection.getEntity();
        Vector rayDirection = ray.getDirection();
        Vector R = rayDirection.addVector(normal.scale(-2*(rayDirection.dot(normal))));
        Vector T = new Vector(0,0,0);
        I = I.add(traceRay(new Ray(intersectionPoint.addVector(R.scale(DELTA)), R), scene, depth-1).multiply(entity.getSpecular()));
             //.add(traceRay(new Ray(intersectionPoint, T), scene, depth-1).scale(entity.getTransmissive())); 
        return I;
    }
    
    private RGB shade(Intersection intersection, Ray ray, Scene scene){
        RGB I = scene.getAmbient();
        if(intersection == null){
            return I;
        }
        Entity entity = intersection.getEntity();
        I = I.multiply(entity.getDiffuse());
        Vector intersectionPoint = intersection.getIntersection();
        Vector normal = intersection.getSurfaceNormal();
        ArrayList<LightSource> lights = scene.getLights();
        RGB diffuse = new RGB(0,0,0);
        RGB specular = new RGB(0,0,0);
        for(LightSource light:lights){
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
        I = I.add(diffuse.multiply(entity.getDiffuse())).add(specular.multiply(entity.getSpecular())).add(entity.getEmissive());
        
        return I;
    }
    
    private int shadowAttenuation(LightSource light, Vector lightDirection, Vector intersectionPoint, Scene scene){
        Ray ray = new Ray(intersectionPoint.addVector(lightDirection.scale(DELTA)), lightDirection);
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
