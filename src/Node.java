import java.awt.*;

public class Node {
    public static final int START = 1;
    public static final int END = 0;
    public static final int PATH = 2;
    public static final int WALL = -1;

    private final int type;
    private final int x;
    private final int y;
    private boolean visited;
    private boolean isStart;
    private boolean isEnd;

    public Node(int type, int x, int y){
        this.type = type;
        this.x = x;
        this.y = y;
        visited = false;
        isStart = false;
        isEnd = false;
    }

    public void setVisited(boolean visited){
        this.visited = visited;
    }

    public void setStart(boolean isStart){
        this.isStart = isStart;
    }

    public void setEnd(boolean isEnd){
        this.isEnd = isEnd;
    }

    public boolean checkIfVisited(){
        return visited;
    }

    public boolean checkIfStart(){
        return isStart;
    }

    public boolean checkIfEnd(){
        return isEnd;
    }

    public boolean isWall(){
        return type == WALL;
    }

    public int getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
