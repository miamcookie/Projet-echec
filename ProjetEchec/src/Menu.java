import java.util.Scanner;




public class Menu {
    // Ici on verra tyout ce qui est lié au menu principal du jeu et à la boucle du jeu, pour gérer tout ce qui n'est pas directement le jeu


    public static void menuLogo() {
        System.out.println("""
                              
                                  _                  \s
                              ___| |__   ___  ___ ___\s
                             / __| '_ \\ / _ \\/ __/ __|
                            | (__| | | |  __/\\__ \\__ \\
                             \\___|_| |_|\\___||___/___/
                            
                            
                   Bienvenue sur notre projet de jeu d'échec, pour commencer,
                   appuyez sur le numéro correspondant au choix souhaité puis
                   cliquez sur "entrer" pour valider.
                  
                   1) Tutoriel & Règles
                  
                   2) Joueur Contre Joueur (local)
                  
                   3) Joueur Contre Robot (En développement)
                  
                   4)Sélection Affichage ( Important)
                  
                   5) Quitter le jeu
               """);
    }




    public static void menuChoix() {
        Scanner scanner = new Scanner(System.in);
        String choix = "0";


        do {
            Menu.menuLogo();
            switch (choix) {
                case "1":
                    System.out.println("tutoriel");
                    menu1Tutoriel();
                    System.out.println("\n\n\n\n");
                    Menu.menuLogo();
                    break;


                case "2":
                    System.out.println("lancement jcj");
                    Deroulement.lancerPartie();
                    break;
                case "3":
                    System.out.println("lancement jcr");


                    break;
                case "4":
                    System.out.println("Paramètres affichage");
                    menu4Affichage();
                    break;
                case "5":
                    System.out.println("sortir du jeu");
                    break;
            }
            choix = scanner.nextLine();
        }
        while ((int) choix.charAt(0) != 53);
    }


    public static void menu1Tutoriel() {
        System.out.println("""
               Bienvenue dans le tutoriel de ce programme d'échec, vous verrez sur cette page comment jouer, quelles sont les règles et autre.
                              
                              
               Tout d'abord, pour naviguer entre les menus, vous devrez appuyer sur le numéro  correspondant à votre requête puis sur entrer.
                              
                             
               Le jeu d'échec est un jeu de plateau composé de 8 cases par 8 cases toutes numérotés avec des coordonnés de a à h et de 1 à 8.
               Pour faire une demande de coup, vous devrez suivre  la syntaxe qui suit:  pièceàdéplacer,casededépart,cased'arrivée
                                                                              
                                                         ce qui peut donner comme coup:  "ra1a8" ou encore: "Ke8e7"
                        ici \'r\' est utilisé pour désigner une pièce sur le plateau, la tour (rook en anglais) 
                        et la case de départ est a1 (tout en bas à gauche du plateau)
                        et la case d'arrivée est a8 (tout en haut à gauche du plateau)  
                       
                        voici les lettres associées à chaques pièces
                        r = tour blanche
                        c = cavalier
                        b = fou
                        q = reine
                        k = roi
                        p = pion
                        Il faut mettre les lettres en majuscule pour désigner les piècees noirs
                       
                        L'objectif des échecs est de capturer le roi adverse, toutes les pièces ont des déplacements associés,
                        le pion: il peut avancer de 2 cases au premier coup si il veut puis d'une case à chaques coups, il mange sur les deux cases en diagonale devant lui il ne peut pas reculer.
                        le cavalier: le cavalier se déplace en forme de L, il peut avancer d'une colonne et de deux lignes ou inversement il peut sauter les pièces devant lui.
                        le fou: se déplace en diagonale, au début de la partie, chaques joueur possède deux fou, un sur chaques couleurs de cases.
                        la tour: la tour se déplace devant, derrière et sur les côtés sans aucune limite de cases parcourues.
                        la reine: se déplace comme un fou mélangé avec une tour, elle peut quasiment aller partout sur le plateau, c'est une pièce importante.
                        le roi: il se déplace comme la reine mais uniquement d'une case.
                       
                        Règles spécifiques:
                        -règle de promotion: un pion atteignant la dernière case de sa colonne ne pouvant plus bouger va devenir une autre pièce du choix du joueur.
                       
                       
               """);


    }


    public static boolean menu4Affichage() {
        System.out.println("""
                       Pour l'affichage, vous devrez choisir entre deux modes de fonctionnement
                       soit en pixel art (Grand écran conseillé et jouer en plein écran)
                       ou alors en affichage réduit ce qui peut parfois conduire à un manque de lisibilité.
                      
                      
               """);
        System.out.print("""
               [42m   [0m[42m   [0m[42m   [0m[42m   [0m[42m   [0m[42m   [0m[42m   [0m[42m   [0m
               [42m   [0m[42m   [0m[42m   [0m[42m   [0m[42m   [0m[42m   [0m[42m   [0m[42m   [0m
               [42m   [0m[42m   [0m[42m   [0m[40m   [0m[40m   [0m[42m   [0m[42m   [0m[42m   [0m
               [42m   [0m[42m   [0m[40m   [0m[107m   [0m[107m   [0m[40m   [0m[42m   [0m[42m   [0m
               [42m   [0m[42m   [0m[40m   [0m[107m   [0m[107m   [0m[40m   [0m[42m   [0m[42m   [0m
               [42m   [0m[42m   [0m[42m   [0m[40m   [0m[40m   [0m[42m   [0m[42m   [0m[42m   [0m
               [42m   [0m[42m   [0m[40m   [0m[107m   [0m[107m   [0m[40m   [0m[42m   [0m[42m   [0m
               [42m   [0m[40m   [0m[107m   [0m[107m   [0m[107m   [0m[107m   [0m[40m   [0m[42m   [0m
               Voici un exemple de case en pixel art
               """);
        int colonne = 8;
        int ligne = 8;


        String[][] plateau = new String[ligne][colonne];




        MiseEnPlaceJeu.remiseAZeroTableau(plateau);
        MiseEnPlaceJeu.créerPlateau(plateau);
        MiseEnPlaceJeu.miseEnPlacePlateau(plateau);
        Affichage.printAffichage(plateau, false);
        System.out.println("Et voici un exemple en Affichage réduit");
        System.out.println("Entrez 3 Pour quitter la sélection de l'afficahge");
        boolean affichageChoisi = false;
        Scanner sc = new Scanner(System.in);
        int choix = 0;
        do {
            choix = sc.nextInt();
            if (choix == 2) {
                affichageChoisi = false;
                System.out.println("Affichage définit en mode réduit");
                break;
            } else {
                affichageChoisi = true;
                System.out.println("Affichage définit en mode pixel art");
                break;
            }


        } while (choix != 3);
        return affichageChoisi;
    }


}

