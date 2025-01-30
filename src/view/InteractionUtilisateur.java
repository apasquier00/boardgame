package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class InteractionUtilisateur
{
    private View view;
    private int botsNumber;
    public InteractionUtilisateur(){
        this.botsNumber = 0;
        this.view = new View();
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

    public boolean getBool(String s) {
        do{
            try{
                view.printMsg(s);
                Scanner scanner = new Scanner(System.in);
                int i = scanner.nextInt();
                switch (i){
                    case 1:return false;
                    case 2:return true;
                }
            }catch (Exception e){

                view.printExeption(e);
            }

        }while (true);

    }


    public void callUnvalidCoordinate(String s){
        view.unvalidCoordinate(s);
    }

    public void printMsg(String s){
        view.printMsg(s);
    }

//    public void endMsg(String s){
//        view.endMsg(s);
//    }


    public void gridDisplay(String[][] Board, String boardBackground){
        view.gridDisplay(Board, boardBackground);
    }

    public List<Integer> getCoordinates(boolean isConnect4) {
        List<Integer> coordinates = new ArrayList<>();

        do {
            try{
                Scanner s = new Scanner(System.in);
                if (isConnect4){
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
