package tracer;

/**
 *
 * @author james
 */
public class RGB {
    private final int RED, GREEN, BLUE;
    
    RGB(int r, int g, int b){
        this.RED = r;
        this.GREEN = g;
        this.BLUE = b;
    }
    
    public int getRed(){
        return RED;
    }
    
    public int getGreen(){
        return GREEN;
    }
    
    public int getBlue(){
        return BLUE;
    }
    
    public int[] RGB(){
        return new int[]{RED, GREEN, BLUE};
    }
}
