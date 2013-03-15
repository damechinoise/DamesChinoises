package damechinoises.SD;

public class TestCharger {


	public static void main(String[] args) {

		
		Partie partie = new Partie("jeudi 28 février 2013 04 h 02 CET");
		
		System.out.println(partie.getPlateau().getTaille());
		System.out.println(partie.getNbJoueurs());

	}

}
