package model.Games;

import controller.InteractionUtilisateur;
import model.Board.Cell;
import model.Players.ArtificialPlayer;
import model.Players.HumanPlayer;
import model.Players.Player;
import model.Tests.TestVictoire;

import java.util.ArrayList;
import java.util.List;

abstract public class BoardGame {


    private Cell[][] Board;
    protected enum GameName{
        CONNECT4, TICTACTOE, GOMOKU
    }
    private GameName gameName;
    private Player[] players;

    private InteractionUtilisateur interactionUtilisateur;
    private TestVictoire testVictoire;
    private boolean gameOver;


    public BoardGame(int size, int victorySize, GameName gameName) {
        this.players = new Player[2];
        this.testVictoire = new TestVictoire();
        this.interactionUtilisateur = new InteractionUtilisateur();
        this.gameOver = false;
        this.gameName = gameName;
        this.size = size;
        this.victorySize = victorySize;
    }

    protected int victorySize;
    protected int size;


    public Cell[][] getBoard() {
        return Board;
    }
    public void play(){
        interactionUtilisateur.printMsg("Bienvenue dans le " + gameName + "\n");

        do{
            createClearBoard();
            createPlayer();

            List<Integer> coordinates = new ArrayList<>();
            int turn = 0;

            do {
                for (Player p : players) {
                    turn ++;

                    //affichage de la grille
                    interactionUtilisateur.gridDisplay(createDisplayBoard(), createBoardBackground());

                    // Tour du Joueur
                    interactionUtilisateur.printMsg("Joueur " + p.getName(gameName.toString()) + " : c'est à vous" );
                    //reinitialisation des coordonées
                    coordinates.clear();
                    //test des coordonées
                    do
                    {
                        coordinates = p.play(Board, isGameNameConnect4());
                        if (testCoordinates(coordinates)) {
                            interactionUtilisateur.unvalidCoordinate(coordinates.toString());
                        }
                    }while (testCoordinates(coordinates));
                    if (gameName.equals(GameName.CONNECT4)){
                        interactionUtilisateur.printMsg("  Joueur "+p.getName(gameName.toString()) + " : à décider de jouer en " + coordinates.get(1) );
                    } else {
                        interactionUtilisateur.printMsg("  Joueur "+p.getName(gameName.toString()) + " : à décider de jouer en " +coordinates.get(0) + " ; " + coordinates.get(1) );
                    }
                    setOwner(coordinates.get(0), coordinates.get(1), p);
                    if (turn == Board.length * Board[0].length){
                        interactionUtilisateur.gridDisplay(createDisplayBoard(), createBoardBackground());
                        interactionUtilisateur.printMsg("\nMatch nul \n");
                        gameOver = true;
                        break;

                    }

                    gameOver = testVictoire.isOver(Board, victorySize, p.getSymbol());
                    if (gameOver) {
                        interactionUtilisateur.gridDisplay(createDisplayBoard(), createBoardBackground());
                        interactionUtilisateur.printMsg("\nVictoire du joueur " + p.getName(gameName.toString()) + "\n");
                        break;
                    }
                }

            } while (!gameOver);


            int exit = 0;
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






    private void createPlayer(){
        // demander le nombre de bots

        int botNumber = 0;
        ArtificialPlayer.Difficulty difficulty = ArtificialPlayer.Difficulty.EASY;
        do {

            botNumber = interactionUtilisateur.getNumber( """
                Combien de joueurs artificiel voulez vous ?\
                
                Joueurs artificiel Max : 2\s""");

            //initialisation des joueurs
        }while ((0 > botNumber || botNumber > 2));
        switch (botNumber) {
            case 0:
                this.players[0] = new HumanPlayer(Cell.cellstate.X, interactionUtilisateur);
                this.players[1] = new HumanPlayer(Cell.cellstate.O, interactionUtilisateur);
                break;
            case 1:
                this.players[0] = new ArtificialPlayer(Cell.cellstate.X, testVictoire, difficulty, interactionUtilisateur, victorySize, gameName.toString());
                this.players[1] = new HumanPlayer(Cell.cellstate.O, interactionUtilisateur);
                break;
            case 2:
                this.players[0] = new ArtificialPlayer(Cell.cellstate.X, testVictoire, difficulty, interactionUtilisateur, victorySize, gameName.toString());
                this.players[1] = new ArtificialPlayer(Cell.cellstate.O, testVictoire, difficulty, interactionUtilisateur, victorySize, gameName.toString());
                break;
        }

    }


    private boolean testCoordinates(List<Integer> coordinate) {
        //verifier que les coordonnées sont valides
        //test de la ligne
        if (coordinate.get(0) < 0 || coordinate.get(0) >= Board.length){ return true;};
        //test de la colonne
        if (coordinate.get(1) < 0 || coordinate.get(1) >= Board[0].length){ return true;}



        return Board[coordinate.get(0)][coordinate.get(1)].getState() != Cell.cellstate.EMPTY;
    }

    protected void setOwner(int ligne, int colonne, Player player) {
        Board[ligne][colonne].setState(player.getSymbol());
    }

    Cell.cellstate getOwner(int ligne, int colonne) {
        return Board[ligne][colonne].getState();
    }

    private void createClearBoard(){
        if (gameName != GameName.CONNECT4){
            Board = new Cell[size][size];
        }else {
            Board = new Cell[size][size + 1];
        }
        for (int i = 0; i < Board.length; i++)
            for (int j = 0; j < Board[0].length; j++) {
                Board[i][j] = new Cell();
            }
    }

    private String[][] createDisplayBoard(){
        String[][] displayBoard = new String[Board.length][Board[0].length];
        for (int i = 0; i < Board.length; i++){
            for (int j = 0; j < Board[0].length; j++){
                displayBoard[i][j] = Board[i][j].getRepresentation(gameName.toString());
            }
        }
        return displayBoard;
    }

    private String createBoardBackground(){
        String boardBackground = switch (gameName) {
            case TICTACTOE -> "\u001B[100m";
            case CONNECT4 -> "\u001B[44m";
            case GOMOKU -> "\u001B[101m";
        };
        return boardBackground;
    }

    private boolean isGameNameConnect4(){
        return gameName == GameName.CONNECT4;
    }



}
