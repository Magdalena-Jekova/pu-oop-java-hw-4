package GameBoard;

import java.awt.*;

public class BoardTiles{

    public static final int BOARD_TILE_SIZE = 100;
    public static final int TILE_SIDE_COUNT = 8;
    protected int row;
    protected int col;
    protected Color color;
    protected String id;

    public BoardTiles(int row, int col, Color color){
        this.row   = row;
        this.col   = col;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    /**
     * Метод за визуализиране на полетата на дъската.
     * @param g Graphics object
     */
    public void render(Graphics g){
        int boardTileX = this.col * BOARD_TILE_SIZE;
        int boardTileY = this.row * BOARD_TILE_SIZE;

        g.setColor(Color.GRAY);
        g.fillRect(boardTileX, boardTileY, BOARD_TILE_SIZE, BOARD_TILE_SIZE);

        g.setColor(getColor());
        g.fillRect(boardTileX + 1, boardTileY + 1, BOARD_TILE_SIZE - 3, BOARD_TILE_SIZE - 3);
    }
}