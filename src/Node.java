import java.awt.*;

/**
 * The Node class defines nodes utilized by the depth-first search and breadth-first
 * algorithms. The MazeSolver application represents the user's input maze image as
 * an array of Nodes whose fields correspond to the pixels of the maze image (i.e. it's 
 * location in the image, if it's a wall pixel, if it's a start or stop point, if 
 * it's been visited previously by an algorithm, etc.).
 *
 * @author Jayson De La Vega
 */
public class Node {
    
    // ******************************
    // Constants
    // ******************************
    
    /**
     * These constants define a node's pixel type (e.g. a wall pixel, start point, etc.).
     */
    public static final int START = 1;
    public static final int END = 0;
    public static final int PATH = 2;
    public static final int WALL = -1;

    // ******************************
    // Fields
    // ******************************
    
    /**
     * This node's pixel type as defined by the above constants
     */
    private final int type;
    
    /**
     * This node's x coordinate
     */
    private final int x;
    
    /**
     * This node's y coordinate
     */
    private final int y;
    
    /**
     * Indicates whether a breadth/depth-first search algorithm has visited this node.
     */
    private boolean visited;
    
    /**
     * Indicates whether this node is the start point
     */
    private boolean isStart;
    
    /**
     * Indicates whether this node is the end point
     */
    private boolean isEnd;

    /**
     * Class constructor that takes in node type, x coordinate, and y coordinate
     *
     * @param type the type of node as defined by the above constants
     * @param x the x coordinate of the node
     * @param y the y coordinate of the node
     */
    public Node(int type, int x, int y){
        this.type = type;
        this.x = x;
        this.y = y;
        visited = false;
        isStart = false;
        isEnd = false;
    }

    // ******************************
    // Public methods
    // ******************************
    
    /**
     * Sets the visited field to the state specified by the function argument
     *
     * @param visited the state indicating that this node has/hasn't been visited
     */
    public void setVisited(boolean visited){
        this.visited = visited;
    }

    /**
     * Sets the isStart field to the state specified by the function argument
     *
     * @param isStart the state indicating that this node is/isn't the start point
     */
    public void setStart(boolean isStart){
        this.isStart = isStart;
    }

    /**
     * Sets the isEnd field to the state specified by the function argument
     *
     * @param isEnd the state indicating that this node is/isn't the end point
     */
    public void setEnd(boolean isEnd){
        this.isEnd = isEnd;
    }

    /**
     * Getter function to return the visited state of this node
     *
     * @return returns true if this node has been visited
     */
    public boolean checkIfVisited(){
        return visited;
    }

    /**
     * Getter function to indicate that this node is the start point
     *
     * @return returns true if this node is the start point
     */
    public boolean checkIfStart(){
        return isStart;
    }

    /**
     * Getter function to indicate that this node is the end point
     *
     * @return returns true if this node is the end point
     */
    public boolean checkIfEnd(){
        return isEnd;
    }

    /**
     * Getter function to indicate that this node is a wall
     *
     * @return returns true if this node is a wall
     */
    public boolean isWall(){
        return type == WALL;
    }

    /**
     * Getter function that returns this node's type
     *
     * @return returns an integer specifying the type as defined by the above constants
     */
    public int getType() {
        return type;
    }

    /**
     * Getter function that returns this node's x coordinate
     *
     * @return returns this node's x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Getter function that returns this node's y coordinate
     *
     * @return returns this node's y coordinate
     */
    public int getY() {
        return y;
    }
}
