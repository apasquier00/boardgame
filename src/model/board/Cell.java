package model.board;



public class Cell {
    public enum cellstate {
        EMPTY,
        X,
        O
    }

    private String representation;

    private cellstate state;


    public Cell(){
        this.representation =  "|     ";
        this.state = cellstate.EMPTY;
    }

    public String setRepresentation(String gameName) {
        switch (gameName){
            case "TICTACTOE":
                switch(state) {
                    case EMPTY:
                        return "|        ";
                    case X:
                        return "| ❌ ";
                    case O:
                        return "| ⭕ ";
                } break;
            case "GOMOKU":
                switch(state) {
                    case EMPTY:
                        return "|        ";
                    case X:
                        return "| ⚪\uFE0F ";
                    case O:
                        return "| ⚫ ";
                } break;
            case "CONNECT4":
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

    public String getRepresentation(String gameName){
        representation = setRepresentation(gameName);
        return representation;
        }

}
