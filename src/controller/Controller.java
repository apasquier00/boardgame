package controller;

import model.games.*;
import view.InteractionUtilisateur;
import view.View;
import java.util.List;

public class Controller {
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
            int i;
            do {
                do {
                    try{
                        i = interactionUtilisateur.getNumber(view.getGameChoiceMsg());
                        createGame(i);
                        break;
                    } catch (Exception e) {
                        view.printExeption(e);
                    }

                }while (true);
                if (i==3){ break;}
                boolean replay;
                do {
                    // création du tableau et des joueurs
                    initGame();

                    // début de la partie

                    do {
                        //recuperation des coordonnées
                        playTurns();
                    }while (!game.isGameOver());

                    view.gridDisplay(game.getDisplayBoard(), game.getBoardBackground());


                    view.endMsg(game.getEndMsg());

                    //demande si rejouer
                    replay = interactionUtilisateur.getBool(view.createReplayMsg(game.getGameName()));
                    if (!replay) {
                        try{
                            createGame(i);
                        }
                        catch (Exception e) {
                            view.printExeption(e);
                        }
                        }
                } while (!replay);

            }while (true);
        }
    }


    // création du tableau et des joueurs
    void initGame(){
        interactionUtilisateur.printMsg("Bienvenue dans le " + game.getGameName() + "\n");

        //créer le tableau
        game.createClearBoard();
        do {
            try {
                int i = askBotNumber();
                if ((i == 0 || i == 1 || i == 2)) {
                    createPlayers(i); //créer les joueurs
                    break;
                } else {
                    throw new Exception("choix invalide");
                }
            } catch (Exception e) {
                view.printExeption(e);
            }

        } while (true);




    }
    // jouer le tour de 1 joueur
    void playTurns(){
            game.changeCurrentPlayer();
            view.gridDisplay(game.getDisplayBoard(), game.getBoardBackground());
            interactionUtilisateur.printMsg("Joueur " + game.getCurrentPlayerName() + " : c'est à vous" );

            //playCoordinates = game.getCurrentPlayer().choosePlayCoordinates();

            askCoordinates();

            interactionUtilisateur.printMsg("Joueur "+ game.getCurrentPlayerName() + " : à décider de jouer en " + game.getOwner() );
    }

//instancie les joueurs
    private void createPlayers (int botNumber)throws Exception{
        switch (botNumber){
            case 0:
                game.createPlayer(false, 0, "X");
                game.createPlayer(false, 0, "O");
                break;
            case 1:
                game.createPlayer(true, askBotDifficulty("X"), "X");
                game.createPlayer(false, 0, "O");
                break;
            case 2:
                game.createPlayer(true, askBotDifficulty("X"), "X");
                game.createPlayer(true, askBotDifficulty("O"), "O");
                break;
            default:throw new Exception("choix de joueurs invalide");
        }
    }


    // Demande le nombre de bot
    private int askBotNumber() throws Exception{

        return interactionUtilisateur.getNumber( view.getArtificialPlayerChoice());
    }



    // Demande la difficultée pour le joueur artificiel
    private int askBotDifficulty(String Symbol){
        do {
            try {
                int g = interactionUtilisateur.getNumber(view.createDifficultyMessage(Symbol));
                if (g ==1 || g ==2 || g ==3 || g ==4){
                    return g;
                }else throw new Exception("choix de la difficultée invalide");
            } catch (Exception e) {
                view.printExeption(e);
            }
        }while (true);

    }

    // Demande les coordonnées à jouer
    private void askCoordinates(){
        do {
            try {
                game.Play();
                break;
            } catch (Exception e) {
                view.printExeption(e);
            }
        }while (true);
    }

    // instancie le jeu
    private void createGame(int i) throws Exception {
        switch (i){
            case 0: game = new TicTacToe(); break;
            case 1: game = new Gomoku(); break;
            case 2: game = new ConnectFour(); break;
            case 3: break;
            default: throw new Exception("choix de jeux invalide");
        }
    }
}


