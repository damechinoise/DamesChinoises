package damechinoises.SD;

public class Partie {
/*###ATTRIBUTS###*/
/*##############*/
	private Plateau plateau;
	private Joueur lesJoueurs[];
	
/*###CONSTRUCTEURS###*/
/*##################*/
	public Partie(){
		int taillePlateau=5;
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
		lesJoueurs[0] = new JoueurHumain(taillePlateau,0,"bleu",0);
		lesJoueurs[1] = new JoueurHumain(taillePlateau,1,"rouge",1);
		lesJoueurs[2] = new JoueurHumain(taillePlateau,2,"orange",2);
		lesJoueurs[3] = new JoueurOrdinateur(taillePlateau,3,"vert",2,3);
		lesJoueurs[4] = new JoueurOrdinateur(taillePlateau,4,"jaune",2,4);
		lesJoueurs[5] = new JoueurOrdinateur(taillePlateau,5,"violet",2,5);
		nbpionparjoueur=plateau.getNbPionParJoueur();
		int k=0;
		int numcas=0;
		//parcours de branches
		for (int i=0;i<6;i++)
		{
			k=plateau.getTaille();
			int t=k;
			int j = 0;
			int nbpion= nbpionparjoueur;
			//parcours de pions
			while (k>0){
				if(j>0 && j%k==0){
					k--;
					j=0;
				}
				else{
					plateau.getBranche(i).getLigne(t-k).getCase(j).setPion(lesJoueurs[i].getPion(--nbpion));
					plateau.getBranche(i).getLigne(t-k).getCase(j).setOccupe(true);
					j++;
				}
			}
		}
	}
	
	public Plateau getPlateau(){
		return plateau;
	}
	
	public Joueur getJoueur(int numjoueur){
		try{
			return lesJoueurs[numjoueur];
		}
		catch (Exception e){
			System.err.println("Une erreur est survenue lors de la recuperation du joueur");
			return null;
		}
    }

	public int getNbJoueurs(){
		int nbj=lesJoueurs.length;
		return nbj;
	}
		
}