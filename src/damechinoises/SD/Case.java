package damechinoises.SD;

public class Case{
	
/*###ATTRIBUTS###*/
/*##############*/
	/**
	 * occupe correspond à l' état de la case, elle vaut true si la case est occupée, false sinon.
	 */
	private boolean occupe;
	private Pion p;
	private int x,y;
	private boolean branche;
	private int angle,dist;
/*###CONSTRUCTEURS###*/
/*##################*/
	/**
	 * Le constructeur par défaut d'une case.
	 */
	public Case(){
		x=0;
		y=0;
		occupe = false;
		p=null;
	}
	
	public Case(boolean b,int n,int d){
		x=0;
		y=0;
		occupe=false;
		p=null;
		branche=b;
		angle=n;
		dist=d;
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
	 * Permet de modifier l'état de cette case.
	 * @param occ un booleen permettant de dire si la case est occupée ou non
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
		if(p!=null)
				p.setPosition(this);
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
	
	public int value(int brancheDepart){
		int value = 0;
		if (branche){
			if(brancheDepart == angle){
				value = 0 - dist * 20;
			} 
			else if((brancheDepart+3)%6==angle){
				value = dist * 20;
			} else {
				value = -100;
			}
		} else {
			int aTemp = (brancheDepart-angle);
			if (aTemp < 0)
				aTemp = -aTemp;
			if(aTemp == 4)
				aTemp+=2;
			switch(aTemp){
			case(0):
				value=20-dist;
				break;
			case(3):
				value=20+dist;
				break;
			default:
				value =10-dist;
			}
		}
		return value;
	}
	
	public boolean equals(Case c){
		return (c.x==x && c.y==y);
	}
}
