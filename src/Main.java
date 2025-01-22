
import model.*;



public class Main {
    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        Gomoku gomoku = new Gomoku();
        ConnectFour connectFour = new ConnectFour();
        do {
            int i = ticTacToe.interactionUtilisateur.getNumber("A quel jeu voulez vous jouer ?\n" +
                    "0 : Tic Tac Toe\n" +
                    "1 : Gomoku\n" +
                    "2 : Connect Four\n" +
                    "3 : Quitter");
            switch (i){
                case 0: ticTacToe.play(); break;
                case 1: gomoku.play(); break;
                case 2: connectFour.play(); break;
                case 3: System.exit(0); break;

            }
        }while (true);

        }

    }
