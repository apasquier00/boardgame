package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class InteractionUtilisateur
{
    private final View VIEW;
    private int botsNumber;
    public InteractionUtilisateur(){
        this.botsNumber = 0;
        this.VIEW = new View();
    }



    public int getNumber(String s) {
        VIEW.printMsg(s);
        Scanner scanner = new Scanner(System.in);
        botsNumber = scanner.nextInt();
        return botsNumber;


    }

    public boolean getBool(String s) {
        do{
            try{
                VIEW.printMsg(s);
                Scanner scanner = new Scanner(System.in);
                int i = scanner.nextInt();
                switch (i){
                    case 1:return false;
                    case 2:return true;
                }
            }catch (Exception e){

                VIEW.printExeption(e);
            }

        }while (true);

    }


    public void callUnvalidCoordinate(String s){
        VIEW.unvalidCoordinate(s);
    }

    public void printMsg(String s){
        VIEW.printMsg(s);
    }

//    public void endMsg(String s){
//        view.endMsg(s);
//    }


    public void gridDisplay(String[][] Board, String boardBackground){
        VIEW.gridDisplay(Board, boardBackground);
    }

    public List<Integer> getCoordinates(boolean isConnect4) throws Exception{
        List<Integer> coordinates = new ArrayList<>();

                Scanner s = new Scanner(System.in);
                if (isConnect4){
                    coordinates.add(0);
                }else {
                    VIEW.printMsg("Veuillez entrer la ligne ou vous souhaitez jouer");
                    s = new Scanner(System.in);
                    coordinates.add(s.nextInt());
                }
                VIEW.printMsg("Veuillez entrer la colonne ou vous souhaitez jouer");
                s = new Scanner(System.in);
                coordinates.add(s.nextInt());
                return coordinates;
            }


        // coordinates contient le numéro de la ligne en 1ère valeur et la colonne en 2ème valeure

    }

