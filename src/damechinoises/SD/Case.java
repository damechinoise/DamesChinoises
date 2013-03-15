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
	private int angle,dist,num;
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
	
	public Case(Case c){
		x = c.x;
		y = c.y;
		branche = c.branche;
		angle = c.angle;
		dist = c.dist;
		num = c.num;
		occupe = false;
		p = null;
	}
	
	public Case(boolean b,int n,int d,int nu){
		x=0;
		y=0;
		occupe=false;
		p=null;
		branche=b;
		angle=n;
		dist=d;
		num=nu;
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
	
	public boolean getBranch(){
		return branche;
	}
	
	public int getAngle(){
		return angle;
	}
	
	public int getDist(){
		return dist;
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
	
	public void setBranche(boolean b){
		this.branche=b;
	}
	
	public void setAngle(int n){
		this.angle=n;
	}
	
	public void setDist(int d){
		this.dist=d;
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
		if(p!=null){
				p.setPosition(this);
				occupe = true;
		} else
			occupe = false;
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
	
	public int value(int brancheDepart,int brancheArrive){
		int value = 0;
		if (branche){
			if(brancheDepart == angle){
				value = 0 - (dist+1) * 20;
			} 
			else if(brancheArrive==angle){
				value = (dist +1) * 20;
			} else {
				value = -100;
			}
		} else {
			if(brancheDepart == angle){
				value = 0 - (dist+1) * 4;
			} 
			else if(brancheArrive==angle){
				value = (dist+1) * 4;
			} else {
				int temp = 0;
				if(brancheArrive > 3)
					temp = brancheArrive - angle;
				else
					temp = brancheDepart - angle;
				value = 0 - dist * temp;
			}
		}
		return value;
	}
	
	public boolean equals(Case c){
		return (c.x==x && c.y==y);
	}

	public int getNum() {
		return num;
	}
}
