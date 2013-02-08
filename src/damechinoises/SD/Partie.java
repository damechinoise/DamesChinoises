package damechinoises.SD;

public class Partie {
/*###ATTRIBUTS###*/
/*##############*/
	private Plateau plateau;
	private Joueur lesJoueurs[];
	
/*###CONSTRUCTEURS###*/
/*##################*/
	public Partie(){
		int taillePlateau=4;
		int nbpionparjoueur;
	
		plateau = new Plateau(taillePlateau);
		
		lesJoueurs = new Joueur[6];
		/*
		nb joueur
		info des joueurs
		position des pions
		au tour de..
		taille plateau
	*/
		lesJoueurs[0] = new JoueurHumain(taillePlateau,0,"Bleu",0);
		lesJoueurs[1] = new JoueurHumain(taillePlateau,1,"Rouge",1);
		lesJoueurs[2] = new JoueurHumain(taillePlateau,2,"Orange",2);
		lesJoueurs[3] = new JoueurOrdinateur(taillePlateau,3,"Vert",2,3);
		lesJoueurs[4] = new JoueurOrdinateur(taillePlateau,4,"jaune",2,4);
		lesJoueurs[5] = new JoueurOrdinateur(taillePlateau,5,"Violet",2,5);
		nbpionparjoueur=plateau.getNbPionParJoueur();
		int k=0;
		int numcas=0;
		//parcours de branches
		for (int i=0;i<6;i++)
		{
			k=0;
			numcas=0;
			//parcours de pions
			for (int j=0;j<nbpionparjoueur;j++){
				if(j == 1 || j == 3 || j == 6){
					k++;
					numcas=0;
				}
				lesJoueurs[i].getPion(j).setPosition(plateau.getBranche(i).getLigne(k).getCase(numcas));
				numcas++;
			}
		}
	}
		
}