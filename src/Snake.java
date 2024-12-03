import java.util.LinkedList;

public class Snake {
    public LinkedList<SnakeBody> snake;
}

class SnakeBody {
    int x;
    int y;
    String direction;
    public SnakeBody(int x, int y, String direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
}