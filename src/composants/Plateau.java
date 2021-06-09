package composants;
import java.util.Vector;

/**
 * Cette classe permet de gérer un plateau de jeu constitué d'une grille de pièces (grille de 7 lignes sur 7 colonnes).
 *
 */
public class Plateau {

	private Piece plateau[][]; // La grille des pièces.

	/**
	 * A Faire (16/05/2021 EH Finalisée)
	 * 
	 * Constructeur permettant de construire un plateau vide (sans pièces) et d'une taille de 7 lignes sur 7 colonnes.
	 */
	public Plateau() {
        this.plateau = new Piece[7][7];
	}

	/**
	 * A Faire (16/05/2021 EH Finalisée)
	 * 
	 * Méthode permettant de placer une pièce sur le plateau.
	 * 
	 * @param piece La pièce à  placer.
	 * @param lignePlateau La ligne du plateau sur laquelle sera placée la pièce (un entier entre 0 et 6).
	 * @param colonnePlateau La colonne du plateau sur laquelle sera placée la pièce (une entier entre 0 et 6).
	 */
	public void positionnePiece(Piece piece,int lignePlateau,int colonnePlateau){
		plateau[lignePlateau][colonnePlateau] = piece;
	}

	/**
	 * A Faire (16/05/2021 EH Finalisée)
	 * 
	 * Méthode retournant une pièce se trouvant sur le plateau à  un emplacement spécifique.
	 * 
	 * @param lignePlateau La ligne du plateau  (un entier entre 0 et 6).
	 * @param colonnePlateau La colonne du plateau (un entier entre 0 et 6).
	 * @return La pièce se trouvant sur la ligne lignePlateau et la colonne colonnePlateau. Si il n'y a pas de pièce, null est retourné.
	 */
	public Piece getPiece(int lignePlateau,int colonnePlateau){
		return plateau[lignePlateau][colonnePlateau];
	}

	/**
	 * 
	 * A Faire (16/05/2021 EH Finalisée)
	 *  
	 * Méthode permettant de placer aléatoirment 49 pièces du jeu sur le plateau.
	 * L'orientation des pièces est aléatoire. Les pièces utilisées doivent être des nouvelles pièces générées à  partir de la méthode Piece.nouvellesPieces.
	 * Parmi les 50 pièces du jeu, la pièce qui n'a pas été placée sur le plateau est retournée par la méthode.
	 * 
	 * @return La seule pièce qui n'a pas été placée sur le plateau
	 */
	public Piece placerPiecesAleatoierment(){
		Piece[] tab = Piece.nouvellesPieces();
        Piece tmp;
        int tmp2;
        for (int i=0;i<49;i++){
            tmp2 = Utils.genererEntier(49);
            tmp=tab[i];
            tab[i]=tab[tmp2];
            tab[tmp2]=tmp;
        }
        int x = 0;
        for (int i =0; i<7;i++){
            for (int j = 0; j<7;j++){
                plateau[i][j]=tab[x];
                x++;
            }
        }
		return tab[x];
	}

	/**
	 * 
	 * Méthode utilitaire permettant de tester si les positions passées en paramètre sont les positions de deux cases différentes et adjacentes d'une grille de 7 lignes sur 7 colonnes.
	 *  
	 * @param posLigCase1 Un entier quelconque.
	 * @param posColCase1 Un entier quelconque.
	 * @param posLigCase2 Un entier quelconque.
	 * @param posColCase2 Un entier quelconque.
	 * @return true si les les positions passées en paramètre sont les positions de deux cases différentes et adjacentes d'une grille de 7 lignes sur 7 colonnes, false sinon.
	 */
	private static boolean casesAdjacentes(int posLigCase1,int posColCase1,int posLigCase2,int posColCase2){
		if ((posLigCase1<0)||(posLigCase2<0)||(posLigCase1>6)||(posLigCase2>6)) return false;
		if ((posColCase1<0)||(posColCase2<0)||(posColCase1>6)||(posColCase2>6)) return false;
		int distLigne=posLigCase1-posLigCase2;
		if (distLigne<0) distLigne=-distLigne;
		int distColonne=posColCase1-posColCase2;
		if (distColonne<0) distColonne=-distColonne;
		if ((distLigne>1)||(distColonne>1)||((distColonne+distLigne)!=1))
			return false;
		return true;
	}

	/**
	 * 
	 * A Faire (16/05/2021 EH Finalisée)
	 * 
	 * Méthode permettant de tester si les positions passées en paramètre sont les positions de deux cases différentes et adjacentes 
	 * de la grille de jeu et qu'il est possible de passer d'une cas à  l'autre compte tenu des deux pièces posées sur les deux cases du plateau.
	 * 
	 * @param posLigCase1 Un entier quelconque.
	 * @param posColCase1 Un entier quelconque.
	 * @param posLigCase2 Un entier quelconque.
	 * @param posColCase2 Un entier quelconque.
	 * @return true si les positions passées en paramètre sont les positions de deux cases différentes et adjacentes de la grille de jeu et qu'il est possible de passer d'une cas à  l'autre compte tenu des deux pièces posées sur les deux cases du plateau, false sinon.
	 */
	private boolean passageEntreCases(int posLigCase1,int posColCase1,int posLigCase2,int posColCase2){
		return (casesAdjacentes(posLigCase1, posColCase1, posLigCase2, posColCase2) && 
			((posLigCase1<posLigCase2 && plateau[posLigCase1][posColCase1].getPointEntree(2) && plateau[posLigCase2][posColCase2].getPointEntree(0))// 1 au dessus de 2
			 || (posColCase1>posColCase2 && plateau[posLigCase1][posColCase1].getPointEntree(3) && plateau[posLigCase2][posColCase2].getPointEntree(1))// 1 a droite de 2
			  || (posLigCase1>posLigCase2 && plateau[posLigCase1][posColCase1].getPointEntree(0) && plateau[posLigCase2][posColCase2].getPointEntree(2))// 1 en dessous de 2
			   || (posColCase1<posColCase2 && plateau[posLigCase1][posColCase1].getPointEntree(1) && plateau[posLigCase2][posColCase2].getPointEntree(3)))); // 1 a gauche de 2
	}

	/**
	 * 
	 * A Faire (16/05/2021 EH Finalisée)
	 * 
	 * Méthode permettant de retourner un éventuel chemin entre deux cases du plateau compte tenu des pièces posées sur le plateau.
	 * Dans le cas où il n'y a pas de chemin entre les deux cases, la valeur null est retournée.
	 * Dans le cas où il existe un chemin, un chemin possible est retourné sous forme d'un tableau d'entiers à  deux dimensions.
	 * La première dimension correspond aux cases du plateau à  emprunter pour aller de la case de départ à  la case d'arrivée.
	 * Dans ce tableau, chaque case est un tableau de deux entiers avec le premier entier qui correspond à  la ligne de la case et
	 * le second entier qui correspond à  la colonne de la case. La première case d'un chemin retourné correspond toujours 
	 * à  la case (posLigCaseDep,posColCaseDep) et la dernière case correspond toujours à  la case (posLigCaseArr,posColCaseArr).
	 *
	 * @param posLigCaseDep La ligne de la case de départ (un entier compris entre 0 et 6).
	 * @param posColCaseDep La colonne de la case de départ (un entier compris entre 0 et 6).
	 * @param posLigCaseArr La ligne de la case d'arrivée (un entier compris entre 0 et 6).
	 * @param posColCaseArr La colonne de la case d'arrivée (un entier compris entre 0 et 6).
	 * @return null si il n'existe pas de chemin entre les deux case, un chemin sinon.
	 */
	public int[][] calculeChemin(int posLigCaseDep,int posColCaseDep,int posLigCaseArr,int posColCaseArr){
		boolean bool[][] = new boolean[7][7]; // tableau de boolean true si la case a déjà été visité
		bool[posLigCaseDep][posColCaseDep]=true;
		int[] couple = new int[2];
		Vector<int[]> chemin = new Vector<>(); // le chemin pour aller jusque la case de fin
		int posLigCaseAct = posLigCaseDep;
		int posColCaseAct = posColCaseDep;
		couple[0]=posLigCaseDep;
		couple[1]=posColCaseDep;
		chemin.add(couple); // première case du chemin
		while (chemin.isEmpty()==false  ){
			if (bool[posLigCaseArr][posColCaseArr]==true){ // break si la case d'arrivée est atteinte
				break;
			}
			else if (passageEntreCases(posLigCaseAct, posColCaseAct, posLigCaseAct-1, posColCaseAct) && bool[posLigCaseAct-1][posColCaseAct]==false){
				// case 1 en bas de case 2
				bool[posLigCaseAct-1][posColCaseAct]=true;
				posLigCaseAct = posLigCaseAct-1;
				couple = new int[2];
				couple[0]=posLigCaseAct;
				couple[1]=posColCaseAct;
				chemin.add(couple);
			}else if (passageEntreCases(posLigCaseAct, posColCaseAct, posLigCaseAct, posColCaseAct-1) && bool[posLigCaseAct][posColCaseAct-1]==false){
				// case 1 à droite de case 2
				bool[posLigCaseAct][posColCaseAct-1]=true;
				posColCaseAct = posColCaseAct-1;
				couple = new int[2];
				couple[0]=posLigCaseAct;
				couple[1]=posColCaseAct;
				chemin.add(couple);
			}else if(passageEntreCases(posLigCaseAct, posColCaseAct, posLigCaseAct+1, posColCaseAct) && bool[posLigCaseAct+1][posColCaseAct]==false){
				// case 1 en haut de case 2
				bool[posLigCaseAct+1][posColCaseAct]=true;
				posLigCaseAct = posLigCaseAct+1;
				couple = new int[2];
				couple[0]=posLigCaseAct;
				couple[1]=posColCaseAct;
				chemin.add(couple);
			}else if(passageEntreCases(posLigCaseAct, posColCaseAct, posLigCaseAct, posColCaseAct+1) && bool[posLigCaseAct][posColCaseAct+1]==false){
				// case 1 à gauche de case 2
				bool[posLigCaseAct][posColCaseAct+1]=true;
				posColCaseAct = posColCaseAct+1;
				couple = new int[2];
				couple[0]=posLigCaseAct;
				couple[1]=posColCaseAct;
				chemin.add(couple);
				
			}else{
				chemin.remove(chemin.size()-1); // enleve la derniere case du chemin si aucun chemin n'est possible(ou pas de chemin déjà testé) vers une autre case
				if (!chemin.isEmpty()){
					posLigCaseAct=chemin.get(chemin.size()-1)[0]; // enleve la coordonnée de ligne de la derniere case
					posColCaseAct=chemin.get(chemin.size()-1)[1]; // enleve la coordonnée de colone de la derniere case
				}
			}
		}
		int resultat[][]=null;
		if (!chemin.isEmpty()){
			resultat= new int[chemin.size()][2];
			for (int i=0; i<chemin.size();i++){
				for (int j=0; j<2;j++){
					resultat[i][j]=chemin.get(i)[j];
				}
			}
		}
		return resultat;
	}
	/**
	 * 
	 * Méthode permettant de calculer un chemin détaillé (chemin entre sous-cases) à  partir d'un chemin entre cases.
	 *  
	 * @param chemin Un tableau représentant un chemin de cases.
	 * @param numJoueur Le numéro du joueur pour lequel nous souaitons construire un chemin détaillé.
	 * 
	 * @return Le chemin détaillé correspondant au chemin de positions de pièces données en paramètre et pour le numéro de joueur donné.
	 */
	public int[][] calculeCheminDetaille(int[][] chemin,int numJoueur){
		if (chemin.length==1)
			return new int[0][0];
		int[][] cheminDetaille=new int[chemin.length*5][4];
		int pos=0;
		int col, lig, colS, ligS;
		for (int i=0;i<chemin.length-1;i++){
			lig=chemin[i][0];
			col=chemin[i][1];
			ligS=chemin[i+1][0];
			colS=chemin[i+1][1];
			if (ligS<lig){
				cheminDetaille[pos][0]=lig;
				cheminDetaille[pos][1]=col;
				cheminDetaille[pos][2]=1;
				cheminDetaille[pos++][3]=1;
				cheminDetaille[pos][0]=lig;
				cheminDetaille[pos][1]=col;
				cheminDetaille[pos][2]=0;
				cheminDetaille[pos++][3]=1;
				cheminDetaille[pos][0]=ligS;
				cheminDetaille[pos][1]=colS;
				cheminDetaille[pos][2]=2;
				cheminDetaille[pos++][3]=1;
			}
			else if (ligS>lig){
				cheminDetaille[pos][0]=lig;
				cheminDetaille[pos][1]=col;
				cheminDetaille[pos][2]=1;
				cheminDetaille[pos++][3]=1;
				cheminDetaille[pos][0]=lig;
				cheminDetaille[pos][1]=col;
				cheminDetaille[pos][2]=2;
				cheminDetaille[pos++][3]=1;
				cheminDetaille[pos][0]=ligS;
				cheminDetaille[pos][1]=colS;
				cheminDetaille[pos][2]=0;
				cheminDetaille[pos++][3]=1;
			} else if (colS<col){
				cheminDetaille[pos][0]=lig;
				cheminDetaille[pos][1]=col;
				cheminDetaille[pos][2]=1;
				cheminDetaille[pos++][3]=1;
				cheminDetaille[pos][0]=lig;
				cheminDetaille[pos][1]=col;
				cheminDetaille[pos][2]=1;
				cheminDetaille[pos++][3]=0;
				cheminDetaille[pos][0]=ligS;
				cheminDetaille[pos][1]=colS;
				cheminDetaille[pos][2]=1;
				cheminDetaille[pos++][3]=2;
			} else if (colS>col){
				cheminDetaille[pos][0]=lig;
				cheminDetaille[pos][1]=col;
				cheminDetaille[pos][2]=1;
				cheminDetaille[pos++][3]=1;
				cheminDetaille[pos][0]=lig;
				cheminDetaille[pos][1]=col;
				cheminDetaille[pos][2]=1;
				cheminDetaille[pos++][3]=2;
				cheminDetaille[pos][0]=ligS;
				cheminDetaille[pos][1]=colS;
				cheminDetaille[pos][2]=1;
				cheminDetaille[pos++][3]=0;
			}
		}
		cheminDetaille[pos][0]=chemin[chemin.length-1][0];
		cheminDetaille[pos][1]=chemin[chemin.length-1][1];
		cheminDetaille[pos][2]=1;
		cheminDetaille[pos++][3]=1;

		int debut=0;
		if ((numJoueur==0)&&((cheminDetaille[pos-2][2]==0)||(cheminDetaille[pos-2][3]==2))) pos--;
		if ((numJoueur==1)&&((cheminDetaille[pos-2][2]==2)||(cheminDetaille[pos-2][3]==2))) pos--;
		if ((numJoueur==2)&&((cheminDetaille[pos-2][2]==2)||(cheminDetaille[pos-2][3]==0))) pos--;
		if ((numJoueur==0)&&((cheminDetaille[1][2]==0)||(cheminDetaille[0][3]==2))) debut++;
		if ((numJoueur==1)&&((cheminDetaille[1][2]==2)||(cheminDetaille[0][3]==2))) debut++;
		if ((numJoueur==2)&&((cheminDetaille[1][2]==2)||(cheminDetaille[0][3]==0))) debut++;

		int[][] resultat=new int[pos-debut][4];
		for (int i=debut;i<pos;i++)
			for (int j=0;j<4;j++)
				resultat[i-debut][j]=cheminDetaille[i][j];
		return resultat;	
	}

	/**
	 * 
	 * Méthode retournant une copie du plateau avec des copies de ses pièces.
	 * 
	 * @return Une copie du plateau avec une copie de toutes ses pièces.
	 */
	public Plateau copy(){
		Plateau plateau=new Plateau();
		for (int ligne=0;ligne<7;ligne++)
			for (int colonne=0;colonne<7;colonne++)
				plateau.positionnePiece((this.plateau[ligne][colonne]).copy(), ligne, colonne);
		return plateau;
	}

}
