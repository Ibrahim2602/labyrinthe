package composants;

/**
 * 
 * Cette classe permet de représenter chacun des objets du jeu.
 *
 */
public class Objet {

	private int numObjet; // Le numéro de l'objet (un entier entre 0 et 17).
	private int poslePlateau; // La le du plateau sur laquelle est éventuellement posé l'objet (un entier entre -1 et 6, -1:lorsqu'il n'est pas sur le plateau).
	private int posconnePlateau; // La conne du plateau sur laquelle est éventuellement posé l'objet (un entier entre -1 et 6, -1:lorsqu'il n'est pas sur le plateau).
	private boolean surPlateau; // Indique si l'objet est sur le plateau ou non (true : sur le plateau, false : hors du plateau).

	/**
	 * 
	 * A Faire (16/05/21 AR Finalisé)
	 * 
	 * Constructeur permettant de construire un objet qui est initialement hors du plateau.
	 * 
	 * @param numObjet Le numéro de l'objet.
	 */
	public Objet(int numObjet) {
		this.numObjet = numObjet;
		this.poslePlateau = -1;
		this.posconnePlateau = -1;
		this.surPlateau = false;
	}

	/**
	 * 
	 * A Faire (16/05/21 AR Finalisé)
	 * 
	 * Méthode permettant de générer un tableau contenant les 18 objets du jeu.
	 * Les objets seront postionnées aléatoirement sur le plateau.  Deux objets ne pourront pas être sur une même case (même le et même conne).
	 * 
	 * @return Un tableau de 18 objets initialisés pour une partie du jeu. Chaque objet a une position générée aléatoirement. Les positions sont différentes pour deux objets distincts.
	 *
	 */
	public static Objet[] nouveauxObjets(){
		Objet [] objets;
		objets = new Objet[18];
		int nObj = 0;
		for (int i = 0; i<objets.length; i++){
			objets[i] = new Objet(i);
			int c = Utils.genererEntier(6);
			int l = Utils.genererEntier(6);
			int j = 0;
			while (j!= nObj){
				if (objets[j].getPosconnePlateau() != c || objets[j].getPoslePlateau() != l ){
					j++;
				}else {
					j = 0;
					c = Utils.genererEntier(6);
					l = Utils.genererEntier(6);
				}
			}
			objets[i].positionneObjet(l,c);
			nObj++;
		}
		return objets;
	}


	/**
	 * 
	 * A Faire (16/05/21 AR Finalisé)
	 * 
	 * Méthode retournant le numéro de l'objet.
	 * 
	 * @return Le numéro de l'objet.
	 */
	public int getNumeroObjet() {
		return numObjet; 
	}


	/**
	 * 
	 * A Faire (16/05/21 AR Finalisé)
	 * 
	 * Méthode retournant le numéro de la le sur laquelle se trouve l'objet.
	 * 
	 * @return Le numéro de la le sur laquelle se trouve l'objet.
	 */
	public int getPoslePlateau() {
		return poslePlateau; 
	}

	/**
	 * 
	 * A Faire (16/05/21 AR En Finalisé)
	 * 
	 * Méthode retournant le numéro de la conne sur laquelle se trouve l'objet.
	 * 
	 * @return Le numéro de la conne sur laquelle se trouve l'objet.
	 */
	public int getPosconnePlateau() {
		return posconnePlateau; 
	}
	
	
	/**
	 * 
	 * A Faire (16/05/21 AR Finalisé)
	 * 
	 * Méthode permettant de positionner l'objet sur une le et une conne données en paramètre.
	 * 
	 * @param lePlateau Un entier compris entre 0 et 6.
	 * @param connePlateau Un entier compris entre 0 et 6.
	 */
	public void positionneObjet(int lePlateau,int connePlateau){
		poslePlateau = lePlateau;
		posconnePlateau = connePlateau;
		surPlateau = true;
	}

	/**
	 * 
	 * A Faire (16/05/21 AR Finalisé)
	 * 
	 * Méthode permettant d'enlever l'objet du plateau.
	 * 
	 */
	public void enleveDuPlateau(){
		surPlateau = false;
		posconnePlateau = -1;
		poslePlateau = -1;
		
	}

	/**
	 * 
	 * A Faire (16/05/21 AR Finalisé)
	 * 
	 * Méthode indiquant si l'objet est sur le plateau au non.
	 * 
	 * @return true si l'objet est sur le plateau, false sinon.
	 */
	public boolean surPlateau() {
		if (poslePlateau == -1 && posconnePlateau == -1){
			surPlateau = false;
		}
		else {
			surPlateau = true;
		}
		return surPlateau; 
	}

	/**
	 * Méthode permettant d'obtenir une représentation d'un objet sous forme de chaîne de caractères.
	 */
	@Override
	public String toString() {
		return "Objet [numObjet=" + numObjet + ", poslePlateau=" + poslePlateau + ", posconnePlateau="
				+ posconnePlateau + ", surPlateau=" + surPlateau + "]";
	}

	/**
	 * 
	 * Méthode permettant de copier l'objet.
	 * 
	 * @return Une copie de l'objet.
	 */
	public Objet copy(){
		Objet objet=new Objet(numObjet);
		objet.poslePlateau=poslePlateau;
		objet.posconnePlateau=posconnePlateau;
		objet.surPlateau=surPlateau;
		return objet;
	}

	/**
	 * Programme testant quelques méthodes de la classe Objet.
	 * @param args arguments du programme
	 */
	public static void main(String[] args) {
		// Un petit test ...
		System.out.println("*** Génération et affichage des 18 objets ... ***");
		Objet objetsJeu[]=nouveauxObjets();
		for (int i=0;i<objetsJeu.length;i++)
			System.out.println(objetsJeu[i]);
		System.out.println("*** On enlève les 18 objets du plateau ... ***");
		for (int i=0;i<objetsJeu.length;i++)
			 objetsJeu[i].enleveDuPlateau();
		System.out.println("*** On affiche de nouveau les 18 objets ... ***");
		for (int i=0;i<objetsJeu.length;i++)
			System.out.println(objetsJeu[i]);
	}
	
}
