package damechinoises.SD;

public class CaseBranche extends Case{
/*###ATTRIBUTS###*/
/*##############*/
	/**
	 * Correspond à la branche à laquelle appartient la case.
	 */
	private int branche;
	
/*###CONSTRUCTEURS###*/
/*##################*/
	/**
	 * Constructeur par défaut d'une Case appartenant à une branche.
	 */
	public CaseBranche(){
		super();
		branche = 0;
	}
	
	/**
	 * Constructeur d'une case appartenant à une branche prenant un booleen et un int en paramètre.
	 * @param occupe définit si la case créée est occupée ou non
	 * @param branche la branche à laquelle appartient cette case
	 */
	public CaseBranche(boolean occupe, int branche){
		super(occupe);
		this.branche = branche;
	}
	
/*###ACCESSEURS###*/
/*###############*/
	/**
	 * Retourne la branche à laquelle appartient cette case.
	 * @return la branche à laquelle appartient cette case.
	 */
	public int getBranche(){
		return branche;
	}
	
	/**
	 * Permet de modifier la branche de cette case.
	 * @param branche la nouvelle branche à laquelle appartient cette case
	 */
	public void setBranche(int branche){
		this.branche = branche;
	}
	
/*###METHODES###*/
/*#############*/
	
	
}
