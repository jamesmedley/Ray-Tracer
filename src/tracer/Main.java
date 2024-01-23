package tracer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;


/**
 *
 * @author james
 */
public class Main {
    static final int WIDTH = 1000;
    static final int HEIGHT = 1000;
    
    
    public static void main(String[] args) {
        Main main = new Main();
        main.generate();
    }
    
    private void generate(){
        Renderer renderer = new Renderer(WIDTH, HEIGHT);
        
        
        /*
            Scene scene = new Scene();
            scene.addSphere() ... etc
            Tracer tracer = new Tracer(renderer) ????
            tracer.trace(scene)
        
        */
        int[] image = renderer.getImage();
        fillArrayWithRandomValues(image, 0, 255);
        writeImageFile(image);
        // end of program!
    }
    
    private void writeImageFile(int[] image){ // write image array to file
        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                int index = (y * WIDTH + x) * 3;
                int rgb = (image[index] << 16) | (image[index + 1] << 8) | image[index + 2];
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
    
    
    private static void fillArrayWithRandomValues(int[] array, int minValue, int maxValue) { // generate random image
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(maxValue - minValue + 1) + minValue;
        }
    }
   
}
