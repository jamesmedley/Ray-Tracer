package tracer;

import java.awt.Desktop;
import maths.Vector;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.imageio.ImageIO;


/*
 *
 * @author james
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        String imageName, sceneName;
        if (args.length == 0) {
            imageName = "box.png";
            sceneName = "box.ser";
        } else {
            imageName = args[0];
            if (!imageName.toLowerCase().endsWith(".png")) {
                imageName += ".png";
            }
            sceneName = args[1];
            if (!sceneName.toLowerCase().endsWith(".ser")) {
                sceneName += ".ser";
            }
        }
        main.generate(imageName, sceneName);
    }

    private void generate(String imageName, String sceneName) throws IOException {
        Renderer renderer = new Renderer(Properties.WIDTH, Properties.HEIGHT);

        // Create and save scene object
        //Scene scene = createScene();
        // Load scene from files.
        Scene scene = deserializeScene("scenes/" + sceneName);
        Camera camera = new Camera(new Vector(0, 0, 0), new Vector(0, 0, 1), new Vector(0, 1, 0));
        Tracer tracer = new Tracer(renderer, camera);
        tracer.traceImage(scene, Properties.SAMPLES_PER_PIXEL, imageName);

        double[] image = renderer.getImage();
        writeImageFile(image, imageName);
        // end of program!
    }

    private Scene createScene() {
        Scene scene = new Scene(Properties.AMBIENT);

        scene.addPlane(new Vector(0, -500, 0), Materials.NEUTRAL, new Vector(0, 1, 0)); // Floor
        scene.addPlane(new Vector(0, 500, 0), Materials.NEUTRAL, new Vector(0, -1, 0)); // Ceiling
        scene.addPlane(new Vector(0, 0, 1000), Materials.NEUTRAL, new Vector(0, 0, -1)); // Back wall 
        scene.addPlane(new Vector(0, 0, -1300), Materials.NEUTRAL, new Vector(0, 0, 1)); // Front wall 
        scene.addPlane(new Vector(500, 0, 0), Materials.materialForColour(new RGB(0, 0, 1)), new Vector(-1, 0, 0)); // Right wall
        scene.addPlane(new Vector(-500, 0, 0), Materials.materialForColour(new RGB(1, 0, 0)), new Vector(1, 0, 0)); // left wall

        scene.addSphere(new Vector(0, -300, 300), Materials.MIRROR, 200);
        scene.addSphere(new Vector(300, -400, 700), Materials.materialForColour(new RGB(0, 1, 0)), 100);
        scene.addSphere(new Vector(-300, -350, -100), Materials.POLISHED_COPPER, 150);

        scene.addLight(new Vector(0, 499, 500), new RGB(1, 1, 1), 1);

        serializeScene(scene, "scenes/box.ser");
        return scene;
    }

    private void serializeScene(Scene scene, String fileName) {
        try ( ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(scene);
            System.out.println("Object serialized and saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Scene deserializeScene(String fileName) {
        try ( ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Object obj = ois.readObject();
            if (obj instanceof Scene) {
                return (Scene) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void writeImageFile(double[] image, String imageName) {
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
            File outputFile = new File("images/" + imageName);
            ImageIO.write(bufferedImage, "png", outputFile);
            System.out.println("Image saved successfully!");
            Desktop.getDesktop().open(outputFile);
        } catch (IOException e) {
        }
    }

}
