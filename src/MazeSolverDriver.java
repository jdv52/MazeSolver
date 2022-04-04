import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Scanner;

public class MazeSolverDriver {
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
