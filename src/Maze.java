import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.NavigableMap;
import java.util.TreeMap;

public class Maze {
    private final int width;
    private final int height;
    private final Node[][] maze;

    public Maze(BufferedImage maze) throws IllegalArgumentException{
        width = maze.getWidth();
        height = maze.getHeight();

        this.maze = new Node[height][width];

        NavigableMap<Double, Color> hueMap = new TreeMap<>();
        hueMap.put(60.0, Color.RED);
        hueMap.put(180.0, Color.GREEN);
        hueMap.put(300.0, Color.BLUE);
        hueMap.put(360.0, Color.RED);

        int startPoints = 0;
        int endPoints = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int currentColor = maze.getRGB(j, i);

                float[] hsb = new float[3];
                int r = (currentColor >> 16) & 0xFF;
                int g = (currentColor >> 8) & 0xFF;
                int b = (currentColor) & 0xFF;
                Color.RGBtoHSB(r, g, b, hsb);
                float hue = hsb[0] * 360;

                if (hsb[1] < 0.1 && hsb[2] > 0.9)
                    this.maze[i][j] = new Node(Node.PATH, j, i);
                else if (hsb[2] < 0.1)
                    this.maze[i][j] = new Node(Node.WALL, j, i);
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

    public Node[][] getMaze(){
        return maze;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Node getNode(int x, int y){
        return maze[y][x];
    }

    public boolean isValidCoordinate(int x, int y){
        return ((x < width) && (x >= 0) && (y < height) && (y >= 0));
    }

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
