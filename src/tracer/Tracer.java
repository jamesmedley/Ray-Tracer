package tracer;

import maths.Intersection;
import maths.Ray;
import maths.Vector;
import entities.Entity;
import java.util.ArrayList;
import java.util.Random;
import me.tongfei.progressbar.ProgressBar;

/**
 *
 * @author james
 */
public class Tracer {

    private final Renderer renderer;
    private final Camera camera;
    private final double EPSILON = 0.0001;

    public Tracer(Renderer renderer) {
        this.renderer = renderer;
        camera = new Camera();
    }

    public Renderer traceImage(Scene scene, int samplesPerPixel, String imageName) {
        int[] imageDimensions = renderer.getDimensions();
        Random random = new Random(); // for anti-aliasing
        String totalTime;
        try ( ProgressBar pb = new ProgressBar(imageName, imageDimensions[0] * imageDimensions[1])) {
            for (int i = 0; i < imageDimensions[0]; i++) {
                for (int j = 0; j < imageDimensions[1]; j++) {
                    pb.step();
                    RGB pixelColour = new RGB(0, 0, 0);

                    for (int s = 0; s < samplesPerPixel; s++) {
                        // random offsets between -1 and 1
                        double offsetX = random.nextDouble() * 2 - 1;
                        double offsetY = random.nextDouble() * 2 - 1;

                        Ray ray = camera.rayForPixel(i + offsetX, j + offsetY);

                        pixelColour = pixelColour.addNoLimit(traceRay(ray, scene, Properties.PATH_DEPTH));
                    }
                    pixelColour = pixelColour.divide(samplesPerPixel);
                    pixelColour = pixelColour.gammaCorrection();
                    renderer.paintPixel(new int[]{i, j}, pixelColour);
                    pb.setExtraMessage("Processing...");
                }
            }
            totalTime = pb.getTotalElapsed().toSeconds() + " seconds";
        }
        System.out.println(totalTime);
        return renderer;
    }

    private RGB traceRay(Ray ray, Scene scene, int depth) {
        Intersection intersection = scene.intersect(ray);
        if (intersection == null) {
            return scene.getAmbient();
        }
        RGB I = shade(intersection, ray, scene);
        if (depth < 1) {
            return I;
        }
        Vector intersectionPoint = intersection.getIntersection();
        Vector normal = intersection.getSurfaceNormal();
        Entity entity = intersection.getEntity();
        Vector rayDirection = ray.getDirection();
        Vector R = rayDirection.addVector(normal.scale(-2 * (rayDirection.dot(normal))));
        Material material = entity.getMaterial();

        double n1 = ray.getRefractiveIndex();
        I = I.add(traceRay(new Ray(intersectionPoint.addVector(R.scale(EPSILON)), R, n1), scene, depth - 1).multiply(material.getSpecular()));

//        double n2 = material.getRefractiveIndex();
//        double n = n1 / n2;
//        if (n <= 1) {
//            normal = normal.scale(-1);
//        }
//        double cosI = Math.abs(normal.dot(rayDirection));
//        double sinT2 = n * n * (1 - cosI * cosI);
//        if (sinT2 <= 1) {
//            double cosT = Math.sqrt(1 - sinT2);
//            Vector T = rayDirection.scale(n).addVector(normal.scale(n * cosI - cosT));
//            I = I.add(traceRay(new Ray(intersectionPoint.addVector(R.scale(DELTA)), T, n2), scene, depth - 1).multiply(material.getTransmissive()));
//        }
        return I;
    }

    private RGB shade(Intersection intersection, Ray ray, Scene scene) {
        RGB I = scene.getAmbient();
        Entity entity = intersection.getEntity();
        Material material = entity.getMaterial();
        I = I.multiply(material.getColour().multiply(material.getAmbient()));
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

            double NdotH_alpha = Math.pow(NdotH, material.getShininess());
            double attenuation = light.distanceAttenuation(intersectionPoint) * shadowAttenuation(light, L, intersectionPoint, scene);

            diffuse = diffuse.add(intensity.multiply(NdotL * attenuation));
            specular = specular.add(intensity.multiply(NdotH_alpha * attenuation));
        }

        diffuse = diffuse.multiply(material.getColour()).multiply(material.getDiffuse());
        specular = specular.multiply(material.getSpecular());
        RGB emissive = material.getColour().multiply(material.getEmissive());

        I = I.add(diffuse).add(specular).add(emissive);

        return I;
    }

    private int shadowAttenuation(LightSource light, Vector lightDirection, Vector intersectionPoint, Scene scene) {
        Ray ray = new Ray(intersectionPoint.addVector(lightDirection.scale(EPSILON)), lightDirection, 1);
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
