package tracer;

/**
 *
 * @author james
 */
public class Renderer {
    
    private final int WIDTH, HEIGHT;
    private final int[] image; // row based index. pixel idx = 3 * ((row * width) + column) 
    
    Renderer(int width, int height){
        this.WIDTH = width;
        this.HEIGHT = height;    
        image = new int[this.WIDTH * this.HEIGHT * 3];
    }
    
    public int[] getImage(){
        return this.image;
    }
    
    public int[] getDimensions(){
        return new int[]{this.WIDTH, this.HEIGHT};
    }
    
    public void paintPixel(int[] imageCoordinate, RGB value){ // value: [r,g,b]
        int index = findIndex(imageCoordinate);
        this.image[index] = value.getRed(); //RED
        this.image[index+1] = value.getGreen(); //GREEN
        this.image[index+2] = value.getBlue(); //BLUE
    }
    
    public RGB samplePixel(int[] imageCoordinate){
        int index = findIndex(imageCoordinate);
        return new RGB(this.image[index], this.image[index+1], this.image[index+2]);
    }
    
    private int findIndex(int[] imageCoordinate){
        return 3 * ((imageCoordinate[0] * this.WIDTH) + imageCoordinate[1]);
    }
    
    
}
