package damechinoises.SD;


/**
 * La classe joueur correspond � un joueur du jeu de dames chinoises.
 * @author Jordan Lepretre
 * @version 1.0
 *
 */
abstract class Joueur {
/*###ATTRIBUTS###*/
/*##############*/
	/**
	 * Correspond au num�ro du joueur.
	 */
	private int numero;
	
	/**
	 * Correspond � la couleur du joueur.
	 */
	private String couleur;
	
	/**
	 * Correspond aux pions du joueur.
	 */
	private Pion lesPions[];
	
/*###CONSTRUCTEURS###*/
/*##################*/
	/**
	 * Constructeur par d�faut d'un joueur.
	 */
	public Joueur(){
		numero = 0;
		couleur = new String("Blanc");
		lesPions = new Pion[10];//A MODIFIER EN RECUPERANT NBPIONPARJOUEUR//
		
		for (int i = 0; i < 10; i++){
			lesPions[i] = new Pion();
		}
	}
	
	/**
	 * Constructeur d'un joueur prenant en param�tre un num�ro et une couleur
	 * @param numero le num�ro du joueur
	 * @param couleur la couleur du joueur
	 */
	public Joueur(int numero, String couleur){
		this.numero = numero;
		this.couleur = couleur;
		lesPions = new Pion[5];//A MODIFIER EN RECUPERANT NBPIONPARJOUEUR//
		
		for (int i = 0; i < 10; i++){
			lesPions[i] = new Pion(/*TROUVER MOYEN DE CHANGER LA POSITION DE CHAQUE PION*/);  
		}
	}
	
/*###ACCESSEURS###*/
/*###############*/
	/**
	 * Retourne le num�ro de ce joueur.
	 * @return le num�ro du joueur
	 */
	public int getNumero(){
		return numero;
	}
	
	/**
	 * Modifie le num�ro du joueur.
	 * @param numero le nouveau num�ro du joueur
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
	 * @param numeroPion le num�ro du pion souhait�
	 * @return le pion du joueur
	 */
	public Pion getPion(int numeroPion){
		return lesPions[numeroPion];
	}
	
	/**
	 * Modifie la position d'un pion
	 * @param numeroPion le num�ro du pion que l'on souhaite modifier
	 * @param position la nouvelle position du pion
	 */
	public void setPion(int numeroPion, Case position){
		this.lesPions[numeroPion].setPosition(position);
	}
	
/*###METHODES###*/
/*#############*/
	 public abstract void choisirPion();
	
}
