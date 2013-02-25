package damechinoises.SD;

public class ArbreAI {
	private NoeudAI root;
	private int nbFils, profondeur ,joueur;
	
	public ArbreAI(Plateau p,int nbFils, int profondeur, int joueur){
		root = new NoeudAI(p);
		this.nbFils=nbFils;
		this.profondeur = profondeur;
		this.joueur=joueur;
	}
}
