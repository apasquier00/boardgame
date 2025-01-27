package model;

public class ConnectFour extends BoardGame{


    public ConnectFour() {
        super(6, 4, GameName.CONNECT4);
    }
    @Override
    void setOwner(int ligne, int colonne, Player player) {
        int i =0;
        for(i = 0; i < size; i++){
            if(getBoard()[i][colonne].getState() != Cell.cellstate.EMPTY){
                getBoard()[i-1][colonne].setState(player.getSymbol());
                return;
            }
        }
        getBoard()[i-1][colonne].setState(player.getSymbol());
    }
    @Override
    Cell.cellstate getOwner(int ligne, int colonne) {
        int i =0;
        for(i = 0; i < size; i++){
            if(getBoard()[i][colonne].getState() != Cell.cellstate.EMPTY){
                return getBoard()[i-1][colonne].getState();
            }
        }
        return getBoard()[i-1][colonne].getState();
    }

}
