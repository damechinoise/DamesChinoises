package damechinoises.SD;

import java.util.Vector;

import damechinoises.jeux.PlateauAffichage;

public class JoueurOrdinateur extends Joueur{
/*###ATTRIBUTS###*/
/*##############*/
		private int difficulte;
		private Vector<Case> mvtprec =null;
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
		public void choisirPion(Partie p,PlateauAffichage pA) {
			ArbreAI a = new ArbreAI(p,p.getNbJoueurs()*(difficulte-1)+1,getNumero());
			mvtprec = a.selectionCoup(pA,mvtprec);
			Case arr = mvtprec.get(0);
			Case dep = mvtprec.get(1);
			pA.move(pA.selCase(dep.getX(),dep.getY()),pA.selCase(arr.getX(),arr.getY()));
			this.ajoutCoup();
		}

		@Override
		public void choisirPion() {
			// TODO Auto-generated method stub
			
		}
		
		public Joueur copy(int taillePlateau) {
			JoueurOrdinateur copy = new JoueurOrdinateur(taillePlateau);
			copy.setNumBrancheDebut(getNumBrancheDebut());
			copy.setNumBrancheFin(getNumBrancheFin());
			for(int i = 0; i < this.getNbPions() ; i++){
				copy.setPion(i,new Pion(this.getPion(i)));
			}
			return copy;
		}
		
}
