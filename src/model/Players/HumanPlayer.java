package model.Players;

import controller.InteractionUtilisateur;
import model.Board.Cell;

import java.util.ArrayList;
import java.util.List;

public class HumanPlayer extends Player {
    private InteractionUtilisateur interactionUtilisateur;
    public HumanPlayer(Cell.cellstate symbolP, InteractionUtilisateur interactionUtilisateur) {
        super(symbolP);
        this.interactionUtilisateur = interactionUtilisateur;
    }

    @Override
    public List<Integer> play(Cell[][] board, boolean isConnect4){
        List<Integer> c = new ArrayList<>();
        c = interactionUtilisateur.getCoordinates(isConnect4);
        return c;    }
}
