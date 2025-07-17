public class Affichage {




    // Ici on va voir tout ce qui est lié à l'affichage, ce qui permet au programme de comprendre le tableau de l'interpréter et de le traduire en quelque chose de plus lisible




    // Affiche de manière brut un plateau d'échec
    public static void printPlateau(String[][] plateau) {
        System.out.println("");
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {


                System.out.print(plateau[ligne][colonne] + "      ");
            }
            System.out.println();
        }
    }


    // PROGRAMME QUI EST LA POUR TRADUIRE LES PRINT POUR L'AFFICHAGE
    public static void printAffichage(String[][] plateau, boolean typeAffichage) {
        if (!typeAffichage) {
            System.out.println("   a  b  c  d  e  f  g  h ");
            String couleur = "";
            char piece = ' ';
            for (int ligne = 0; ligne < plateau.length; ligne++) {




                for (int col = 0; col < plateau[ligne].length; col++) {
                    if (col == 0) {
                        System.out.print(plateau[ligne][col].charAt(2) + " ");
                    }


                    if (plateau[ligne][col].charAt(5) == '1') {
                        couleur = "\u001B[100m"; // BLANC


                    } else {
                        couleur = "\u001B[40m"; // NOIR
                    }


                    piece = determinCharPiecePourAffichage(plateau[ligne][col].charAt(8));
                    System.out.print(couleur + " " + piece + " \u001B[0m"); // on affiche la couleur de l'arrière plan, la pièce et on arrête la couleur du background
                    couleur = "";




                }
                System.out.println("");
            }
        } else { // Ici donc typeAfficahge = true, on affiche le tableau en pixel art


            char colonne = 'a';
            char lignes = '8';
            String tile = "";
            tile = "";
            tile = tile + colonne;
            tile = tile + lignes;




            System.out.println(tile);
            for (int ligne = 0; ligne < plateau.length; ligne++) {


                for (int col = 0; col < 9; col++) {
                    for (int pixel = 0; pixel < 8; pixel++) {


                        Affichage.tileIndicePixelArt(plateau, tile, col);
                        colonne = (char) ((int) colonne + 1);
                        tile = "";
                        tile = tile + colonne;
                        tile = tile + lignes;


                    }
                    System.out.println();
                    colonne = 'a';
                    tile = "";
                    tile = tile + colonne;
                    tile = tile + lignes;


                }
                lignes = (char) ((int) lignes - 1);
                tile = "";
                tile = tile + colonne;
                tile = tile + lignes;


            }


        }




    }


    // Ce programme prend une piece (type p, P, r,R,k,K,q,Q) et un indice de 0 à 7 et renvoit la ligne en pixel art corresepondant à l'indice
    public static String ligneCasePixelArt(char piece, int indice) {
        return "";
    }


    // Programme qui prend une "tile" et un indice, et il renvoit ce qui doit s'afficher en mode pixel art
    // il faut l'appeler 8 fois pour avoir la case entière d'affiché




    public static String tileIndicePixelArt(String[][] plateau, String tile, int index) {
        String convertTileEnCaseComplete = plateau[7 - (tile.charAt(1) - 49)][tile.charAt(0) - 97];
        String background = "[107m [0m"; // Blanc par défaut


        index = index * 24;
        if (convertTileEnCaseComplete.charAt(5) == '0') {
            background = "[42m [0m"; // Background devient NOIR(vert)
        }


        if (convertTileEnCaseComplete.charAt(8) == 'X') { // Case vide
            for (int i = 0; i < 24; i++) {
                System.out.print(background);
            }
        } else {
            if (convertTileEnCaseComplete.charAt(8) == 'p') { // Divise en fonction des pièces
                if ((index / 24) == 8) {
                    System.out.print("||||||||Pion Blanc||||||");
                } else {
                    // On donne le pixel art de la pièce
                    String pionBlanc = ("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnNNNNNNnnnnnnnnnnnnnnnNNNBBBBBBNNNnnnnnnnnnnnnNNNBBBBBBNNNnnnnnnnnnnnnnnnNNNNNNnnnnnnnnnnnnnnnNNNBBBBBBNNNnnnnnnnnnNNNNNNBBBBBBNNNNNNnnn");
                    for (int i = 0; i < 24; i++) { // On répète le nombre de caractères à print


                        if (pionBlanc.charAt(i + index) == 'n') {
                            System.out.print(background);
                        } else if (pionBlanc.charAt(i + index) == 'N') {
                            System.out.print("[40m [0m"); // COuleur noir
                        } else if (pionBlanc.charAt(i + index) == 'B') {
                            System.out.print("[107m [0m"); // couleur blanc
                        } else {
                            System.out.print("[41m [0m");// Couleur rouge pour Reine
                        }
                    }
                }


            } else if (convertTileEnCaseComplete.charAt(8) == 'P') {
                if ((index / 24) == 8) {
                    System.out.print("||||||||Pion Noir|||||||");
                } else {
                    String pionNoir = ("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnNNNNNNnnnnnnnnnnnnnnnNNNNNNNNNNNNnnnnnnnnnnnnNNNNNNNNNNNNnnnnnnnnnnnnnnnNNNNNNnnnnnnnnnnnnnnnNNNNNNNNNNNNnnnnnnnnnNNNNNNNNNNNNNNNNNNnnn");
                    for (int i = 0; i < 24; i++) { // On répète le nombre de caractères à print


                        if (pionNoir.charAt(i + index) == 'n') {
                            System.out.print(background);
                        } else if (pionNoir.charAt(i + index) == 'N') {
                            System.out.print("[40m [0m"); // COuleur noir
                        } else if (pionNoir.charAt(i + index) == 'B') {
                            System.out.print("[107m [0m"); // couleur blanc
                        } else {
                            System.out.print("[41m [0m");// Couleur rouge pour Reine
                        }
                    }
                }
            } else if (convertTileEnCaseComplete.charAt(8) == 'c') {
                if ((index / 24) == 8) {
                    System.out.print("|||||Cavalier Blanc|||||");
                } else {
                    String cavalierBlanc = ("nnnnnnNNNNNNNNNNNNnnnnnnnnnNNNBBBBBBBBBBBBNNNnnnNNNBBBNNNNNNNNNNNNnnnnnnNNNBBBNNNnnnnnnnnnnnnnnnNNNBBBNNNnnnnnnnnnnnnnnnNNNBBBNNNNNNNNNNNNnnnnnnnnnNNNBBBBBBBBBBBBNNNnnnnnnnnnNNNNNNNNNNNNnnnnnn");
                    for (int i = 0; i < 24; i++) { // On répète le nombre de caractères à print


                        if (cavalierBlanc.charAt(i + index) == 'n') {
                            System.out.print(background);
                        } else if (cavalierBlanc.charAt(i + index) == 'N') {
                            System.out.print("[40m [0m"); // COuleur noir
                        } else if (cavalierBlanc.charAt(i + index) == 'B') {
                            System.out.print("[107m [0m"); // couleur blanc
                        } else {
                            System.out.print("[41m [0m");// Couleur rouge pour Reine
                        }
                    }
                }
            } else if (convertTileEnCaseComplete.charAt(8) == 'C') {
                if ((index / 24) == 8) {
                    System.out.print("||||||Cavalier Noir|||||");
                } else {
                    String cavalierNoir = ("nnnnnnNNNNNNNNNNNNnnnnnnnnnNNNNNNNNNNNNNNNNNNnnnNNNNNNNNNNNNNNNNNNnnnnnnNNNNNNNNNnnnnnnnnnnnnnnnNNNNNNNNNnnnnnnnnnnnnnnnNNNNNNNNNNNNNNNNNNnnnnnnnnnNNNNNNNNNNNNNNNNNNnnnnnnnnnNNNNNNNNNNNNnnnnnn");
                    for (int i = 0; i < 24; i++) { // On répète le nombre de caractères à print


                        if (cavalierNoir.charAt(i + index) == 'n') {
                            System.out.print(background);
                        } else if (cavalierNoir.charAt(i + index) == 'N') {
                            System.out.print("[40m [0m"); // COuleur noir
                        } else if (cavalierNoir.charAt(i + index) == 'B') {
                            System.out.print("[107m [0m"); // couleur blanc
                        } else {
                            System.out.print("[42m [0m");// Couleur rouge pour Reine
                        }
                    }
                }
            } else if (convertTileEnCaseComplete.charAt(8) == 'b') {
                if ((index / 24) == 8) {
                    System.out.print("||||||||Fou Blanc|||||||");
                } else {
                    String fouBLanc = ("nnnnnnnnnNNNNNNnnnnnnnnnnnnnnnNNNBBBBBBNNNnnnnnnnnnNNNBBBBBBBBBnnnNNNnnnNNNBBBBBBBBBnnnNNNBBBNNNNNNBBBBBBBBBNNNBBBBBBNNNNNNBBBBBBBBBBBBBBBBBBNNNnnnNNNBBBBBBBBBBBBNNNnnnnnnnnnNNNBBBBBBNNNnnnnnn");
                    for (int i = 0; i < 24; i++) { // On répète le nombre de caractères à print


                        if (fouBLanc.charAt(i + index) == 'n') {
                            System.out.print(background);
                        } else if (fouBLanc.charAt(i + index) == 'N') {
                            System.out.print("[40m [0m"); // COuleur noir
                        } else if (fouBLanc.charAt(i + index) == 'B') {
                            System.out.print("[107m [0m"); // couleur blanc
                        } else {
                            System.out.print("[41m [0m");// Couleur rouge pour Reine
                        }
                    }
                }
            } else if (convertTileEnCaseComplete.charAt(8) == 'B') {
                if ((index / 24) == 8) {
                    System.out.print("||||||||Fou Noir||||||||");
                } else {
                    String fouNoir = ("nnnnnnnnnNNNNNNnnnnnnnnnnnnnnnNNNNNNNNNNNNnnnnnnnnnNNNNNNNNNNNNnnnNNNnnnNNNNNNNNNNNNnnnNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNnnnNNNNNNNNNNNNNNNNNNnnnnnnnnnNNNNNNNNNNNNnnnnnn");
                    for (int i = 0; i < 24; i++) { // On répète le nombre de caractères à print


                        if (fouNoir.charAt(i + index) == 'n') {
                            System.out.print(background);
                        } else if (fouNoir.charAt(i + index) == 'N') {
                            System.out.print("[40m [0m"); // COuleur noir
                        } else if (fouNoir.charAt(i + index) == 'B') {
                            System.out.print("[107m [0m"); // couleur blanc
                        } else {
                            System.out.print("[41m [0m");// Couleur rouge pour Reine
                        }
                    }
                }
            } else if (convertTileEnCaseComplete.charAt(8) == 'r') {
                if ((index / 24) == 8) {
                    System.out.print("||||||Tour Blanche||||||");
                } else {
                    String tourBlanche = ("nnnNNNnnnNNNNNNnnnNNNnnnnnnNNNnnnNNNNNNnnnNNNnnnnnnnnnNNNBBBBBBNNNnnnnnnnnnnnnNNNBBBBBBNNNnnnnnnnnnnnnNNNBBBBBBNNNnnnnnnnnnnnnNNNBBBBBBNNNnnnnnnnnnnnnNNNBBBBBBNNNnnnnnnnnnNNNNNNBBBBBBNNNNNNnnn");
                    for (int i = 0; i < 24; i++) { // On répète le nombre de caractères à print


                        if (tourBlanche.charAt(i + index) == 'n') {
                            System.out.print(background);
                        } else if (tourBlanche.charAt(i + index) == 'N') {
                            System.out.print("[40m [0m"); // COuleur noir
                        } else if (tourBlanche.charAt(i + index) == 'B') {
                            System.out.print("[107m [0m"); // couleur blanc
                        } else {
                            System.out.print("[41m [0m");// Couleur rouge pour Reine
                        }
                    }
                }
            } else if (convertTileEnCaseComplete.charAt(8) == 'R') {
                if ((index / 24) == 8) {
                    System.out.print("|||||||Tour Noir||||||||");
                } else {
                    String tourNoir = ("nnnNNNnnnNNNNNNnnnNNNnnnnnnNNNnnnNNNNNNnnnNNNnnnnnnnnnNNNNNNNNNNNNnnnnnnnnnnnnNNNNNNNNNNNNnnnnnnnnnnnnNNNNNNNNNNNNnnnnnnnnnnnnNNNNNNNNNNNNnnnnnnnnnnnnNNNNNNNNNNNNnnnnnnnnnNNNNNNNNNNNNNNNNNNnnn");
                    for (int i = 0; i < 24; i++) { // On répète le nombre de caractères à print


                        if (tourNoir.charAt(i + index) == 'n') {
                            System.out.print(background);
                        } else if (tourNoir.charAt(i + index) == 'N') {
                            System.out.print("[40m [0m"); // COuleur noir
                        } else if (tourNoir.charAt(i + index) == 'B') {
                            System.out.print("[107m [0m"); // couleur blanc
                        } else {
                            System.out.print("[41m [0m");// Couleur rouge pour Reine
                        }
                    }
                }
            } else if (convertTileEnCaseComplete.charAt(8) == 'k') {
                if ((index / 24) == 8) {
                    System.out.print("||||||||Roi Blanc|||||||");
                } else {
                    String reineBlanche = ("nnnNNNnnnNNNNNNnnnNNNnnnNNNBBBNNNBBBBBBNNNBBBNNNNNNBBBBBBRRRRRRBBBBBBNNNnnnNNNBBBRRRRRRBBBNNNnnnnnnnnnNNNBBBBBBNNNnnnnnnnnnnnnNNNBBBBBBNNNnnnnnnnnnNNNBBBBBBBBBBBBNNNnnnNNNNNNBBBBBBBBBBBBNNNNNN");
                    for (int i = 0; i < 24; i++) { // On répète le nombre de caractères à print


                        if (reineBlanche.charAt(i + index) == 'n') {
                            System.out.print(background);
                        } else if (reineBlanche.charAt(i + index) == 'N') {
                            System.out.print("[40m [0m"); // COuleur noir
                        } else if (reineBlanche.charAt(i + index) == 'B') {
                            System.out.print("[107m [0m"); // couleur blanc
                        } else {
                            System.out.print("[41m [0m");// Couleur rouge pour Reine
                        }
                    }
                }
            } else if (convertTileEnCaseComplete.charAt(8) == 'K') {
                if ((index / 24) == 8) {
                    System.out.print("||||||||Roi Noir||||||||");
                } else {
                    for (int i = 0; i < 24; i++) { // On répète le nombre de caractères à print
                        String reineNoir = ("nnnNNNnnnNNNNNNnnnNNNnnnNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNRRRRRRNNNNNNNNNnnnNNNNNNRRRRRRNNNNNNnnnnnnnnnNNNNNNNNNNNNnnnnnnnnnnnnNNNNNNNNNNNNnnnnnnnnnNNNNNNNNNNNNNNNNNNnnnNNNNNNNNNNNNNNNNNNNNNNNN");
                        if (reineNoir.charAt(i + index) == 'n') {
                            System.out.print(background);
                        } else if (reineNoir.charAt(i + index) == 'N') {
                            System.out.print("[40m [0m"); // COuleur noir
                        } else if (reineNoir.charAt(i + index) == 'B') {
                            System.out.print("[107m [0m"); // couleur blanc
                        } else {
                            System.out.print("[41m [0m");// Couleur rouge pour Reine
                        }
                    }
                }
            } else if (convertTileEnCaseComplete.charAt(8) == 'q') {
                if ((index / 24) == 8) {
                    System.out.print("||||||Reine Blanche|||||");
                } else {
                    String reineBlanche = ("RRRnnnRRRnnnnnnRRRnnnRRRNNNnnnNNNnnnnnnNNNnnnNNNNNNNNNnnnNNNNNNnnnNNNNNNnnnNNNNNNNNNNNNNNNNNNnnnnnnnnnNNNBBBBBBNNNnnnnnnnnnnnnNNNBBBBBBNNNnnnnnnnnnnnnNNNBBBBBBNNNnnnnnnnnnNNNBBBBBBBBBBBBNNNnnn");
                    for (int i = 0; i < 24; i++) { // On répète le nombre de caractères à print
                        if (reineBlanche.charAt(i + index) == 'n') {
                            System.out.print(background);
                        } else if (reineBlanche.charAt(i + index) == 'N') {
                            System.out.print("[40m [0m"); // COuleur noir
                        } else if (reineBlanche.charAt(i + index) == 'B') {
                            System.out.print("[107m [0m"); // couleur blanc
                        } else {
                            System.out.print("[41m [0m");// Couleur rouge pour Reine
                        }
                    }
                }
            } else if (convertTileEnCaseComplete.charAt(8) == 'Q') {


                if ((index / 24) == 8) {
                    System.out.print("|||||||Reine Noir|||||||");
                } else {


                    for (int i = 0; i < 24; i++) { // On répète le nombre de caractères à print
                        String reineNoir = ("RRRnnnRRRnnnnnnRRRnnnRRRNNNnnnNNNnnnnnnNNNnnnNNNNNNNNNnnnNNNNNNnnnNNNNNNnnnNNNNNNNNNNNNNNNNNNnnnnnnnnnNNNNNNNNNNNNnnnnnnnnnnnnNNNNNNNNNNNNnnnnnnnnnnnnNNNNNNNNNNNNnnnnnnnnnNNNNNNNNNNNNNNNNNNnnn");
                        if (reineNoir.charAt(i + index) == 'n') {
                            System.out.print(background);
                        } else if (reineNoir.charAt(i + index) == 'N') {
                            System.out.print("[40m [0m"); // COuleur noir
                        } else if (reineNoir.charAt(i + index) == 'B') {
                            System.out.print("[107m [0m"); // couleur blanc
                        } else {
                            System.out.print("[41m [0m");// Couleur rouge pour Reine
                        }
                    }
                }


            }




        }




        return "";
    }


    // FOnction qui prend un caractère d'une pièce ex :'R' et qui renvoit le caractère UTF-8 de la tour noir
    public static char determinCharPiecePourAffichage(char piece) {
        char caraUTF = ' ';
        if (piece == 'X') {
            return caraUTF;
        } else if (piece == 'p') {
            caraUTF = '♟';
        } else if (piece == 'P') {
            caraUTF = '♙';
        } else if (piece == 'c') {
            caraUTF = '♞';
        } else if (piece == 'C') {
            caraUTF = '♘';
        } else if (piece == 'b') {
            caraUTF = '♝';
        } else if (piece == 'B') {
            caraUTF = '♗';
        } else if (piece == 'r') {
            caraUTF = '♜';
        } else if (piece == 'R') {
            caraUTF = '♖';
        } else if (piece == 'q') {
            caraUTF = '♛';
        } else if (piece == 'Q') {
            caraUTF = '♕';
        } else if (piece == 'k') {
            caraUTF = '♚';
        } else {
            caraUTF = '♔';
        }
        return caraUTF;


    }




    // TEst d'affichages
    public static void main(String[] args) {
        // INVERSER LES COULEURS PR LE TERMINAL
        System.out.println("♔ ROI BLANC => ROB = k");
        System.out.println("♕ REINE BLANCHE => REB q");
        System.out.println("♖ TOUR BLANCHE => TB = r");  // FAIT
        System.out.println("♗ FOU BLANC => FB = b"); // FAIT
        System.out.println("♘ CAVALIER BLANC => CB = c"); // FAIT
        System.out.println("♙ PION BLANC => PB = p"); // FAIT
        System.out.println("♚ ROI NOIR => RON = K");
        System.out.println("♛ REINE NOIR => REN = Q");
        System.out.println("♜ TOUR NOIR => TN = R");  // FAIT
        System.out.println("♝ FOU NOIR => FN = B");  // FAIT
        System.out.println("♞ CAVALIER NOIR => CN =C"); // FAIT
        System.out.println("♟ PION NOIR => PN = P"); // FAIT
        System.out.println(" INVERSER COULEURS ");
    }


}

