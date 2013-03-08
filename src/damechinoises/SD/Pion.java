package damechinoises.SD;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Pion{

/*###ATTRIBUTS###*/
/*##############*/

	/**
	 * Correspond à la position du pion, c'est à dire la case sur laquelle il se situe.
	 * @see Case
	 */
	private Case position;
	private String couleur;
	
	JLabel casegraph;
	ImageIcon iconefond;

/*###CONSTRUCTEURS###*/
/*##################*/
	/**
	 * Constructeur par défaut d'un pion.
	 */
	public Pion(){
		position = null;
	}
	
	public Pion(Pion copie){
		this.position=copie.getPosition();
		this.couleur=copie.getCouleur();
	}
	
	/**
	 * Constructeur d'un pion.
	 * @param position la position de ce pion
	 */
	public Pion(String couleur){
		this.couleur=couleur;
	}
	
/*###ACCESSEURS###*/
/*###############*/
	public String getCouleur(){
		return couleur;
	}
	
	public void setCouleur(String couleur){
		this.couleur=couleur;
	}
	
	public Case getPosition(){
		return position;
	}
	
	public void setPosition(Case p){
		position=p;
	}
/*###METHODES###*/
/*#############*/
	public void deplacer(){
		
	}
}
