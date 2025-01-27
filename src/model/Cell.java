package model;

public class Cell {
    public enum cellstate {
        EMPTY,
        X,
        O
    }

    private String representation;

    private cellstate state;

BoardGame.GameName gameName;

    Cell(BoardGame.GameName gameName){
        this.gameName = gameName;
        this.representation =  "|     ";
        this.state = cellstate.EMPTY;
    }

    public String setRepresentation() {
        switch (gameName){
            case BoardGame.GameName.TICTACTOE:
                switch(state) {
                    case EMPTY:
                        return "|        ";
                    case X:
                        return "| ❌ ";
                    case O:
                        return "| ⭕ ";
                } break;
            case BoardGame.GameName.GOMOKU:
                switch(state) {
                    case EMPTY:
                        return "|        ";
                    case X:
                        return "| ⚪\uFE0F ";
                    case O:
                        return "| ⚫ ";
                } break;
            case BoardGame.GameName.CONNECT4:
                switch(state) {
                    case EMPTY:
                        return "|        ";
                    case X:
                        return "| \uD83C\uDF15 ";
                    case O:
                        return "| \uD83D\uDD34 ";
                } break;
    }
        return "|     ";
    }

    public cellstate getState() {
        return state;
    }

    public void setState(cellstate state) {
        this.state = state;
    }

    public String getRepresentation(){
        representation = setRepresentation();
        return representation;
        }

}
