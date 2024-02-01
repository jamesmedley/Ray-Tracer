package tracer;

import maths.Ray;
import maths.Vector;

/**
 *
 * @author james
 */
public class Camera {

    private final Vector position;
    private final Vector forward;
    private final Vector right;
    private final Vector up;

    public Camera(Vector position, Vector target, Vector upVector) {
        double diagonalLength = Math.sqrt(Math.pow(Properties.WIDTH / 2, 2) + Math.pow(Properties.HEIGHT / 2, 2));
        double d = diagonalLength / Math.tan(Properties.DFOV / 2);
        forward = target.addVector(position.scale(-1)).normalise(1);
        this.position = position.addVector(forward.scale(-d));
        right = upVector.cross(forward).normalise(1);
        up = forward.cross(right).normalise(1);
    }

    public Vector getPosition() {
        return position;
    }

    public Ray rayForPixel(double row, double column) {
        Vector worldPixelCoordinate = imageToWorldCoordinates(new double[]{row, column});
        Vector direction = worldPixelCoordinate.xScale(right).addVector(worldPixelCoordinate.yScale(up)).addVector(forward).normalise(1);
        return new Ray(position, direction, 1);
    }

    private Vector imageToWorldCoordinates(double[] imageCoordinates) {
        double halfWidth = Properties.WIDTH / 2.0;
        double halfHeight = Properties.HEIGHT / 2.0;
        double aspectRatio = Properties.WIDTH / Properties.HEIGHT;

        double x = (imageCoordinates[0] - halfWidth) / halfWidth;
        double y = (halfHeight - imageCoordinates[1]) / halfHeight;

        return right.scale(x * aspectRatio).addVector(up.scale(y)).addVector(forward);
    }

}
