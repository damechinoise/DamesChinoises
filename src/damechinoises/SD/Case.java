package damechinoises.SD;

public class Case{
	
/*###ATTRIBUTS###*/
/*##############*/
	/**
	 * occupe correspond � l' �tat de la case, elle vaut true si la case est occup�e, false sinon.
	 */
	private boolean occupe;
	private String couleur;
	
/*###CONSTRUCTEURS###*/
/*##################*/
	/**
	 * Le constructeur par d�faut d'une case.
	 */
	public Case(){
		occupe = false;
		couleur = null;
	}
	
	public Case(String couleur){
		occupe = true;
		this.couleur = couleur;
	}
	
	/**
	 * Un constructeur d'une case prenant un booleen en param�tre.
	 * @param occupe d�finit si la case cr��e est occup�e ou non
	 */
	public Case(boolean occupe){
		this.occupe = occupe;
	}
	
/*###ACCESSEURS###*/
/*###############*/
	/**
	 * Retourne l'�tat de cette case.
	 * @return true si la case est occup�e, false sinon
	 */
	public boolean getOccupe(){
		return occupe;
	}
	
	public String getCouleur(){
		return couleur;
	}
	/**
	 * Permet de modifier l'�tat de cette case.
	 * @param occ un booleen permettant de dire si la case est occup�e ou non
	 */
	public void setOccupe(boolean occ){
		occupe=occ;
	}
	
	public void setCouleur(String couleur){
		this.couleur=couleur;
	}
/*###METHODES###*/
/*#############*/
	
}
