package model;

public class Cell {
    String representation;
    public enum cellstate {
        EMPTY,
        X,
        O
    }


    public cellstate state;

BoardGame.GameName gameName;

    Cell(BoardGame.GameName gameName){
        this.gameName = gameName;
        this.representation =  "|     ";
        this.state = cellstate.EMPTY;
    }

    public cellstate getState() {
        return state;
    }

    public void setState(cellstate state) {
        this.state = state;
    }

    public String getRepresentation() {
        switch (gameName){
            case BoardGame.GameName.tictactoe:
                switch(state) {
                case EMPTY:
                    return "| ▪\uFE0F ";
                case X:
                    return "| ❌ ";
                case O:
                    return "| ⭕ ";
            } break;
            case BoardGame.GameName.gomoku:
                switch(state) {
                    case EMPTY:
                        return "| ▪\uFE0F ";
                    case X:
                        return "| ⚪\uFE0F ";
                    case O:
                        return "| \uD83C\uDF15 ";
                } break;
            case BoardGame.GameName.connect4:
                switch(state) {
                    case EMPTY:
                        return "| ▪\uFE0F ";
                    case X:
                        return "| \uD83C\uDF15 ";
                    case O:
                        return "| \uD83D\uDD34 ";
                } break;
        }

        return "|     ";
    }
}
