package damechinoises.SD;

public class CaseBranche extends Case{
/*###ATTRIBUTS###*/
/*##############*/
	/**
	 * Correspond � la branche � laquelle appartient la case.
	 */
	private int branche;
	
/*###CONSTRUCTEURS###*/
/*##################*/
	/**
	 * Constructeur par d�faut d'une Case appartenant � une branche.
	 */
	public CaseBranche(){
		super();
		branche = 0;
	}
	
	/**
	 * Constructeur d'une case appartenant � une branche prenant un booleen et un int en param�tre.
	 * @param occupe d�finit si la case cr��e est occup�e ou non
	 * @param branche la branche � laquelle appartient cette case
	 */
	public CaseBranche(boolean occupe, int branche){
		super(occupe);
		this.branche = branche;
	}
	
/*###ACCESSEURS###*/
/*###############*/
	/**
	 * Retourne la branche � laquelle appartient cette case.
	 * @return la branche � laquelle appartient cette case.
	 */
	public int getBranche(){
		return branche;
	}
	
	/**
	 * Permet de modifier la branche de cette case.
	 * @param branche la nouvelle branche � laquelle appartient cette case
	 */
	public void setBranche(int branche){
		this.branche = branche;
	}
	
/*###METHODES###*/
/*#############*/
	
	
}
