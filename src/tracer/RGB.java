package tracer;

/**
 *
 * @author james
 */
public class RGB {

    private final double RED, GREEN, BLUE;

    RGB(double r, double g, double b) { // range [0,1]
        this.RED = r;
        this.GREEN = g;
        this.BLUE = b;
    }

    public double getRed() {
        return RED;
    }

    public double getGreen() {
        return GREEN;
    }

    public double getBlue() {
        return BLUE;
    }

    public double[] RGB() {
        return new double[]{RED, GREEN, BLUE};
    }

    public RGB scale(double scalar) {
        double r = Math.max(0, Math.min(RED * scalar, 1));
        double g = Math.max(0, Math.min(GREEN * scalar, 1));
        double b = Math.max(0, Math.min(BLUE * scalar, 1));
        return new RGB(r, g, b);
    }

    public RGB add(RGB colour) {
        double r = Math.max(0, Math.min(RED + colour.RED, 1));
        double g = Math.max(0, Math.min(GREEN + colour.GREEN, 1));
        double b = Math.max(0, Math.min(BLUE + colour.BLUE, 1));
        return new RGB(r, g, b);
    }

    public RGB addNoLimit(RGB colour) {
        double r = RED + colour.RED;
        double g = GREEN + colour.GREEN;
        double b = BLUE + colour.BLUE;
        return new RGB(r, g, b);
    }

    public RGB multiply(double scalar) {
        double r = RED * scalar;
        double g = GREEN * scalar;
        double b = BLUE * scalar;
        return new RGB(r, g, b);
    }

    public RGB multiply(RGB colour) {
        double r = RED * colour.RED;
        double g = GREEN * colour.GREEN;
        double b = BLUE * colour.BLUE;
        return new RGB(r, g, b);
    }

    public RGB divide(int samplesPerPixel) {
        return new RGB(RED / samplesPerPixel, GREEN / samplesPerPixel, BLUE / samplesPerPixel);
    }

    @Override
    public String toString() {
        return RED + ", " + GREEN + ", " + BLUE;
    }

}
