package GameBoard;

import java.awt.*;

public class StartTile extends BoardTiles {

    public StartTile(int row, int col, String id) {
        super(row, col, Color.decode("#FFFF99"));
        this.id = id;
    }

    /**
     * Метод за визуализиране на стартовия GPS координат.
     * @param g Graphics object
     */
    @Override
    public void render(Graphics g) {

        int boardTileX = this.col * BOARD_TILE_SIZE;
        int boardTileY = this.row * BOARD_TILE_SIZE;

        g.setColor(Color.GRAY);
        g.fillRect(boardTileX, boardTileY, BOARD_TILE_SIZE, BOARD_TILE_SIZE);

        g.setColor(getColor());
        g.fillRect(boardTileX + 1, boardTileY + 1, BOARD_TILE_SIZE - 3, BOARD_TILE_SIZE - 3);

        g.drawString(this.id,50,50);
    }
}