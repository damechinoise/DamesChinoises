package damechinoises.SD;

import java.util.Vector;

public class Plateau {
	
/*###ATTRIBUTS###*/
/*##############*/
	private int taillePlateau;
	private int nbPionParJoueur;
	private Vector<AnneauCases> anneaux;
	private BrancheCase[] branches;
	//AJOUTER LES CASES//
	
/*###CONSTRUCTEURS###*/
/*##################*/
	public Plateau(){
		taillePlateau = 4;
		anneaux = new Vector<AnneauCases>();
		branches = new BrancheCase[6];
		for (int i = 0; i <= taillePlateau+1; i++)
			anneaux.add(new AnneauCases(i));
		for (int i = 0 ; i < 6 ; i++)
			branches[i]=new BrancheCase(taillePlateau);
	}
	
/*###ACCESSEURS###*/
/*###############*/
	public int getTaille(){
		return taillePlateau;
	}
	
	public void setTaille(int taillePlateau){
		this.taillePlateau = taillePlateau;
	}
	
	public int getNbPionParJoueur(){
		return nbPionParJoueur;
	}
	
	public void setNbPionParJoueur(int nbPionParJoueur){
		this.nbPionParJoueur = nbPionParJoueur;
	}
	
/*###METHODES###*/
/*#############*/
}
