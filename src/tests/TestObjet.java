package tests;
import composants.Objet;
import grafix.interfaceGraphique.IG;

public class TestObjet {
    public static void main(String[] args){
        Objet[] tab = Objet.nouveauxObjets(); 
        // Une petite démonstration conernant l'interface graphique

		// différents paramétres par défaut
		Object parametres[];
		parametres=IG.saisirParametres(); // On ouvre la fenêtre de paramétrage pour la saisie
		
		// Création de la fenêtre de jeu et affichage de la fenêtre 
		int nbJoueurs=((Integer)parametres[0]).intValue(); // Récupération du nombre de joueurs
		IG.creerFenetreJeu("Test Objet",nbJoueurs); // On crée la fenêtre
		IG.rendreVisibleFenetreJeu();  // On rend visible la fenêtre de jeu

        	// Affichage d'un message
		String message[]={
            "",
            "Cliquer pour continuer ...",
            ""
        };
        IG.afficherMessage(message); // On change de message de la fenêtre de jeu
        IG.miseAJourAffichage(); // On effectue le rafraichissement de la fenêtre de jeu
		IG.attendreClic();

        for (int i = 0; i<tab.length; i++){
            IG.placerObjetPlateau(tab[i].getNumeroObjet(),tab[i].getPosconnePlateau(), tab[i].getPoslePlateau());
        }
        IG.miseAJourAffichage();
		IG.attendreClic();
		IG.fermerFenetreJeu();
		System.exit(0);

    }
}
