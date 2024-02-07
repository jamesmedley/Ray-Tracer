package tracer;

import com.github.ivelate.JavaHDR.HDREncoder;
import com.github.ivelate.JavaHDR.HDRImage;
import maths.Intersection;
import maths.Ray;
import maths.Vector;
import entities.Entity;
import java.io.File;
import java.io.IOException;
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
    private final HDRImage SKYBOX_IMG;
    private final int SKYBOX_WIDTH;
    private final int SKYBOX_HEIGHT;

    public Tracer(Renderer renderer, Camera camera) throws IOException {
        this.renderer = renderer;
        this.camera = camera;
        File imageFile = new File(Properties.SKYBOX);
        SKYBOX_IMG = HDREncoder.readHDR(imageFile, true);
        SKYBOX_WIDTH = SKYBOX_IMG.getWidth();
        SKYBOX_HEIGHT = SKYBOX_IMG.getHeight();

    }

    public Renderer traceImage(Scene scene, int samplesPerPixel, String imageName) {
        int[] imageDimensions = renderer.getDimensions();
        Random random = new Random(); // for anti-aliasing
        String totalTime;
        try ( ProgressBar pb = new ProgressBar(imageName, imageDimensions[0] * imageDimensions[1])) {
            pb.setExtraMessage("Processing...");
            for (int i = 0; i < imageDimensions[0]; i++) {
                for (int j = 0; j < imageDimensions[1]; j++) {
                    pb.step();
                    RGB pixelColour = new RGB(0, 0, 0);

                    for (int s = 0; s < samplesPerPixel; s++) {
                        // random offsets between -1 and 1
                        double offsetX = random.nextDouble() * 2 - 1;
                        double offsetY = random.nextDouble() * 2 - 1;

                        Ray ray = camera.rayForPixel(i + offsetX, j + offsetY);

                        pixelColour = pixelColour.addNoLimit(traceRay(ray, scene, Properties.PATH_DEPTH, Properties.PATH_DEPTH));
                    }
                    pixelColour = pixelColour.divide(samplesPerPixel);
                    pixelColour = pixelColour.gammaCorrection();
                    renderer.paintPixel(new int[]{i, j}, pixelColour);
                }
            }
            totalTime = pb.getTotalElapsed().toSeconds() + " seconds";
        }
        System.out.println(totalTime);
        return renderer;
    }

    private Vector randomUnitVector() {
        Random random = new Random();
        double a = random.nextDouble() * 2 - 1;
        double b = random.nextDouble() * 2 - 1;
        double c = random.nextDouble() * 2 - 1;
        Vector unitVector = new Vector(a, b, c);
        return unitVector.normalise(1);

    }

    private RGB traceDiffuse(Ray ray, Scene scene, int diffuseDepth) {
        Intersection intersection = scene.intersect(ray);
        if (intersection == null) {
            Vector direction = ray.getDirection().normalise(1);
            RGB skyboxColour = skyboxColour(direction);
            return skyboxColour;
        }
        RGB I = shade(intersection, ray, scene);
        if (diffuseDepth < 1) {
            return I;
        }
        Vector intersectionPoint = intersection.getIntersection();
        Vector normal = intersection.getSurfaceNormal();
        Entity entity = intersection.getEntity();
        Material material = entity.getMaterial();
        Vector lambertian = normal.addVector(randomUnitVector());
        double n1 = ray.getRefractiveIndex();
        I = I.add(traceDiffuse(new Ray(intersectionPoint.addVector(lambertian.scale(EPSILON)), lambertian.normalise(1), n1), scene, diffuseDepth - 1)).multiply(material.getDiffuse().scale(lambertian.magnitude()));
        return I;
    }

    private RGB traceSpecular(Ray ray, Scene scene, int specularDepth) {
        Intersection intersection = scene.intersect(ray);
        if (intersection == null) {
            Vector direction = ray.getDirection().normalise(1);
            RGB skyboxColour = skyboxColour(direction);
            return skyboxColour;
        }
        RGB I = shade(intersection, ray, scene);
        if (specularDepth < 1) {
            return I;
        }
        Vector intersectionPoint = intersection.getIntersection();
        Vector normal = intersection.getSurfaceNormal();
        Vector rayDirection = ray.getDirection();
        Entity entity = intersection.getEntity();
        Material material = entity.getMaterial();
        double n1 = ray.getRefractiveIndex();
        Vector R = rayDirection.addVector(normal.scale(-2 * (rayDirection.dot(normal))));
        R = R.addVector(randomUnitVector().scale(entity.getMaterial().getRoughness())).normalise(1);
        I = I.add(traceSpecular(new Ray(intersectionPoint.addVector(R.scale(EPSILON)), R, n1), scene, specularDepth - 1).multiply(material.getSpecular()));
        return I;
    }

    private RGB traceRay(Ray ray, Scene scene, int specularDepth, int diffuseDepth) {
        Intersection intersection = scene.intersect(ray);
        if (intersection == null) {
            Vector direction = ray.getDirection().normalise(1);
            RGB skyboxColour = skyboxColour(direction);
            return skyboxColour;
        }
        RGB I = shade(intersection, ray, scene);

        I = I.add(traceDiffuse(ray, scene, diffuseDepth));

        I = I.add(traceSpecular(ray, scene, specularDepth));

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

    private RGB skyboxColour(Vector direction) {
        double u = 0.5 + (Math.atan2(direction.getZ(), direction.getX())) / (2 * Math.PI);
        double v = 1 - (0.5 + (Math.asin(direction.getY())) / (Math.PI));
        int x = (int) (SKYBOX_WIDTH * u);
        int y = (int) (SKYBOX_HEIGHT * v);
        double red = Math.max(Math.min(SKYBOX_IMG.getPixelValue(x, y, 0), 1), 0);
        double green = Math.max(Math.min(SKYBOX_IMG.getPixelValue(x, y, 1), 1), 0);
        double blue = Math.max(Math.min(SKYBOX_IMG.getPixelValue(x, y, 2), 1), 0);
        return new RGB(red, green, blue);
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
