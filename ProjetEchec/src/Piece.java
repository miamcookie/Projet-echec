import java.util.ArrayList;
import java.util.Scanner;


public class Piece {


    // Ici on va retrouver les déplacements de chaques pièces




    public static void promotion(String[][] plateau, String coup, ArrayList<String> allier) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = true;
        if (coup.charAt(0) == 'p') {
            while (exit) {
                System.out.println("choisiser la piece: ");
                System.out.println("c: Chavalier\nt:Tour\nf:Fou\nr:Reine");
                String choix = scanner.nextLine();
                char choixchar = choix.charAt(0);
                switch (choixchar) {
                    case 'c':
                        Mouvement.supprimePiece(plateau, coup.substring(1,3), allier);
                        MiseEnPlaceJeu.ajouterPièce(plateau, coup.substring(3), 'c');
                        Mouvement.changepiece(allier, coup.charAt(0) + coup.substring(3), 'c');
                        exit = false;
                        break;
                    case 't':
                        Mouvement.supprimePiece(plateau, coup.substring(1,3), allier);
                        MiseEnPlaceJeu.ajouterPièce(plateau, coup.substring(3), 'r');
                        Mouvement.changepiece(allier, coup.charAt(0) + coup.substring(3), 'r');
                        exit = false;
                        break;
                    case 'f':
                        Mouvement.supprimePiece(plateau, coup.substring(1,3), allier);
                        MiseEnPlaceJeu.ajouterPièce(plateau, coup.substring(3), 'b');
                        Mouvement.changepiece(allier, coup.charAt(0) + coup.substring(3), 'b');
                        exit = false;
                        break;
                    case 'r':
                        Mouvement.supprimePiece(plateau, coup.substring(1,3), allier);
                        MiseEnPlaceJeu.ajouterPièce(plateau, coup.substring(3), 'q');
                        Mouvement.changepiece(allier, coup.charAt(0) + coup.substring(3), 'q');
                        exit = false;
                        break;
                    default:
                        System.out.println("Lettre non reconnue\n");
















                }
            }
        } else {
            while (exit) {
                System.out.println("choisiser la piece:");
                System.out.println("C: Chavalier\nT:Tour\nF:Fou\nR:Reine");
                String choix = scanner.nextLine();
                char choixchar = choix.charAt(0);
                switch (choixchar) {
                    case 'C':
                        Mouvement.supprimePiece(plateau, coup.substring(1,3), allier);
                        MiseEnPlaceJeu.ajouterPièce(plateau, coup.substring(3), 'C');
                        Mouvement.changepiece(allier, coup.charAt(0) + coup.substring(3), 'C');
                        exit = false;
                        break;
                    case 'T':
                        Mouvement.supprimePiece(plateau, coup.substring(1,3), allier);
                        MiseEnPlaceJeu.ajouterPièce(plateau, coup.substring(3), 'R');
                        Mouvement.changepiece(allier, coup.charAt(0) + coup.substring(3), 'R');
                        exit = false;
                        break;
                    case 'F':
                        Mouvement.supprimePiece(plateau, coup.substring(1,3), allier);
                        MiseEnPlaceJeu.ajouterPièce(plateau, coup.substring(3), 'B');
                        Mouvement.changepiece(allier, coup.charAt(0) + coup.substring(3), 'B');
                        exit = false;
                        break;
                    case 'R':
                        Mouvement.supprimePiece(plateau, coup.substring(1,3), allier);
                        MiseEnPlaceJeu.ajouterPièce(plateau, coup.substring(3), 'Q');
                        Mouvement.changepiece(allier, coup.charAt(0) + coup.substring(3), 'Q');
                        exit = false;
                        break;
                    default:
                        System.out.println("Lettre non reconnue\n");
                }
            }
        }
















    }












    // FOnction qui Prend un coup et qui nous dit si la pièce jouée est blanche ou noir TRUE = BLANC // case vide est considéré comme noir
    public static boolean blancOuNoir(String coupJoue) {
        boolean blancOuNoir;
        if (coupJoue.charAt(0) > 88) {
            blancOuNoir = true;
        } else blancOuNoir = false;
        return blancOuNoir;
    }


    public static boolean coupReine(String[][] plateau, String coupJoue) { // ACTUELLEMENT BUGGE, la reine se tranfsforme bizarrement ???????????????
        boolean coupvalide = false;
        if (!coupTour(plateau, coupJoue) && coupBishop(plateau, coupJoue) || !coupBishop(plateau, coupJoue) && coupTour(plateau, coupJoue)) {
            coupvalide = true;




        }
        return coupvalide;
    }




    public static boolean coupTour(String[][] plateau, String coupJoue) {




        // Deja, on vérifie si le coup entré est correct


        if (!Mouvement.validiteCoup(coupJoue)) {
            //System.out.println("CoupTour syntaxe Incorrecte");
            return false;
        } else if (coupJoue.charAt(1) != coupJoue.charAt(3) && coupJoue.charAt(2) != coupJoue.charAt(4)) {
            //System.out.println("coupTour Deplacement");
            return false; // Ici le joueur essaie de déplacer la Tour en diagonale ou de la mettre n'importe ou sur le plateau
        } else if (coupJoue.charAt(2) != coupJoue.charAt(4) || coupJoue.charAt(1) != coupJoue.charAt(4)) {
            //System.out.println("coupTour return True");
            return true;
        }
        //System.out.println("return false par défaut");
        return false;
    }


    public static boolean coupBishop(String[][] plateau, String coup) {
        boolean coupvalide = false;
        if (!Mouvement.validiteCoup(coup)) {
            //System.out.println("Verifie si bishop");
            return coupvalide;
        } else {
            if (plateau[7 - ((int) coup.charAt(4) - '1')][coup.charAt(3) - 'a'].charAt(5) == plateau[7 - ((int) coup.charAt(2) - '1')][coup.charAt(1) - 'a'].charAt(5)) {//si case de couleur de départ est la meme que l'arrivée
                //System.out.println("Verifie couleur case depart arrivée");
                //changer les coordonée du point de depart et point d'arriver en chiffre
                int L1 = (int) coup.charAt(1) - 'a';
                // System.out.println(L1);
                int C1 = (int) 7 - (coup.charAt(2) - '1');
                // System.out.println(C1);
                int L2 = (int) coup.charAt(3) - 'a';
                // System.out.println(L2);
                int C2 = (int) 7 - (coup.charAt(4) - '1');
                // System.out.println(C2);
                //faire la difference des point depart et arrivée
                int calcul1 = L2 - L1;
                // System.out.println(calcul1);
                calcul1 = Programmes.valAbs(calcul1);
                // System.out.println(calcul1);
                int calcul2 = C2 - C1;
                // System.out.println(calcul2);
                calcul2 = Programmes.valAbs(calcul2);
                //  System.out.println(calcul2);
                //si la difference sont égaux, alors le fou peut faire le mouvement


                if (calcul1 == calcul2) {
                    //     System.out.println("coordonée correcte ?");
                    coupvalide = true;
                }
            }
        }
        return coupvalide;
    }


    public static boolean coupCavalier(String[][] plateau, String coupJoue) {
        boolean coupValide = true;
        if (!Mouvement.validiteCoup(coupJoue)) {
            coupValide = false;
        }
        String cooArrivee = String.valueOf(coupJoue.charAt(3));
        cooArrivee += String.valueOf(coupJoue.charAt(4));
        String cooDepart = String.valueOf(coupJoue.charAt(1));
        cooDepart += String.valueOf(coupJoue.charAt(2));
        // comme il n'y a que 8 coup possible au maximum, on va faire un grand If et mettre un false si il ne valide aucune des caractéristiques
        if (((int) cooDepart.charAt(0) + 1 != (int) cooArrivee.charAt(0) || (int) cooDepart.charAt(1) + 2 != (int) cooArrivee.charAt(1)) && ((int) cooDepart.charAt(0) + 2 != (int) cooArrivee.charAt(0) || (int) cooDepart.charAt(1) + 1 != (int) cooArrivee.charAt(1)) && ((int) cooDepart.charAt(0) - 1 != (int) cooArrivee.charAt(0) || (int) cooDepart.charAt(1) + 2 != (int) cooArrivee.charAt(1)) && ((int) cooDepart.charAt(0) - 2 != (int) cooArrivee.charAt(0) || (int) cooDepart.charAt(1) + 1 != (int) cooArrivee.charAt(1)) && ((int) cooDepart.charAt(0) + 1 != (int) cooArrivee.charAt(0) || (int) cooDepart.charAt(1) - 2 != (int) cooArrivee.charAt(1)) && ((int) cooDepart.charAt(0) + 2 != (int) cooArrivee.charAt(0) || (int) cooDepart.charAt(1) - 1 != (int) cooArrivee.charAt(1)) && ((int) cooDepart.charAt(0) - 1 != (int) cooArrivee.charAt(0) || (int) cooDepart.charAt(1) - 2 != (int) cooArrivee.charAt(1)) && ((int) cooDepart.charAt(0) - 2 != (int) cooArrivee.charAt(0) || (int) cooDepart.charAt(1) - 1 != (int) cooArrivee.charAt(1))) {
            coupValide = false;
        }
        return coupValide;
    }


    public static boolean coupPion(String[][] plateau, String coup) {
        if (coup.charAt(0) == 'P') {




            //bouge en diagonale
            if ((((int) coup.charAt(4)) + 1 == ((int) coup.charAt(2)) && ((int) coup.charAt(3)) + 1 == ((int) coup.charAt(1))) || (((int) coup.charAt(4)) + 1 == ((int) coup.charAt(2)) && ((int) coup.charAt(3)) - 1 == ((int) coup.charAt(1)))) {
                //si ennemie a l'arrivé
                if (plateau[7 - ((int) coup.charAt(4) - '1')][(int) coup.charAt(3) - 'a'].charAt(8) >= 'a' && plateau[7 - ((int) coup.charAt(4) - '1')][(int) coup.charAt(3) - 'a'].charAt(8) <= 'z') {
                    return true;
                }
            }
            //bouge d'une case en verticale
            if (((int) coup.charAt(4)) + 1 == ((int) coup.charAt(2)) && coup.charAt(3) == coup.charAt(1)) {
                //verifie si ennemie a l'arrivé
                if (plateau[7 - ((int) coup.charAt(4) - '1')][(int) coup.charAt(3) - 'a'].charAt(8) == 'X') {
                    return true;
                }
            }
            //bouge de 2 case
            if (((int) coup.charAt(4)) + 2 == ((int) coup.charAt(2)) && coup.charAt(3) == coup.charAt(1)) {
                //verifie si c'est la case de départ
                if (coup.charAt(2) == '7') {
                    //verifie si case libre a l arrivé
                    if (plateau[7 - ((int) coup.charAt(4) - '1')][(int) coup.charAt(3) - 'a'].charAt(8) == 'X') {
                        //verifie si case entre l arrivé et le depart
                        if (plateau[7 - ((int) coup.charAt(4) - '1') - 1][(int) coup.charAt(3) - 'a'].charAt(8) == 'X') {
                            return true;
                        }
                    }
                }
            }
        } else {
            //bouge en diagonale
            if ((((int) coup.charAt(4)) - 1 == ((int) coup.charAt(2)) && ((int) coup.charAt(3)) - 1 == ((int) coup.charAt(1))) || (((int) coup.charAt(4)) - 1 == ((int) coup.charAt(2)) && ((int) coup.charAt(3)) + 1 == ((int) coup.charAt(1)))) {
                //si ennemie a l'arrivé
                if (plateau[7 - ((int) coup.charAt(4) - '1')][(int) coup.charAt(3) - 'a'].charAt(8) >= 'A' && plateau[7 - ((int) coup.charAt(4) - '1')][(int) coup.charAt(3) - 'a'].charAt(8) <= 'T') {
                    return true;
                }
            }
            //bouge d'une case en verticale
            if (((int) coup.charAt(4)) - 1 == ((int) coup.charAt(2)) && coup.charAt(3) == coup.charAt(1)) {
                //verifie si ennemie a l'arrivé
                if (plateau[7 - ((int) coup.charAt(4) - '1')][(int) coup.charAt(3) - 'a'].charAt(8) == 'X') {
                    return true;
                }
            }
            //bouge de 2 case
            if (((int) coup.charAt(4)) - 2 == ((int) coup.charAt(2)) && coup.charAt(3) == coup.charAt(1)) {
                //verifie si c'est la case de départ
                if (coup.charAt(2) == '2') {
                    //verifie si case libre a l arrivé
                    if (plateau[7 - ((int) coup.charAt(4) - '1')][(int) coup.charAt(3) - 'a'].charAt(8) == 'X') {
                        //verifie si case entre l arrivé et le depart
                        if (plateau[7 - ((int) coup.charAt(4) - '1') + 1][(int) coup.charAt(3) - 'a'].charAt(8) == 'X') {
                            return true;
                        }
                    }
                }
            }




        }




        return false;
    }




    public static boolean coupRoi(String[][] plateau, String coup) {
        if (coup.charAt(0) != 'k' && coup.charAt(0) != 'K') {
            return false;
        } else {
            int arriveeL = (int) coup.charAt(3) - 'a';
            int arriveC = (int) (coup.charAt(4) - '1');
            int departL = (int) coup.charAt(1) - 'a';
            int departC = (int) (coup.charAt(2) - '1');
            int calculL = arriveeL - departL;
            int calculC = arriveC - departC;
            if (calculC > 1 || calculL > 1 || calculC < -1 || calculL < -1) {
                return false;
            } else if (plateau[arriveC][arriveeL].charAt(8) != 'X') {
                return false;
            }
        }
        return true;
    }




    public static boolean ennemie(String[][] plateau, String coup) { //verifie si ennemie entre point depart et point d'arriver
        int arriveeL = (int) coup.charAt(3) - 'a';
        int arriveC = 7 - ((int) (coup.charAt(4) - '1'));
        int departL = (int) coup.charAt(1) - 'a';
        int departC = 7 - ((int) (coup.charAt(2) - '1'));
        int calculL = arriveeL - departL;
        int calculC = arriveC - departC;
          /* System.out.println(arriveeL);
          System.out.println(arriveC);
          System.out.println(departL);
          System.out.println(departC);
          System.out.println(calculL);
          System.out.println(calculC);*/
        if ((calculC == 0 || calculL == 0) && (coup.charAt(0) != 'b' && coup.charAt(0) != 'B')) { //si bouge en ligne droite
            //System.out.println("bouge en ligne droite");
            if (calculC == 0) { // si bouge en horizontale
                // System.out.println("bouge en horizontale");
                if (calculL > 0) { // tour ou reine par a droite
                    for (int i = 1; i <= calculL - 1; i++) {
                        if (plateau[departC][departL + i].charAt(8) != 'X') { //verifie chaque case horizontale de point de depart a arrivée
                            //System.out.println("case prise");
                            return false;
                        }
                        // System.out.println("case libre itteration : " + i);
                    }
                    return true;
                } else { //tour/reine par a gauche
                    calculL = Programmes.valAbs(calculL);
                    for (int i = 1; i <= calculL - 1; i++) {
                        if (plateau[departC][departL - i].charAt(8) != 'X') { //verifie chaque case horizontale de point de depart a arrivée
                            //System.out.println("case prise");
                            return false;
                        }
                        // System.out.println("case libre itteration : " + i);
                    }
                    return true;
                }
            } else { //si bouge en verticale
                //System.out.println("bouge verticale");
                if (calculC > 0) {
                    //System.out.println(calculC);
                    for (int i = 1; i <= calculC - 1; i++) {
                        // System.out.println(i);
                        // System.out.println(departC);
                        // System.out.println(departL);
                        if (plateau[departC + i][departL].charAt(8) != 'X') {
                            //System.out.println("case prise");
                            return false;
                        }
                        //System.out.println("case libre itteration : " + i );
                    }
                    return true;
                } else { // ICI
                    calculC = Programmes.valAbs(calculC);
                    for (int i = 1; i <= calculC - 1; i++) {
                        if (plateau[departC - i][departL].charAt(8) != 'X') {
                            //System.out.println("case prise");
                            return false;
                        }
                    }
                    return true;
                }
            }
        } else { // Mouvement diagonal
            //System.out.println("mouvement diagonale");
            if (calculC < 0 && calculL < 0) {




                calculC = Programmes.valAbs(calculC);
                for (int i = 1; i <= calculC-1; i++) {
                    //System.out.println("haut gauche");
                    if (plateau[departC - i][departL - i].charAt(8) != 'X') {
                        return false;
                    }

                }
                return true;
            } else if (calculC < 0 && calculL > 0) {




                //System.out.println("<0 >0");
                //System.out.println("bas gauche");
                for (int i = 1; i <= calculL-1; i++) {
                    //System.out.println(i);
                    if (plateau[departC - i][departL + i].charAt(8) != 'X') {
                        return false;
                    }
                }
                return true;
            } else if (calculC > 0 && calculL < 0) {
                //System.out.println(">0 <0");
                //System.out.println("haut droit");
                for (int i = 1; i <= calculC-1; i++) {
                    if (plateau[departC + i][departL - i].charAt(8) != 'X') {
                        return false;
                    }
                }
                return true;
            } else if (calculC > 0 && calculL > 0) {
                //System.out.println(">0 >0");
                //System.out.println("bas droit");
                for (int i = 1; i <= calculC-1; i++) {
                    if (plateau[departC + i][departL + i].charAt(8) != 'X') {
                        //System.out.println(i);
                        return false;
                    }
                }
                return true;
            }
        }


        return false;
    }




}























