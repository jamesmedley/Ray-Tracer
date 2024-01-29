package tracer;

import maths.Vector;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import javax.imageio.ImageIO;


/*
 *
 * @author james
 */
public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.generate();
    }

    private void generate() {
        Renderer renderer = new Renderer(Properties.WIDTH, Properties.HEIGHT);
        Scene scene = new Scene(Properties.AMBIENT);

        scene.addPlane(new Vector(0, -500, 0), Materials.MIRROR, new Vector(0, 1, 0)); // Floor
        scene.addPlane(new Vector(0, 500, 0), Materials.MIRROR, new Vector(0, -1, 0)); // Ceiling
        scene.addPlane(new Vector(0, 0, 1000), Materials.MIRROR, new Vector(0, 0, -1)); // Back wall 
        scene.addPlane(new Vector(500, 0, 0), Materials.materialForColour(new RGB(1,0,0)), new Vector(-1, 0, 0)); // Right wall
        scene.addPlane(new Vector(-500, 0, 0), Materials.materialForColour(new RGB(0,1,0)), new Vector(1, 0, 0)); // left wall

        scene.addSphere(new Vector(-150, -300, 300), Materials.POLISHED_SILVER,200); // Polished Silver
        scene.addSphere(new Vector(300, -400, 200), Materials.materialForColour(new RGB(0,1,0)),100); // Green


        scene.addLight(new Vector(499, 499, 999), new RGB(1, 1,1), 2);
        scene.addLight(new Vector(-499, 499, 999), new RGB(1, 1, 1), 2);
        scene.addLight(new Vector(0, 499, 0), new RGB(1,1, 1), 2);
        


        Tracer tracer = new Tracer(renderer);
        tracer.traceImage(scene, Properties.SAMPLES_PER_PIXEL);

        double[] image = renderer.getImage();
        System.out.println(Arrays.toString(image));
        writeImageFile(image);
        // end of program!
    }

    private void writeImageFile(double[] image) {
        BufferedImage bufferedImage = new BufferedImage(Properties.WIDTH, Properties.HEIGHT, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < Properties.HEIGHT; y++) {
            for (int x = 0; x < Properties.WIDTH; x++) {
                int index = (y * Properties.WIDTH + x) * 3;
                int red = (int) (image[index] * 255);
                int green = (int) (image[index + 1] * 255);
                int blue = (int) (image[index + 2] * 255);
                int rgb = (red << 16) | (green << 8) | blue;

                bufferedImage.setRGB(x, y, rgb);
            }
        }
        try {
            File outputFile = new File("src/images/test.png");
            ImageIO.write(bufferedImage, "png", outputFile);
            System.out.println("Image saved successfully!");
        } catch (IOException e) {
        }
    }

    private static void fillArrayWithRandomValues(double[] array) { // generate random image
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextDouble();
        }
    }

}
