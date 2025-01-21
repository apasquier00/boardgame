import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class InteractionUtilisateur
{
    View view;
    int botsNumber;
    InteractionUtilisateur(View view){
        this.botsNumber = 0;
        this.view = view;
    }



    public int getBotsNumber() {
        do{
            try{
                view.askBotsNumber();

                Scanner scanner = new Scanner(System.in);
                botsNumber = scanner.nextInt();
                return botsNumber;

            }catch (Exception e){
                view.printExeption(e);
            }

        }while (true);

    }

    public int getBotsDifficulty() {
        do{
            try{
                view.printMsg("Merci de choisir votre difficultée : 0 = EASY; 1 = MEDIUM; 2 = HARD");

                Scanner scanner = new Scanner(System.in);
                botsNumber = scanner.nextInt();
                return botsNumber;

            }catch (Exception e){
                view.printExeption(e);
            }

        }while (true);

    }


    public List<Integer> getCoordinates() {
        List<Integer> coordinates = new ArrayList<>();

        do {
            try{
                view.printMsg("Veuillez entrer la ligne ou vous souhaitez jouer");
                Scanner s = new Scanner(System.in);
                coordinates.add(s.nextInt());
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
