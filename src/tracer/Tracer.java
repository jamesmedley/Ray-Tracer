package tracer;

import maths.Intersection;
import maths.Ray;
import maths.Vector;
import entities.Entity;
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

    public Tracer(Renderer renderer) {
        this.renderer = renderer;
        camera = new Camera();
    }

    public Renderer traceImage(Scene scene, int samplesPerPixel) {
        int[] imageDimensions = renderer.getDimensions();
        Random random = new Random(); // for anti-aliasing
        for (int i = 0; i < imageDimensions[0]; i++) {
            for (int j = 0; j < imageDimensions[1]; j++) {
                RGB pixelColor = new RGB(0, 0, 0);

                for (int s = 0; s < samplesPerPixel; s++) {
                    // random offsets between -1 and 1
                    double offsetX = random.nextDouble() * 2 - 1;
                    double offsetY = random.nextDouble() * 2 - 1;

                    Ray ray = camera.rayForPixel(i + offsetX, j + offsetY);

                    pixelColor = pixelColor.addNoLimit(traceRay(ray, scene, Properties.PATH_DEPTH));
                }
                pixelColor = pixelColor.divide(samplesPerPixel);
                renderer.paintPixel(new int[]{i, j}, pixelColor);
            }
        }
        return renderer;
    }

    private RGB traceRay(Ray ray, Scene scene, int depth) {
        Intersection intersection = scene.intersect(ray);
        if (depth < 1 || intersection == null) {
            return scene.getAmbient();
        }
        RGB I = shade(intersection, ray, scene);
        Vector intersectionPoint = intersection.getIntersection();
        Vector normal = intersection.getSurfaceNormal();
        Entity entity = intersection.getEntity();
        Vector rayDirection = ray.getDirection();
        Vector R = rayDirection.addVector(normal.scale(-2 * (rayDirection.dot(normal))));
        Vector T = new Vector(0, 0, 0);
        I = I.add(traceRay(new Ray(intersectionPoint.addVector(R.scale(DELTA)), R), scene, depth - 1).multiply(entity.getSpecular()));
        //.add(traceRay(new Ray(intersectionPoint, T), scene, depth-1).scale(entity.getTransmissive())); 
        return I;
    }

    private RGB shade(Intersection intersection, Ray ray, Scene scene) {
        RGB I = scene.getAmbient();
        Entity entity = intersection.getEntity();
        I = I.multiply(entity.getColour().multiply(entity.getDiffuse()));
        Vector intersectionPoint = intersection.getIntersection();
        Vector normal = intersection.getSurfaceNormal();
        ArrayList<LightSource> lights = scene.getLights();
        RGB diffuse = new RGB(0, 0, 0);
        RGB specular = new RGB(0, 0, 0);
        for (LightSource light : lights) {
            RGB intensity = light.getColour().multiply(light.getIntensity());

            Vector L = light.getPosition().addVector(intersectionPoint.scale(-1)).normalise(1);
            Vector V = ray.getDirection().normalise(-1);
            Vector H = V.addVector(L).normalise(1);

            double NdotL = normal.dot(L);
            double NdotH = normal.dot(H);

            double NdotH_alpha = Math.pow(NdotH, entity.getShininess());

            double attenuation = light.distanceAttenuation(intersectionPoint) * shadowAttenuation(light, L, intersectionPoint, scene);

            diffuse = diffuse.add(intensity.scale(NdotL * attenuation));
            specular = specular.add(intensity.scale(NdotH_alpha * attenuation));
        }

        diffuse = diffuse.multiply(entity.getColour()).multiply(entity.getDiffuse());
        specular = specular.multiply(entity.getSpecular());
        RGB emissive = entity.getColour().multiply(entity.getEmissive());

        I = I.add(diffuse).add(specular).add(emissive);

        return I;
    }

    private int shadowAttenuation(LightSource light, Vector lightDirection, Vector intersectionPoint, Scene scene) {
        Ray ray = new Ray(intersectionPoint.addVector(lightDirection.scale(DELTA)), lightDirection);
        Intersection intersection = scene.intersect(ray);
        if (intersection == null) {
            return 1;
        }
        double distanceToLight = light.getPosition().addVector(intersectionPoint.scale(-1)).magnitude();
        double distanceToIntersection = intersection.getIntersection().addVector(intersectionPoint.scale(-1)).magnitude();
        if (distanceToIntersection <= distanceToLight) {
            return 0;
        } else {
            return 1;
        }
    }
}
