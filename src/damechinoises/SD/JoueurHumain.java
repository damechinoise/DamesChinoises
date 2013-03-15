package damechinoises.SD;

import damechinoises.jeux.PlateauAffichage;

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

	@Override
	public void choisirPion(Partie partie, PlateauAffichage plateauAffichage) {
	}

	@Override
	public Joueur copy(int taillePlateau) {
		JoueurHumain copy = new JoueurHumain(taillePlateau);
		copy.setNumBrancheDebut(getNumBrancheDebut());
		copy.setNumBrancheFin(getNumBrancheFin());
		for(int i = 0; i < this.getNbPions() ; i++){
			copy.setPion(i,new Pion(this.getPion(i)));
		}
		return copy;
	}
	
}
