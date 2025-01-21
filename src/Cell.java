class Cell {
    String representation;
    enum cellstate {
        EMPTY,
        X,
        O
    }


    public cellstate state;



    Cell(){
        this.representation =  "|     ";
        this.state = cellstate.EMPTY;
    }

    public cellstate getState() {
        return state;
    }

    public void setState(cellstate state) {
        this.state = state;
    }

    String getRepresentation() {
        switch(state) {
            case EMPTY:
                return "|      ";
                case X:
                return "|  ❌  ";
                case O:
                return "|  ⭕  ";
        }
        return "|     ";
    }
}
