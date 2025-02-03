package model.board;

import model.games.BoardGame;

public class Board {


    private Cell[][] board;
    int size;
    BoardGame.GameName gameName;

    public Board(int size, BoardGame.GameName gameName) {
        this.size = size;
        this.gameName = gameName;
        createClearBoard();
    }


    public void createClearBoard() {
        if (gameName != BoardGame.GameName.CONNECT4) {
            board = new Cell[size][size];
        } else {
            board = new Cell[size][size + 1];
        }
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Cell();
            }
    }



    public Cell[][] getBoard() {
        return board;
    }

}
