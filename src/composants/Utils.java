package composants;

import java.util.Date;
import java.util.Random;

/**
 * 
 * Classe contenant quelques fonctions utilitaires.
 * 
 */
public class Utils {
	private static Random generateur = new Random((new Date().getTime()));

	/**
	 * A Faire (02/05/21 EH Finilalisée)
	 * 
	 * Méthode permettant de générer aléatoirement un nombre entier.
	 * 
	 * @param max Le nombre entier maximal pouvant être retourné.
	 * @return Un nombre entier compris entre 0 et max (inclus).
	 */
	public static int genererEntier(int max){
		return generateur.nextInt(max+1);
	}
	/**
	 * A Faire (07/05/21 EH finalisée)
	 * 
	 * Méthode permettant de générer un tableau d'entiers dont la longueur longTab est donnée en paramètre.
	 * Le tableau généré doit contenir chaque entier compris entre 0 et longTab-1. La position de ces entiers
	 * dans le tableau doit être aléatoire.
	 * 
	 * @param longTab La longueur du tableau.
	 * @return Un tableau contenant les entiers 0,...,longTab-1 placés aléatoirement dans le tableau.
	 */
	public static int[] genereTabIntAleatoirement(int longTab){
			int tab[] =new int[longTab];
			for (int i=0; i<longTab;i++){
				tab[i]=i;
			}
			for (int i=0; i<longTab;i++){
				int temp = tab[i];
				int temp2 = genererEntier(longTab-1);
				tab[i] = tab[temp2];
				tab[temp2] = temp;
			}
		return tab;
	}
	/**
	 * Programme testant la méthode genereTabIntAleatoirement.
	 * @param args arguments du programme
	 */
	public static void main(String[] args) {
		// Un petit test ...
		int tab[]=genereTabIntAleatoirement(18);
		for (int i=0;i<tab.length;i++)
			System.out.print(tab[i]+" ");

	}

}
