import java.util.*;

/**
 * MazeSolver applies depth-first search and breadth-first search algorithms to solve
 * a maze defined by a Maze object.
 *
 * @author Jayson De La Vega
 */
public class MazeSolver {
    
    /**
     * Constant defining matrix linear transformations for fthe four cardinal directions
     */
    private final int[][] CARDINAL_DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    // ******************************
    // Public methods
    // ******************************
    
    /**
     * This method uses a depth-first search algorithm to solve a maze defined by a Maze
     * object.
     *
     * @param startX the x coordinate of the starting point
     * @param startY the y coordinate of the starting point
     * @param maze a Maze object containing the maze to be solved
     * @return a list of 2x1 integer arrays containing x and y coordinates of the solution path
     */
    public List<int[]> solve(int startX, int startY, Maze maze){
        List<int[]> path = new ArrayList<>();

        if (explore(startX, startY, maze, path))
            return path;
        return Collections.emptyList();
    }
    
    /**
     * This method uses a breadth-first search algorithm to solve a maze defined by a Maze
     * object.
     *
     * TODO: Complete implementation of this algorithm
     *
     * @param startX the x coordinate of the starting point
     * @param startY the y coordinate of the starting point
     * @param maze a Maze object containing the maze to be solved
     * @return the list of nodes (maze cells) on the solution path
     */
    public List<Node> solveBFS(int startX, int startY, Maze maze){
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(maze.getNode(startX, startY));
        while(!queue.isEmpty()){
            //pop queue
            Node v = queue.remove();
            if (v.isWall()){
                continue;
            }
            if (v.checkIfEnd()){
                System.out.println("End found!");
                return Collections.emptyList();
            }
            if (!v.checkIfVisited()){
                v.setVisited(true);
                for (int[] direction : CARDINAL_DIRECTIONS){
                    if(maze.isValidCoordinate(v.getX() + direction[0], v.getY() + direction[1])){
                        queue.add(maze.getNode(v.getX() + direction[0], v.getY() + direction[1]));
                        System.out.printf("Exploring node at: %d, %d\n", v.getX() + direction[0], v.getY() + direction[1] );
                    }
                }
            }
        }
        return Collections.emptyList();
    }

    // ******************************
    // Private methods
    // ******************************
    
    /**
     * Helper function to recursively explore adjacent maze cells in the four cardinal
     * directions. The function tracks the path of the cells it explores using a list 
     * of arrays containing x and y coordinates of cells in the maze.
     *
     * @param x the x coordinate of the cell to be explored
     * @param y the y coordinate of the cell to be explored
     * @param maze the maze to be solved
     * @param path a list containing the x y coordinates of all the cells on the path being explored
     * @return returns true when this function reaches the end point
     */
    private boolean explore(int x, int y, Maze maze, List<int[]> path){

        // Base case: cell is outside of maze
        if (!maze.isValidCoordinate(x, y)){
            return false;
        }

        Node currentNode = maze.getNode(x, y);

        // Base case: cell is a wall or has already been explored
        if (currentNode.isWall() || currentNode.checkIfVisited()) {
            return false;
        }

        // Base case: cell is the end point
        if (currentNode.checkIfEnd())
            return true;

        int[] currentCoordinate = {x, y};
        path.add(currentCoordinate);
        currentNode.setVisited(true);

        // Explore adjacent cells in the four cardinal directions
        for (int[] direction : CARDINAL_DIRECTIONS){
            int[] coordinates = vectorAdd(currentCoordinate, direction);
            if (explore(coordinates[0], coordinates[1], maze, path))
                return true;
        }

        // If none return true, remove current coordinate from queue and return false
        path.remove(path.size() - 1);
        return false;
    }

    /**
     * Utility function to add 2 vectors.
     *
     * @param a first vector addend
     * @param b second vector addend
     * @return resultant vector
     */
    private int[] vectorAdd(int [] a, int[] b){
        int[] resultant = new int[a.length];
        for (int i = 0; i < a.length; ++i){
            resultant[i] = a[i] + b[i];
        }
        return resultant;
    }
}
