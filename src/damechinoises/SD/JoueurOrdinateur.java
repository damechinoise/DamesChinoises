package damechinoises.SD;

public class JoueurOrdinateur extends Joueur{
/*###ATTRIBUTS###*/
/*##############*/
		private String difficulte;
		
/*###CONSTRUCTEURS###*/
/*##################*/
		public JoueurOrdinateur(){
			super();
			difficulte = new String("facile");
		}
		
		public JoueurOrdinateur(int numero, String couleur,String difficulte){
			super(numero, couleur);
			difficulte = new String(difficulte);
		}
		
/*###ACCESSEURS###*/
/*###############*/
		public String getDifficulte(){
			return difficulte;
		}
		
		public void setDificulte(String difficulte){
			this.difficulte = difficulte;
		}
		
/*###METHODES###*/
/*#############*/
		public void choisirPion() {
			
		}
		
}
