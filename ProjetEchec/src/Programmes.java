import java.util.ArrayList;
import java.util.Scanner;


public class Programmes {
    public static int valAbs(int val) {


        if (val >= 0) {
            return val;
        } else return -val;
    }


    public static void bougerpieceliste(ArrayList<String> liste, String coup) {
        String debut = coup.substring(0, 3);
        String fin = coup.charAt(0) + coup.substring(3, 5);
        liste.remove(fin);
        liste.add(fin);
    }
    public static String[][] copieplateau(String[][] plateau){
        String[][] copie = new String[plateau.length][plateau.length];
        for(int i = 0; i < plateau.length;i++){
            for(int j = 0; j < plateau.length; j++){
                copie[i][j] = plateau[i][j];
            }
        }
        return copie;
    }




}





