package model.players;

import model.board.Cell;

import java.util.List;


public abstract class Player {


    protected int difficultyInt;


    protected boolean isBot;
    private final Cell.cellstate symbol;
    Player(Cell.cellstate symbol) {
        this.symbol = symbol;
    }

    public String getName(String gameName) {
        return switch (gameName) {
            case "TICTACTOE" -> switch (symbol) {
                case EMPTY -> null;
                case X -> "X";
                case O -> "O";
            };
            case "GOMOKU" -> switch (symbol) {
                case EMPTY -> null;
                case X -> "blanc";
                case O -> "noir";
            };
            case "CONNECT4" -> switch (symbol) {
                case EMPTY -> null;
                case X -> "jaune";
                case O -> "rouge";
            };
            default -> throw new IllegalStateException("Unexpected value: " + gameName);
        };
    }

    public abstract List<Integer> choosePlayCoordinates(Cell[][] board, boolean isConnect4, List<Integer> coordinates);

    public Cell.cellstate getSymbol() {
        //retourne le symbol du joueur
    return symbol;
    }

    public boolean isBot() {
        return isBot;
    }
    public int getDifficultyInt() {
        return difficultyInt;
    }
    }
