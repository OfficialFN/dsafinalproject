import java.util.LinkedList;

public class Tile {
    int x;
    int y;
    boolean occupied;
    boolean food;
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        occupied = false;
        food = false;
    }
}
