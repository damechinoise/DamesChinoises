package damechinoises.SD;

public class ArbreAI {
	private NoeudAI root;
	private int nbFils, profondeur ,joueur;
	
	public ArbreAI(Partie p,int nbFils, int profondeur, int joueur){
		root = new NoeudAI(p,joueur);
		this.nbFils=nbFils;
		this.profondeur = profondeur;
		this.joueur=joueur;
	}
}
