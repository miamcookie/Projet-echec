import java.util.*;


public class ToursJoueurs {




    // Ici on va peut-être retrouver ce qui va s'occuper de cahques joueurs et de leurs mouvements


    public static void main(String[] args) {




    }


    // Tire au sort pour savoir qui est blanc ou Noir.
    public static boolean blancOuNoir() {
        boolean blancOuNoir;


        if (Math.random() < 0.5) {
            blancOuNoir = true;
        } else {
            blancOuNoir = false;
        }
        return blancOuNoir;
    }


    // prend l'indice en int du tour et renvoit le tour actuel du joueur
    public static boolean tourDuJoueurCouleur(int tourPrecedent) {


        if (tourPrecedent % 2 == 0) {
            return false;
        } else
            return true;
    }




}

