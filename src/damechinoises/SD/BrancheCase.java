package damechinoises.SD;

import java.util.Vector;

public class BrancheCase {

	private int tailleBranche;
	private Vector<LigneCase> lignes;
	
	public BrancheCase(int taille){
		tailleBranche = taille;
		lignes = new Vector<LigneCase>();
		for (int i = 1; i <= tailleBranche ; i ++)
			lignes.add(new LigneCase(i));
	}
	
public LigneCase getLigne(int numLigne){
		try{
			return lignes.get(numLigne);
		}
		catch (Exception e){
			System.err.println("Une erreur est survenue lors de la recuperation de la ligne");
			return null;
		}
	}
	
}
