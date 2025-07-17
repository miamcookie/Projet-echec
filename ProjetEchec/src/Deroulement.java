import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


public class Deroulement {


    public static void lancerPartie() { // Programme au début de la partie, ce programme la,nce la partie et mets en place le plateau
        Scanner sc = new Scanner(System.in);
        int colonne = 8;
        int ligne = 8;


        String[][] plateau = new String[ligne][colonne];
        MiseEnPlaceJeu.remiseAZeroTableau(plateau);
        MiseEnPlaceJeu.créerPlateau(plateau);
        MiseEnPlaceJeu.miseEnPlacePlateau(plateau);


        // ZONE DE TEST POUR RAJOUTER DES PIECES


        // ---------------------------------------
        String pseudoJ1 = "";
        String pseudoJ2 = "a ";
        do {
            System.out.println("Notez que les pseudo doivent être différents");
            System.out.println("Joueur n°1, veuillez choisir votre pseudo:");
            pseudoJ1 = sc.nextLine();


            System.out.println("Joueur n°2, veuillez choisir votre pseudo:");
            pseudoJ2 = sc.nextLine();
        } while (pseudoJ1.equals(pseudoJ2));
        // On va maintenant demander le type d'affichage
        String typeAffichage ="";
        do {
            System.out.println("""
               Quel Type d'affichage voulez vous, En pixel Art ou en réduit ?
               Entrez 1 pour pixel Art et 2 pour réduit
               """);
            typeAffichage = sc.nextLine();


        }while(typeAffichage.charAt(0) != '1' && typeAffichage.charAt(0) != '2');


        Boolean affichage = false;
        if (typeAffichage.charAt(0) == '1'){
            affichage = !affichage;
        }
        System.out.println(typeAffichage.charAt(0));
        System.out.println(affichage);




        String[] listPseudoJoueur = new String[2];
        // On tire maintenant au sort ppour savoir quel joueur jouera les blancs
        if (ToursJoueurs.blancOuNoir()){
            System.out.println(pseudoJ1 + " Tu joueras maintenant les blancs");
            System.out.println(pseudoJ2 + " Tu joueras maintenant les noirs");
            listPseudoJoueur = new String[]{pseudoJ1, pseudoJ2};


        }else {
            System.out.println(pseudoJ2 + " Tu joueras maintenant les blancs");
            System.out.println(pseudoJ1 + " Tu joueras maintenant les noirs");
            listPseudoJoueur = new String[]{pseudoJ2,pseudoJ1};
        }
        int nbrTours = 1;
        ArrayList<String> listePieceBlanche = MiseEnPlaceJeu.CreationListeBlanc();
        ArrayList<String> listePieceNoir = MiseEnPlaceJeu.CreationListeNoir();


        while(nbrTours>0) {
            nbrTours = deroulePartieJCJ(plateau,nbrTours,listPseudoJoueur,affichage,listePieceBlanche,listePieceNoir);
        }

        if (nbrTours == -1){
            System.out.println("Felicitation "+ pseudoJ1 + " Tu remportes la partie");
        } else if (nbrTours == -2) {
            System.out.println("Felicitation "+ pseudoJ2 + " Tu remportes la partie");
        }else {
            System.out.println( " Egalité entre "+ pseudoJ1 + " et "+ pseudoJ2);
        }


    }




    public static int deroulePartieJCJ(String[][] plateau, int nbrTours, String[] listPseudoEtCouleur, boolean typeAffichage, ArrayList<String> pieceBlancheListe, ArrayList<String> pieceNoirsListe) { // Renvoit 0 tant que la partie n'est pas finie, 1 si J1 à gagné,2 si J2 à gagné, -1 pour l'égalité


        Affichage.printAffichage(plateau, typeAffichage); // On affiche le plateau
        //System.out.println("ICI 1");
        boolean quelTourAQuelJoueur = false;  // devient True pour joueur blanc
        int verificateur = 0;
        String roiNoir = "";
        String roiBlanc = "";
        int parcours = 0;
        while (roiBlanc.isEmpty()) {
            if (pieceBlancheListe.get(parcours).charAt(0) == 'k') {
                roiBlanc = pieceBlancheListe.get(parcours).substring(1, 3);
            }
            parcours++;
        }
        int parcours2 = 0;
        while (roiNoir.isEmpty()) {
            if (pieceNoirsListe.get(parcours2).charAt(0) == 'K') {
                roiNoir = pieceNoirsListe.get(parcours2).substring(1, 3);
            }
            parcours2++;
        }




        if (nbrTours % 2 == 0) {
            System.out.println(listPseudoEtCouleur[1] + " En attente de votre coup:");
            quelTourAQuelJoueur = false;
        } else {
            System.out.println(listPseudoEtCouleur[0] + " En attente de votre coup:");
            quelTourAQuelJoueur = true;
        }
        Scanner scan = new Scanner(System.in);
        //System.out.println("ICI 3");
        String coupJoue = "";
        int erreurDansDernierCoup = 0;
        do {
            if (erreurDansDernierCoup >=1 ){
                System.out.println("Il y a une erreur dans votre dernier coup, réessayez");
                System.out.println("Verifier que votre roi n'est pas en danger OU que votre coup mette le roi en danger");
            }
            coupJoue = scan.nextLine();


            erreurDansDernierCoup ++;


        } while ((!Mouvement.coupVerificateurFinal(plateau, coupJoue, listPseudoEtCouleur, nbrTours)) || ( Mouvement.roiEchec(plateau, pieceNoirsListe, roiBlanc, listPseudoEtCouleur, nbrTours) || Mouvement.roiEchec(plateau, pieceBlancheListe, roiNoir, listPseudoEtCouleur, nbrTours) )||( Mouvement.dangerRoiAllie(plateau,coupJoue,pieceBlancheListe,roiNoir,listPseudoEtCouleur,nbrTours) || Mouvement.dangerRoiAllie(plateau,coupJoue,pieceNoirsListe,roiBlanc, listPseudoEtCouleur, nbrTours))); // On vérifie si le coup est valide
        erreurDansDernierCoup = 0;



        if (quelTourAQuelJoueur) { // Joueur Blanc
            Mouvement.deplacerPiece(plateau, coupJoue, pieceNoirsListe, pieceBlancheListe);
        } else { // Joueur Noir
            Mouvement.deplacerPiece(plateau, coupJoue, pieceBlancheListe, pieceNoirsListe);
        }


        if(coupJoue.charAt(0) == 'p' && coupJoue.charAt(4) == '8'){
            Piece.promotion(plateau,coupJoue,pieceBlancheListe);
        }
        if(coupJoue.charAt(0) == 'P' && coupJoue.charAt(4) == '1'){
            Piece.promotion(plateau,coupJoue,pieceNoirsListe);
        }

        ArrayList<String> coupPossibleNoir = Mouvement.coupPossible(plateau,pieceNoirsListe,listPseudoEtCouleur,2,pieceBlancheListe,roiNoir);
        //System.out.println(coupPossibleNoir.size());
        if(Mouvement.echecETmat(coupPossibleNoir,plateau,pieceBlancheListe,roiNoir,listPseudoEtCouleur,nbrTours)){
            //System.out.println("return connard");
            Affichage.printAffichage(plateau, typeAffichage);
            return -1;
        }
        ArrayList<String> coupPossibleBlanc = Mouvement.coupPossible(plateau,pieceBlancheListe,listPseudoEtCouleur,1,pieceNoirsListe,roiBlanc);
        //System.out.println(coupPossibleBlanc.size());
        if(Mouvement.echecETmat(coupPossibleBlanc,plateau,pieceNoirsListe,roiBlanc,listPseudoEtCouleur,nbrTours)){
            //System.out.println("return pls");
            Affichage.printAffichage(plateau, typeAffichage);
            return -2;
        }
        if(Mouvement.egalité(plateau,coupPossibleBlanc,coupPossibleNoir,pieceNoirsListe,pieceBlancheListe,listPseudoEtCouleur,nbrTours)){
            Affichage.printAffichage(plateau, typeAffichage);
            return -3;
        }


        // ICI on doit vérifier que les conditions de fin de parties ne sont pas vérifiées SI elles sont vérifiées, on peut renvoyer un nombre de tours impossible du type -1 -2 -3 respectivement pour VIctoireJ1 VictoireJ2 Egalite
        return nbrTours + 1;


    }
}

