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
	
	public JoueurHumain(int taillePlateau,Joueur copie,String nom){
		super(taillePlateau, copie,nom, "humain");
	}
	
	public JoueurHumain(int taillePlateau, int numero, String couleur, int numeroBrancheDebut){
		super(taillePlateau, numero, couleur, numeroBrancheDebut);
		super.setType("humain");
	}
	
	public JoueurHumain(int taillePlateau, int numero, String couleur, int numBrancheDebut,String nom){
		super(taillePlateau, numero, couleur, numBrancheDebut,nom,"humain");
	}
	
/*###ACCESSEURS###*/
/*###############*/
	
	
/*###METHODES###*/
/*#############*/
	public void choisirPion(){
		
	}
	
}
