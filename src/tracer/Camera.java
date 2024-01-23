package tracer;

import entities.Plane;

/**
 *
 * @author james
 */
public class Camera {
    private final double DFOV; // radians
    private final Vector position;
    
    public Camera(double DFOV){
        this.DFOV = DFOV;
        double diagonalLength = Math.sqrt(Math.pow(Main.WIDTH/2, 2) + Math.pow(Main.HEIGHT/2, 2));
        double d = diagonalLength/Math.tan(DFOV/2);
        position = new Vector(0,0,-d);
    }
    
    public Vector getPosition(){
        return position;
    }

    public double getDFOV(){
        return DFOV;
    }
    
    public Ray rayForPixel(int row, int column){ // image coordinates
        Vector worldPixelCoordinate = imageToWorldCoordinates(new double[]{row, column});
        return new Ray(position, worldPixelCoordinate.addVector(position.scale(-1)).normalise(1));
    }
    
    private Vector imageToWorldCoordinates(double[] imageCoordinates){
        imageCoordinates[0] = imageCoordinates[0] - Main.WIDTH/2;
        imageCoordinates[1] = Main.HEIGHT/2 - imageCoordinates[1];
        return new Vector(imageCoordinates[0], imageCoordinates[1], 0);
    }
    
}
