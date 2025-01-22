package model;

public class ConnectFour extends BoardGame{


    public ConnectFour() {
        // MODIFIER LA SIZE POUR QU'ELLE CONTIENNE 2 VALEURES
        size = 6;
        victorySize = 4;
        this.gameName = GameName.connect4;
    }
    @Override
    void setOwner(int ligne, int colonne, Player player) {
        int i =0;
        for(i = 0; i < size; i++){
            if(Board[i][colonne].getState() != Cell.cellstate.EMPTY){
                Board[i-1][colonne].setState(player.getSymbol());
                return;
            }
        }
        Board[i-1][colonne].setState(player.getSymbol());
    }

}
