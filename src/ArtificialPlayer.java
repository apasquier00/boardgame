import java.util.ArrayList;
import java.util.List;

public class ArtificialPlayer extends Player {

    enum Difficulty {EASY, MEDIUM, HARD};
    public Difficulty difficulty;
    TestVictoire testVictoire;
    InteractionUtilisateur interactionUtilisateur;
    // RAJOUTER LE EXTEND PLAYER
    ArtificialPlayer(Symbol symbolP, TestVictoire testVictoire, Difficulty dif, InteractionUtilisateur interactionUtilisateur) {
        super(symbolP);
        this.difficulty = dif;
        this.testVictoire = testVictoire;
        this.interactionUtilisateur = interactionUtilisateur;

        setDifficulty();

    }



    public void setDifficulty() {
        int botDifficulty;

        this.difficulty = difficulty;
        do {
            botDifficulty = interactionUtilisateur.getBotsDifficulty(symbol);

            switch (botDifficulty){
                case 0: break;
                case 1: difficulty= ArtificialPlayer.Difficulty.MEDIUM; break;
                case 2: difficulty= ArtificialPlayer.Difficulty.HARD; break;
            }
        }
        while (0 > botDifficulty || botDifficulty > 3 );

    }

    @Override
    List<Integer> play(Cell[][] board) {
        switch (difficulty){
            case EASY: return autoPlay(board);
            case MEDIUM, HARD:
                return ia(board);

        }
        List<Integer> c = new ArrayList<>();

        //c = autoPlay(board);

        c = ia(board);

        return c;
    }



    List<Integer> autoPlay(Cell[][] board) {

        List<int[]> listCoordos = new ArrayList<>();
        List<Integer> coordinates = new ArrayList<Integer>();
        for (int i = 0; i < board.length - 1; i++) {
            for (int j = 0; j < board[i].length - 1; j++) {
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

    List<Integer> ia(Cell[][] board) {
        List<Integer> coordinates = new ArrayList<Integer>();
        //le robot joue au milieu si le tableau est pair
        //seulement avec le bot difficil
        if (difficulty == Difficulty.HARD) {
            if ((board.length % 2) == 0 && board[(board.length - 1) / 2][(board.length - 1) / 2].state == Cell.cellstate.EMPTY) {
                coordinates.add((board.length - 1) / 2);
                coordinates.add((board.length - 1) / 2);
                return coordinates;
            }
        }



        for (int i = 0; i < board.length - 1; i++) {
            for (int j = 0; j < board[i].length - 1; j++) {


                //verification si la victoire est possible

                if (isWinningCell(board, i, j, false)) {
                    coordinates.add(i);
                    coordinates.add(j);
                    return coordinates;
                }

                // verification si la victoire de l'ennemi est possible
                //seulement avec le bot difficile

                if (difficulty == Difficulty.HARD) {
                    if (isWinningCell(board, i, j, true)) {
                        coordinates.add(i);
                        coordinates.add(j);
                        return coordinates;
                    }
                }

            }
        }

        //verrification de la victoire par l'adversaire




        return autoPlay(board);
    }

boolean isWinningCell(Cell[][] board1, int i, int j, boolean forEnemy) {
        boolean winning = false;
        if (board1[i][j].state != Cell.cellstate.EMPTY) {
            return false;
        } else if (((symbol==Player.Symbol.O)&&!forEnemy) || ((symbol==Player.Symbol.X)&& forEnemy)) {
            board1[i][j].setState(Cell.cellstate.O);
        } else if (((symbol==Player.Symbol.X)&&!forEnemy) || ((symbol==Player.Symbol.O)&& forEnemy)) {
            board1[i][j].setState(Cell.cellstate.X);
        }
        winning = testVictoire.isOver(board1, board1.length - 1);
        board1[i][j].setState(Cell.cellstate.EMPTY);
    return winning;
}
}


