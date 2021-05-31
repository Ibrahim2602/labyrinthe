package composants;

/**
 * 
 * Cette classe permet de représenter les pièces du jeu de modèle 2.
 *
 */
public class PieceM2 extends Piece {

	/**
	 * A Faire (04/05/21 EH Finalisée)
	 * 
	 * Constructeur permettant de construire une pièce de modèle 2 et d'orientation 0.
	 */
	public PieceM2() {
		super(2,true,true,false,true); 
	}
	/**
	 * A Faire (04/05/21 EH finalisée)
	 * 
	 * Méthode permettant de créer une copie de la pièce (un nouvelle objet Java).
	 * @return Une copie de la pièce.
	 */
	public Piece copy(){
		Piece piece = new PieceM2();
		return piece;

	}
}