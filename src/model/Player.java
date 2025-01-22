package model;

import java.util.List;


public abstract class Player {


    public enum Symbol {
        O,
        X
    }

    boolean isBot;
    public Symbol symbol;

    Player(Symbol symbolP) {
        this.symbol = symbolP;
    }

    public String getRepresentation(BoardGame.GameName gameName) {
        return switch (gameName) {
            case BoardGame.GameName.tictactoe -> switch (symbol) {
                case X -> "X";
                case O -> "O";
            };
            case BoardGame.GameName.gomoku -> switch (symbol) {
                case X -> "blanc";
                case O -> "jaune";
            };
            case BoardGame.GameName.connect4 -> switch (symbol) {
                case X -> "jaune";
                case O -> "rouge";
            };
        };
    }

    abstract List<Integer> play(Cell[][] board);

    public Cell.cellstate getSymbol() {
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
