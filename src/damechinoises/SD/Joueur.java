package damechinoises.SD;


/**
 * La classe joueur correspond à un joueur du jeu de dames chinoises.
 * @author Jordan Lepretre
 * @version 1.0
 *
 */
public abstract class Joueur {
/*###ATTRIBUTS###*/
/*##############*/
	/**
	 * Correspond au numéro du joueur.
	 */
	private int numero;
	
	/**
	 * Correspond à la couleur du joueur.
	 */
	private String couleur;
	
	/**
	 * Correspond aux pions du joueur.
	 */
	private Pion lesPions[];
	
	/**
	 * Correspond à la branche de départ du joueur.
	 */
	private int numBrancheDebut;
	
	/**
	 * Correspond à la branche d'arrivée du joueur.
	 */
	private int numBrancheFin;
	
/*###CONSTRUCTEURS###*/
/*##################*/
	/**
	 * Constructeur par défaut d'un joueur.
	 * @param taillePlateau la taille du plateau
	 */
	public Joueur(int taillePlateau){
		int nbPionParJoueur = 0;
		
		for (int i = 0; i < taillePlateau; i++){
			nbPionParJoueur += taillePlateau-i;
		}
		
		numero = 0;
		couleur = new String("Blanc");
		lesPions = new Pion[nbPionParJoueur];
		
		for (int i = 0; i < nbPionParJoueur; i++){
			lesPions[i] = new Pion();
		}
	}
	
	/**
	 * Constructeur d'un joueur prenant en paramètre un numéro et une couleur
	 * @param taillePlateau la taille du plateau
	 * @param numero le numéro du joueur
	 * @param couleur la couleur du joueur
	 */
	public Joueur(int taillePlateau, int numero, String couleur, int numBrancheDebut){
		int nbPionParJoueur = 0;
		
		for (int i = 1; i <= taillePlateau; i++){
			nbPionParJoueur += i;
		}
		
		this.numero = numero;
		this.couleur = couleur;
		this.numBrancheDebut = numBrancheDebut;
		this.numBrancheFin = (numBrancheDebut + 3)%6;
		lesPions = new Pion[nbPionParJoueur];
		
		for (int i = 0; i < nbPionParJoueur; i++){
			lesPions[i] = new Pion(couleur); //PLACER LES PIONS//
		}
	}
	
/*###ACCESSEURS###*/
/*###############*/
	/**
	 * Retourne le numéro de ce joueur.
	 * @return le numéro du joueur
	 */
	public int getNumero(){
		return numero;
	}
	
	/**
	 * Modifie le numéro du joueur.
	 * @param numero le nouveau numéro du joueur
	 */
	public void setNumero(int numero){
		this.numero = numero;
	}
	
	/**
	 * Retourne la couleur du joueur.
	 * @return la couleur du joueur
	 */
	public String getCouleur(){
		return couleur;
	}
	
	/**
	 * Modifie la couleur du joueur.
	 * @param couleur la nouvelle couleur du joueur
	 */
	public void setCouleur(String couleur){
		this.couleur = couleur;
	}
	
	/**
	 * Retourne un pion du joueur.
	 * @param numeroPion le numéro du pion souhaité
	 * @return le pion du joueur
	 */
	public Pion getPion(int numeroPion){
		return lesPions[numeroPion];
	}

	
	/**
	 * Retourne le numéro de la branche de départ de ce joueur.
	 * @return le numéro de la branche de départ de ce joueur
	 */
	public int getNumBrancheDebut(){
		return numBrancheDebut;
	}
	
	/**
	 * Modifie le numéro de la branche de départ de ce joueur.
	 * @param num le nouveau numéro de la branche de départ de ce joueur
	 */
	public void setNumBrancheDebut(int num){
		this.numBrancheDebut = num;
	}
	
	/**
	 * Retourne le numéro de la branche d'arrivée de ce joueur.
	 * @return le numéro de la branche d'arrivée de ce joueur
	 */
	public int getNumBrancheFin(){
		return numBrancheFin;
	}
	
	/**
	 * Modifie le numéro de la branche d'arrivée de ce joueur.
	 * @param num le nouveau numéro de la branche d'arrivée de ce joueur
	 */
	public void setNumBrancheFin(int num){
		this.numBrancheFin = num;
	}
	
/*###METHODES###*/
/*#############*/
	public int nbPions(){
		return lesPions.length;
	}
	public abstract void choisirPion();
	
	public boolean pionAppartient(Pion p){
		for (int i = 0 ; i < nbPions() ; i++)
			if(lesPions[i].getPosition().equals(p.getPosition()))
				return true;
		return false;
		}
}
