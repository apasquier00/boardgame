public class TestVictoire {



    public String victoryMessage;

    public String getVictoryMessage() {
        return victoryMessage;
    }


    boolean isOver(Cell[][] board, int boardSize){
        int colO=0;
        int colX=0;
        int lineO=0;
        int lineX=0;
        int diagO1=0;
        int diagX1=0;
        int diagO2=0;
        int diagX2=0;
        int empty=0;

        // test de ligne identique
        for (int i = 0; i < boardSize; i++) {

            colO=0;
            colX=0;
            lineO=0;
            lineX=0;

            for (int j = 0; j < boardSize; j++) {
                //calcul des O ou des X dans chaque ligne
                switch (board[i][j].state) {
                    case X:
                        lineX++;
                        break;
                    case O:
                        lineO++;
                        break;
                }
                //meme calcul pour chaque colonne
                switch (board[j][i].state) {
                    case X:
                        colX++;
                        break;
                    case O:
                        colO++;
                        break;
                }
                //condition de victoire par ligne ou colonne
                if (lineX==boardSize){
                    victoryMessage = "victoire du joueur X avec une ligne";
                    return true;
                }else if (lineO==boardSize){
                    victoryMessage = "victoire du joueur O avec une ligne";
                    return true;
                } else if (colX==boardSize) {
                    victoryMessage = "victoire du joueur X avec une colonne";
                    return true;
                }else if (colO==boardSize) {
                    victoryMessage = "victoire du joueur O avec une colonne";
                    return true;
                }

                //calcul du nombre de cases vides
                switch (board[i][j].state){
                    case EMPTY : empty++;
                        break;
                }

                //calcul des diagonales
                if (i == j){
                    switch (board[i][j].state){
                        case X : diagX1++;
                            break;
                        case O : diagO1++;
                            break;
                    }
                }
                if ((i + j) == boardSize-1){
                    switch (board[i][j].state){
                        case X : diagX2++;
                            break;
                        case O : diagO2++;
                            break;
                    }
                }
                //condition de victoire par diagonale
                if ((diagX1==boardSize)|| (diagX2==boardSize)){
                    this.victoryMessage = "victoire du joueur X avec une diagonale";
                    return true;
                } else if ((diagO2==boardSize) || (diagO1==boardSize )) {
                    this.victoryMessage = "victoire du joueur O avec une diagonale";

                }

            }
        }
        //condition de match nul

        if (empty == 0){
            this.victoryMessage = "Match nul";

            return true;
        }
        return false;

    }}
