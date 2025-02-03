package model.games;

import model.board.Board;
import model.board.Cell;
import model.players.ArtificialPlayer;
import model.players.HumanPlayer;
import model.players.Player;
import model.tests.TestVictoire;

import java.util.ArrayList;
import java.util.List;

abstract public class BoardGame {
    private String coordinates;
    private int turn;


    private Player currentPlayer;
    private String endMsg;
    private Board board;

    public enum GameName {
        CONNECT4, TICTACTOE, GOMOKU
    }

    protected GameName gameName;
    private final Player[] PLAYER;
    private final TestVictoire TESTVICTOIRE;
    private boolean gameOver;


    public BoardGame(int size, int victorySize, GameName gameName) {
        this.PLAYER = new Player[2];
        this.TESTVICTOIRE = new TestVictoire();
        this.gameOver = false;
        this.gameName = gameName;
        this.size = size;
        this.victorySize = victorySize;
        this.endMsg = "";
        this.turn = 0;
    }

    protected int victorySize;
    protected int size;


    public Cell[][] getBoard() {
        return board.getBoard();
    }

    public String getEndMsg() {
        return endMsg;
    }

    public void setEndMsg(String endMsg) {
        this.endMsg = endMsg;
    }


    public void Play(){
        turn++;
        List<Integer> Coordinates = new ArrayList<>();

        do {
            Coordinates = currentPlayer.choosePlayCoordinates(getBoard(), testGameNameConnect4(), Coordinates);
        } while (testCoordinates(Coordinates));
        setOwner(Coordinates.get(0), Coordinates.get(1), currentPlayer);
        testEnd();
    }


    public void testEnd() {
        setGameOver(TESTVICTOIRE.isOver(getBoard(), victorySize, currentPlayer.getSymbol()));

        if (gameOver) {
            setEndMsg("Victoire du joueur " + currentPlayer.getName(gameName.toString()));
        } else if (turn == getBoard().length * getBoard()[0].length) {
            setEndMsg("\nMatch nul \n");
            gameOver = true;
        }
    }

    public void createClearBoard(){
        board = new Board(size, gameName);
    }

    public void createPlayer(boolean isBot, int difficulty, String symbol) throws Exception {

        if (isBot){
            switch (symbol){
                case "X":
                    this.PLAYER[0] = new ArtificialPlayer(Cell.CellState.X, TESTVICTOIRE, victorySize, getGameName(), difficulty);
                    break;
                case "O":
                    this.PLAYER[1] = new ArtificialPlayer(Cell.CellState.O, TESTVICTOIRE, victorySize, getGameName(), difficulty);
                    break;
            }
        }else {
            switch (symbol){
                case "X":
                    this.PLAYER[0] = new HumanPlayer(Cell.CellState.X);
                break;
                case "O":
                    this.PLAYER[1] = new HumanPlayer(Cell.CellState.O);
                    break;

            }
        }

    }


    private boolean testCoordinates(List<Integer> coordinate) {
        //test de la ligne
         if (coordinate.get(0) < 0 || coordinate.get(0) >= getBoard().length) {
            return true;        //test de la colonne
        }else if (coordinate.get(1) < 0 || coordinate.get(1) >= getBoard()[0].length) {
            return true;
        }else return getBoard()[coordinate.get(0)][coordinate.get(1)].getState() != Cell.CellState.EMPTY;
    }

    protected void setOwner(int ligne, int colonne, Player player) {
        coordinates = ("[" + ligne + ", " + colonne + "]");
        getBoard()[ligne][colonne].setState(player.getSymbol());
    }

    Cell.CellState getOwner(int ligne, int colonne) {
        return getBoard()[ligne][colonne].getState();
    }



    public String getGameName() {
        return gameName.toString();
    }

    public String[][] getDisplayBoard() {
        String[][] displayBoard = new String[getBoard().length][getBoard()[0].length];
        for (int i = 0; i < getBoard().length; i++) {
            for (int j = 0; j < getBoard()[0].length; j++) {
                displayBoard[i][j] = getBoard()[i][j].getRepresentation(getGameName());
            }
        }
        return displayBoard;
    }

    public String getBoardBackground() {
        return switch (gameName) {
            case TICTACTOE -> "\u001B[100m";
            case CONNECT4 -> "\u001B[44m";
            case GOMOKU -> "\u001B[101m";
        };
    }

    public boolean testGameNameConnect4() {
        return gameName == GameName.CONNECT4;
    }

    public String getCurrentPlayerName() {
        return currentPlayer.getName(getGameName());
    }


    public String getOwner() {
        return coordinates;
    }

    public void changeCurrentPlayer() {
        if (getCurrentPlayer() == null) {
            int random = (int) (Math.random() * 2);
            switch (random) {
                case 0:
                    setCurrentPlayer(this.PLAYER[0]);
                    break;
                case 1:
                    setCurrentPlayer(this.PLAYER[1]);
                    break;
            }
        } else if (getCurrentPlayer() == this.PLAYER[0]) {
            setCurrentPlayer(this.PLAYER[1]);
        } else if (getCurrentPlayer() == this.PLAYER[1]) {
            setCurrentPlayer(this.PLAYER[0]);
        }

    }


    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }


    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isCurrentPlayerBot() {
        return currentPlayer.isBot();
    }

    public int getCurrentPlayerDifficulty(){
        return currentPlayer.getDifficultyInt();
    }
}
