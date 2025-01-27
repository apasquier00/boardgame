package model;

import controller.TestVictoire;
import view.InteractionUtilisateur;

import java.util.ArrayList;
import java.util.List;

public class ArtificialPlayer extends Player {
    enum Difficulty {EASY, MEDIUM, HARD};
    public Difficulty difficulty;
    TestVictoire testVictoire;
    InteractionUtilisateur interactionUtilisateur;
    int victorySize;
    BoardGame.GameName gameName;
    // RAJOUTER LE EXTEND PLAYER
    ArtificialPlayer(Player.Symbol symbolP, TestVictoire testVictoire, Difficulty dif, InteractionUtilisateur interactionUtilisateur, int victorySize, BoardGame.GameName gameName) {
        super(symbolP);
        this.difficulty = dif;
        this.testVictoire = testVictoire;
        this.interactionUtilisateur = interactionUtilisateur;
        this.victorySize = victorySize;
        this.gameName = gameName;

        setDifficulty();
    }



    public void setDifficulty() {
        int botDifficulty;

        this.difficulty = difficulty;
        do {
            botDifficulty = interactionUtilisateur.getNumber("Merci de choisir votre difficultée pour Artificial player " + getRepresentation(gameName) + " : 0 = EASY; 1 = MEDIUM; 2 = HARD");

            switch (botDifficulty){
                case 0: break;
                case 1: difficulty= ArtificialPlayer.Difficulty.MEDIUM; break;
                case 2: difficulty= ArtificialPlayer.Difficulty.HARD; break;
            }
        }
        while (0 > botDifficulty || botDifficulty > 2 );

    }

    @Override
    List<Integer> play(Cell[][] board) {
        switch (difficulty){
            case EASY: return autoPlay(board);
            case MEDIUM, HARD:
                return ia(board, this);

        }
        List<Integer> c = new ArrayList<>();

        //c = autoPlay(board);

        c = ia(board, this);

        return c;
    }



    List<Integer> autoPlay(Cell[][] board) {

        List<int[]> listCoordos = new ArrayList<>();
        List<Integer> coordinates = new ArrayList<Integer>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].state == Cell.cellstate.EMPTY) {
                    listCoordos.add(new int[]{i, j});
                }
            }
        }

        int[] botCoordinates = listCoordos.get(randomizeNumber(listCoordos.size() - 1));
        coordinates.add(botCoordinates[0]);
        coordinates.add(botCoordinates[1]);


        return coordinates;
    }


    int randomizeNumber(int n) {
        int n1 = 0;
        n1 = ((int) ((n + 1) * Math.random()));
        return n1;
    }

    List<Integer> ia(Cell[][] board, Player player) {
        List<Integer> coordinates = new ArrayList<Integer>();
        //le robot joue au milieu si le tableau est pair
        //seulement avec le bot difficile et le tictactoe
        if ((difficulty == Difficulty.HARD && gameName== BoardGame.GameName.tictactoe)) {
            if ((board.length % 2) != 0 && board[(board.length) / 2][(board.length) / 2].state == Cell.cellstate.EMPTY) {
                coordinates.add((board.length) / 2);
                coordinates.add((board.length) / 2);
                return coordinates;
            }
        }
            for (int i = 0; i < board[0].length; i++) {
                if (gameName != BoardGame.GameName.connect4) {
                    for (int j = 0; j < board[i].length; j++) {

                        //verification si la victoire est possible
                        if (isWinningCell(board, i, j, false, player.symbol)) {
                            coordinates.add(i);
                            coordinates.add(j);
                            return coordinates;
                        }


                    }
                }else {
                    //Test pour le puissance 4

                    int j = returnLowestFreeCoordinate(board, i);
                    if (j<0){
                        return autoPlay(board);
                    }
                    if (isWinningCell(board, j, i, false, player.symbol)) {
                        coordinates.add(j);
                        coordinates.add(i);
                        return coordinates;
                    }

                }
            }
        // verification si la victoire de l'ennemi est possible
        //seulement avec le bot difficile
            if (difficulty == Difficulty.HARD){
                for (int i = 0; i < board[0].length; i++) {
                    for (int j = 0; j < board.length; j++) {
                        if (gameName != BoardGame.GameName.connect4) {
                            if (isWinningCell(board, i, j, true, player.symbol)) {
                                coordinates.add(i);
                                coordinates.add(j);
                                return coordinates;
                            }
                        }else {
                            if (isWinningCell(board, j, i, true, player.symbol)) {
                                coordinates.add(j);
                                coordinates.add(i);
                                return coordinates;
                            }
                        }

                    }
                }
            }

            //ia avancée PAS ENCORE OPERATIONELLE
            
            /*
            if (!isBoardEmpty(board)){
                List<Integer> randomAdjacent = new ArrayList<>();
                int i = 0;
                int j = 0;
                do {
                    do {
                        i = (int) (Math.random() * board.length-1);
                        j = (int) (Math.random() * board[0].length-1);
                    }while (board[i][j].state == Cell.cellstate.EMPTY);
                    randomAdjacent = returnRandomAdjacentCell(i, j);

                } while ((randomAdjacent.get(0)) < 0 || (randomAdjacent.get(1) < 0) || (randomAdjacent.get(0) > board.length) || (randomAdjacent.get(1) > board[0].length));
                return randomAdjacent;
            }

             */



        //verification de la victoire par l'adversaire




        return autoPlay(board);
    }

    int returnLowestFreeCoordinate(Cell[][] board, int j){
        for (int i = 0; i < board.length; i++) {
            if (board[i][j].state != Cell.cellstate.EMPTY){
                return i-1;
            }
        }
        return board.length -1;
    }



boolean isWinningCell(Cell[][] board1, int i, int j, boolean forEnemy, Player.Symbol symbolForTest) {
        boolean winning = false;
        if (board1[i][j].state != Cell.cellstate.EMPTY) {
            return false;
            //verification de la victoire pour le joueur
        } else if (!forEnemy){
            switch (symbolForTest){
                case O: board1[i][j].setState(Cell.cellstate.O);break;
                case X: board1[i][j].setState(Cell.cellstate.X);break;

            }
        }else{
            //verification de la victoire pour le joueur adverse

            switch (symbolForTest) {
                case X:
                    board1[i][j].setState(Cell.cellstate.O);
                    symbolForTest = Player.Symbol.O;
                    break;
                case O:
                    board1[i][j].setState(Cell.cellstate.X);
                    symbolForTest = Player.Symbol.X;
                    break;
            }
        }
        winning = testVictoire.isOver(board1, victorySize, symbolForTest, gameName, this.getRepresentation(gameName));

    board1[i][j].setState(Cell.cellstate.EMPTY);

    return winning;
}


    List<Integer> returnRandomAdjacentCell(int i, int j){
        List<Integer> adjacentCells = new ArrayList<>();
        int randomCell = (int) (Math.random() * 7);
        switch (randomCell){
            case 0:
                adjacentCells.add(i-1);adjacentCells.add(j-1); return adjacentCells;
            case 1:
                adjacentCells.add(i-1);adjacentCells.add(j); return adjacentCells;
            case 2:
                adjacentCells.add(i-1);adjacentCells.add(j+1); return adjacentCells;
            case 3:
                adjacentCells.add(i);adjacentCells.add(j-1); return adjacentCells;
            case 4:
                adjacentCells.add(i);adjacentCells.add(j+1); return adjacentCells;
            case 5:
                adjacentCells.add(i+1);adjacentCells.add(j-1); return adjacentCells;
            case 6:
                adjacentCells.add(i+1);adjacentCells.add(j); return adjacentCells;
            case 7:
                adjacentCells.add(i+1);adjacentCells.add(j+1); return adjacentCells;


        }
    return adjacentCells;
    }

    boolean isBoardEmpty(Cell[][] board){
        for (Cell[] cells : board) {
            for (int j = 0; j < board.length; j++) {
                if (cells[j].state != Cell.cellstate.EMPTY) {
                    return false;
                }
            }
        }
        return true;

    }



}





