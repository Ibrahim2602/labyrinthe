package joueurs;

import composants.Objet;
import composants.Plateau;
import partie.ElementsPartie;

/**
 * 
 * Cette classe permet de reprÃ©senter un joueur ordinateur de type T3.
 * 
 * @author Jean-FranÃ§ois Condotta - 2021
 *
 */

public class JoueurOrdinateurT3 extends JoueurOrdinateur {

	/**
	 * Constructeur permettant de crÃ©er un joueur.
	 * 
	 * @param numJoueur Le numÃ©ro du joueur.
	 * @param nomJoueur Le nom du joueur.
	 * @param numeroImagePersonnage Le numÃ©ro de l'image reprÃ©sentant le joueur.
	 * @param posLignePlateau La ligne du plateau sur laquelle est positionnÃ©e le joueur.
	 * @param posColonnePlateau La colonne du plateau sur laquelle est positionnÃ©e le joueur.

	 */
	public JoueurOrdinateurT3(int numJoueur,String nomJoueur, int numeroImagePersonnage,int posLignePlateau,int posColonnePlateau) {
				super(numJoueur,nomJoueur, numeroImagePersonnage,posLignePlateau,posColonnePlateau);
	}

	@Override
	public String getCategorie() {
		return "OrdiType3";
	}
	@Override
	public int[] choisirOrientationEntree(ElementsPartie elementsPartie){
		int resultat[]=new int[2];
		for (int i=0; i<=27;i++){ // test toutes les entrées du plateau
			for (int j=0; j<4; j++){ // test toutes les orientation de la piece hors plateau
				Plateau plateauCopy = elementsPartie.getPlateau().copy();
				// copie des elements de la partie pour simuler une insertion
				ElementsPartie copyElementsPartie = new ElementsPartie(elementsPartie.getJoueurs(), elementsPartie.getObjets(), plateauCopy, elementsPartie.getPieceLibre());
				copyElementsPartie.getPieceLibre().setOrientation(j);
				copyElementsPartie.insertionPieceLibre(i);
				int[][] testInsertion0 = copyElementsPartie.getPlateau().calculeChemin(copyElementsPartie.getJoueurs()[getNumJoueur()].getPosLigne(), 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getPosLigne(), 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getProchainObjet().getPoslePlateau(), 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getProchainObjet().getPoslePlateau());

				int[][] testInsertion1 = copyElementsPartie.getPlateau().calculeChemin(copyElementsPartie.getJoueurs()[getNumJoueur()].getPosLigne(), 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getPosLigne(), 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getProchainObjet().getPoslePlateau()-1, 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getProchainObjet().getPoslePlateau());

				int[][] testInsertion2 = copyElementsPartie.getPlateau().calculeChemin(copyElementsPartie.getJoueurs()[getNumJoueur()].getPosLigne(), 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getPosLigne(), 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getProchainObjet().getPoslePlateau()+1, 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getProchainObjet().getPoslePlateau());

				int[][] testInsertion3 = copyElementsPartie.getPlateau().calculeChemin(copyElementsPartie.getJoueurs()[getNumJoueur()].getPosLigne(), 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getPosLigne(), 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getProchainObjet().getPoslePlateau(), 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getProchainObjet().getPoslePlateau()-1);

				int[][] testInsertion4 = copyElementsPartie.getPlateau().calculeChemin(copyElementsPartie.getJoueurs()[getNumJoueur()].getPosLigne(), 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getPosLigne(), 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getProchainObjet().getPoslePlateau(), 
															copyElementsPartie.getJoueurs()[getNumJoueur()].getProchainObjet().getPoslePlateau()+1);
				//Si un chemin vers sont objet est possible on stock i et j (l'entrée et l'orientation de la piece libre)
				if (testInsertion0!=null || testInsertion1!=null || testInsertion2!=null || testInsertion3!=null || testInsertion4!=null){
					resultat[1]=i;
					resultat[0]=j;
					return resultat;
				}
			}
		}
		return resultat;
	}

	
	@Override
	public Joueur copy(Objet objets[]){
		Joueur nouveauJoueur=new JoueurOrdinateurT3(getNumJoueur(),getNomJoueur(), getNumeroImagePersonnage(),getPosLigne(),getPosColonne());
		nouveauJoueur.setObjetsJoueur(this.getObjetsJoueurGeneral(objets));
		while (nouveauJoueur.getNombreObjetsRecuperes()!=this.getNombreObjetsRecuperes())
			nouveauJoueur.recupererObjet();
		return nouveauJoueur;
	}


	
}
