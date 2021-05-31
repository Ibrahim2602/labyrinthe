package tests;
import composants.Plateau;
import composants.Piece;
import grafix.interfaceGraphique.IG;

    /**
	 * 
	 * Cette classe permet de tester les differentes méthode crée pour la realisation du jeu.
	 * 
	 */

public class TestPlateau {
    /**
	 * 
	 * Methode main permetants d'afficher et tester une partie du jeu realisé.
	 * 
	 * 
	 */
    public static void main(String[] args) {
		// Une petite démonstration conernant l'interface graphique

	
		// différents paramétres par défaut
		Object parametres[];
		parametres=IG.saisirParametres(); // On ouvre la fenêtre de paramétrage pour la saisie
		
		// Création de la fenêtre de jeu et affichage de la fenêtre 
		int nbJoueurs=((Integer)parametres[0]).intValue(); // Récupération du nombre de joueurs
		IG.creerFenetreJeu("Test Plateau",nbJoueurs); // On crée la fenêtre
		IG.rendreVisibleFenetreJeu();  // On rend visible la fenêtre de jeu

        		// Affichage d'un message
		String message[]={
            "",
            "Cliquer pour continuer ...",
            ""
        };
        IG.afficherMessage(message); // On change de message de la fenêtre de jeu
        IG.miseAJourAffichage(); // On effectue le rafraichissement de la fenêtre de jeu
		IG.attendreClic();

        Plateau plateau = new Plateau();
        Piece pieceHorsPlateau=plateau.placerPiecesAleatoierment();
		for (int i=0; i<7; i++){
			for (int j=0; j<7; j++){
				IG.changerPiecePlateau(i, j, plateau.getPiece(i,j).getModelePiece(), plateau.getPiece(i,j).getOrientationPiece());
			}
		}
        IG.changerPieceHorsPlateau(pieceHorsPlateau.getModelePiece(), pieceHorsPlateau.getOrientationPiece());
        IG.miseAJourAffichage();
        int maxi=0;
        System.out.println("La liste des chemins trouvés à partir de la case (3,3) :");
        System.out.println();
        for (int i=0;i<7;i++){
            for (int j=0;j<7;j++){
                int[][] resultat = plateau.calculeChemin(3, 3, i, j);
                if (plateau.calculeChemin(3, 3, i, j)!=null){
                    System.out.print("\nChemin entre la case (3,3) et (" + i + "," + j +") : ");
                    for(int n = 0; n < resultat.length; n++) {
                        System.out.print("("+resultat[n][0]+"," +resultat[n][1] + ")" );
                    }
                    if(resultat.length>maxi){
                        maxi=resultat.length;
                    }
                }
            }
        }
        for (int i=0;i<7;i++){
            for (int j=0;j<7;j++){
                int[][] resultat = plateau.calculeChemin(3, 3, i, j);
                if (plateau.calculeChemin(3, 3, i, j)!=null && resultat.length==maxi){
                    for (int k=0; k<resultat.length;k++){
                        IG.placerBilleSurPlateau(resultat[k][0], resultat[k][1], 1, 1, 0);
                    }
                    break;
                }
            }
        }
        IG.attendreClic();
        IG.miseAJourAffichage();
        IG.attendreClic();
        IG.fermerFenetreJeu();
		System.exit(0);
    }
}
