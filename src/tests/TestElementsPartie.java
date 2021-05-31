package tests;
import joueurs.Joueur;
import composants.Plateau;
import composants.Piece;
import composants.Objet;
import partie.ElementsPartie;
import grafix.interfaceGraphique.IG;

public class TestElementsPartie {
    public static void main(String[] args){


        Object parametres[];
            parametres=IG.saisirParametres(); // On ouvre la fenêtre de paramétrage pour la saisie
            
            // Création de la fenêtre de jeu 
            int nbJoueurs=((Integer)parametres[0]).intValue(); // Récupération du nombre de joueurs
            IG.creerFenetreJeu("- TestElementsPartie",nbJoueurs);            //creation et mise en place des pieces du plateau
            Plateau plateau = new Plateau();
            Piece pieceHorsPlateau=plateau.placerPiecesAleatoierment();
            Joueur joueurs[]=Joueur.nouveauxJoueurs(parametres);
            Objet[] tab = Objet.nouveauxObjets();
            ElementsPartie elementsPartie=new ElementsPartie(joueurs);
            for (int i=0; i<7; i++){
                for (int j=0; j<7; j++){
                    IG.changerPiecePlateau(i, j, plateau.getPiece(i,j).getModelePiece(), plateau.getPiece(i,j).getOrientationPiece());
                }
            }
            IG.changerPieceHorsPlateau(pieceHorsPlateau.getModelePiece(), pieceHorsPlateau.getOrientationPiece());
            
            // creation et placement des joueurs
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

            for (int i = 0; i<tab.length; i++){
                IG.placerObjetPlateau(tab[i].getNumeroObjet(),tab[i].getPosconnePlateau(), tab[i].getPoslePlateau());
            }
            


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

            IG.attendreClic();
            IG.fermerFenetreJeu();
            System.exit(0);
    }
}