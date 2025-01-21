public class View {
    View(){
    }
//affichage du tableau
    void gridDisplay(int size, Cell[][] cell) {
        //la longueure de la ligne en fonction de size
        StringBuilder line = new StringBuilder();
        line.append("--------".repeat(Math.max(0, size)));
        //affichage d'une ligne de case
        for (int i = 0; i < size; i++) {
            System.out.print("\n " + line + " \n  ");
            for (int j = 0; j < size; j++) {
                System.out.print(cell[i][j].getRepresentation());
            }
            //fin de ligne
            System.out.print("|");
        }
        System.out.print("\n " + line + " \n  ");
    }

    void askBotsNumber(){
        System.out.println("""
                Combien de joueurs artificiel voulez vous ?\
                
                Joueurs artificiel Max : 2\
                
                Entrer 3 pour quitter le programme\s"""
        );
    }


    void playerTurn(Player p){
        System.out.println("Joueur : " + p.getSymbol() + " c'est à vous");

    }

    void unvalidCoordinate(){
        System.out.println("Coordonnées non valides");

    }

    void endMsg(String victoryMessage){
        System.out.println("\n                    la partie est finie");
        System.out.println("\n           " + victoryMessage + "\n");
    }

    void msgError(Exception e){
        System.err.println(e.getMessage());
    }

    void printMsg(String msg){
        System.out.println(msg);
    }
    void printExeption(Exception error){
        System.out.println("ERROR : " + error);
    }

}
