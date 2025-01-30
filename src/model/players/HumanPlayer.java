package model.players;

import view.InteractionUtilisateur;
import model.board.Cell;

import java.util.ArrayList;
import java.util.List;

public class HumanPlayer extends Player {
    public HumanPlayer(Cell.cellstate symbolP) {
        super(symbolP);
        this.isBot = false;

    }

    @Override
    public List<Integer> play(Cell[][] board, boolean isConnect4, List<Integer> coordinates){
        return coordinates;    }
}
