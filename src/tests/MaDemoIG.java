package tests;
import grafix.interfaceGraphique.IG;

public class MaDemoIG {
    public static void main(String[] args) {
		// Une petite démonstration conernant l'interface graphique

	
		// différents paramétres par défaut
		Object parametres[];
		parametres=IG.saisirParametres(); // On ouvre la fenêtre de paramétrage pour la saisie
		
		// Création de la fenêtre de jeu et affichage de la fenêtre 
		int nbJoueurs=((Integer)parametres[0]).intValue(); // Récupération du nombre de joueurs
		IG.creerFenetreJeu("Démo Librairie IG version 1.9",nbJoueurs); // On crée la fenêtre
		IG.rendreVisibleFenetreJeu();  // On rend visible la fenêtre de jeu
		
		IG.jouerUnSon(2); // On joue le son numéro 2
		IG.pause(300); // On attend 300 ms
		IG.jouerUnSon(2); // On joue de nouveau le son numéro 2
		
		// Affichage d'un message
		String message[]={
					"",
					"Bonjour !",
					"Cliquer pour continuer ...",
					""
		};
		IG.afficherMessage(message); // On change de message de la fenêtre de jeu
		IG.miseAJourAffichage(); // On effectue le rafraichissement de la fenêtre de jeu
		
		
		// Changement des caractéristiques du premier joueur avec les paramétres saisis
		int numImageJoueur0=((Integer)parametres[3]).intValue();
		String nomJoueur0=(String)parametres[1];
		String categorieJoueur0=(String)parametres[2];
		IG.changerNomJoueur(0, nomJoueur0+" ("+categorieJoueur0+")");
		IG.changerImageJoueur(0,numImageJoueur0);
		IG.miseAJourAffichage();
		

		// Piece modele 2 orientation 0
		for (int i=0; i<7; i++){
			for (int j=0; j<7; j++){
				IG.changerPiecePlateau(i, j, 2, 0);
			}
		}
		// Change la piéce hors du plateau
		IG.changerPieceHorsPlateau(1,0);
		IG.miseAJourAffichage();
		
		// Changement d'objets au premier joueur et second joueur

		if (nbJoueurs==2){
			for (int i=0;i<9;i++){
				IG.changerObjetJoueur(0,i,i);
				IG.changerObjetJoueur(1,i+9,i);
			}
		}else{
			for (int i=0;i<6;i++){
				IG.changerObjetJoueur(0,i,i);
				IG.changerObjetJoueur(1,i+6,i);
				IG.changerObjetJoueur(2,i+12,i);
			}
		}
		IG.miseAJourAffichage();

		
		// Place des objets sur le plateau
		int numObjet=0;
		for (int i=0;i<7;i++)
			for (int j=0;j<7;j++){
				if (numObjet<18)
					IG.placerObjetPlateau((numObjet++)%18,i,j);
			}
		IG.miseAJourAffichage();
		
		
		// Place tous les joueurs sur le plateaux
	

		
		IG.placerJoueurPrecis(0, 3, 0, 1, 0);
		IG.placerJoueurPrecis(1, 3, 6, 1, 2);
		IG.miseAJourAffichage();
		IG.attendreClic();

		// modification 4 clic piece, joueur, bille
		for (int x = 1; x<5;x++){
			for (int i=0; i<7; i++){
				for (int j=0; j<7; j++){
					if (x==4){
						IG.changerPiecePlateau(i, j, 2, 0);

					}else{
						IG.changerPiecePlateau(i, j, 2, x);
					}
				}
			}
			if (x == 4){
				IG.placerBilleSurPlateau(3,1,1,0,0);
				IG.placerBilleSurPlateau(3,5,1,2,0);
				IG.changerPieceHorsPlateau(1, 0);
				IG.placerJoueurPrecis(0, 3, 1, 1, 1);
				IG.placerJoueurPrecis(1, 3, 5, 1, 1);
			}else if (x==3){
				IG.placerBilleSurPlateau(3,0,1,x-1,0);
				IG.placerBilleSurPlateau(3,6,1,0,0);
				IG.changerPieceHorsPlateau(1, 1);
				IG.placerJoueurPrecis(0, 3, 1, 1, 0);
				IG.placerJoueurPrecis(1, 3, 5, 1, 2);
			}else if (x==2){
				IG.placerBilleSurPlateau(3,0,1,x-1,0);
				IG.placerBilleSurPlateau(3,6,1,x-1,0);
				IG.changerPieceHorsPlateau(1, 0);
				IG.placerJoueurPrecis(0, 3, 0, 1, x);
				IG.placerJoueurPrecis(1, 3, 6, 1, 0);
			}else{
				IG.placerBilleSurPlateau(3,0,1,x-1,0);
				IG.placerBilleSurPlateau(3,6,1,x+1,0);
				IG.changerPieceHorsPlateau(1, 1);
				IG.placerJoueurPrecis(1, 3, 6, 1, x);
				IG.placerJoueurPrecis(0, 3, 0, 1, x);
			}
			String messageClic[]={
				"",
				"Après le clic " + x ,
				"Cliquer pour continuer ...",
				""
			};
			IG.afficherMessage(messageClic);
			IG.miseAJourAffichage();
			IG.attendreClic();
		}
	
		// afficher le joueur 1 comme gagant
		String messageFin[]={
			"",
			"Cliquer sur une flèhe",
			"pour quitter",
			""
		};
		IG.afficherMessage(messageFin);
		IG.afficherGagnant(0);
		IG.miseAJourAffichage();

		// Attente d'un clic sur une entrée
		int entree=IG.attendreChoixEntree();
		System.out.println("Entrée : "+entree);
		
		// Attente d'un clic sur une piéce
		message[0]="";
		message[1]="Arrêt du programme ";
		message[2]="dans 2 secondes ";
		message[3]="";	
		IG.afficherMessage(message);
		IG.miseAJourAffichage();
		IG.pause(2000);
		IG.fermerFenetreJeu();
		System.exit(0);
	}
}
