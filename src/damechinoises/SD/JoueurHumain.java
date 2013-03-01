package damechinoises.SD;

public class JoueurHumain extends Joueur{
/*###ATTRIBUTS###*/
/*##############*/
	
	
/*###CONSTRUCTEURS###*/
/*##################*/
	public JoueurHumain(int taillePlateau){
		super(taillePlateau);
		super.setType("humain");
	}
	
	public JoueurHumain(int taillePlateau, int numero, String couleur, int numeroBrancheDebut){
		super(taillePlateau, numero, couleur, numeroBrancheDebut);
		super.setType("humain");
	}
	
/*###ACCESSEURS###*/
/*###############*/
	
	
/*###METHODES###*/
/*#############*/
	public void choisirPion(){
		
	}
	
}
