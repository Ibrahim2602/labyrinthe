package composants;

/**
 * 
 * Cette classe permet de representer les differentes pieces du jeu.
 * 
 */
abstract public class Piece {

	/**
     *
     */
    private int modelePiece; 		// Le modele de la piece
	private int orientationPiece; 	// L'orientation de la piece
	private boolean[] pointsEntree; // Les points d'entrée indice 0 pour le haut, 1 pour la droite, 2 pour le bas et 3 pour la gauche.

	/**
	 * A Faire (02/05/21 IB Finalisé):
	 * 
	 * 
	 * Constructeur permettant de créer une piece d'un modele avec l'orientation 0.
	 * @param modelePiece Le modele de la piece.
	 * @param pointEntreeHaut Un boolean indiquant si la piece a un point d'entrée en haut.
	 * @param pointEntreeDroite Un boolean indiquant si la piece a un point d'entrée à  droite.
	 * @param pointEntreeBas Un boolean indiquant si la piece a un point d'entrée en bas.
	 * @param pointEntreeGauche Un boolean indiquant si la piece a un point d'entrée à  gauche.
	 */
	
	public Piece(int modelePiece,boolean pointEntreeHaut,boolean pointEntreeDroite,boolean pointEntreeBas,boolean pointEntreeGauche){
		this.modelePiece= modelePiece;
		pointsEntree = new boolean[] {pointEntreeHaut,pointEntreeDroite,pointEntreeBas, pointEntreeGauche};
		
	}
	
	/**
	 * Méthode retournant un String prepresentant la piece.
	 */
	@Override
	public String toString() {
		return "[m:"+modelePiece+"|o:"+orientationPiece+"|pe:"+pointsEntree[0]+" "+pointsEntree[1]+" "+pointsEntree[2]+" "+pointsEntree[3]+"]";
	}
	
	/**
	 * A Faire (02/05/21 IB Finalisé)
	 * 
	 * Méthode permettant de rotationner une piece dans le sens d'une horloge.
	 */

	public void rotation(){
		boolean tab[]=new boolean[] {getPointEntree(3),getPointEntree(0),getPointEntree(1),getPointEntree(2)};
	    if (orientationPiece ==3)
		    orientationPiece = 0;
		else{
			orientationPiece++;
		}
		pointsEntree =tab;
    }
	
	/**
	 * A Faire (02/05/21 IB Finalisé)
	 * 
	 * Méthode permettant d'orienter une piece vers une orientation spécifique.
	 * @param orientationPiece Un entier correspondant à  la nouvelle orientation de la piece.
	 */
	public void setOrientation(int orientationPiece){
		int tmp = this.orientationPiece;
		while (tmp != orientationPiece){
			tmp++;
			if (tmp==4){
				tmp=0;
			}
			rotation();
		}
		this.orientationPiece=orientationPiece;
	}

	/**
	 * A Faire (02/05/21 IB Finalisé)
	 * 
	 * Méthode retournant le modele de la piece.
	 * @return Un entier corrspondant au modele de la piece.
	 */
	public int getModelePiece() {
		return modelePiece;
	}

	/**
	 * A Faire (02/05/21 IB Finalisé)
	 * 
	 * Méthode retournant l'orientation de la piece.
	 * @return un entier retournant l'orientation de la piece.
	 */
	public int getOrientationPiece() {
		
		return orientationPiece;
	}

	/**
	 * A Faire (02/05/21 IB Finalisé)
	 * 
	 * Méthode indiquant si il existe un point d'entrée à  une certaine position (0: en haut, 1: à  droite, 2: en bas, 3: à  gauche).
	 * @param pointEntree L'indice/la position du point d'entrée.
	 * @return true si il y a un point d'entrée, sinon false.
	 */
	public boolean getPointEntree(int pointEntree){
		if (this.pointsEntree[pointEntree] == true)
			return true;
		else return false;
	}
	
	/**
	 * A Faire (02/05/21 IB EnCours)
	 * 
	 * Méthode permettant de créer un tableau contenant toutes les pieces du jeu (les 50 pieces).
	 * Le tableau contiendra 20 pieces du modele 0, 12 pieces du modele 1 et 18 pieces du modele 2.
	 * L'orientation de chaque piece sera aléatoire.
	 * @return Un tableau contenant toutes les pieces du jeu.
	 */
	public static Piece[] nouvellesPieces(){
		Piece pieces[]=new Piece[50];
        for (int i=0; i<50;i++){
            if (i<20){
                pieces[i]= new PieceM0();
			}else if(i<32){
				pieces[i]=new PieceM1();
			}else{
				pieces[i]=new PieceM2();
			}
			pieces[i].setOrientation(Utils.genererEntier(3));
		}
		return pieces;
	}
	
	/**
	 * Méthode permettant de créer une copie de la piece (un nouvelle objet Java).
	 * @return Une copie de la piece.
	 */
	public abstract Piece copy();
}
