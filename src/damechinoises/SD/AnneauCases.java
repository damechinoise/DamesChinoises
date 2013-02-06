package damechinoises.SD;

public class AnneauCases {
	
	private int tailleLigne;
	private LigneCase[] lignes;
	
	public AnneauCases(int taille){
		tailleLigne = taille;
		lignes = new LigneCase[6];
		for (int i = 0 ; i < lignes.length; i++ )
			lignes[i]=new LigneCase(tailleLigne);
		for (int i = 0 ; i < lignes.length; i++ ){
			int j = (i+1)%lignes.length;
			lignes[i].setCase(tailleLigne-1, lignes[j].getCase(0));
		}
	}
	
}
