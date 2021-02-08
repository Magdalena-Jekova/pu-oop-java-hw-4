package GameBoard;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameBoard extends JFrame {

    public BoardTiles[][] boardTiles;
    private Random random;
    private int randomRow;
    private int randomCol;

    public GameBoard(){
        this.boardTiles = new BoardTiles[BoardTiles.TILE_SIDE_COUNT][BoardTiles.TILE_SIDE_COUNT];
        random = new Random();
        setBoardTilesOnRandomPositions();

        this.setSize(800,800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Метод за визуализиране на всички полета върху дъската.
     * @param g Graphics object
     */
    @Override
    public void paint(Graphics g) {
        for(int row = 0; row < boardTiles.length; row++){
            for(int col = 0; col < boardTiles.length; col++){
                BoardTiles tile = boardTiles[row][col];
                tile.render(g);
            }
        }
    }

    /**
     * Метод, при извикването на който се инициализират всички полета върху дъската на случайни позиции.
     */
    public void setBoardTilesOnRandomPositions(){
        setStartTile();
        setUnexploredTiles();
        setGpsCoordinates();
        setDangerTile();
    }

    /**
     * Метод за инициализиране на стартовия GPS координат,
     * който винаги се намира в един от четирите ъгъла на дъската.
     */
    private void setStartTile(){
        int randomStartTileCoordinates = random.nextInt(4);
        switch (randomStartTileCoordinates){
            case 0:
                boardTiles[0][0] = new StartTile(0,0,"");
                break;
            case 1:
                boardTiles[0][7] = new StartTile(0,7,"");
                break;
            case 2:
                boardTiles[7][0] = new StartTile(7,0,"");
                break;
            case 3:
                boardTiles[7][7] = new StartTile(7,7,"");
                break;
        }
    }

    /**
     * Метод за инициализиране на неизследваните полета.
     */
    private void setUnexploredTiles(){

        for(int i = 0; i < 50; i++){

            randomRow = random.nextInt(8);
            randomCol = random.nextInt(8);

            if(boardTiles[randomRow][randomCol] == null){
                boardTiles[randomRow][randomCol] = new UnexploredTile(randomRow, randomCol);
            }else{
                i--;
            }
        }
    }

    /**
     * Метод за инициализиране на GPS координатите.
     */
    private void setGpsCoordinates(){

        for(int i = 0; i < 8; i++){

            randomRow = random.nextInt(8);
            randomCol = random.nextInt(8);

            if(boardTiles[randomRow][randomCol] == null) {
                boardTiles[randomRow][randomCol] = new GpsCoordinateTile(randomRow, randomCol,"");
                if(i == 3){
                    boardTiles[randomRow][randomCol].setId("Баба Яга");
                }
            }else{
                i--;
            }
        }
    }

    /**
     * Метод за инициализиране на непроходимите полета.
     */
    private void setDangerTile(){

        for(int i = 0; i < 5; i++){

            randomRow = random.nextInt(8);
            randomCol = random.nextInt(8);

            if(boardTiles[randomRow][randomCol] == null){
                boardTiles[randomRow][randomCol] = new DangerTile(randomRow, randomCol);
            }else{
                i--;
            }
        }
    }
}