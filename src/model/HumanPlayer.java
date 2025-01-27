package model;

import view.InteractionUtilisateur;

import java.util.ArrayList;
import java.util.List;

public class HumanPlayer extends Player {
    private InteractionUtilisateur interactionUtilisateur;
    private BoardGame.GameName gamename;
    HumanPlayer(Cell.cellstate symbolP, InteractionUtilisateur interactionUtilisateur, BoardGame.GameName gamename) {
        super(symbolP);
        this.interactionUtilisateur = interactionUtilisateur;
        this.gamename = gamename;
    }

    @Override
    List<Integer> play(Cell[][] board){
        List<Integer> c = new ArrayList<>();
        c = interactionUtilisateur.getCoordinates(gamename);
        return c;    }
}
