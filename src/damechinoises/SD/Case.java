package damechinoises.SD;

public class Case{
	
/*###ATTRIBUTS###*/
/*##############*/
	/**
	 * occupe correspond � l' �tat de la case, elle vaut true si la case est occup�e, false sinon.
	 */
	private boolean occupe;
	private Pion p;
	private int x,y;
	
/*###CONSTRUCTEURS###*/
/*##################*/
	/**
	 * Le constructeur par d�faut d'une case.
	 */
	public Case(){
		x=0;
		y=0;
		occupe = false;
		p=null;
	}
	
	public Case(String couleur){
		x=0;
		y=0;
		occupe = true;
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
		if(p!=null)
			return p.getCouleur();
		else
			return null;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	/**
	 * Permet de modifier l'�tat de cette case.
	 * @param occ un booleen permettant de dire si la case est occup�e ou non
	 */
	public void setOccupe(boolean occ){
		occupe=occ;
	}
	
	public void setX(int x){
		this.x=x;
	}
	
	public void setY(int y){
		this.y=y;
	}
	
	public Pion getPion(){
		return p;
	}
	
	public void setPion(Pion p){
		this.p = p;
	}
/*###METHODES###*/
/*#############*/
	
	public boolean move(Case c){
		boolean move = true;
		if (occupe){
			return false;
		}
		else{
			this.setPion(c.getPion());
			c.setPion(null);
			this.setOccupe(true);
			c.setOccupe(false);
		}
		return move;
	}
}
