package tracer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import javax.imageio.ImageIO;


/**
 *
 * @author james
 */
public class Main {
    static public final int WIDTH = 1000;
    static public final int HEIGHT = 1000;
    static public final double DFOV = 1; // 1 radian = approx. 60 degrees
    private final RGB ambient = new RGB(0,0,0);
    
    public static void main(String[] args) {
        Main main = new Main();
        main.generate();
    }
    
    private void generate(){
        Renderer renderer = new Renderer(WIDTH, HEIGHT);
        Scene scene = new Scene(ambient);
        scene.addPlane(new Vector(0,0,1000), new Vector(1,0,0),new Vector(1,1,1),0,0, 10, new Vector(0,0,-1)); // back
        scene.addPlane(new Vector(0,-500,0), new Vector(0,1,0),new Vector(1,1,1),0,0, 10, new Vector(0,1,0)); // floor
        scene.addPlane(new Vector(500,0,0), new Vector(0,0,1),new Vector(1,1,1),0,0, 5, new Vector(-1,0,0)); // RHS wall
        scene.addPlane(new Vector(-500,0,0), new Vector(0,0,1),new Vector(1,1,1),0,0, 10, new Vector(1,0,0)); // LHS wall
        scene.addPlane(new Vector(0,500,0), new Vector(0,1,0),new Vector(1,1,1),0,0, 10, new Vector(0,-1,0)); // ceiling
        scene.addSphere(new Vector(200,-400,100), new Vector(1,0,0), new Vector(1,1,1),0,0, 1000, 100);
        scene.addSphere(new Vector(-200,-320,100), new Vector(0,0,1), new Vector(1,1,1),0,0, 300, 180);
        scene.addLight(new Vector(-499,499,999), new RGB(1,1,1), new RGB(1,1,1));
        scene.addLight(new Vector(499,-499,999), new RGB(1,1,1), new RGB(1,1,1));
        scene.addLight(new Vector(-499,-499,0), new RGB(1,1,1), new RGB(1,1,1));
        scene.addLight(new Vector(499,499,0), new RGB(1,1,1), new RGB(1,1,1));
        Tracer tracer = new Tracer(renderer);
        tracer.traceImage(scene);
       
        double[] image = renderer.getImage();
        System.out.println(Arrays.toString(image));
        //fillArrayWithRandomValues(image);
        writeImageFile(image);
        // end of program!
    }
    
    private void writeImageFile(double[] image) {
    BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    for (int y = 0; y < HEIGHT; y++) {
        for (int x = 0; x < WIDTH; x++) {
            int index = (y * WIDTH + x) * 3;
            int red = (int) (image[index] * 255);
            int green = (int) (image[index + 1] * 255);
            int blue = (int) (image[index + 2] * 255);
            int rgb = (red << 16) | (green << 8) | blue;

            bufferedImage.setRGB(x, y, rgb);
        }
    }
    try {
        File outputFile = new File("src/images/image.png");
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
