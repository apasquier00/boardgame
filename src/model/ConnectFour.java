package model;

public class ConnectFour extends BoardGame{


    public ConnectFour() {
        super(6, 4, GameName.CONNECT4);
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
    @Override
    Cell.cellstate getOwner(int ligne, int colonne) {
        int i =0;
        for(i = 0; i < size; i++){
            if(Board[i][colonne].getState() != Cell.cellstate.EMPTY){
                return Board[i-1][colonne].getState();
            }
        }
        return Board[i-1][colonne].getState();
    }

}
