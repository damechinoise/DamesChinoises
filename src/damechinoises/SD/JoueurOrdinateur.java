package damechinoises.SD;

public class JoueurOrdinateur extends Joueur{
/*###ATTRIBUTS###*/
/*##############*/
		private int difficulte;
/*###CONSTRUCTEURS###*/
/*##################*/
		public JoueurOrdinateur(int taillePlateau){
			super(taillePlateau);
			super.setType("ordinateur");
			difficulte = 1;
		}
		
		public JoueurOrdinateur(int taillePlateau,Joueur copie,String nom,int difficulte){
			super(taillePlateau, copie,nom, "ordinateur");
			this.difficulte = difficulte;
		}
		
		public JoueurOrdinateur(int taillePlateau, int numero, String couleur,int difficulte, int numeroBrancheDebut){
			super(taillePlateau, numero, couleur, numeroBrancheDebut);
			super.setType("ordinateur");
			this.difficulte = difficulte;
		}
		
		public JoueurOrdinateur(int taillePlateau, int numero, String couleur,int difficulte, int numeroBrancheDebut,String nom){
			super(taillePlateau, numero, couleur, numeroBrancheDebut,nom,"ordinateur");
			this.difficulte = difficulte;
		}
		
/*###ACCESSEURS###*/
/*###############*/
		public int getDifficulte(){
			return difficulte;
		}
		
		public void setDificulte(int difficulte){
			this.difficulte = difficulte;
		}
		
/*###METHODES###*/
/*#############*/
		public void choisirPion() {
			
		}
		
}
