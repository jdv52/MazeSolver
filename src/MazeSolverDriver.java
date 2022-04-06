import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 * MazeSolverDriver is the driver class for the maze solver application.
 *
 * @author Jayson De La Vega
 */
public class MazeSolverDriver {
    
    /**
     * Main function prompts user for a .png maze image and cals the maze
     * solver's solve method to solve the maze. Main then draws the solution
     * path if the input maze is valid.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) throws Exception {
        Scanner scnr = new Scanner(System.in);

        System.out.print("Please input the maze file name: ");
        String fileName = scnr.nextLine();
        BufferedImage bufferImg = ImageIO.read(new File(fileName));

        Maze maze = new Maze(bufferImg);
        MazeSolver solver = new MazeSolver();

        int[] startCoords = new int[2];

        for (int i = 0; i < maze.getHeight(); i++){
            for (int j = 0; j < maze.getWidth(); j++){
                if (maze.getMaze()[i][j].checkIfStart()){
                    startCoords[0] = j;
                    startCoords[1] = i;
                }
            }
        }

        System.out.println("Solving maze...");

        List<int[]> path = solver.solve(startCoords[0], startCoords[1], maze);
        if (!path.isEmpty()) {
            System.out.println("Drawing maze path...");
            drawPath1(bufferImg, path);
        }
        else {
            System.out.println("Could not solve maze");
        }
        System.out.println("Success!");
    }

    /**
     * Draws the path returned by the depth-first search maze solver algorithm.
     *
     * @ param bufferedImage the maze image in png format
     * @ param path a list of 2x1 integer arrays containing the x and y coordinates of the solution path
     */
    public static void drawPath1(BufferedImage bufferedImage, List<int[]> path) throws Exception{
        for (int[] coordinate : path){
            int x = coordinate[0];
            int y = coordinate[1];

            bufferedImage.setRGB(x, y, Color.BLUE.getRGB());
        }
        bufferedImage.setRGB(path.get(0)[0], path.get(0)[1], Color.GREEN.getRGB());

        File outFile = new File("solvedMaze.png");
        ImageIO.write(bufferedImage, "png", outFile);
    }
}
