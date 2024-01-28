package tracer;

import maths.Ray;
import maths.Vector;

/**
 *
 * @author james
 */
public class Camera {
    private final Vector position;
    
    public Camera(){
        double diagonalLength = Math.sqrt(Math.pow(Properties.WIDTH/2, 2) + Math.pow(Properties.HEIGHT/2, 2));
        double d = diagonalLength/Math.tan(Properties.DFOV/2);
        position = new Vector(0,0, -d);
    }
    
    public Vector getPosition(){
        return position;
    }

    
    public Ray rayForPixel(double row, double column){ // image coordinates
        Vector worldPixelCoordinate = imageToWorldCoordinates(new double[]{row, column});
        return new Ray(position, worldPixelCoordinate.addVector(position.scale(-1)).normalise(1));
    }
    
    private Vector imageToWorldCoordinates(double[] imageCoordinates){
        imageCoordinates[0] = imageCoordinates[0] - Properties.WIDTH/2;
        imageCoordinates[1] = Properties.HEIGHT/2 - imageCoordinates[1];
        return new Vector(imageCoordinates[0], imageCoordinates[1], 0);
    }
    
}
