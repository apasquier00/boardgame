import java.util.ArrayList;
import java.util.List;

public class HumanPlayer extends Player {
    InteractionUtilisateur interactionUtilisateur;

    HumanPlayer(Symbol symbolP, InteractionUtilisateur interactionUtilisateur) {
        super(symbolP);
        this.interactionUtilisateur = interactionUtilisateur;

    }

    @Override
    List<Integer> play(Cell[][] board){
        List<Integer> c = new ArrayList<>();
        c = interactionUtilisateur.getCoordinates();
        return c;    }
}
