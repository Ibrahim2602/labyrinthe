package tests;
import joueurs.Joueur;
import composants.Plateau;
import composants.Piece;
import grafix.interfaceGraphique.IG;

public class TestJoueur {
    public static void main(String[] args) {
		// Une petite démonstration conernant l'interface graphique

	
		// différents paramétres par défaut
		Object parametres[];
		parametres=IG.saisirParametres(); // On ouvre la fenêtre de paramétrage pour la saisie
		
		// Création de la fenêtre de jeu 
		int nbJoueurs=((Integer)parametres[0]).intValue(); // Récupération du nombre de joueurs
		IG.creerFenetreJeu("Test Joueur",nbJoueurs); // On crée la fenêtre
        //creation et mise en place des pieces du plateau
        Plateau plateau = new Plateau();
        Piece pieceHorsPlateau=plateau.placerPiecesAleatoierment();
		for (int i=0; i<7; i++){
			for (int j=0; j<7; j++){
				IG.changerPiecePlateau(i, j, plateau.getPiece(i,j).getModelePiece(), plateau.getPiece(i,j).getOrientationPiece());
			}
		}
        IG.changerPieceHorsPlateau(pieceHorsPlateau.getModelePiece(), pieceHorsPlateau.getOrientationPiece());
        
        // creation et placement des joueurs
        Joueur joueurs[]=Joueur.nouveauxJoueurs(parametres);
        if (nbJoueurs==2){
            joueurs[0].setPosition(0, 0);
            IG.placerJoueurSurPlateau(0, 0, 0);
            joueurs[1].setPosition(0, 6);
            IG.placerJoueurSurPlateau(1, 0, 6);
        }else{
            joueurs[0].setPosition(0, 0);
            IG.placerJoueurSurPlateau(0, 0, 0);
            joueurs[1].setPosition(0, 6);
            IG.placerJoueurSurPlateau(1, 0, 6);
            joueurs[2].setPosition(6, 6);
            IG.placerJoueurSurPlateau(2, 6, 6);
        }
        // mise en place des image/nom/categorie des joueurs
        for (int i=0; i<nbJoueurs;i++){
            int numImageJoueur=(joueurs[i].getNumeroImagePersonnage());
            String nomJoueur=(joueurs[i].getNomJoueur());
            String categorieJoueur=(joueurs[i].getCategorie());
            IG.changerNomJoueur(i, nomJoueur+" ("+categorieJoueur+")");
            IG.changerImageJoueur(i,numImageJoueur);
        }
        

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
        for (int i=0; i<nbJoueurs;i++){
            if(joueurs[i].getCategorie()=="ordinateur"){
                IG.placerBilleSurPlateau(joueurs[i].getPosLigne(), joueurs[i].getPosColonne(), 1, 1, 0);
            }else{
                String messageJoueur[]={
                    "",
                    "Au tour de " + joueurs[i].getNomJoueur(),
                    "Selectionner une case ...",
                    ""
                };
                IG.afficherMessage(messageJoueur);
                IG.miseAJourAffichage();
                int[] casArr = new int[2];
                casArr = joueurs[i].choisirCaseArrivee(null);

                int[][] resultat = plateau.calculeChemin(joueurs[i].getPosLigne(), joueurs[i].getPosColonne(), casArr[0], casArr[1]);
                while(resultat==null){
                    casArr = joueurs[i].choisirCaseArrivee(null);
                    resultat = plateau.calculeChemin(joueurs[i].getPosLigne(), joueurs[i].getPosColonne(), casArr[0], casArr[1]);
                }
                for (int x=0;x<7;x++){
                    for (int j=0;j<7;j++){
                        if (resultat!=null){
                            IG.placerJoueurSurPlateau(joueurs[i].getNumJoueur(), resultat[resultat.length-1][0], resultat[resultat.length-1][1]);
                            for (int k=0; k<resultat.length;k++){
                                IG.placerBilleSurPlateau(resultat[k][0], resultat[k][1], 1, 1, i);
                            }
                            break;
                        }
                    }
                }
            }
        }
        String messageFin[]={
            "",
            "C'est terminer !",
            "Cliquer pour quitter ...",
            ""
        };
        IG.afficherMessage(messageFin);
        IG.miseAJourAffichage();
        IG.attendreClic();
        IG.fermerFenetreJeu();
		System.exit(0);

    }
}