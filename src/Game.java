import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;

public class Game {
    Tile[][] board = new Tile[20][20];
    JButton dijkstra, gbfs, astar;
    JPanel gridPanel;
    Snake snake;
    Timer t;
    LinkedList<SnakeBody> currentPath;
    HashSet<Point> pathPoints = new HashSet<>();
    Point apple;

    public Game() {
        dijkstra = new JButton("Dijkstra's Algorithm");
        gbfs = new JButton("Greedy Best First Search");
        astar = new JButton("A*");

        JFrame frame = new JFrame("Snake Board");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 850);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(dijkstra);
        buttonPanel.add(gbfs);
        buttonPanel.add(astar);

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(20, 20));

        snake = new Snake(10, 10);
        createGrid();
        makeapple();
        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(gridPanel, BorderLayout.CENTER);

        updateboard();
        startGame();

        frame.setVisible(true);
    }

    public void makeapple() {
        Random rand = new Random();
        do {
            apple = new Point(rand.nextInt(20), rand.nextInt(20));
        } while (snake.contains(apple.x, apple.y));
        board[apple.x][apple.y].appleemoji("🍎");
    }

    public void createGrid() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if ((i + j) % 2 == 0) {
                    board[i][j] = new Tile(new Color(170, 215, 80), i, j);
                } else {
                    board[i][j] = new Tile(new Color(162, 209, 72), i, j);
                }
                gridPanel.add(board[i][j]);
            }
        }
    }

    public void startGame() {
        astar.addActionListener(e -> {
            updateAppleOnGrid();
            findPathUsingAStar(snake.getHead().x, snake.getHead().y, apple.x, apple.y);
        });
    }

    public void updateAppleOnGrid() {
        board[apple.x][apple.y].appleemoji("🍎");
    }
    public void findPathUsingAStar(int startX, int startY, int targetX, int targetY) {
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(a -> a.fCost));
        boolean[][] visited = new boolean[20][20];
        Node[][] nodes = new Node[20][20];

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                nodes[i][j] = new Node(i, j);
            }
        }

        Node startNode = nodes[startX][startY];
        Node targetNode = nodes[targetX][targetY];

        startNode.gCost = 0;
        startNode.hCost = Math.abs(startX - targetX) + Math.abs(startY - targetY);
        startNode.fCost = startNode.gCost + startNode.hCost;

        frontier.add(startNode);

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            if (!(current.x == startX && current.y == startY)) {
                board[current.x][current.y].setBackground(Color.RED);
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (current == targetNode) {
                reconstructPath(targetNode, startNode);
                return;
            }

            visited[current.x][current.y] = true;

            for (int[] direction : new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}}) {
                int neighborX = current.x + direction[0];
                int neighborY = current.y + direction[1];

                if (neighborX < 0 || neighborY < 0 || neighborX >= 20 || neighborY >= 20 || visited[neighborX][neighborY]) {
                    continue;
                }

                if (snake.contains(neighborX, neighborY)) {
                    continue;
                }

                Node neighbor = nodes[neighborX][neighborY];
//f = g+h
                int currentCost = current.gCost + 1;
                if (currentCost < neighbor.gCost) {
                    neighbor.gCost = currentCost;
                    neighbor.hCost = Math.abs(neighborX - targetX) + Math.abs(neighborY - targetY);
                    neighbor.fCost = neighbor.gCost + neighbor.hCost;
                    neighbor.parent = current;

                    if (!frontier.contains(neighbor)) {
                        frontier.add(neighbor);
                        if (!(neighbor.x == startX && neighbor.y == startY)) {
                            board[neighbor.x][neighbor.y].setBackground(Color.GREEN);
                        }
                    }
                }
            }
            reconstructPartialPath(current);
        }
    }


    public void reconstructPartialPath(Node current) {
        while (current != null) {
            if (board[current.x][current.y].getBackground() != Color.RED) {
                board[current.x][current.y].setBackground(Color.GREEN);
            }
            current = current.parent;
        }
    }

    public void reconstructPath(Node targetNode, Node startNode) {
        LinkedList<SnakeBody> path = new LinkedList<>();
        Node current = targetNode;
        pathPoints.clear();

        while (current != null) {
            Point point = new Point(current.x, current.y);
            pathPoints.add(point);
            path.addFirst(new SnakeBody(current.x, current.y, false));
            board[current.x][current.y].setBackground(Color.GREEN);
            current = current.parent;
        }

        path.getFirst().isSnakeHead = true;
        currentPath = path;
        moveSnakeAlongPath();
    }

    public void moveSnakeAlongPath() {
        if (t != null) {
            t.cancel();
        }
        t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!currentPath.isEmpty()) {
                    SnakeBody head = currentPath.removeFirst();
                    head.isSnakeHead = true;
                    pathPoints.remove(new Point(head.x, head.y));

                    if (head.x == apple.x && head.y == apple.y) {
                        snake.grow(new SnakeBody(apple.x, apple.y, false));
                        makeapple();
                        currentPath.clear();
                        findPathUsingAStar(head.x, head.y, apple.x, apple.y);
                    } else {
                        snake.move(head);
                    }

                    updateboard();
                } else {
                    t.cancel();
                }
            }
        }, 0, 200);
    }

    public void updateboard() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (!pathPoints.contains(new Point(i, j))) {
                    Color bg;
                    if ((i + j) % 2 == 0) {
                        bg = new Color(170, 215, 80);
                    } else {
                        bg = new Color(162, 209, 72);
                    }
                    board[i][j].setBackground(bg);
                }
                board[i][j].clearCircle();
            }
        }
        board[apple.x][apple.y].appleemoji("🍎");

        for (int i = 0; i < snake.snake.size(); i++) {
            SnakeBody body = snake.snake.get(i);
            if (i == 0) {
                board[body.x][body.y].drawCircle(Color.GREEN);
            } else {
                board[body.x][body.y].drawCircle(Color.BLACK);
            }
        }
    }

    public static void main(String[] args) {
        new Game();
    }
}








