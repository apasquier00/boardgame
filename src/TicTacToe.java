import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TicTacToe {
    Player[] players;
    InteractionUtilisateur interactionUtilisateur;
    TestVictoire testVictoire;
    View view;
    boolean gameOver;
    //constructor
    TicTacToe() {
        for (int i = 0; i <= size; i++)
            for (int j = 0; j <= size; j++) {
                ticTacToeArray[i][j] = new Cell();
            }
        this.players = new Player[2];
        this.view = new View();
        this.testVictoire = new TestVictoire();
        this.interactionUtilisateur = new InteractionUtilisateur(view);
        this.gameOver = false;
    }

    public final int size = 3;
    Cell[][] ticTacToeArray = new Cell[size + 1][size + 1];


    void play() {

        do{

            clearGrid();
            createPlayer();

            List<Integer> coordinates = new ArrayList<>();

            do {
                for (Player p : players) {
                    //affichage de la grille
                    view.gridDisplay(size, ticTacToeArray);

                    // Tour du Joueur
                    view.playerTurn(p);
                    //reinitialisation des coordonées
                    coordinates.clear();
                    coordinates.add(-1);
                    coordinates.add(-1);
                    //test des coordonées
                    while (!testCoordinates(coordinates)){

                        coordinates = p.play(ticTacToeArray);
                        if (!testCoordinates(coordinates)) {
                            view.unvalidCoordinate();
                            view.printMsg(coordinates.toString());
                        }
                    }
                    setOwner(coordinates.get(0), coordinates.get(1), p);
                    gameOver = testVictoire.isOver(ticTacToeArray, size);
                    if (gameOver) {
                        break;
                    }
                }

            } while (!gameOver);

            view.gridDisplay(size, ticTacToeArray);

            view.endMsg(testVictoire.getVictoryMessage());
        }while (true);



    }



    void clearGrid(){
        for (int i = 0; i <= size; i++){
            for (int j = 0; j <= size; j++){
                ticTacToeArray[i][j].state = Cell.cellstate.EMPTY;
            }
        }
    }


    void setOwner(int ligne, int colonne, Player player) {
        ticTacToeArray[ligne][colonne].setState(player.getSymbol());
    }

    void createPlayer(){
        // demander le nombre de bots

        int botNumber = 0;
        int botDifficulty = 0;
        ArtificialPlayer.Difficulty difficulty = ArtificialPlayer.Difficulty.EASY;
        do {

            botNumber = interactionUtilisateur.getBotsNumber();
                //initialisation des joueurs
            botDifficulty = interactionUtilisateur.getBotsDifficulty();
        }while ((0 > botNumber || botNumber > 3)&& 0>botDifficulty || botDifficulty >3 );
        switch (botDifficulty){
            case 0: break;
            case 1: difficulty= ArtificialPlayer.Difficulty.MEDIUM; break;
            case 2: difficulty= ArtificialPlayer.Difficulty.HARD; break;
        }
        switch (botNumber) {
            case 0:
                this.players[0] = new HumanPlayer(Player.Symbol.X, interactionUtilisateur);
                this.players[1] = new HumanPlayer(Player.Symbol.O, interactionUtilisateur);
                break;
            case 1:
                this.players[0] = new ArtificialPlayer(Player.Symbol.X, testVictoire, difficulty);
                this.players[1] = new HumanPlayer(Player.Symbol.O, interactionUtilisateur);
                break;
            case 2:
                this.players[0] = new ArtificialPlayer(Player.Symbol.X, testVictoire, difficulty);
                this.players[1] = new ArtificialPlayer(Player.Symbol.O, testVictoire, difficulty);
                break;
                //fermer le programme si le joueur selectionne 3
            case 3:
                System.exit(0);
        }
    }

    boolean testCoordinates(List<Integer> coordinate) {
        //verifier que les coordonnées sont valides
        for (Integer integer : coordinate) {
            if (integer < 0 || integer > size-1) return false;
        }
        if (ticTacToeArray[coordinate.get(0)][coordinate.get(1)].getState() != Cell.cellstate.EMPTY){
            return false;
        }
    return true;
    }




}



