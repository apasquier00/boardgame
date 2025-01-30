package model.players;

import model.board.Cell;

import java.util.List;

public class HumanPlayer extends Player {
    public HumanPlayer(Cell.cellstate symbolP) {
        super(symbolP);
        this.isBot = false;
        this.difficultyInt = 0;

    }

    @Override
    public List<Integer> choosePlayCoordinates(Cell[][] board, boolean isConnect4, List<Integer> coordinates){
        return coordinates;
    }
}
