package controller;

import model.games.*;
import view.InteractionUtilisateur;
import view.View;
import java.util.List;

public class Controller {
    private boolean replay;
    InteractionUtilisateur interactionUtilisateur;
    View view;
    BoardGame game;
    private List<Integer> coordinates;
    public Controller() {
        interactionUtilisateur = new InteractionUtilisateur();
        view = new View();
    }



    public void start(){
        {
            do {
                do {
                    int i = interactionUtilisateur.getNumber("""
                    A quel jeu voulez vous jouer ?
                    0 : Tic Tac Toe
                    1 : Gomoku
                    2 : Connect Four
                    3 : Quitter""");
                    switch (i){
                        case 0: game = new TicTacToe(); break;
                        case 1: game = new Gomoku(); break;
                        case 2: game = new ConnectFour(); break;
                        case 3: System.exit(0); break;
                    }
                }while (game == null);

                do {
                    initGame(); // création du tableau et des joueurs

                    // début de la partie

                    do {
                        //recuperation des coordonnées
                        playTurns();
                    }while (!game.isGameOver());

                    view.gridDisplay(game.getDisplayBoard(), game.getBoardBackground());


                    view.endMsg(game.getEndMsg());

                    //demande si rejouer
                    replay = interactionUtilisateur.getBool("Voulez vous refaire une partie de " + game.getGameName() + " ?\n" +
                            "1 : OUI\n" +
                            "2 : NON");
                } while (!replay);

            }while (true);
        }
    }
    void initGame(){
        interactionUtilisateur.printMsg("Bienvenue dans le " + game.getGameName() + "\n");

        game.createClearBoard();

        int botNumber = askBotNumber();

        game.createPlayer(botNumber);
    }

    void playTurns(){
            game.changeCurrentPlayer();
            view.gridDisplay(game.getDisplayBoard(), game.getBoardBackground());
            interactionUtilisateur.printMsg("Joueur " + game.getCurrentPlayerName() + " : c'est à vous" );
            if (game.isCurrentPlayerBot()){
                game.botPlay();
            }else {
                askCoordinates();
            }
            interactionUtilisateur.printMsg("Joueur "+ game.getCurrentPlayerName() + " : à décider de jouer en " + game.getOwner() );
    }




    private int askBotNumber(){
        int botNumber = 0;
        do {
            botNumber = interactionUtilisateur.getNumber( """
                Combien de joueurs artificiel voulez vous ?\
                
                Joueurs artificiel Max : 2\s""");

            //initialisation des joueurs
        }while ((0 > botNumber || botNumber > 2));
        return botNumber;
    }

    private void askCoordinates(){
        do {
            try {
                game.play(interactionUtilisateur.getCoordinates(game.testGameNameConnect4()));
                break;
            } catch (InvalidCoordinatesException e) {
                interactionUtilisateur.callUnvalidCoordinate(e.getCoordinates().toString());
            }
        }while (true);
    }


}


