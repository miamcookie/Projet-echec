import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        int colonne = 8;
        int ligne = 8;
        String testString = "re4e8"; // Test qui est censé marcher pour une tour

        String[][] plateau = new String[ligne][colonne];
/*
        MiseEnPlaceJeu.remiseAZeroTableau(plateau); // INDISPENSABLE AVANT CHAQUES TESTS
        MiseEnPlaceJeu.créerPlateau(plateau);
        MiseEnPlaceJeu.miseEnPlacePlateau(plateau);
        // Simple classe pour tester nos fonctions au fur et à mesure
        //  Programmes.créerPlateau(plateau);

        System.out.println("");
        Affichage.printPlateau(plateau);
        System.out.println("");

        Affichage.printAffichage(plateau, false);
        //Menu.menuChoix();
/*
        ArrayList<String> testblanc = new ArrayList<>();
        testblanc = MiseEnPlaceJeu.CreationListeBlanc();
        ArrayList<String> testnoir = new ArrayList<>();
        testnoir = MiseEnPlaceJeu.CreationListeNoir();
/*
        System.out.println(Piece.ennemie(plateau, "bc1c8"));
        System.out.println(Piece.ennemie(plateau, "bc1h1"));
        System.out.println(Piece.ennemie(plateau, "bc1c1"));
        System.out.println(Piece.ennemie(plateau, "bc8c1"));
        System.out.println(Piece.ennemie(plateau, "bh1c1"));

 */


        MiseEnPlaceJeu.remiseAZeroTableau(plateau); // INDISPENSABLE AVANT CHAQUES TESTS
        MiseEnPlaceJeu.créerPlateau(plateau);
        MiseEnPlaceJeu.miseEnPlacePlateau(plateau); // INDISPENSABLE AVANT CHAUES TESTS
        Affichage.printPlateau(plateau);


        Menu.menuChoix();





/*

        MiseEnPlaceJeu.ajouterPièce(plateau,"a7",'p');
        testblanc.add("pa7");
        Piece.promotion(plateau,"pa7a8",testblanc);
        for(String i: testblanc){
            System.out.println(i);
        }*/

        //MiseEnPlaceJeu.ajouterPièce(plateau, "a1",'p');
        //MiseEnPlaceJeu.ajouterPièce(plateau,"a3", 'p');
        //Affichage.printPlateau(plateau);
        //
        //System.out.println(Piece.coupBishop("Bb1b7", plateau));
        //System.out.println(Piece.ennemie("ba1h8",plateau));
/*
        for (String in:testblanc) {
            System.out.println(in);
        }
        for (String in:testnoir){
            System.out.println(in);
        }
        System.out.println();
        Mouvement.bougerpieceliste(testnoir,"Pa7a6");

        for (String in:testnoir){
            System.out.println(in);
        }






    */


    }

}