package controller;

import model.BoardGame;
import model.Cell;
import model.Player;

public class TestVictoire {



    public String victoryMessage;

    public String getVictoryMessage() {
        return victoryMessage;
    }


    public TestVictoire() {
    }

    public boolean isOver(Cell[][] board, int victoryNumber, Player.Symbol symbol, BoardGame.GameName gameName, String playerName) {
        Cell.cellstate testCellState = Cell.cellstate.EMPTY;
        if (symbol == Player.Symbol.X){
            testCellState = Cell.cellstate.X;
        } else if (symbol == Player.Symbol.O){
            testCellState = Cell.cellstate.O;
            }


        //test de victoire par collonne ou ligne
        if (countLines(board, victoryNumber, testCellState, playerName)){
            return true;
        }else if (countCols(board, victoryNumber, testCellState, playerName)){
            return true;
        }
        else if (countDiags(board, victoryNumber, testCellState, playerName)){
            return true;
        }else if (countEmptyCells(board)){
            return true;
        }

        return false;
    }

    boolean countLines(Cell[][] board, int victoryNumber, Cell.cellstate testCellState, String playerName) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].state == testCellState){
                    count++;
                    if (count == victoryNumber) {
                        victoryMessage = "Victoire du joueur " + playerName + " avec une ligne";
                        return true;
                    }
                }else {
                    count = 0;
                }
            }
            count = 0;
        }

        return false;
    }

    boolean countCols(Cell[][] board, int victoryNumber, Cell.cellstate testCellState, String playerName) {
        int count = 0;

        for (int j = 0; j < board[0].length; j++) {
            for (int i = 0; i < board.length; i++) {
                if (board[i][j].state == testCellState){
                    count++;
                    if (count == victoryNumber) {
                        victoryMessage = "Victoire du joueur " + playerName + " avec une colonne";
                        return true;
                    }
                }else {
                    count = 0;
                }
            }
            count = 0;

        }
        return false;
    }



        boolean countDiags(Cell[][] board, int victoryNumber, Cell.cellstate testCellState, String playerName){
        int count = 0;
        for (int k = 0; k < ((board[0].length)*2); k++) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                        if (i+j == k){
                            if (board[i][j].state == testCellState){
                                count++;
                                if (count == victoryNumber) {
                                    victoryMessage = "Victoire du joueur " + playerName + " avec une diagonnale";
                                    return true;
                                }
                            }
                            else {
                                count = 0;
                            }

                        }

                }
            }
            count = 0;

        }
        for (int k = -(board[0].length); k < board[0].length; k++) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (i-j == k){
                        if (board[i][j].state == testCellState){
                            count++;
                            if (count == victoryNumber) {
                                victoryMessage = "Victoire du joueur " + playerName + " avec une diagonnale";
                                return true;
                            }
                        }
                        else {
                            count = 0;
                        }

                    }

                }
            }
            count = 0;

        }

        return false;
    }


    boolean countEmptyCells(Cell[][] board){
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].state == Cell.cellstate.EMPTY){
                    count++;
                }
            }
        }

        if(count == 0){
            victoryMessage = "Match nul";

            return true;
        }
        return false;
    }


//OLD FUNCTION
    /*
    public boolean isOver(Cell[][] board, int boardSize, int victoryNumber) {
        int colO=0;
        int colX=0;
        int lineO=0;
        int lineX=0;
        int diagO1=0;
        int diagX1=0;
        int diagO2=0;
        int diagX2=0;
        int empty=0;

        // test de ligne identique
        for (int i = 0; i < boardSize; i++) {

            colO=0;
            colX=0;
            lineO=0;
            lineX=0;

            for (int j = 0; j < boardSize; j++) {
                //calcul des O ou des X dans chaque ligne
                switch (board[i][j].state) {
                    case Cell.cellstate.X:
                        lineX++;
                        break;
                    case Cell.cellstate.O:
                        lineO++;
                        break;
                }
                //meme calcul pour chaque colonne
                switch (board[j][i].state) {
                    case Cell.cellstate.X:
                        colX++;
                        break;
                    case Cell.cellstate.O:
                        colO++;
                        break;
                }
                //condition de victoire par ligne ou colonne
                if (lineX==victoryNumber){
                    victoryMessage = "victoire du joueur X avec une ligne";
                    return true;
                }else if (lineO==victoryNumber){
                    victoryMessage = "victoire du joueur O avec une ligne";
                    return true;
                } else if (colX==victoryNumber) {
                    victoryMessage = "victoire du joueur X avec une colonne";
                    return true;
                }else if (colO==victoryNumber) {
                    victoryMessage = "victoire du joueur O avec une colonne";
                    return true;
                }

                //calcul du nombre de cases vides
                switch (board[i][j].state){
                    case Cell.cellstate.EMPTY : empty++;
                        break;
                }

                //calcul des diagonales
                if (i == j){
                    switch (board[i][j].state){
                        case Cell.cellstate.X : diagX1++;
                            break;
                        case Cell.cellstate.O : diagO1++;
                            break;
                    }
                }
                if ((i + j) == boardSize-1){
                    switch (board[i][j].state){
                        case Cell.cellstate.X : diagX2++;
                            break;
                        case Cell.cellstate.O : diagO2++;
                            break;
                    }
                }
                //condition de victoire par diagonale
                if ((diagX1==victoryNumber)|| (diagX2==victoryNumber)){
                    this.victoryMessage = "victoire du joueur X avec une diagonale";
                    return true;
                } else if ((diagO2==victoryNumber) || (diagO1==victoryNumber )) {
                    this.victoryMessage = "victoire du joueur O avec une diagonale";

                }

            }
        }
        //condition de match nul

        if (empty == 0){
            this.victoryMessage = "Match nul";

            return true;
        }
        return false;

    }     */
    }


