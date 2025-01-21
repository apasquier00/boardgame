import java.util.ArrayList;
import java.util.List;


abstract class Player {


    enum Symbol {
        O,
        X
    }

    boolean isBot;
    public Symbol symbol;

    Player(Symbol symbolP) {
        this.symbol = symbolP;
    }

    abstract List<Integer> play(Cell[][] board);

    Cell.cellstate getSymbol() {
        //retourne le symbol du joueur sous forme de Cell.cellstate
        switch (symbol) {
            case O:
                return Cell.cellstate.O;
            case X:
                return Cell.cellstate.X;
            default:
                return Cell.cellstate.EMPTY;
        }
    }



    }
