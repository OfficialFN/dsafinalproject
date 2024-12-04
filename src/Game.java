import javax.swing.*;
import java.awt.*;

public class Game {
    Tile[][] board = new Tile[20][20];
    JButton dijkstra, gbfs, astar;
    JPanel gridPanel;

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
        createGrid();

        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(gridPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public void createGrid() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                Color color;
                if ((i + j) % 2 == 0) {
                    color = new Color(170, 215, 80);
                } else {
                    color = new Color(162, 209, 72);
                }
                board[i][j] = new Tile(color);
                gridPanel.add(board[i][j]);
            }
        }
    }


    class Tile extends JPanel {
        public Tile(Color color) {
            setBackground(color);
        }
    }

    public static void main(String[] args) {
        new Game();
    }
}
