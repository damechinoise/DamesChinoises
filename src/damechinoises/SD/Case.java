package damechinoises.SD;

public class Case{
	
/*###ATTRIBUTS###*/
/*##############*/
	/**
	 * occupe correspond à l' état de la case, elle vaut true si la case est occupée, false sinon.
	 */
	private boolean occupe;
	private String couleur;
	
/*###CONSTRUCTEURS###*/
/*##################*/
	/**
	 * Le constructeur par défaut d'une case.
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
	 * Un constructeur d'une case prenant un booleen en paramètre.
	 * @param occupe définit si la case créée est occupée ou non
	 */
	public Case(boolean occupe){
		this.occupe = occupe;
	}
	
/*###ACCESSEURS###*/
/*###############*/
	/**
	 * Retourne l'état de cette case.
	 * @return true si la case est occupée, false sinon
	 */
	public boolean getOccupe(){
		return occupe;
	}
	
	public String getCouleur(){
		return couleur;
	}
	/**
	 * Permet de modifier l'état de cette case.
	 * @param occ un booleen permettant de dire si la case est occupée ou non
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
