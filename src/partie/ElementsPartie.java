package partie;

import composants.Objet;
import composants.Piece;
import composants.Plateau;
import composants.Utils;
import joueurs.Joueur;

/**
 * 
 * Cette classe permet de représenter un ensemble d'élements composant une partie de jeu.
 * 
 */
public class ElementsPartie {

	private Joueur[] joueurs; 	// Les joueurs de la partie.
	private Objet[] objets; 	// Les 18 objets de la partie dans l'ordre de leurs numéros.
	private Plateau plateau; 	// Le plateau des pièces.
	private Piece pieceLibre; 	// La pièce libre.
	private int nombreJoueurs; 	// Le nombre de joueurs.

	/**
	 * 
	 * A Faire (30/05/2021 EH Finalisée)
	 *  
	 * Constructeur permettant de générer et d'initialiser l'ensemble des éléments d'une partie (sauf les joueurs qui sont donnés en paramètres).
	 * 
	 * Un plateau est créé en placant 49 pièces de manière aléatoire (utilisation de la méthode placerPiecesAleatoierment de la classe Plateau).
	 * La pièce restante (celle non présente sur le plateau) est affectée à  la pièce libre.
	 * Les 18 objets sont créés avec des positions aléatoires sur le plateau (utilisation de la méthode Objet.nouveauxObjets)
	 * et distribuées aux différents joueurs (utilisation de la méthode attribuerObjetsAuxJoueurs).
	 * 
	 * @param joueurs Les joueurs de la partie. Les objets des joueurs ne sont pas encore attribués (c'est au constructeur de le faire).
	 */
	public ElementsPartie(Joueur[] joueurs) {
		this.joueurs=joueurs;
		nombreJoueurs=joueurs.length;
		plateau = new Plateau();
        pieceLibre=plateau.placerPiecesAleatoierment();
		objets = Objet.nouveauxObjets();
		attribuerObjetsAuxJoueurs();
	}

	/**
	 * Un simple constructeur.
	 * 
	 * @param joueurs Les joueurs de la partie.
	 * @param objets Les 18 objets de la partie.
	 * @param plateau Le plateau de jeu.
	 * @param pieceLibre La pièce libre (la pièce hors plateau).
	 */
	public ElementsPartie(Joueur[] joueurs,Objet[] objets,Plateau plateau,Piece pieceLibre) {
		this.joueurs=joueurs;
		nombreJoueurs=joueurs.length;
		this.objets=objets;
		this.plateau=plateau;
		this.pieceLibre=pieceLibre;
	}

	/**
	 * A Faire (30/05/2021 EH Finalisée)
	 * 
	 * Méthode permettant d'attribuer les objets aux différents joueurs de manière aléatoire.
	 */
	private void attribuerObjetsAuxJoueurs(){
		int nbObjets=18/nombreJoueurs; // objets par joueurs
		int nbObjetsattribuer = 0; // objets attribuer aux joueurs
		Objet[] objetJoueur = new Objet[nbObjets];
		int tabal[] = Utils.genereTabIntAleatoirement(18);
		// boucle pour attribuer les objets aux joueurs 
		for (int k=0; k<nombreJoueurs; k++){
			while (nbObjetsattribuer<nbObjets){
				for (int i=0; i<nbObjets;i++){
					objetJoueur[i]=objets[tabal[i+(k*nbObjets)]];
				}
				joueurs[k].setObjetsJoueur(objetJoueur);
				nbObjetsattribuer++;
			}
			objetJoueur = new Objet[nbObjets];
			nbObjetsattribuer = 0;
		}

		for(int j= 0; j<(nbObjets); j++ ){
			System.out.print("joueur 1" + ","+joueurs[0].getObjetsJoueur()[j].getNumeroObjet()+" ,,,,");
		}
		for(int j= 0; j<(nbObjets); j++ ){
			System.out.print("joueur 2 " + ","+joueurs[1].getObjetsJoueur()[j].getNumeroObjet()+" ,,,,");
		}
		for(int j= 0; j<(nbObjets); j++ ){
			System.out.print("joueur 3" + ","+joueurs[2].getObjetsJoueur()[j].getNumeroObjet()+" ,,,,");
		}
	}

	/**
	 * A Faire (30/05/2021 EH Finalisée)
	 * 
	 * Méthode permettant de récupérer les joueurs de la partie.
	 * @return Les joueurs de la partie.
	 */
	public Joueur[] getJoueurs() {
		return joueurs;
	}


	/**
	 * A Faire (30/05/2021 EH Finalisée)
	 * 
	 * Méthode permettant de récupérer les pièces de la partie.
	 * @return Les objets de la partie.
	 */
	public Objet[] getObjets() {
		return objets;
	}


	/**
	 * A Faire (Quand Qui Statut)
	 * 
	 * Méthode permettant de récupérer le plateau de pièces de la partie.
	 * @return Le plateau de pièces.
	 */
	public Plateau getPlateau() {
		return plateau;
	}


	/**
	 * A Faire (30/05/2021 EH Finalisée)
	 * 
	 * Méthode permettant de récupérer la pièce libre de la partie.
	 * @return La pièce libre.
	 */
	public Piece getPieceLibre() {
		return pieceLibre; 
	}


	/**
	 * A Faire (30/05/2021 EH Finalisée)
	 * 
	 * Méthode permettant de récupérer le nombre de joueurs de la partie.
	 * @return Le nombre de joueurs.
	 */
	public int getNombreJoueurs() {
		return nombreJoueurs; 
	}


	/**
	 * A Faire (30/05/2021 EH Finalisée)
	 * 
	 * Méthode modifiant les différents éléments de la partie suite à  l'insertion de la pièce libre dans le plateau.
	 * 
	 * @param choixEntree L'entrée choisie pour réaliser l'insertion (un nombre entre 0 et 27).
	 */
	public void insertionPieceLibre(int choixEntree){
		int cpt=0;
		if (choixEntree == 21 || choixEntree == 14)
			cpt=6;
		if (choixEntree == 22 || choixEntree == 15)
			cpt=5;
		if (choixEntree==23 || choixEntree == 16)
			cpt=4;
		if (choixEntree==25 || choixEntree == 18)
			cpt=-4;
		if (choixEntree == 26 || choixEntree == 19)
			cpt=-5;
		if (choixEntree == 27 || choixEntree == 20)
			cpt=-6;
		// Piece hors plateau va en haut à la colone choixEntree
		if (choixEntree<7){
            Piece save = plateau.getPiece(6, choixEntree);
            for (int i=1; i<6; i++){
                plateau.positionnePiece(plateau.getPiece(i, choixEntree), i+1, choixEntree);
            }
            plateau.positionnePiece(pieceLibre, 0, choixEntree);
            pieceLibre= save;
        // Piece hors plateau va à droite à la ligne choixEntree-7
        }else if(choixEntree<14){
            Piece save = plateau.getPiece(choixEntree-7, 0);
            for (int i=6; i>0; i--){
                plateau.positionnePiece(plateau.getPiece(choixEntree-7, i), choixEntree-7,i-1 );
            } 
            plateau.positionnePiece(pieceLibre, choixEntree-7, 0);
            pieceLibre= save;
        // Piece hors plateau va en bas à la colone choixEntree-14
        }else if (choixEntree<21){

            Piece save = plateau.getPiece(6, choixEntree-14+cpt);
            for (int i=6; i>0; i--){
                plateau.positionnePiece(plateau.getPiece(i, choixEntree-14+cpt), i-1, choixEntree-14+cpt);
            } 
            plateau.positionnePiece(pieceLibre, 0, choixEntree-14+cpt);
            pieceLibre= save;
        // Piece hors plateau va à gauche à la ligne choixEntree-21
        }else{

            Piece save = plateau.getPiece(choixEntree-21+cpt, 0);
            for (int i=1; i<6; i++){
                plateau.positionnePiece(plateau.getPiece(choixEntree-21+cpt, i), choixEntree-21+cpt,i+1 );
            } 
            plateau.positionnePiece(pieceLibre, choixEntree-21+cpt, 0);
            pieceLibre= save;
        }
	}


	/**
	 * Méthode retournant une copie.
	 * 
	 * @return Une copie des éléments.
	 */
	public ElementsPartie copy(){
		Objet[] nouveauxObjets=new Objet[(this.objets).length];
		for (int i=0;i<objets.length;i++)
			nouveauxObjets[i]=(this.objets[i]).copy();
		Joueur[] nouveauxJoueurs=new Joueur[nombreJoueurs];
		for (int i=0;i<nombreJoueurs;i++)
			nouveauxJoueurs[i]=(this.joueurs[i]).copy(objets);
		Plateau nouveauPlateau=(this.plateau).copy();
		Piece nouvellePieceLibre=(this.pieceLibre).copy();
		ElementsPartie nouveauxElements=new ElementsPartie(nouveauxJoueurs,nouveauxObjets,nouveauPlateau,nouvellePieceLibre); 
		return nouveauxElements;
	}


}
