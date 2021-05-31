package tests;
import composants.Piece;
import composants.Utils;
import grafix.interfaceGraphique.IG;

public class TestPiece {
    public static void main(String[] args) {
		// Une petite démonstration conernant l'interface graphique

	
		// différents paramétres par défaut
		Object parametres[];
		parametres=IG.saisirParametres(); // On ouvre la fenêtre de paramétrage pour la saisie
		
		// Création de la fenêtre de jeu et affichage de la fenêtre 
		int nbJoueurs=((Integer)parametres[0]).intValue(); // Récupération du nombre de joueurs
		IG.creerFenetreJeu("Test Pieces",nbJoueurs); // On crée la fenêtre
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
		
		//Initialisation du plateau.
		int x = 0;
		Piece[] tab = Piece.nouvellesPieces();
		for (int i=0;i<50;i++){
			tab[i].setOrientation(Utils.genererEntier(3));
		}
		for (int i=0; i<7; i++){
			for (int j=0; j<7; j++){
				IG.changerPiecePlateau(i, j, tab[x].getModelePiece(), tab[x].getOrientationPiece());
				System.out.println(tab[x].toString());
				x++;
			}
		}
		IG.changerPieceHorsPlateau(tab[x].getModelePiece(), tab[x].getOrientationPiece());
		System.out.println(tab[x].toString());
		IG.miseAJourAffichage();

		//Modification du plateau avec une rotation de chaque piece par clic.
		x=0;
		for (int k=0; k<4;k++){
			IG.attendreClic();
			//Boucle de rotation de chaque piece dans le tableau.
			for (int i=0; i<50; i++){
					tab[i].rotation();
			}
			x=0;
			//Changement des pieces dans le plateau avec les nouvelles orientations.
			for (int i=0; i<7; i++){
				for (int j=0; j<7; j++){
					System.out.println(tab[x].toString());
					IG.changerPiecePlateau(i, j, tab[x].getModelePiece(), tab[x].getOrientationPiece());
					x++;
				}
			}
			IG.changerPieceHorsPlateau(tab[x].getModelePiece(), tab[x].getOrientationPiece());
			System.out.println(tab[x].toString());
			IG.miseAJourAffichage();
		}
		IG.attendreClic();
		IG.fermerFenetreJeu();
		System.exit(0);
	}
}