package model.games;


import model.board.Cell;
import model.players.Player;

public class ConnectFour extends BoardGame{


    public ConnectFour() {
        super(6, 4, GameName.CONNECT4);
    }
    @Override
    protected void setOwner(int ligne, int colonne, Player player) {
        int i =0;
        for(i = 0; i < size; i++){
            if(getBoard()[i][colonne].getState() != Cell.CellState.EMPTY){
                getBoard()[i-1][colonne].setState(player.getSymbol());
                return;
            }
        }
        getBoard()[i-1][colonne].setState(player.getSymbol());
    }


}
