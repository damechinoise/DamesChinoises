package damechinoises.SD;

import java.util.Vector;

public class NoeudAI {

	private Plateau p;
	private Vector<NoeudAI> fils = new Vector<NoeudAI>();
	private NoeudAI parent = null;
	
	public NoeudAI(Plateau p){
		this.p = p;
	}
	
	public NoeudAI(Plateau p,NoeudAI parent){
		this.parent=parent;
		this.p = p;
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
	
	public int getPoids(int joueur){
		int poids = 0;
		if(fils.isEmpty()){
			poids = 1;
			//TODO - ajouter le cacul du poids en fonction du plateau donné (calcul fait uniquement pour les feuilles)//
		}
		else{
			int max = fils.firstElement().getPoids(joueur);
			for(int i = 1;i < fils.size(); i++){
				int val=fils.get(i).getPoids(joueur);
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
