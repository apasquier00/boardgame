package model;

import controller.InteractionUtilisateur;
import model.Games.ConnectFour;
import model.Games.Gomoku;
import model.Games.TicTacToe;

public class MainMenu {

    public void Start(){
        InteractionUtilisateur interactionUtilisateur = new InteractionUtilisateur();
        TicTacToe ticTacToe = new TicTacToe();
        Gomoku gomoku = new Gomoku();
        ConnectFour connectFour = new ConnectFour();
        do {
            int i = interactionUtilisateur.getNumber("""
                    A quel jeu voulez vous jouer ?
                    0 : Tic Tac Toe
                    1 : Gomoku
                    2 : Connect Four
                    3 : Quitter""");
            switch (i){
                case 0: ticTacToe.play(); break;
                case 1: gomoku.play(); break;
                case 2: connectFour.play(); break;
                case 3: System.exit(0); break;

            }
        }while (true);

    }

}



