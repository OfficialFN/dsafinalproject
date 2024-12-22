import javax.swing.*;
import java.awt.*;

class Tile extends JPanel {
    int x, y;
    public Color circleColor;

    public Tile(Color color, int x, int y) {
        this.x = x;
        this.y = y;
        setBackground(color);
    }

    public void drawCircle(Color color) {
        this.circleColor = color;
        repaint();
    }

    public void appleemoji(String emoji) {
        JLabel label = new JLabel(emoji, SwingConstants.CENTER);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        removeAll();
        setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void clearCircle() {
        this.circleColor = null;
        removeAll();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (circleColor != null) {
            g.setColor(circleColor);
            int padding = 10;
            g.fillOval(padding, padding, getWidth() - 2 * padding, getHeight() - 2 * padding);
        }
    }
}