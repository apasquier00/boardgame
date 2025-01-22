package view;

import model.Cell;
import model.Player;

public class View {
    public View(){
    }
//affichage du tableau
    public void gridDisplay(Cell[][] board) {
        //la longueure de la ligne en fonction de size
        StringBuilder line = new StringBuilder();

        StringBuilder topline = new StringBuilder();
        topline.append("      ");
        // affichage de la première ligne
        for (int i = 0; i < board[0].length ; i++) {
            if (i> 9){
                topline.append("   ").append(i).append("  ");
            }else {
                topline.append("      ").append(i).append("  ");
            }
        }
        System.out.print("\n " + line + " \n  ");
        line.append(" ");
        line.append("-----".repeat(Math.max(0, board[0].length )));
        if (board[0].length > 10 ) {
            line.append("-".repeat((board[0].length-10)/2));
        }
        //affichage d'une ligne de case
        System.out.print(topline);

        for (int i = 0; i < board.length; i++) {

            System.out.print("\n      " + line + " \n  ");
            if (i > 9){
                System.out.print(i + "  ");
            }else {
                System.out.print(i + "   ");
            }
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j].getRepresentation());
            }
            //fin de ligne
            System.out.print("|");
        }
        System.out.print("\n      " + line + " \n  ");
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
