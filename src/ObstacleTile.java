import java.awt.Color;
public class ObstacleTile extends Tile {
    public ObstacleTile(int x, int y) {
        super(Color.GRAY, x, y);
    }

    public boolean isOccupied() {
        return true;
    }
}
