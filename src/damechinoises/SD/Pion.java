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
		position = new Case ();
		
		iconefond= new ImageIcon("casevide.jpg","case vide");
		casegraph=new JLabel(iconefond);
		casegraph.setSize(32,32);
	}
	
	/**
	 * Constructeur d'un pion.
	 * @param position la position de ce pion
	 */
	public Pion(Case position, String couleur){
		
		this.position = position;
		this.couleur=couleur;
		if (couleur.equals("vide"))
			iconefond= new ImageIcon("Images/casevide.png","case vide");
		if (couleur.equals("bleu"))
			iconefond= new ImageIcon("Images/casebleu.png","case vide");
		if (couleur.equals("rouge"))
			iconefond= new ImageIcon("Images/caserouge.png","case vide");
		if (couleur.equals("orange"))
			iconefond= new ImageIcon("Images/caseorange.png","case vide");
		if (couleur.equals("vert"))
			iconefond= new ImageIcon("Images/casevert.png","case vide");
		if (couleur.equals("jaune"))
			iconefond= new ImageIcon("Images/casejaune.png","case vide");
		if (couleur.equals("violet"))
			iconefond= new ImageIcon("Images/caseviolet.png","case vide");
		casegraph=new JLabel(iconefond);
		casegraph.setSize(32,32);
	}
	
/*###ACCESSEURS###*/
/*###############*/
	/**
	 * Retourne la position de ce pion
	 * 
	 * @return la case correspondant à la position du pion
	 */
	public Case getPosition(){
		return position;
	}

	/**
	 * Permet de modifier la position de ce pion
	 * 
	 * @param pos la nouvelle position de ce pion
	 */
	public void setPosition(Case pos){
		position.setOccupe(false);
		position=pos;
		pos.setOccupe(true);
	}
	
/*###METHODES###*/
/*#############*/
	public void deplacer(){
		
	}
}
