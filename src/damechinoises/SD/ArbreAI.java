package damechinoises.SD;

import damechinoises.jeux.PartieAffichage;
import damechinoises.jeux.PlateauAffichage;

public class ArbreAI {
	private NoeudAI root;
	private int nbFils, profondeur ,joueur;
	private Partie partie;
	private PlateauAI p;
	
	public ArbreAI(Partie part, int profondeur, int joueur){
		partie = part;
		p = new PlateauAI(part);
		this.root = new NoeudAI(p,joueur);
		this.nbFils=p.getJoueur(0).nbPions()*3;
		if(profondeur > 1)
			this.profondeur = profondeur;
		else
			this.profondeur = 1;
		this.joueur=joueur;
		for (int i = 0 ; i < nbFils ; i++)
			for (int j = 0; j < 3; j ++){
			PlateauAI tour = p.tourJoueurAI(joueur,i/3,j);
			if (tour!=null)
				root.ajoutFils(ajoutNoeud(profondeur-1,tour,p.joueurSuivant(part.getTourDe())));
		}
	}
	
	public NoeudAI ajoutNoeud(int profondeur,PlateauAI p,int joueur){
		NoeudAI node = new NoeudAI(p,joueur);
		if(profondeur != 0){
			for (int i = 0 ; i < nbFils ; i++){
				PlateauAI tour = p.tourJoueurAI(joueur,i%p.getJoueur(0).nbPions(),i/p.getJoueur(0).nbPions());
				if (tour!=null)
					node.ajoutFils(ajoutNoeud(profondeur-1,tour,p.joueurSuivant(joueur)));
			}
		}
		return node;
	}
	
	public void selectionCoup(PlateauAffichage pA){
		int max = root.fils(0).alphabeta(joueur, -5000000, 5000000);
		int fils = 0;
		for(int i = 1; i < nbFils ; i++)
			if(root.fils(i).alphabeta(joueur, -5000000, 5000000)>max)
				fils = i;
		Case arr = p.changement(root.fils(fils).getPlateau());
		Case dep = p.getJoueur(joueur).getPion(fils/3).getPosition();
		pA.move(pA.selCase(dep.getX(),dep.getY()),pA.selCase(arr.getX(),arr.getY()));
	}
}
