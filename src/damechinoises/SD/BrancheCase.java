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
}
