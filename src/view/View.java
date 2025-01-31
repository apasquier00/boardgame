package view;


public class View {
    private static final String console_background = "\u001B[40m";




    public View(){
    }
//affichage du tableau
    public void gridDisplay(String[][] board, String Board_background) {


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
        line.append("-----".repeat(board[0].length));
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
                System.out.print(Board_background + board[i][j]);
            }
            //fin de ligne
            System.out.print("|" + console_background);
        }
        System.out.print("\n     " + line + " \n  ");
    }



    public void unvalidCoordinate(String coordinate){
        System.out.println("Coordonnées non valides : " + coordinate);

    }

    public void endMsg(String victoryMessage){
        System.out.println("\n           la partie est finie");
        System.out.println("\n           " + victoryMessage + "\n");
    }



    public void printMsg(String msg){
        System.out.println(msg);
    }


    public void printExeption(Exception error){
        System.err.println("erreur : " + error);
    }

    public void printState(String state){
        System.out.println("//////////////////////////////////" + state + "//////////////////////////////////");
    }

    public String createDifficultyMessage(String symbol){
                return """
            Veuillez indiquer la difficultée du joueur\s""" + symbol + """
            
            1 = EASY 😃
            2 = MEDIUM 🙁
            3 = HARD 😡
            4 = EXTREME ☠️""";
    }

    public String getGameChoiceMsg() {
        return """
                        A quel jeu voulez vous jouer ?
                        0 : Quitter
                        1 : Tic Tac Toe
                        2 : Gomoku
                        3 : Connect Four
                        """;
    }



    public String getArtificialPlayerChoice() {
        return """
                Combien de joueurs artificiel voulez vous ?\
                
                Joueurs artificiel Max : 2\s""";
    }

    public String createReplayMsg(String gameName){
        return "Voulez vous refaire une partie de " + gameName + " ?\n" +
                "1 : OUI\n" +
                "2 : NON";
    }

}
