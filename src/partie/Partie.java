package partie;

import composants.Objet;
import composants.Piece;
import composants.Plateau;
import grafix.interfaceGraphique.IG;
import joueurs.Joueur;
import joueurs.JoueurOrdinateur;

public class Partie {
	static double version=0.0;

		for(int j= 0; j<(tabObjet.length/nbJoueurs); j++ ){
			IG.changerObjetJoueur(0,joueurs[0].getObjetsJoueur()[j].getNumeroObjet(), j);
			IG.changerObjetJoueur(1,joueurs[1].getObjetsJoueur()[j].getNumeroObjet(), j);
			IG.changerObjetJoueur(2,joueurs[2].getObjetsJoueur()[j].getNumeroObjet(), j);
		}

	private ElementsPartie elementsPartie; // Les éléments de la partie.

	/**
	 * 
	 * A Faire (01/06/2021 EH Finalisée)
	 * 
	 * Constructeur permettant de créer et d'initialiser une nouvelle partie.
	 */
	public Partie(){
		// Initialisation de la partie
		parametrerEtInitialiser();

		// On affiche l'ensemble des éléments
		
        //creation et mise en place des pieces du plateau
		for (int i=0; i<7; i++){
			for (int j=0; j<7; j++){
				IG.changerPiecePlateau(i, j, elementsPartie.getPlateau().getPiece(i,j).getModelePiece(), elementsPartie.getPlateau().getPiece(i,j).getOrientationPiece());
			}
		}
		IG.changerPieceHorsPlateau(elementsPartie.getPieceLibre().getModelePiece(), elementsPartie.getPieceLibre().getOrientationPiece());
		
		// creation et placement des joueurs
		int nbJoueurs = elementsPartie.getNombreJoueurs();
		Joueur[] joueurs = elementsPartie.getJoueurs();
		
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

		Objet[] tabObjet = elementsPartie.getObjets();
		
		for (int i = 0; i<tabObjet.length; i++){
			IG.placerObjetPlateau(tabObjet[i].getNumeroObjet(),tabObjet[i].getPoslePlateau(), tabObjet[i].getPosconnePlateau());
		}

		for(int j= 0; j<(tabObjet.length/nbJoueurs); j++ ){
			IG.changerObjetJoueur(0,joueurs[0].getObjetsJoueur()[j].getNumeroObjet(), j);
			IG.changerObjetJoueur(1,joueurs[1].getObjetsJoueur()[j].getNumeroObjet(), j);
			IG.changerObjetJoueur(2,joueurs[2].getObjetsJoueur()[j].getNumeroObjet(), j);
		}

		IG.rendreVisibleFenetreJeu();
	}

	/**
	 * Méthode permettant de paramètrer et initialiser les éléments de la partie.
	 */
	private void parametrerEtInitialiser(){
		// Saisie des différents paramètres
		Object parametresJeu[];
		parametresJeu=IG.saisirParametres();
		int nombreJoueurs=((Integer)parametresJeu[0]).intValue();
		IG.creerFenetreJeu("- Version "+version, nombreJoueurs);
		// Création des joueurs
		Joueur joueurs[]=Joueur.nouveauxJoueurs(parametresJeu);
		// Création des éléments de la partie
		elementsPartie=new ElementsPartie(joueurs);
	}


	/**
	 * 
	 * A Faire (02/06/2021 SA/IB/AR EnCours)
	 * 
	 * Méthode permettant de lancer une partie.
	 */
	public void lancer(){
		int nbJoueurs = elementsPartie.getNombreJoueurs();
		Joueur[] joueurs = elementsPartie.getJoueurs();
		Objet[] tabObjet = elementsPartie.getObjets();
		while (joueurs[0].getNombreObjetsRecuperes()!= tabObjet.length/nbJoueurs && joueurs[1].getNombreObjetsRecuperes()!= tabObjet.length/nbJoueurs && joueurs[2].getNombreObjetsRecuperes()!= tabObjet.length/nbJoueurs){
			
		}
	}

	/**
	 * 
	 * Programme principal permettant de lancer le jeu.
	 * 
	 * @param args Les arguments du programmes.
	 */
	public static void main(String[] args) {
		while(true){
			Partie partie=new Partie();
			partie.lancer();
		}
	}

}
