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
		for (int i = 1; i <= taillePlateau+1; i++)
			anneaux.add(new AnneauCases(i));
		for (int i = 0 ; i < 6 ; i++)
			branches[i]=new BrancheCase(taillePlateau);
	}
	
	public Plateau(int taillePlateau){
		this.taillePlateau=taillePlateau;
		anneaux = new Vector<AnneauCases>();
		branches = new BrancheCase[6];
		nbPionParJoueur = 0;
		for (int i =1 ; i<= taillePlateau ; i++)
			nbPionParJoueur+=i;
		for (int i = 1; i <= taillePlateau+1; i++)
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
	
	public BrancheCase getBranche(int numBranche){
		try{
			return branches[numBranche];
		}
		catch (Exception e){
			System.err.println("Une erreur est survenue lors de la recuperation de la branche");
			return null;
		}
	}
	
	public AnneauCases getAnneau(int numAnneau){
		try{
			return anneaux.get(numAnneau);
		}
		catch (Exception e){
			System.err.println("Une erreur est survenue lors de la recuperation de l'anneau");
			return null;
		}
	}
/*###METHODES###*/
/*#############*/
}
