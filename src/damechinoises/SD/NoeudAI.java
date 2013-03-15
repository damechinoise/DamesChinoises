package damechinoises.SD;

import java.util.Vector;

public class NoeudAI {

	private PlateauAI p;
	private Vector<NoeudAI> fils = new Vector<NoeudAI>();
	private NoeudAI parent = null;
	private int joueur;
	
	public NoeudAI(PlateauAI p,int joueur){
		this.p = p;
		this.joueur = joueur;
	}
	
	public NoeudAI(PlateauAI p,NoeudAI parent,int joueur){
		this.parent=parent;
		this.p = p;
		this.joueur = joueur;
	}
	
	public boolean ajoutFils(NoeudAI fils){
		boolean ajout = true;
		try{
			this.fils.addElement(fils);
		}
		catch(Exception e){
			ajout = false;
		}
		return ajout;
	}
	
	public PlateauAI getPlateau(){
		return p;
	}
	
	public int getPoids(int joueur){
		int poids = 0;
		for (int i = 0; i < p.getNbJoueurs(); i++)
			if(i!=joueur)
				poids -= p.etatJoueur(i);
			else
				poids += p.etatJoueur(i);
		return poids;
	}
	
	public NoeudAI fils(int index){
		try{
			return fils.get(index);
		}
		catch(Exception e){
			return null;
		}
	}
	
	public int alphabeta(int joueurini, int alpha, int beta){
		if(fils.isEmpty()){
			return getPoids(joueurini);
		} else {
			int val = 0;;
			if(joueur != joueurini){
				val = 5000000;
				for (int i = 0 ; i < fils.size(); i ++){
					val = min(val,fils.get(i).alphabeta(joueurini,alpha,beta));
					if (alpha >= val)
						return val;
					beta = min(beta,val);
				}
			} else {
				val = -5000000;
				for (int i = 0 ; i < fils.size(); i ++){
					val = max(val,fils.get(i).alphabeta(joueurini,alpha,beta));
					if (beta <= val)
						return val;
					alpha = max(alpha,val);
				}
			}
			return val;
		}
	}
	
	public int max (int a, int b){
		if (a<b)
			return b;
		else
			return a;
	}
	
	public int min (int a, int b){
		if (a<b)
			return a;
		else
			return b;
	}

	public int nbFils() {
		return fils.size();
	}
}
