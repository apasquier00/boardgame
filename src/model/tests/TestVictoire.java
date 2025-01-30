package model.tests;


import model.board.Cell;

public class TestVictoire {




    public TestVictoire() {
    }

    public boolean isOver(Cell[][] board, int victoryNumber, Cell.cellstate symbol) {


        //test de victoire par collonne ou ligne simplifié en 1 seule ligne
//        if (countLines(board, victoryNumber, symbol, playerName)){
//            return true;
//        }else if (countCols(board, victoryNumber, symbol, playerName)){
//            return true;
//        }
//        else if (countDiags(board, victoryNumber, symbol, playerName)){
//            return true;
//        }else if (countEmptyCells(board)){
//            return true;
//        }
        //        return false;

        return countLines(board, victoryNumber, symbol) || countCols(board, victoryNumber, symbol)
                || countRisingDiags(board, victoryNumber, symbol)
                || countDescendingDiags(board, victoryNumber, symbol);

    }

    private boolean solve(Cell[][] board, int victoryNumber, Cell.cellstate testCellState) {
        int count = 0;
        for (Cell[] cells : board) {
            for (int j = 0; j < board[0].length; j++) {
                if (cells[j].getState() == testCellState) {
                    count++;
                    if (count == victoryNumber) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
            count = 0;
        }

        return false;
    }

    private boolean countLines(Cell[][] board, int victoryNumber, Cell.cellstate testCellState) {
//        int count = 0;
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[0].length; j++) {
//                if (board[i][j].getState() == testCellState) {
//                    count++;
//                    if (count == victoryNumber) {
//                        victoryMessage = "Victoire du joueur " + playerName + " avec une ligne";
//                        return true;
//                    }
//                } else {
//                    count = 0;
//                }
//            }
//            count = 0;
//        }
//
//        return false;
        return solve(board, victoryNumber, testCellState);
    }


    private boolean countCols(Cell[][] board, int victoryNumber, Cell.cellstate testCellState) {
        Cell[][] newBoard = new Cell[board[0].length][board.length];
        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard[0].length; j++) {
                newBoard[i][j] = board[j][i];
            }
        }
        return solve(newBoard, victoryNumber, testCellState);
//        int count = 0;
//
//        for (int j = 0; j < board[0].length; j++) {
//            for (int i = 0; i < board.length; i++) {
//                if (board[i][j].getState() == testCellState) {
//                    count++;
//                    if (count == victoryNumber) {
//                        victoryMessage = "Victoire du joueur " + playerName + " avec une colonne";
//                        return true;
//                    }
//                } else {
//                    count = 0;
//                }
//            }
//            count = 0;
//
//        }
//        return false;
    }


    private boolean countRisingDiags(Cell[][] board, int victoryNumber, Cell.cellstate testCellState) {
        //RISING DIAG
//        int maxDiagLength = Math.max(board.length, board[0].length);
//        int nbrOfDiagsToTest = board.length + board[0].length -1 - (victoryNumber - 1) * 2;
//        Cell[][] newBoard = new Cell[maxDiagLength][nbrOfDiagsToTest];
//        for (int i = 0; i < newBoard.length; i++) {
//            for (int j = 0; j < newBoard[0].length; j++) {
//                newBoard[i][j] = new Cell(BoardGame.GameName.GOMOKU);
//                newBoard[i][j].setState(Cell.cellstate.EMPTY);
//            }
//        }
//
//        for (int k = victoryNumber -1; k < (board.length + board[0].length) - victoryNumber ; k++) {
//            for (int i = 0; i < board.length ; i++) {
//                for (int j = 0; j < board[0].length ; j++) {
//
//                    if (i+j == k){
//                        newBoard[i][j]=board[i][j];
//                        System.out.println("INITIALISATION DE LA CASE I = " + i +" J = " + j + " STATE = " + board[i][j].getState());
//
//                    }
//
//                }
//            }
//        }
//        for (Cell[] cells : newBoard) {
//            for (int j = 0; j < newBoard[0].length; j++) {
//                System.out.print("[" + cells[j].getState() + "]");
//            }
//            System.out.println();
//        }
//        return solve(newBoard, victoryNumber, testCellState);





        int count = 0;

        for (int k = victoryNumber -1; k < (board.length + board[0].length) - victoryNumber ; k++) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (i + j == k) {
                        if (board[i][j].getState() == testCellState) {
                            count++;
                            if (count == victoryNumber) {
                                return true;
                            }
                        } else {
                            count = 0;
                        }

                    }


                }


            }
            count = 0;
        }
        return false;
    }
    private boolean countDescendingDiags(Cell[][] board, int victoryNumber, Cell.cellstate testCellState) {
        int count = 0;
        //DESCENDING DIAG
        for (int k = -(board[0].length) + victoryNumber; k <= board.length-victoryNumber ; k++) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (i - j == k) {
                        if (board[i][j].getState() == testCellState) {
                            count++;
                            if (count == victoryNumber) {
                                return true;
                            }
                        } else {
                            count = 0;
                        }

                    }

                }
            }
            count = 0;

        }

        return false;
    }


// INNUTILE
//    boolean countEmptyCells(Cell[][] board) {
//        int count = 0;
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[0].length; j++) {
//                if (board[i][j].getState() == Cell.cellstate.EMPTY) {
//                    count++;
//                }
//            }
//        }
//
//        if (count == 0) {
//            return true;
//        }
//        return false;
//    }


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


