import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * The maze object defines a maze based on the user's input image file.
 * 
 * @author Jayson De La Vega
 */
public class Maze {
    
    // ******************************
    // Fields
    // ******************************
    
    /**
     * The width of the maze
     */
    private final int width;
    
    /**
     * The width of the maze
     */
    private final int height;
    
    /**
     * A 2x2 array of nodes that describes the maze
     */
    private final Node[][] maze;

    /**
     * Class constructor that populates fields based on user's input image
     *
     * @param maze object that contains the rgb information of the input maze
     * @throws IllegalArgumentException thrown if maze contains too many/few
     *                                  start or stop points.
     */
    public Maze(BufferedImage maze) throws IllegalArgumentException{
        width = maze.getWidth();
        height = maze.getHeight();

        this.maze = new Node[height][width];
        
        // Utilize hue map ceilings to detect red and green cells
        NavigableMap<Double, Color> hueMap = new TreeMap<>();
        hueMap.put(60.0, Color.RED);
        hueMap.put(180.0, Color.GREEN);
        hueMap.put(300.0, Color.BLUE);
        hueMap.put(360.0, Color.RED);

        // Iterate through each pixel in buffered image to classify each node in maze array
        int startPoints = 0;
        int endPoints = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int currentColor = maze.getRGB(j, i);

                // Use hue, saturation, brightness to classify maze nodes
                float[] hsb = new float[3];
                int r = (currentColor >> 16) & 0xFF;
                int g = (currentColor >> 8) & 0xFF;
                int b = (currentColor) & 0xFF;
                Color.RGBtoHSB(r, g, b, hsb);
                float hue = hsb[0] * 360;
                
                // Checks for cells with low saturation and high brightness (i.e. close to white)
                if (hsb[1] < 0.1 && hsb[2] > 0.9)
                    this.maze[i][j] = new Node(Node.PATH, j, i);
                
                // Checks for cells with low brightness (i.e. close to black)
                else if (hsb[2] < 0.1)
                    this.maze[i][j] = new Node(Node.WALL, j, i);
                
                // All other cells are compared against the hue map to detect red or green cells
                else {
                    if (hueMap.ceilingEntry((double)hue).getValue().equals(Color.RED)) {
                        this.maze[i][j] = new Node(Node.END, j, i);
                        this.maze[i][j].setEnd(true);
                        startPoints++;
                    }
                    if (hueMap.ceilingEntry((double)hue).getValue().equals(Color.GREEN)) {
                        this.maze[i][j] = new Node(Node.START, j, i);
                        this.maze[i][j].setStart(true);
                        endPoints++;
                    }
                }
            }
        }
        if (startPoints != 1 | endPoints != 1){
            throw new IllegalArgumentException("MazeSolver.Maze has illegal number of start and/or endpoints");
        }
    }

    // ******************************
    // Public methods
    // ******************************
    
    /**
     * Getter function to return node array
     *
     * @return a 2x2 array containing the nodes that describe the maze
     */
    public Node[][] getMaze(){
        return maze;
    }

    /**
     * Getter function to return maze height
     *
     * @return maze height
     */
    public int getHeight(){
        return height;
    }

    /**
     * Getter function to return maze width
     *
     * @return maze width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Getter function to return a node at a particular xy coordinate
     *
     * @param x x coordinate of the desired node
     * @param y y coordinate of the desired node
     * @return the node at coordinates (x, y)
     */
    public Node getNode(int x, int y){
        return maze[y][x];
    }

    /**
     * Function to check if the coordinate x, y is inside of the maze
     * 
     * @param x x coordinate of interest
     * @param y y coordinate of interest
     * @return returns true if coordinate x, y is inside of the maze
     */
    public boolean isValidCoordinate(int x, int y){
        return ((x < width) && (x >= 0) && (y < height) && (y >= 0));
    }

    /**
     * Function to print the maze
     */
    public void printMaze(){
        for (Node[] row : maze){
            for (Node col : row){
                switch (col.getType()){
                    case -1 -> System.out.print("#");
                    case 0 -> System.out.print("1");
                    case 1 -> System.out.print("0");
                    case 2 -> System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
