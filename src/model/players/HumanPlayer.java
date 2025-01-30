package model.players;

import model.board.Cell;
import view.InteractionUtilisateur;

import java.util.List;

public class HumanPlayer extends Player {
    InteractionUtilisateur utilisateur;

    public HumanPlayer(Cell.cellstate symbolP) {
        super(symbolP);
        this.isBot = false;
        this.difficultyInt = 0;
        this.utilisateur = new InteractionUtilisateur();

    }

    @Override
    public List<Integer> choosePlayCoordinates(Cell[][] board, boolean isConnect4, List<Integer> coordinates){
        try {
            return utilisateur.getCoordinates(isConnect4);
        } catch (Exception _) {

        }
        return coordinates;
    }
}
