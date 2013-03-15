package damechinoises.SD;

import java.util.Random;
import java.util.Vector;

import damechinoises.jeux.PartieAffichage;
import damechinoises.jeux.PlateauAffichage;

public class ArbreAI {
	private NoeudAI root;
	private int nbFils,nbPetitFils ,joueur;
	private PlateauAI p;
	
	public ArbreAI(Partie part, int profondeur, int joueur){
		p = new PlateauAI(part);
		this.root = new NoeudAI(p,joueur);
		this.nbFils=p.getJoueur(0).nbPions();
		int prof = 1;
		if(profondeur > 1)
			prof = profondeur;
		this.joueur=joueur;
		nbPetitFils = 3;
		for (int i = 0 ; i < nbFils ; i++)
			for (int j = 0; j < 1; j ++){
			PlateauAI tour = p.tourJoueurAI(joueur,i,j);
			if (tour!=null)
				root.ajoutFils(ajoutNoeud(prof-1,tour,p.joueurSuivant(joueur)));
		}
	}
	
	public NoeudAI ajoutNoeud(int profondeur,PlateauAI p,int joueur){
		NoeudAI node = new NoeudAI(p,joueur);
		Random r = new Random();
		if(profondeur != 0){
			for (int i = 0 ; i < nbPetitFils ; i++)
				for (int j = 0; j < 1 ; j ++){
					int v = r.nextInt(nbFils);
					PlateauAI tour = p.tourJoueurAI(joueur,v,j);
					if (tour!=null)
						node.ajoutFils(ajoutNoeud(profondeur-1,tour,p.joueurSuivant(joueur)));
			}
		}
		return node;
	}
	
	public void selectionCoup(PlateauAffichage pA){
		int min = root.fils(0).alphabeta(joueur, -5000000, 5000000);
		int fils = 0;
		for(int i = 1; i < root.nbFils() ; i++)
			if(root.fils(i)!=null && root.fils(i).alphabeta(joueur, -5000000, 5000000)>min)
				fils = i;
		Vector<Case> mvt =p.changement(root.fils(fils).getPlateau());
		Case arr = mvt.get(0);
		Case dep = mvt.get(1);
		pA.move(pA.selCase(dep.getX(),dep.getY()),pA.selCase(arr.getX(),arr.getY()));
	}
}
