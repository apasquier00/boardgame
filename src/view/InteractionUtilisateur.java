package view;

import model.BoardGame;
import model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class InteractionUtilisateur
{
    private View view;
    private int botsNumber;
    public InteractionUtilisateur(View view){
        this.botsNumber = 0;
        this.view = view;
    }



    public int getNumber(String s) {
        do{
            try{
                view.printMsg(s);

                Scanner scanner = new Scanner(System.in);
                botsNumber = scanner.nextInt();
                return botsNumber;

            }catch (Exception e){

                view.printExeption(e);
            }

        }while (true);

    }


    public List<Integer> getCoordinates(BoardGame.GameName gamename) {
        List<Integer> coordinates = new ArrayList<>();

        do {
            try{
                Scanner s = new Scanner(System.in);
                if (gamename == BoardGame.GameName.CONNECT4){
                    coordinates.add(0);
                }else {
                    view.printMsg("Veuillez entrer la ligne ou vous souhaitez jouer");
                    s = new Scanner(System.in);
                    coordinates.add(s.nextInt());
                }
                view.printMsg("Veuillez entrer la colonne ou vous souhaitez jouer");
                s = new Scanner(System.in);
                coordinates.add(s.nextInt());
                return coordinates;

            } catch (Exception e){
                view.printExeption(e);
            }

        } while (true);

        // coordinates contient le numéro de la ligne en 1ère valeur et la colonne en 2ème valeure

    }
}
