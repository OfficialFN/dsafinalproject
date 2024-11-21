import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Game implements ActionListener {
    Tile[][] board = new Tile[20][20];
    JButton dijkstra, gbfs, astar;
    public Game() {
        dijkstra = new JButton("Dijkstra's Algorithm");
        gbfs = new JButton("Greedy Best First Search");
        astar = new JButton("A*");

        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850,850);
        frame.setBackground(Color.GREEN);
        frame.add(dijkstra);
        frame.add(gbfs);
        frame.add(astar);
        frame.setVisible(true);
    }

    public void createGrid() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {

            }
        }
    }
    public void paintComponent( Graphics g ) {
        for ( int x = 30; x <= 300; x += 30 )
            for ( int y = 30; y <= 300; y += 30 )
                g.drawRect( x, y, 30, 30 );

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
