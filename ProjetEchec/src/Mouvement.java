import java.util.ArrayList;
import java.util.Scanner;


import static java.lang.Character.toLowerCase;


public class Mouvement {


    // Ici on va retrouver tout ce qui touche au mouvement ( l'autorisation de bouger ou de dire si un coup proposé est valide
    //


    public static void changepiece(ArrayList<String> allier, String coord, char change) {
        String nouvcoord = change + coord.substring(1);
        allier.remove(coord);
        allier.add(nouvcoord);
    }


    public static void deplacerPiece(String[][] plateau, String coupJoue, ArrayList<String> listPieceAdversaire, ArrayList<String> listePieceJoueur) {
        String cooArrivee = "";
        cooArrivee += coupJoue.charAt(3);
        cooArrivee += coupJoue.charAt(4); // On a les coordonnés d'arrivée de la pièce.
        String cooDepart = "";
        cooDepart += coupJoue.charAt(1);
        cooDepart += coupJoue.charAt(2);
        char pieceDeplacee = coupJoue.charAt(0);
        Mouvement.supprimePiece(plateau, cooArrivee, listPieceAdversaire); // On supprime la pièce sur les coordonnées d'arrivée
        Mouvement.supprimePiecesansliste(plateau, cooDepart); // On supprime la pièce sur les coordonnées de départ
        bougerpieceliste(listePieceJoueur, coupJoue);
        MiseEnPlaceJeu.ajouterPièce(plateau, cooArrivee, pieceDeplacee);// On ajoute la pièce déplacée sur les coordonnées d'arrivée
    }

    // Renvoit true si le coup proposé suit le bon format
    public static boolean validiteCoup(String coupJoue) {
        boolean reponse = true;
        // On va empêcher de donner les mêmes coordonnés d'arrivé et de départ Rx: ra1a1 => false
        String cooDepart = String.valueOf(coupJoue.charAt(1));
        cooDepart += String.valueOf(coupJoue.charAt(2));


        String cooArrivee = String.valueOf(coupJoue.charAt(3));
        cooArrivee += String.valueOf(coupJoue.charAt(4));


        if (cooArrivee.equals(cooDepart)) {
            return false;
        } else if (coupJoue.length() != 5) {
            reponse = false;


            // On va maintenant vérifier si le premier caractère du coup joué est une pièce répertoriée
        } else if (coupJoue.charAt(0) != 'r' && coupJoue.charAt(0) != 'R' && coupJoue.charAt(0) != 'c' && coupJoue.charAt(0) != 'C' && coupJoue.charAt(0) != 'b' && coupJoue.charAt(0) != 'B' && coupJoue.charAt(0) != 'q' && coupJoue.charAt(0) != 'Q' && coupJoue.charAt(0) != 'k' && coupJoue.charAt(0) != 'K' && coupJoue.charAt(0) != 'p' && coupJoue.charAt(0) != 'P') {
            reponse = false;
        } // On verifie maintenant si le 3ème ou 5ème caractère du coup joué est compris entre 1 et 8 inclus
        else if (coupJoue.charAt(2) < '1' || coupJoue.charAt(2) > '8' || coupJoue.charAt(4) < '1' || coupJoue.charAt(4) > '8') {
            reponse = false;
        }// On vérifie maintenant que le 2ème ou 4ème caractère du coup sont compris entre a et h inclus
        else if ((int) coupJoue.charAt(1) < 97 || (int) coupJoue.charAt(1) > 104 || (int) coupJoue.charAt(3) < 97 || (int) coupJoue.charAt(3) > 104) {
            reponse = false;
        }


        return reponse;
    }


    public static void supprimePiece(String[][] plateau, String tile, ArrayList<String> allier) {
        String caseSansPiece = "";
        // /a1/0/K/
        String convertTileEnCaseComplete = plateau[7 - (tile.charAt(1) - 49)][tile.charAt(0) - 97];
        String supr = convertTileEnCaseComplete.charAt(8) + convertTileEnCaseComplete.substring(1, 3);
        allier.remove(supr);
        caseSansPiece = convertTileEnCaseComplete.substring(0, 8);
        caseSansPiece += "X   ";
        plateau[56 - (int) convertTileEnCaseComplete.charAt(2)][(int) convertTileEnCaseComplete.charAt(1) - 97] = caseSansPiece;


    }

    public static void supprimePiecesansliste(String[][] plateau, String tile) {
        MiseEnPlaceJeu.ajouterPièce(plateau, tile, 'X');
        /*
        String caseSansPiece = "";
        // /a1/0/K/
        String convertTileEnCaseComplete = plateau[7 - (tile.charAt(1) - 49)][tile.charAt(0) - 97];
        caseSansPiece = convertTileEnCaseComplete.substring(0, 8);
        caseSansPiece += "X   ";








        plateau[56 - (int) convertTileEnCaseComplete.charAt(2)][(int) convertTileEnCaseComplete.charAt(1) - 97] = caseSansPiece;







*/
    }


    public static boolean coupMangeAmis(String[][] plateau, String coupJoue) { // Programme qui renvoit True si la pièce arrive sur une case vide ou pièce couleur opposée


        String cooArrivee = String.valueOf(coupJoue.charAt(3));
        cooArrivee += String.valueOf(coupJoue.charAt(4));
        String cooDepart = String.valueOf(coupJoue.charAt(1));
        cooDepart += String.valueOf(coupJoue.charAt(2));


        if (coupJoue.charAt(0) > 97 && plateau[56 - (int) coupJoue.charAt(4)][(int) coupJoue.charAt(3) - 97].charAt(8) <= 88) { // on vérifie que Pièce est blanc ET pièce arrivé est noir
            return true;
        } else if (coupJoue.charAt(0) > 97 && plateau[56 - (int) coupJoue.charAt(4)][(int) coupJoue.charAt(3) - 97].charAt(8) > 97) { // blanc et Blanc
            return false;
        } else if (coupJoue.charAt(0) < 87 && plateau[56 - (int) coupJoue.charAt(4)][(int) coupJoue.charAt(3) - 97].charAt(8) < 87) { // noir et Noir
            return false;
        } else if (coupJoue.charAt(0) < 87 && plateau[56 - (int) coupJoue.charAt(4)][(int) coupJoue.charAt(3) - 97].charAt(8) >= 88) { // Si Pièce noir et pièce blanche
            return true;


        }
        return false; // Ce programme return false Si les pièce ne vont pas et ne sont ni noirs ni blanches par exemple


    }
/*
   public static void actionDeplacePièce(String[][] plateau, String coupJoue,) { // Supprime pièce sur case départ, supprimé pièce sur case arrivée et copie pièce départ sur case arrivée
       // C'est le programme qu'on lance si le coup passe toutes les vérifications nécessaires


       String cooArrivee = String.valueOf(coupJoue.charAt(3));
       cooArrivee += String.valueOf(coupJoue.charAt(4));
       String cooDepart = String.valueOf(coupJoue.charAt(1));
       cooDepart += String.valueOf(coupJoue.charAt(2));


       char pieceDepart = coupJoue.charAt(0);
       Mouvement.supprimePiece(plateau, cooArrivee,);
       Mouvement.supprimePiece(plateau, cooDepart,);


       MiseEnPlaceJeu.ajouterPièce(plateau, cooArrivee, pieceDepart);


   }*/


    public static boolean coupVerificateurFinal(String[][] plateau, String coupJoue, String[] listPseudo, int nbrTours) {// Ce programme prend un coup joué et vérifie si la pièce se déplace bien, si le coup est valide, si la pièce sur la case d'arrivée est adverse, si il y a une pièce sur le chemin
        boolean coupInvalide = true;


        String cooDepart = String.valueOf(coupJoue.charAt(1));
        cooDepart += String.valueOf(coupJoue.charAt(2));
        if (nbrTours % 2 == 0 && coupJoue.charAt(0) > 88) {  // condition joueur noir déplace pièce blanche
            coupInvalide = false;
        } else if (nbrTours % 2 == 1 && coupJoue.charAt(0) < 97) {  // condition joueur BLanc déplace pièce noir
            coupInvalide = false;
        }
        if (!validiteCoup(coupJoue)) {
            //System.out.println("Votre coup est invalide");
            coupInvalide = false;
        }
        char piece = toLowerCase(plateau[7 - ((int) cooDepart.charAt(1) - '1')][((int) cooDepart.charAt(0) - 'a')].charAt(8));
        //System.out.println(piece);
        //System.out.println((int)piece);
        if (piece != toLowerCase(coupJoue.charAt(0))) {
            //System.out.println("La pièce sur la case de départ n'est pas égale à la pièce saisie");
            return false;
        }

        switch (piece) {
            case 'x':
                //System.out.println("la case de départ est vide");
                coupInvalide = false;
                break;


            case 'r':
                if (!Piece.coupTour(plateau, coupJoue)) {
                    coupInvalide = false;
                } else if (!Piece.ennemie(plateau, coupJoue)) {
                    coupInvalide = false;
                } else if (!coupMangeAmis(plateau, coupJoue)) {
                    coupInvalide = false;
                }
                break;
            case 'b':
                //System.out.println("BISHOP");
                if (!Piece.coupBishop(plateau, coupJoue)) {
                    //System.out.println("coupBishop");
                    coupInvalide = false;


                } else if (!Piece.ennemie(plateau, coupJoue)) {
                    coupInvalide = false;
                    //System.out.println("ennemie");


                    //System.out.println("coupennemie");
                } else if (!coupMangeAmis(plateau, coupJoue)) {
                    coupInvalide = false;
                    //System.out.println("coupMangaAMis");


                }
                break;
            case 'q':
                if (!Piece.coupReine(plateau, coupJoue)) {
                    coupInvalide = false;
                } else if (!Piece.ennemie(plateau, coupJoue)) {
                    coupInvalide = false;
                } else if (!coupMangeAmis(plateau, coupJoue)) {
                    coupInvalide = false;
                }
                break;
            case 'c':
                if (!Piece.coupCavalier(plateau, coupJoue)) {
                    coupInvalide = false;
                } else if (!coupMangeAmis(plateau, coupJoue)) {
                    coupInvalide = false;
                }
                break;
            case 'k':
                if (!Piece.coupRoi(plateau, coupJoue)) {
                    coupInvalide = false;
                } else if (!coupMangeAmis(plateau, coupJoue)) {
                    coupInvalide = false;
                }
                break;
            case 'p':
                if (!Piece.coupPion(plateau, coupJoue)) {
                    coupInvalide = false;
                } else if (!coupMangeAmis(plateau, coupJoue)) {
                    coupInvalide = false;
                }
                break;
        }


        return coupInvalide;
    }

    // MERCI MIKE
    public static boolean roiEchec(String[][] plateau, ArrayList<String> advers, String king, String[] listPseudo, int nbrTours) { //verifie si le roi est en danger
        int i = 0;
        boolean echec = false;
        while (i < advers.size() && !echec) {
            String coup = advers.get(i) + king;
            //System.out.println(coup);
            echec = coupVerificateurFinal(plateau, coup, listPseudo, nbrTours); //verifie si toute les piece adverse peuvent atteindre le roi
            i++;
            //System.out.println(echec);
        }
        return echec;
    }


    //simule le déplacement de la piece pour voir si le déplacement met en danger le roi, renvoie true si c est le cas et false si ca ne l est pas


    public static boolean dangerRoiAllie(String[][] plateau, String coup, ArrayList<String> advers, String roi, String[] listPseudo, int nbrTours) {
        String[][] simulation = Programmes.copieplateau(plateau);
        // ERREUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUR MIKE JE TE HAIS
        String cooDepart = "";
        cooDepart += coup.charAt(1);
        cooDepart += coup.charAt(2);
        Mouvement.supprimePiecesansliste(simulation, cooDepart);
        MiseEnPlaceJeu.ajouterPièce(simulation, coup.substring(3), coup.charAt(0));
        //System.out.println("\n \n");
        return Mouvement.roiEchec(simulation, advers, roi, listPseudo, nbrTours + 1);


    }


    //renvoie une liste des coup possible
    public static ArrayList<String> coupPossible(String[][] plateau, ArrayList<String> Listepiece, String[] listPseudoEtOrdreJoueurs, int nbrTours, ArrayList<String> ennemie, String roi) {
        ArrayList<String> ListeDesCoupPossible = new ArrayList<>();
        //3boucle pour chaque piece chaque case
        for (String piece : Listepiece) {
            for (String[] chiffre : plateau) {
                for (String lettre : chiffre) {
                    String coup = piece + lettre.substring(1, 3); //crée le coup
                    //System.out.println(coup);
                    if (coupVerificateurFinal(plateau, coup, listPseudoEtOrdreJoueurs, nbrTours)) { //regarde si le coup respecte les règle
                        if(!dangerRoiAllie(plateau,coup,ennemie,roi,listPseudoEtOrdreJoueurs,nbrTours)){
                            ListeDesCoupPossible.add(coup);//si le coup est possible tout en respectant les regle, il est ajouté dans la liste
                        }
                    }
                }
            }
        }
        return ListeDesCoupPossible;
    }


    public static boolean egalité(String[][] plateau, ArrayList<String> coupPossibleBlanc, ArrayList<String> coupPossibleNoir, ArrayList<String> noir, ArrayList<String> blanc, String[] listPseudoEtOrdreJoueurs, int nbrTours) {
        int roiNoir = 0;//index du roi noir
        int roiBlanc = 0;//index du roi blanc


        for (int piece = 0; piece < noir.size(); piece++) { //obtenir l'index du roi noir
            if (noir.get(piece).charAt(0) == 'R') {
                roiNoir = piece;
            }
        }
        for (int piece2 = 0; piece2 < blanc.size(); piece2++) { //obtenir l'index du roi blanc
            if (blanc.get(piece2).charAt(0) == 'r') {
                roiBlanc = piece2;
            }
        }
        //condition: PAT = roi qui peut pas faire de mouvement sans qu'il soient illegeaux
        if (!roiEchec(plateau, noir, noir.get(roiNoir), listPseudoEtOrdreJoueurs, nbrTours)) {// si roi non echec
            if (coupPossibleNoir.isEmpty()) {//et aucun mouvement n est possible
                System.out.println("égalité\tPAT");
                return true;
            }
        }
        if (!roiEchec(plateau, blanc, blanc.get(roiBlanc), listPseudoEtOrdreJoueurs, nbrTours)) {//si roi non echec
            if (coupPossibleBlanc.isEmpty()) {// et aucun movement possible
                System.out.println("égalité\tPAT");
                return true;
            }
        }
        //si il reste plus les roi dans le jeux
        if (noir.size() == 1 && blanc.size() == 1) {
            System.out.println("égalité, plus aucune piece restante");
            return true;
        }
        //condition: impossiblité de mater par insuffisance materiel ou pratique
        if (noir.size() == 1) { //si il reste seulment un roi noir
            if (blanc.size() == 2) {//et qu'il reste une piece blanche
                if ((blanc.get(blanc.size() - roiBlanc)).charAt(0) == 'c' || (blanc.get(blanc.size() - roiBlanc)).charAt(0) == 'b') {
                    //si la piece restante blanc est un cavalier ou un fou, il est impossible de mater l'adverser, donc c'est une égalité
                    System.out.println("égalité, insuffisance materiel");
                    return true;
                }
            }
        }
        if (noir.size() == 2) { //si 2 piece noir (roi et une autre piece)
            if (blanc.size() == 1) {//si reste que roi blanc
                if ((noir.get(noir.size() - roiNoir)).charAt(0) == 'C' || (noir.get(noir.size() - roiNoir)).charAt(0) == 'B') {
                    //si la piece restante noir est un cavalier ou un fou, il est impossible de mater l'adverser, donc c'est une égalité
                    System.out.println("égalité, insuffisance materiel");
                    return true;
                }
            }
            if (blanc.size() == 2) {//si il reste un roi blanc et une autre piece
                if ((noir.get(noir.size() - roiNoir).charAt(0) == 'B' || noir.get(noir.size() - roiNoir).charAt(0) == 'C') && (blanc.get(blanc.size() - roiBlanc).charAt(0) == 'b' || blanc.get(blanc.size() - roiBlanc).charAt(0) == 'c')) {
                    //si la piece restante des joueur sont soit des fous soit des cavalier soit un mix de fou et cavalier,
                    //égalité par insuffisance materiel ou insuffisance pratique
                    System.out.println("égalité, insufisance materiel");
                    return true;
                }
            }
        }
        return false;
    }

    public static void bougerpieceliste(ArrayList<String> liste, String coup) {
        String debut = coup.substring(0, 3);
        String fin = coup.charAt(0) + coup.substring(3, 5);
        liste.remove(debut);
        liste.add(fin);
    }

    public static boolean echecETmat(ArrayList<String> coupPossible, String[][] plateau, ArrayList<String> advers, String king, String[] joueur, int tour) {
       // System.out.println(coupPossible.isEmpty() && roiEchec(plateau, advers, king, joueur, tour));
       // System.out.println("echec et mat");
       // System.out.println(coupPossible.isEmpty());
       // System.out.println(roiEchec(plateau,advers,king,joueur,tour));
        return (coupPossible.isEmpty() && roiEchec(plateau, advers, king, joueur, tour));
    }


}

