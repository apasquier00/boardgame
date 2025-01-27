package model;

import controller.TestVictoire;
import view.InteractionUtilisateur;

import java.util.ArrayList;
import java.util.List;

public class ArtificialPlayer extends Player {
    public enum Difficulty {EASY, MEDIUM, HARD, EXTREME};

    private Difficulty difficulty;
    private TestVictoire testVictoire;
    private InteractionUtilisateur interactionUtilisateur;
    private int victorySize;
    private BoardGame.GameName gameName;


    ArtificialPlayer(Cell.cellstate symbolP, TestVictoire testVictoire, Difficulty dif, InteractionUtilisateur interactionUtilisateur, int victorySize, BoardGame.GameName gameName) {
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
        do {
            botDifficulty = interactionUtilisateur.getNumber("Merci de choisir votre difficultée pour Artificial player " + getName(gameName) + " : \n0 = EASY \uD83E\uDD21\n1 = MEDIUM \uD83D\uDE10\n2 = HARD \uD83D\uDE21 \n3 = EXTREME \uD83D\uDC80");

            switch (botDifficulty){
                case 0: break;
                case 1: difficulty= ArtificialPlayer.Difficulty.MEDIUM; break;
                case 2: difficulty= ArtificialPlayer.Difficulty.HARD; break;
                case 3: difficulty= ArtificialPlayer.Difficulty.EXTREME; break;
            }
        }
        while (0 > botDifficulty || botDifficulty > 3 );

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
        List<Integer> coordinates = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getState() == Cell.cellstate.EMPTY) {
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
        if ((difficulty == Difficulty.HARD && gameName== BoardGame.GameName.TICTACTOE)) {
            if ((board.length % 2) != 0 && board[(board.length) / 2][(board.length) / 2].getState() == Cell.cellstate.EMPTY) {
                coordinates.add((board.length) / 2);
                coordinates.add((board.length) / 2);
                return coordinates;
            }
        }
            for (int i = 0; i < board[0].length; i++) {
                if (gameName != BoardGame.GameName.CONNECT4) {
                    for (int j = 0; j < board[i].length; j++) {

                        //verification si la victoire est possible
                        if (isWinningCell(board, i, j, false, victorySize)) {
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
                    if (isWinningCell(board, j, i, false, victorySize)) {
                        coordinates.add(j);
                        coordinates.add(i);
                        return coordinates;
                    }

                }
            }
        // verification si la victoire de l'ennemi est possible
        //seulement avec le bot difficile
            if (difficulty == Difficulty.HARD || difficulty == Difficulty.EXTREME) {
                for (int i = 0; i < board[0].length; i++) {
                    for (int j = 0; j < board.length; j++) {

                            if (isWinningCell(board, j, i, true, victorySize)) {
                                coordinates.add(j);
                                coordinates.add(i);
                                return coordinates;
                            }


                    }
                }
            }

            if (difficulty == Difficulty.EXTREME) {
                return ExtremeAI(board);
            }



        return autoPlay(board);
    }

    int returnLowestFreeCoordinate(Cell[][] board, int j){
        for (int i = 0; i < board.length; i++) {
            if (board[i][j].getState() != Cell.cellstate.EMPTY){
                return i-1;
            }
        }
        return board.length -1;
    }



boolean isWinningCell(Cell[][] board1, int i, int j, boolean forEnemy, int victorySize) {
        boolean winning;
        Cell.cellstate symboltotest = getSymbol();
        if (board1[i][j].getState() != Cell.cellstate.EMPTY) {
            return false;
            //verification de la victoire pour le joueur
        } else if (!forEnemy){
            switch (getSymbol()){
                case O: board1[i][j].setState(Cell.cellstate.O);break;
                case X: board1[i][j].setState(Cell.cellstate.X);break;

            }
        }else{
            //verification de la victoire pour le joueur adverse

            symboltotest = switch (getSymbol()) {
                case X -> {
                    board1[i][j].setState(Cell.cellstate.O);
                    yield Cell.cellstate.O;
                }
                case O -> {
                    board1[i][j].setState(Cell.cellstate.X);
                    yield Cell.cellstate.X;
                }
                default -> symboltotest;
            };
        }
        winning = testVictoire.isOver(board1, victorySize, symboltotest, gameName, this.getName(gameName));

    board1[i][j].setState(Cell.cellstate.EMPTY);

    return winning;
}


    List<Integer> returnRandomAdjacentCell(int i, int j){
        List<Integer> adjacentCells = new ArrayList<>();
        int randomCell = (int) (Math.random() * 7);
        System.out.println("pour : "  + adjacentCells + " returnRandomAdjacentCell retourne " );
        switch (randomCell){
            case 0:
                adjacentCells.add(Math.abs(i-1));adjacentCells.add(Math.abs(j-1)); return adjacentCells;
            case 1:
                adjacentCells.add(Math.abs(i-1));adjacentCells.add(Math.abs(j)); return adjacentCells;
            case 2:
                adjacentCells.add(Math.abs(i-1));adjacentCells.add(Math.abs(j+1)); return adjacentCells;
            case 3:
                adjacentCells.add(Math.abs(i));adjacentCells.add(Math.abs(j-1)); return adjacentCells;
            case 4:
                adjacentCells.add(Math.abs(i));adjacentCells.add(Math.abs(j+1)); return adjacentCells;
            case 5:
                adjacentCells.add(Math.abs(i+1));adjacentCells.add(Math.abs(j-1)); return adjacentCells;
            case 6:
                adjacentCells.add(Math.abs(i+1));adjacentCells.add(Math.abs(j)); return adjacentCells;
            case 7:
                adjacentCells.add(Math.abs(i+1));adjacentCells.add(Math.abs(j+1)); return adjacentCells;


        }
    return adjacentCells;
    }

    boolean isBoardEmpty(Cell[][] board){
        for (Cell[] cells : board) {
            for (int j = 0; j < board.length; j++) {
                if (cells[j].getState() != Cell.cellstate.EMPTY) {
                    return false;
                }
            }
        }
        return true;

    }


    List<Integer> ExtremeAI(Cell[][] board){
        //ia avancée PAS ENCORE OPERATIONELLE
        List<Integer> randomAdjacent = new ArrayList<>();


            if (!isBoardEmpty(board)){
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board.length; j++) {
                        if (isWinningCell(board, i, j, true, victorySize-1)){
                            randomAdjacent.add(i);
                            randomAdjacent.add(j);
                            System.out.println("victorySize = -1 blocage de l'ennemie : " + randomAdjacent);
                            return randomAdjacent;
                        }
                    }
                }
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board.length; j++) {
                        if (isWinningCell(board, i, j, false, victorySize-1)){
                            randomAdjacent.add(i);
                            randomAdjacent.add(j);
                            System.out.println("victorySize = -1 : " + randomAdjacent);

                            return randomAdjacent;
                        }
                    }
                }
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board.length; j++) {
                        if (isWinningCell(board, i, j, true, victorySize-2)){
                            randomAdjacent.add(i);
                            randomAdjacent.add(j);
                            System.out.println("victorySize = -2 blocage de l'ennemie : " + randomAdjacent);

                            return randomAdjacent;
                        }
                    }
                }
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board.length; j++) {
                        if (isWinningCell(board, i, j, false, victorySize-2)){
                            randomAdjacent.add(i);
                            randomAdjacent.add(j);
                            System.out.println("victorySize = -2 : " + randomAdjacent);

                            return randomAdjacent;
                        }
                    }
                }

                int i = 0;
                int j = 0;
                do {
                    do {
                        i = (int) (Math.random() * board.length-1);
                        j = (int) (Math.random() * board[0].length-1);
                    }while (board[i][j].getState() == Cell.cellstate.EMPTY);

                    randomAdjacent = returnRandomAdjacentCell(i, j);
                    System.out.print(randomAdjacent);
                } while ((randomAdjacent.get(0)) < 0 || (randomAdjacent.get(1) < 0) || (randomAdjacent.get(0) > board.length) || (randomAdjacent.get(1) > board[0].length));
                return randomAdjacent;
            } else {
                return autoPlay(board);
            }
    }


}





