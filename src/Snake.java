import java.util.LinkedList;

class Snake {
    public LinkedList<SnakeBody> snake;

    public Snake(int x, int y) {
        snake = new LinkedList<>();
        snake.add(new SnakeBody(x, y, true));
    }

    public void move(SnakeBody a) {
        snake.addFirst(a);
        snake.removeLast();
    }

    public void grow(SnakeBody a) {
        snake.addFirst(a);
    }

    public SnakeBody getHead() {
        return snake.getFirst();
    }

    public boolean contains(int x, int y) {
        for (SnakeBody body : snake) {
            if (body.x == x && body.y == y) {
                return true;
            }
        }
        return false;
    }
}