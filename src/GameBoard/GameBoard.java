package GameBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class GameBoard extends JFrame implements MouseListener {

    public BoardTiles[][] boardTiles;
    public BoardTiles clickedTile;
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
        this.addMouseListener(this);
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

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = this.getBoardDimensionBasedOnCoordinates(e.getY());
        int col = this.getBoardDimensionBasedOnCoordinates(e.getX());

        if(row < 8 && col < 8) {
            if (this.clickedTile != null) {
                if (this.clickedTile.getColor().equals(Color.decode("#FFFF99"))) {
                    generateTile(row, col);
                    return;
                }
                if (this.clickedTile.getColor().equals(Color.decode("#EC7063"))) {
                    this.clickedTile.setColor(Color.decode("#FFFF99"));
                }
                repaint();
            }

            if (this.hasBoardTile(row, col)) {
                this.clickedTile = this.getBoardTile(row, col);
                if (this.clickedTile.getColor().equals(Color.decode("#EC7063"))) {
                    this.clickedTile.setColor(Color.decode("#FFFF99"));
                    repaint();
                }
                if (this.clickedTile.getColor().equals(Color.decode("#82E0AA"))) {
                    this.gameOver();
                }
            }
        }else{
            Modal.showMessage(this, "Излизате извън границите на дъската! Изберете ново поле!");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Метод, който генерира жълто или синьо поле върху дъската при втори клик с мишката върху първоначално избраното поле.
     * @param row - редът, на който ще се генерира новото поле
     * @param col - колоната, на която ще се генерира новото поле
     */
    private void generateTile(int row, int col){
        int randomNumber = random.nextInt(10);
        if(randomNumber == 0 || randomNumber == 1){
            this.boardTiles[row][col] = new DangerTile(row, col);
            repaint();
            Modal.showMessage(this,"Непроходима територия! Трябва да заобиколите!");
        }else {
            this.boardTiles[row][col] = new StartTile(row, col,"");
            repaint();
        }
        this.clickedTile = null;
    }

    /**
     * Метод, който визуализира модален прозорец за край на играта,
     * когато потребителят достигне до едно от зелените полета, върху което се намира Баба Яга.
     */
    private void gameOver(){
        if (this.clickedTile.getId().equals("Баба Яга")) {
            Modal.showEndOfTheGameMessage(this, "Край на играта!", "Намерихте Баба Яга!");
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

    /**
     * Метод, който връща поле от дъската, което се намира на съответния ред и колона.
     * @param row - редът, на който се намира полето върху дъската
     * @param col - колоната, на която се намира полето върху дъската
     * @return object
     */
    private BoardTiles getBoardTile(int row, int col) {
        return this.boardTiles[row][col];
    }

    /**
     * Метод, който проверява дали на съответния ред и колона има поле върху дъската.
     * @param row - редът, на който се проверява дали има поле
     * @param col - колоната, на която се проверява дали има поле
     * @return true, ако има поле и false, ако няма поле
     */
    private boolean hasBoardTile(int row, int col) {
        return this.getBoardTile(row, col) != null;
    }

    private int getBoardDimensionBasedOnCoordinates(int coordinates) {
        return coordinates / BoardTiles.BOARD_TILE_SIZE;
    }
}