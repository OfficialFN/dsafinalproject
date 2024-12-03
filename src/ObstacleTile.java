public class ObstacleTile extends Tile {
    public ObstacleTile(int x, int y) {
        super(x, y);
    }

    public boolean isOccupied() {
        return true;
    }
}
