import java.util.*;

public class MazeSolver {
    // Matrix linear transformations that define cardinal directions
    private final int[][] CARDINAL_DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    // User will call this solve method
    public List<int[]> solve(int startX, int startY, Maze maze){
        List<int[]> path = new ArrayList<>();

        // Recursion occurs in explore method
        if (explore(startX, startY, maze, path))
            return path;
        return Collections.emptyList();
    }

    // Recursively explores adjacent paths
    private boolean explore(int x, int y, Maze maze, List<int[]> path){

        // Base case:
        // cell is outside of maze
        if (!maze.isValidCoordinate(x, y)){
            return false;
        }

        Node currentNode = maze.getNode(x, y);

        // Base case:
        // if current cell is invalid, wall, or is explored, return false
        if (currentNode.isWall() || currentNode.checkIfVisited()) {
            return false;
        }

        // Base case:
        // if current cell is exit, return true
        if (currentNode.checkIfEnd())
            return true;

        // Add current coordinates to queue and set current node as visited\
        int[] currentCoordinate = {x, y};
        path.add(currentCoordinate);
        currentNode.setVisited(true);

        // For all coordinates in 4 cardinal directions, explore
        // If one coordinate pair is true, return true
        for (int[] direction : CARDINAL_DIRECTIONS){
            int[] coordinates = vectorAdd(currentCoordinate, direction);
            if (explore(coordinates[0], coordinates[1], maze, path))
                return true;
        }

        // If none return true, remove current coordinate from queue and return false
        path.remove(path.size() - 1);
        return false;
    }

    // Utility method for vector addition
    private int[] vectorAdd(int [] a, int[] b){
        int[] resultant = new int[a.length];
        for (int i = 0; i < a.length; ++i){
            resultant[i] = a[i] + b[i];
        }
        return resultant;
    }

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
}
