package GameBoard;

import java.awt.*;

public class GpsCoordinateTile extends BoardTiles {

    public GpsCoordinateTile(int row, int col, String id) {
        super(row, col, Color.decode("#82E0AA"));
        this.id = id;
    }
}