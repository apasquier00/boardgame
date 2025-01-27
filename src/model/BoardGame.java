package model;

import controller.TestVictoire;
import view.InteractionUtilisateur;
import view.View;

import java.util.ArrayList;
import java.util.List;

abstract public class BoardGame {
    public Cell[][] getBoard() {
        return Board;
    }

    private Cell[][] Board;
    public enum GameName{
        CONNECT4, TICTACTOE, GOMOKU
    }
    private GameName gameName;
    private Player[] players;

    private InteractionUtilisateur interactionUtilisateur;
    private TestVictoire testVictoire;
    private View view;
    private boolean gameOver;
    private TicTacToe ticTacToe = new TicTacToe();
    private Gomoku gomoku = new Gomoku();
    private ConnectFour connectFour = new ConnectFour();
    public BoardGame(int size, int victorySize, GameName gameName) {
        this.ticTacToe = new TicTacToe();
        this.gomoku = new Gomoku();
        this.connectFour = new ConnectFour();
        this.players = new Player[2];
        this.view = new View();
        this.testVictoire = new TestVictoire();
        this.interactionUtilisateur = new InteractionUtilisateur(view);
        this.gameOver = false;
        this.gameName = gameName;
        this.size = size;
        this.victorySize = victorySize;
    }

    int victorySize;
    int size;



    public void play(){
        view.printMsg("Bienvenue dans le " + gameName + "\n");


        do{

            createClearBoard();
            createPlayer();

            List<Integer> coordinates = new ArrayList<>();

            do {
                for (Player p : players) {
                    //affichage de la grille
                    view.gridDisplay(Board, gameName);

                    // Tour du Joueur
                    view.printMsg("Joueur " + p.getName(gameName) + " : c'est à vous" );
                    //reinitialisation des coordonées
                    coordinates.clear();
                    //test des coordonées
                    do
                    {
                        coordinates = p.play(Board);
                        if (!testCoordinates(coordinates)) {
                            view.unvalidCoordinate();
                            view.printMsg(coordinates.toString());
                        }
                    }while (!testCoordinates(coordinates));
                    if (gameName.equals(GameName.CONNECT4)){
                        view.printMsg("  Joueur "+p.getName(gameName) + " : à décider de jouer en " + coordinates.get(1) );
                    } else {
                        view.printMsg("  Joueur "+p.getName(gameName) + " : à décider de jouer en " +coordinates.get(0) + " ; " + coordinates.get(1) );
                    }

                    setOwner(coordinates.get(0), coordinates.get(1), p);
                    gameOver = testVictoire.isOver(Board, victorySize, p.getSymbol(), gameName, p.getName(gameName));
                    if (gameOver) {
                        break;
                    }
                }

            } while (!gameOver);

            view.gridDisplay(Board, gameName);

            int exit = 0;
            view.endMsg(testVictoire.getVictoryMessage());
            do{
                exit = interactionUtilisateur.getNumber("Voulez vous refaire une partie de " + gameName + " ?\n" +
                        "0 : OUI\n" +
                        "1 : NON");
            } while (exit < 0 || exit > 1);

            if (exit == 1){
                break;
            }
        }while (true);



    }






    void createPlayer(){
        // demander le nombre de bots

        int botNumber = 0;
        int botDifficulty = 0;
        ArtificialPlayer.Difficulty difficulty = ArtificialPlayer.Difficulty.EASY;
        do {

            botNumber = interactionUtilisateur.getNumber( """
                Combien de joueurs artificiel voulez vous ?\
                
                Joueurs artificiel Max : 2\s""");

            //initialisation des joueurs
        }while ((0 > botNumber || botNumber > 2));
        switch (botNumber) {
            case 0:
                this.players[0] = new HumanPlayer(Cell.cellstate.X, interactionUtilisateur, gameName);
                this.players[1] = new HumanPlayer(Cell.cellstate.O, interactionUtilisateur, gameName);
                break;
            case 1:
                this.players[0] = new ArtificialPlayer(Cell.cellstate.X, testVictoire, difficulty, interactionUtilisateur, victorySize, gameName);
                this.players[1] = new HumanPlayer(Cell.cellstate.O, interactionUtilisateur, gameName);
                break;
            case 2:
                this.players[0] = new ArtificialPlayer(Cell.cellstate.X, testVictoire, difficulty, interactionUtilisateur, victorySize, gameName);
                this.players[1] = new ArtificialPlayer(Cell.cellstate.O, testVictoire, difficulty, interactionUtilisateur, victorySize, gameName);
                break;
        }

    }


    boolean testCoordinates(List<Integer> coordinate) {
        //verifier que les coordonnées sont valides
        //test de la ligne
        if (coordinate.get(0) < 0 || coordinate.get(0) > Board.length){ return false;};
        //test de la colonne
        if(gameName.equals(GameName.CONNECT4)){
            if (coordinate.get(1) < 0 || coordinate.get(1) > Board[0].length-1){ return false;};
        }else {
            if (coordinate.get(1) < 0 || coordinate.get(1) > Board[0].length){ return false;};
        }


        if (Board[coordinate.get(0)][coordinate.get(1)].getState() != Cell.cellstate.EMPTY){
            return false;
        }
        return true;
    }

    void setOwner(int ligne, int colonne, Player player) {
        Board[ligne][colonne].setState(player.getSymbol());
    }

    Cell.cellstate getOwner(int ligne, int colonne) {
        return Board[ligne][colonne].getState();
    }

    void createClearBoard(){
        if (gameName != GameName.CONNECT4){
            Board = new Cell[size][size];
        }else {
            Board = new Cell[size][size + 1];
        }
        for (int i = 0; i < Board.length; i++)
            for (int j = 0; j < Board[0].length; j++) {
                Board[i][j] = new Cell(gameName);
            }
    }
}
