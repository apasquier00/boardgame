package model;

import java.util.List;


public abstract class Player {



    private boolean isBot;
    private Cell.cellstate symbol;

    Player(Cell.cellstate symbol) {
        this.symbol = symbol;
    }

    public String getName(BoardGame.GameName gameName) {
        return switch (gameName) {
            case BoardGame.GameName.TICTACTOE -> switch (symbol) {
                case EMPTY -> null;
                case X -> "X";
                case O -> "O";
            };
            case BoardGame.GameName.GOMOKU -> switch (symbol) {
                case EMPTY -> null;
                case X -> "blanc";
                case O -> "noir";
            };
            case BoardGame.GameName.CONNECT4 -> switch (symbol) {
                case EMPTY -> null;
                case X -> "jaune";
                case O -> "rouge";
            };
        };
    }

    abstract List<Integer> play(Cell[][] board);

    public Cell.cellstate getSymbol() {
        //retourne le symbol du joueur
    return symbol;
    }



    }
