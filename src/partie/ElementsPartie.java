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
		// boucle objets il y a 2 joueurs
		for (int k=0; k<nombreJoueurs; k++){
			while (nbObjetsattribuer<nbObjets){
				for (int i=0; i<nbObjets;i++){
					objetJoueur[i]=objets[tabal[i+(k*nbObjets)]];
				}
				joueurs[k].setObjetsJoueur(objetJoueur);
				nbObjetsattribuer++;
			}
			nbObjetsattribuer = 0;
			objetJoueur = new Objet[nbObjets];
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
	 * A Faire (30/05/2021 EH Finalisée)
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
		// Fleche du haut selectionne
		if (choixEntree<7){
            Piece save = plateau.getPiece(6, choixEntree);
            for (int i=6; i>=1; i--){
                plateau.positionnePiece(plateau.getPiece(i-1, choixEntree), i, choixEntree);
            }
            plateau.positionnePiece(pieceLibre, 0, choixEntree);
            pieceLibre= save;
			// modification de la position des joueurs
			for (int n =0; n<nombreJoueurs;n++){
				if (joueurs[n].getPosColonne()==choixEntree && joueurs[n].getPosLigne()==6){
					joueurs[n].setPosition(0, joueurs[n].getPosColonne());
				}else if (joueurs[n].getPosColonne()==choixEntree){
					joueurs[n].setPosition(joueurs[n].getPosLigne()+1, choixEntree);
				}
			}
			// modification de la position des Objets
			for (int numObjet =0; numObjet<objets.length;numObjet++){
				if (objets[numObjet].getPosconnePlateau()==choixEntree && objets[numObjet].getPoslePlateau()==6){
					objets[numObjet].positionneObjet(0, objets[numObjet].getPosconnePlateau());
				}else if (objets[numObjet].getPosconnePlateau()==choixEntree){
					objets[numObjet].positionneObjet(objets[numObjet].getPoslePlateau()+1, choixEntree);
				}
			}
        // Fleche de droite selectionne
        }else if(choixEntree<14){
            Piece save = plateau.getPiece(choixEntree-7, 0);
            for (int i=0; i<6; i++){
                plateau.positionnePiece(plateau.getPiece(choixEntree-7, i+1), choixEntree-7,i);
            } 
            plateau.positionnePiece(pieceLibre, choixEntree-7, 6);
            pieceLibre= save;
			// modification de la position des joueurs
			for (int n =0; n<nombreJoueurs;n++){
				if (joueurs[n].getPosLigne()==choixEntree-7 && joueurs[n].getPosColonne()==0){
					joueurs[n].setPosition(joueurs[n].getPosLigne(), 6);
				}else if (joueurs[n].getPosLigne()==choixEntree-7){
					joueurs[n].setPosition(choixEntree-7, joueurs[n].getPosColonne()-1);
				}
			}
			// modification de la position des Objets
			for (int numObjet =0; numObjet<objets.length;numObjet++){

				if (objets[numObjet].getPoslePlateau()==choixEntree-7 && objets[numObjet].getPosconnePlateau()==0){
					objets[numObjet].positionneObjet(objets[numObjet].getPoslePlateau(), 6);
				}else if (objets[numObjet].getPoslePlateau()==choixEntree-7){
					objets[numObjet].positionneObjet(choixEntree-7, objets[numObjet].getPosconnePlateau()-1);
				}

			}
        // Fleche du bas selectionne
        }else if (choixEntree<21){

            Piece save = plateau.getPiece(0, 20-choixEntree);
            for (int i=0; i<6; i++){
                plateau.positionnePiece(plateau.getPiece(i+1, 20-choixEntree), i, 20-choixEntree);
            } 
            plateau.positionnePiece(pieceLibre, 6, 20-choixEntree);
            pieceLibre= save;
			// modification de la position des joueurs
			for (int n =0; n<nombreJoueurs;n++){
				if (joueurs[n].getPosColonne()==20-choixEntree && joueurs[n].getPosLigne()==0){
					joueurs[n].setPosition(6, 20-choixEntree);
				}else if (joueurs[n].getPosColonne()==20-choixEntree){
					joueurs[n].setPosition(joueurs[n].getPosLigne()-1, 20-choixEntree);
				}
			}
			// modification de la position des Objets
			for (int numObjet =0; numObjet<objets.length;numObjet++){
				if (objets[numObjet].getPosconnePlateau()==20-choixEntree && objets[numObjet].getPoslePlateau()==0){
					objets[numObjet].positionneObjet(6, 20-choixEntree);
				}else if (objets[numObjet].getPosconnePlateau()==20-choixEntree){
					objets[numObjet].positionneObjet(objets[numObjet].getPoslePlateau()-1, 20-choixEntree);
				}
			}
        // Fleche de gauche selectionne
        }else{
            Piece save = plateau.getPiece(27-choixEntree, 6);
            for (int i=6; i>=1; i--){
                plateau.positionnePiece(plateau.getPiece(27-choixEntree, i-1), 27-choixEntree,i );
            } 
            plateau.positionnePiece(pieceLibre, 27-choixEntree, 0);
            pieceLibre= save;
			// modification de la position des joueurs
			for (int n =0; n<nombreJoueurs;n++){
				if (joueurs[n].getPosLigne()==27-choixEntree && joueurs[n].getPosColonne()==6){
					joueurs[n].setPosition(27-choixEntree, 0);
				}else if (joueurs[n].getPosLigne()==27-choixEntree){
					joueurs[n].setPosition(27-choixEntree, joueurs[n].getPosColonne()+1);
				}
			}
			// modification de la position des Objets
			for (int numObjet = 0; numObjet<objets.length;numObjet++){
				if (objets[numObjet].getPoslePlateau()==27-choixEntree && objets[numObjet].getPosconnePlateau()==6){
					objets[numObjet].positionneObjet(objets[numObjet].getPoslePlateau(), 0);
				}else if (objets[numObjet].getPoslePlateau()==27-choixEntree){
					objets[numObjet].positionneObjet(27-choixEntree, objets[numObjet].getPosconnePlateau()+1);
				}
			}
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
