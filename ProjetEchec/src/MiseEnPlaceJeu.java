import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class MiseEnPlaceJeu {


    // Ici On peut retrouver toutes les fonctions qui mettent en place le plateau et le préparent en vu des parties qui s'annoncent


    // Prend un plateau et enlève tout ce qu'il y a dedans, indispensable à chaqsues créations de plateau
    public static void remiseAZeroTableau(String[][] plateau) {
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                plateau[ligne][colonne] = "";
            }
        }


    }


    public static ArrayList<String> CreationListeBlanc() {
        ArrayList<String> resultat = new ArrayList<>();
        Collections.addAll(resultat, "ra1", "cb1", "bc1", "qd1", "ke1", "bf1", "cg1", "rh1", "pa2", "pb2", "pc2", "pd2", "pe2", "pf2", "pg2", "ph2");
        return resultat;
    }


    public static ArrayList<String> CreationListeNoir() {
        ArrayList<String> resultat = new ArrayList<>();
        Collections.addAll(resultat, "Ra8", "Cb8", "Bc8", "Qd8", "Ke8", "Bf8", "Cg8", "Rh8", "Pa7", "Pb7", "Pc7", "Pd7", "Pe7", "Pf7", "Pg7", "Ph7");
        return resultat;
    }




    public static void identiteCase(String[][] plateau) {
        char lettreplateau = 'a';
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int col = 0; col < plateau[ligne].length; col++) {
                plateau[ligne][col] += lettreplateau;
                plateau[ligne][col] += 8 - ligne;
                plateau[ligne][col] += " ";


                lettreplateau++;
            }
            lettreplateau = 'a';
        }
    }




    // Sépare chaques cases du plateau en rajoutant un espace à la fin de chaques cases
    public static void separateurCases(String[][] plateau) {
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int col = 0; col < plateau[ligne].length; col++) {
                plateau[ligne][col] += " ";
            }
        }
    }


    // EXEMPLE CASE PLATEAU : " a1  0  X    "
    public static void ajouterPieceVide(String[][] plateau) {
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int col = 0; col < plateau[ligne].length; col++) {
                plateau[ligne][col] += "X   ";
            }
        }




    }




    // S'occupe de remplir la plateau avec toutes les informations nécessaires
    public static void créerPlateau(String[][] plateau) {
        separateurCases(plateau);
        identiteCase(plateau);
        separateurCases(plateau);
        couleurPlateau(plateau);
        separateurCases(plateau);
        separateurCases(plateau);
        ajouterPieceVide(plateau);
    }


    // Rajoute la couleur en fonction des cases dans un plateau


    public static void couleurPlateau(String[][] plateau) {


        boolean inverseur = false;
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int col = 0; col < plateau[ligne].length; col++) {


                if (inverseur == false) {


                    if (col % 2 == 0) {
                        plateau[ligne][col] += "1";
                    } else plateau[ligne][col] += "0";
                } else {
                    if (col % 2 == 0) {
                        plateau[ligne][col] += "0";
                    } else plateau[ligne][col] += "1";
                }
            }
            inverseur = !inverseur;
        }
    }




    public static void ajouterPièce(String[][] plateau, String coord, char piece) {
        String pivot = "";
        for (int i = 0; i < plateau[7 - (coord.charAt(1) - 49)][coord.charAt(0) - 97].length(); i++) {
            if (i == 8) {
                pivot += piece;
            } else {
                pivot += plateau[7 - (coord.charAt(1) - 49)][coord.charAt(0) - 97].charAt(i);
            }


        }


        plateau[7 - (coord.charAt(1) - 49)][coord.charAt(0) - 97] = pivot;




    }




    public static void ajoutPionBLanc(String[][] plateau, String coord) {




        ajouterPièce(plateau, coord, 'p');
    }


    public static void ajoutPionNoir(String[][] plateau, String coord) {
        ajouterPièce(plateau, coord, 'P');
    }


    public static void ajoutCavalierBlanc(String[][] plateau, String coord) {
        ajouterPièce(plateau, coord, 'c');
    }


    public static void ajoutCavalierNoir(String[][] plateau, String coord) {
        ajouterPièce(plateau, coord, 'C');
    }


    public static void ajoutFouBlanc(String[][] plateau, String coord) {
        ajouterPièce(plateau, coord, 'b');
    }


    public static void ajoutFouNoir(String[][] plateau, String coord) {
        ajouterPièce(plateau, coord, 'B');
    }


    public static void ajoutTourBlanche(String[][] plateau, String coord) {
        ajouterPièce(plateau, coord, 'r');
    }


    public static void ajoutTourNoir(String[][] plateau, String coord) {
        ajouterPièce(plateau, coord, 'R');
    }


    public static void ajoutReineBlanche(String[][] plateau, String coord) {
        ajouterPièce(plateau, coord, 'q');
    }


    public static void ajoutReineNoir(String[][] plateau, String coord) {
        ajouterPièce(plateau, coord, 'Q');
    }


    public static void ajoutRoiBLanc(String[][] plateau, String coord) {
        ajouterPièce(plateau, coord, 'k');
    }


    public static void ajoutRoiNOir(String[][] plateau, String coord) {
        ajouterPièce(plateau, coord, 'K');
    }


    public static void ajoutRangéePion(String[][] plateau, int ligne, char couleur) {


        char convertisseur = 'a';
        String resultat = "";
        int conversionInt = (int) convertisseur;
        if (couleur == 'B') {
            for (int i = 0; i < plateau.length; i++) {
                // On doit convertir les colonnes (de a à h) en entier pour y additionner i puis renvoyer l'équivalent en char
                resultat = "" + convertisseur + ligne;


                ajoutPionBLanc(plateau, resultat);
                conversionInt += 1;
                convertisseur = (char) conversionInt;


            }
        } else {
            for (int i = 0; i < plateau.length; i++) {
                // On doit convertir les colonnes (de a à h) en entier pour y additionner i puis renvoyer l'équivalent en char
                resultat = "" + convertisseur + ligne;


                ajoutPionNoir(plateau, resultat);
                conversionInt += 1;
                convertisseur = (char) conversionInt;


            }
        }


    }


    public static void miseEnPlacePlateau(String[][] plateau) {
        ajoutRangéePion(plateau, 2, 'B');
        ajoutRangéePion(plateau, 7, 'N');
        ajoutTourBlanche(plateau, "a1");
        ajoutTourBlanche(plateau, "h1");
        ajoutCavalierBlanc(plateau, "b1");
        ajoutCavalierBlanc(plateau, "g1");
        ajoutFouBlanc(plateau, "c1");
        ajoutFouBlanc(plateau, "f1");
        ajoutReineBlanche(plateau, "d1");
        ajoutRoiBLanc(plateau, "e1");
        ajoutTourNoir(plateau, "a8");
        ajoutTourNoir(plateau, "h8");
        ajoutCavalierNoir(plateau, "b8");
        ajoutCavalierNoir(plateau, "g8");
        ajoutFouNoir(plateau, "c8");
        ajoutFouNoir(plateau, "f8");
        ajoutReineNoir(plateau, "d8");
        ajoutRoiNOir(plateau, "e8");


    }


    public static String[] couleurEtPseudonymesJoueur() { // fonction qui renvoit les pseudo des joueurs et leurs couleurs associés dans un tableau Ex: [Miamcookie][Sscutix] le pseudonyme à l'indice 0 du tableau joue les Noirs
        Scanner sc = new Scanner(System.in);
        String pseudoJ1 = "";
        String pseudoJ2 = "";
        String[] listPseudo = new String[2];
        do {
            System.out.println("les pseudonymes que vous devez choisir doivent être différents");
            System.out.println("Joueur 1 choisissez votre Pseudonyme :");
            pseudoJ1 = sc.nextLine();
            System.out.println("Joueur 2 choisissez votre Pseudonyme :");
            pseudoJ2 = sc.nextLine();


        } while (pseudoJ1.equals(pseudoJ2));
        boolean random = ToursJoueurs.blancOuNoir();
        if (random) {
            listPseudo = new String[]{pseudoJ1, pseudoJ2};
            System.out.println(pseudoJ1 + " Jouera les Noirs," + pseudoJ2 + " Jouera les Blancs");
        } else {
            listPseudo = new String[]{pseudoJ1, pseudoJ2};
            System.out.println(pseudoJ1 + " Jouera les Noirs," + pseudoJ2 + " Jouera les Blancs");
        }
        return listPseudo;
    }




}

