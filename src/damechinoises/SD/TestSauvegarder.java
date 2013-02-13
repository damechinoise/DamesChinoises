package damechinoises.SD;

public class TestSauvegarder {


	public static void main(String[] args) {

		Sauvegarder save = new Sauvegarder();
		Joueur joueur = new JoueurHumain(5, 1, "rouge", 1);
		Case case1 = new Case(true);
		Pion pion = new Pion(joueur.getCouleur());
		Plateau plateau = new Plateau();
		
		
		save.save(1,joueur, pion, plateau);
		
	}

}
