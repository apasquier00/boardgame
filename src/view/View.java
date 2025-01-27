package view;

import model.BoardGame;
import model.Cell;
import model.Player;

public class View {
    private static String Board_background = "\u001B[47m";
    private static final String console_background = "\u001B[40m";

    public View(){
    }
//affichage du tableau
    public void gridDisplay(Cell[][] board, BoardGame.GameName gameName) {
        switch (gameName){
            case TICTACTOE: Board_background = "\u001B[100m";break;
            case CONNECT4: Board_background = "\u001B[44m";break;
            case GOMOKU: Board_background = "\u001B[101m";break;
        }

        //la longueure de la ligne en fonction de size
        StringBuilder line = new StringBuilder();

        StringBuilder topline = new StringBuilder();
        topline.append("     ");
        // affichage de la première ligne
        for (int i = 0; i < board[0].length ; i++) {
            if (i> 9){
                topline.append("   ").append(i).append("  ");
            }else {
                topline.append("      ").append(i).append("  ");
            }
        }
        System.out.print("\n " + line + " \n  ");
        line.append(Board_background).append("   ");
        line.append("-----".repeat(Math.max(0, board[0].length )));
        if (board[0].length > 10 ) {
            line.append("-".repeat((board[0].length-10)/2));
        }
        if (board.length > 3) {
            line.append(" "+console_background);

        }else {
            line.deleteCharAt(line.length()-1);
            line.deleteCharAt(line.length()-2);

            line.append("  "+console_background);
        }

        //affichage d'une ligne de case
        System.out.print(topline);

        for (int i = 0; i < board.length; i++) {

            System.out.print("\n     " + line + " \n  ");
            if (i > 9){
                System.out.print(i + " ");
            }else {
                System.out.print(i + "  ");
            }
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(Board_background + board[i][j].getRepresentation());
            }
            //fin de ligne
            System.out.print("|" + console_background);
        }
        System.out.print("\n     " + line + " \n  ");
    }




    public void playerTurn(Player p){
        System.out.println("Joueur : " + p.getSymbol() + " c'est à vous");

    }

    public void unvalidCoordinate(){
        System.out.println("Coordonnées non valides");

    }

    public void endMsg(String victoryMessage){
        System.out.println("\n                    la partie est finie");
        System.out.println("\n           " + victoryMessage + "\n");
    }

    public void msgError(Exception e){
        System.err.println(e.getMessage());
    }

    public void printMsg(String msg){
        System.out.println(msg);
    }
    void printExeption(Exception error){
        System.out.println("ERROR : " + error);
    }

}
