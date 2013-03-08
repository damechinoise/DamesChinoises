package damechinoises.SD;

import java.util.Vector;

public class NoeudAI {

	private Partie p;
	private Vector<NoeudAI> fils = new Vector<NoeudAI>();
	private NoeudAI parent = null;
	private int joueur;
	
	public NoeudAI(Partie p,int joueur){
		this.p = p;
		this.joueur = joueur;
	}
	
	public NoeudAI(Partie p,NoeudAI parent,int joueur){
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
	
	public int getPoids(){
		int poids = 0;
		if(fils.isEmpty()){
			for (int i = 0; i < p.getNbJoueurs(); i++)
				if(i!=joueur)
					poids -= p.etatJoueur(i);
				else
					poids += p.etatJoueur(i);
		}
		else{
			int max = fils.firstElement().getPoids();
			for(int i = 1;i < fils.size(); i++){
				int val=fils.get(i).getPoids();
				if (val > max)
					max = val;
			}
		}
		return poids;
	}
	
	public NoeudAI fils(int index){
		try{
			return fils.get(index);
		}
		catch(ArrayIndexOutOfBoundsException e){
			return null;
		}
	}
}
