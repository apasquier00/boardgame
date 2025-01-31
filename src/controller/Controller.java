package controller;

import model.games.BoardGame;
import model.games.ConnectFour;
import model.games.Gomoku;
import model.games.TicTacToe;
import view.InteractionUtilisateur;
import view.View;


public class Controller {


    private int gameChoice;
    private CurrentState currentState;
    private InteractionUtilisateur interactionUtilisateur;
    private View view;
    private BoardGame.GameName currentGame;
    private BoardGame game;
    private int botNumber;



    public Controller() {
        this.gameChoice = 0;
        this.currentState = CurrentState.GAMESELECTION;
        interactionUtilisateur = new InteractionUtilisateur();
        view = new View();
        this.botNumber = 0;
    }


    public void start() {
        {
            do {
                switch (currentState) {
                    case GAMESELECTION:
                        askGame();
                        break;
                    case CREATEGAME:
                        createGame();
                        break;
                    case PLAYERSELECTION:
                        playerSelection();
                        break;
                    case CREATEPLAYERS:
                        createPlayers();
                        break;
                    case PLAYING:
                        play();
                        break;
                    case ENDING:
                        replay();
                        break;
                    case BREAK:
                        break;
                }
            } while (currentState != CurrentState.BREAK);

        }
    }


    //Demande à l'utilisateur à quel jeu jouer
    private void askGame() {
        do {
            try {
                gameChoice = interactionUtilisateur.getNumber(view.getGameChoiceMsg());
                setCurrentGame(gameChoice);
                break;
            } catch (Exception e) {
                view.printExeption(e);
            }
        } while (true);
        if (gameChoice == 0){
            setCurrentState(CurrentState.BREAK);
        }else {
            setCurrentState(CurrentState.CREATEGAME);
        }
    }

    //Demande à l'utilisateur le nombre de joueurs artificiels

    private void playerSelection(){
        do {
            try {
                botNumber = askBotNumber();
                if ((botNumber == 0 || botNumber == 1 || botNumber == 2)) {
                    break;
                } else {
                    throw new Exception("choix invalide");
                }
            } catch (Exception e) {
                view.printExeption(e);
            }

        } while (true);
        setCurrentState(CurrentState.CREATEPLAYERS);
    }

    //Instancie les joueurs

    private void createPlayers() {
        do {
            try {
                createPlayers(botNumber); //créer les joueurs
                setCurrentState(CurrentState.PLAYING);
                break;
            }
            catch (Exception e) {
                view.printExeption(e);
                setCurrentState(CurrentState.PLAYERSELECTION);
            }
        }while (true);
    }

    // joue des tours jusque à la fin de la partie


    private void play() {

        do {
            //recuperation des coordonnées
            playTurns();
        } while (!game.isGameOver());

        view.gridDisplay(game.getDisplayBoard(), game.getBoardBackground());
        view.endMsg(game.getEndMsg());
        setCurrentState(CurrentState.ENDING);

    }


    //Demander au joueur si il veut rejouer
    private void replay() {
        boolean replay;
        try {
            replay = interactionUtilisateur.getBool(view.createReplayMsg(game.getGameName()));
            if (replay) {
                setCurrentState(CurrentState.GAMESELECTION);
            }else {
                setCurrentState(CurrentState.CREATEGAME);
            }
        }catch (Exception e) {
            view.printExeption(e);
        }

    }

    // jouer le tour de 1 joueur
    private void playTurns() {
        game.changeCurrentPlayer();
        view.gridDisplay(game.getDisplayBoard(), game.getBoardBackground());
        interactionUtilisateur.printMsg("Joueur " + game.getCurrentPlayerName() + " : c'est à vous");

        //playCoordinates = game.getCurrentPlayer().choosePlayCoordinates();

        askCoordinates();

        interactionUtilisateur.printMsg("Joueur " + game.getCurrentPlayerName() + " : à décider de jouer en " + game.getOwner());
    }

    //instancie les joueurs
    private void createPlayers(int botNumber) throws Exception {
        switch (botNumber) {
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
            default:
                throw new Exception("choix de joueurs invalide");
        }
    }


    // Demande le nombre de bot
    private int askBotNumber() throws Exception {

        return interactionUtilisateur.getNumber(view.getArtificialPlayerChoice());
    }


    // Demande la difficultée pour le joueur artificiel
    private int askBotDifficulty(String Symbol) {
        do {
            try {
                int g = interactionUtilisateur.getNumber(view.createDifficultyMessage(Symbol));
                if (g == 1 || g == 2 || g == 3 || g == 4) {
                    return g;
                } else throw new Exception("choix de la difficultée invalide");
            } catch (Exception e) {
                view.printExeption(e);
            }
        } while (true);

    }

    // Demande les coordonnées à jouer
    private void askCoordinates() {
        do {
            try {
                game.Play();
                break;
            } catch (Exception e) {
                view.printExeption(e);
            }
        } while (true);
    }

    // instancie le jeu
    private void createGame(){
        switch (currentGame) {
            case TICTACTOE:
                game = new TicTacToe();
                break;
           case GOMOKU:
                game = new Gomoku();
               break;
            case CONNECT4:
                game = new ConnectFour();
                break;
            case null:
                break;
            default:
                view.printMsg("Choix invalide");
                setCurrentState(CurrentState.GAMESELECTION);
        }
        interactionUtilisateur.printMsg("Bienvenue dans le " + game.getGameName() + "\n");

        game.createClearBoard();
        setCurrentState(CurrentState.PLAYERSELECTION);


    }

    private void setCurrentGame(int i) throws Exception {
        switch (i){
            case 0:currentGame = null;break;
            case 1:currentGame = BoardGame.GameName.TICTACTOE;break;
            case 2:currentGame = BoardGame.GameName.GOMOKU;break;
            case 3:currentGame = BoardGame.GameName.CONNECT4;break;
            default: throw new Exception("choix de jeux invalide");
        }
    }

    private void setCurrentState(CurrentState currentState) {
        this.currentState = currentState;
    }
}


