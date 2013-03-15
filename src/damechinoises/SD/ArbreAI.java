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
		this.root = new NoeudAI(p,joueur,0);
		this.nbFils=p.getJoueur(0).nbPions();
		int prof = 1;
		if(profondeur > 1)
			prof = profondeur;
		this.joueur=joueur;
		nbPetitFils = 3;
		for (int i = 0 ; i < nbFils ; i++)
			for (int j = 0; j < 2; j ++){
			PlateauAI tour = p.tourJoueurAI(joueur,i,j);
			if (tour!=null)
				root.ajoutFils(ajoutNoeud(prof-1,tour,joueur,1));
		}
	}
	
	public NoeudAI ajoutNoeud(int profondeur,PlateauAI p,int joueur,int prof){
		NoeudAI node = new NoeudAI(p,joueur,prof);
		Random r = new Random();
		if(profondeur != 0){
			for (int i = 0 ; i < nbPetitFils ; i++)
				for (int j = 0; j < 2 ; j ++){
					int v = r.nextInt(nbFils);
					PlateauAI tour = p.tourJoueurAI(joueur,v,j);
					if (tour!=null)
						node.ajoutFils(ajoutNoeud(profondeur-1,tour,p.joueurSuivant(joueur),prof+1));
			}
		}
		return node;
	}
	
	public Vector<Case> selectionCoup(PlateauAffichage pA, Vector<Case> mvtprec){
		int valPA = pA.getPartie().etatJoueur(joueur);
		int min = root.fils(0).alphabeta(joueur, -5000000, 5000000);
		int fils = 0;
		int count = 0;
		boolean move = false;
		Case dep = null,arr = null;
		Vector<Case> mvt = null;
		Vector<Integer> passer = new Vector<Integer>();
		do{
			while(min == valPA && fils < root.nbFils()){
				fils++;
				min = root.fils(fils).alphabeta(joueur, -5000000, 5000000);
			}
			for(int i = fils+1; i < root.nbFils() ; i++){
				boolean pass = false;
				int val = root.fils(i).alphabeta(joueur, -5000000, 5000000);
				if(passer.size() > 0)
					for(int j = 0 ; j < passer.size() ; j++)
						if(passer.get(j)==i)
							pass = true;
				if(root.fils(i)!=null && val>min && val!=valPA && !pass){
					fils = i;
					min = val;
				}
			}
			int val = root.fils(fils).alphabeta(joueur, -5000000, 5000000);
			mvt =p.changement(root.fils(fils).getPlateau());
			arr = mvt.get(0);
			dep = mvt.get(1);
			if (mvtprec != null ){
				Case dep1 = mvtprec.get(1);
				Case arr1 = mvtprec.get(0);
				if(dep1.getX() == arr.getX() && dep1.getY() == arr.getY() && arr1.getX() == dep.getX() && arr1.getY() == dep.getY() && count !=3){
					passer.add(new Integer(fils));
					count++;
					System.out.print(count);
				}
				else
					move = true;
			}
			else
				move = true;
		}while (!move);
		System.out.println();
		return mvt;
	}
}
