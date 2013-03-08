package damechinoises.SD;

public class ArbreAI {
	private NoeudAI root;
	private int nbFils, profondeur ,joueur;
	
	public ArbreAI(Partie p,int nbFils, int profondeur, int joueur){
		root = new NoeudAI(p,joueur);
		this.nbFils=nbFils;
		if(profondeur > 1)
			this.profondeur = profondeur;
		else
			this.profondeur = 1;
		this.joueur=joueur;
		for (int i = 0 ; i < nbFils ; i++){
			root.ajoutFils(ajoutNoeud(profondeur-1,p.tourJoueurAI(joueur),p.joueurSuivant()));
		}
	}
	
	public NoeudAI ajoutNoeud(int profondeur,Partie p,int joueur){
		NoeudAI node = new NoeudAI(p,joueur);
		if(profondeur != 0){
			for (int i = 0 ; i < nbFils ; i++){
				node.ajoutFils(ajoutNoeud(profondeur-1,p.tourJoueurAI(joueur),p.joueurSuivant()));
			}
		}
		return node;
	}
	
	public Partie selectionCoup(){
		Partie retour = null;
		NoeudAI parcours;
		int max = root.alphabeta(joueur, -5000000, 5000000);
		for(int i = 0; i < nbFils ; i++){
			if(root.fils(i).alphabeta(joueur, -5000000, 5000000)==max)
				retour = root.fils(i).getPartie();
		}
		return retour;
	}
}
