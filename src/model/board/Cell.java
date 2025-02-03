package model.board;


public class Cell {
    public enum CellState {
        EMPTY,
        X,
        O
    }

    private String representation;

    private CellState state;


    public Cell() {
        this.representation = "|     ";
        this.state = CellState.EMPTY;
    }

    public String setRepresentation(String gameName) {
        switch (gameName) {
            case "TICTACTOE":
                switch (state) {
                    case EMPTY:
                        return "|        ";
                    case X:
                        return "| ❌ ";
                    case O:
                        return "| ⭕ ";
                }
                break;
            case "GOMOKU":
                switch (state) {
                    case EMPTY:
                        return "|        ";
                    case X:
                        return "| ⚪\uFE0F ";
                    case O:
                        return "| ⚫ ";
                }
                break;
            case "CONNECT4":
                switch (state) {
                    case EMPTY:
                        return "|        ";
                    case X:
                        return "| \uD83C\uDF15 ";
                    case O:
                        return "| \uD83D\uDD34 ";
                }
                break;
        }
        return "|     ";
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public String getRepresentation(String gameName) {
        representation = setRepresentation(gameName);
        return representation;
    }

}
