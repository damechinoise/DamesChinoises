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
	
	public AnneauCases(int taille,int dist){
		tailleLigne = taille;
		lignes = new LigneCase[6];
		for (int i = 0 ; i < lignes.length; i++ )
			lignes[i]=new LigneCase(tailleLigne,false,i,dist);
		for (int i = 0 ; i < lignes.length; i++ ){
			int j = (i+1)%lignes.length;
			lignes[i].setCase(tailleLigne-1, lignes[j].getCase(0));
		}
	}
	
	public LigneCase getLigne(int numLigne){
		try{
			return lignes[numLigne];
		}
		catch (Exception e){
			System.err.println("Erreur dans la récupération de la ligne");
			return null;
		}
	}
	
}
