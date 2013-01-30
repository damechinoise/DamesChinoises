package damechinoises.SD;

import java.util.Vector;

public class LigneCase {
	
	private Vector<Case> cases;
	private int tailleLigne;
	
	public LigneCase(int taille){
		tailleLigne = taille;
		cases = new Vector<Case>();
		for (int i = 0 ; i < tailleLigne ; i++)
			cases.add(new Case());
	}
	
	public Case getCase(int numCase){
		try{
			return cases.get(numCase);
		}
		catch (Exception e){
			System.err.println("Une erreur est survenue lors de la recuperation de la case");
			return null;
		}
	}
	
	public void setCase(int numCase,Case c){
		cases.set(numCase, c);
	}
	
	public int getTaille(){
		return tailleLigne;
	}
	
	public void setTaille(int taille){
		tailleLigne= taille;
	}
}
