import java.awt.Color;
public class SpeedTile extends Tile{
    int speedmod;
    public SpeedTile(int x, int y, int speedmod) {
        super(Color.ORANGE, x, y);
        this.speedmod = speedmod;
    }
}
