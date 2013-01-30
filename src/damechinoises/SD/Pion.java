package damechinoises.SD;

public class Pion{

/*###ATTRIBUTS###*/
/*##############*/

	/**
	 * Correspond à la position du pion, c'est à dire la case sur laquelle il se situe.
	 * @see Case
	 */
	private Case position;

/*###CONSTRUCTEURS###*/
/*##################*/
	/**
	 * Constructeur par défaut d'un pion.
	 */
	public Pion(){
		position = new Case ();
	}
	
	/**
	 * Constructeur d'un pion.
	 * @param position la position de ce pion
	 */
	public Pion(Case position){
		this.position = position;
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
