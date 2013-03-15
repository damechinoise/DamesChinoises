package damechinoises.SD;

import java.util.Random;
import java.util.Vector;

public class PlateauAI {
	
	private Vector<Case> cases;
	private Joueur[] joueurs;
	private MouvementPion m;
	private int taille;
	private int tailleplateau;
	private int nbpions;
	
	public PlateauAI (Partie p){
		m = new MouvementPion(this);
		taille = p.getPlateau().getTaille();
		tailleplateau = p.getPlateau().getTaille();
		joueurs = new Joueur[p.getNbJoueurs()];
		cases = new Vector<Case>();
		nbpions = p.getPlateau().getNbPionParJoueur();
		for (int i = 0 ; i < p.getNbJoueurs() ; i++){
			joueurs[i]=p.getJoueur(i).copy(tailleplateau);
		}
		cases.add(new Case(p.getPlateau().getAnneau(0).getLigne(0).getCase(0)));
		for (int anneau = 1; anneau < 1+taille ; anneau ++)
			for (int cases = 0 ; cases < 6 ; cases ++)
				for (int i = 0 ; i < anneau ; i++)
					this.cases.add(new Case(p.getPlateau().getAnneau(anneau).getLigne(cases).getCase(i)));
		int taillebranche = taille;
		for (int branche = 0 ; branche < taille ; branche++){
			for (int cases = 0 ; cases < 6 ; cases ++)
				for (int i = 0 ; i < taillebranche ; i++ )
					this.cases.add(new Case(p.getPlateau().getBranche(cases).getLigne(branche).getCase(i)));
			taillebranche --;
		}
		taille = cases.size();
		for (int i = 0; i < p.getNbJoueurs() ; i ++){
			Joueur j = joueurs[i];
			for(int k =0 ; k < nbpions; k++){
				Pion pi = j.getPion(k);
				Case pos = pi.getPosition();
				pi.setPosition(m.selCase(pos.getX(), pos.getY()));
				pi.getPosition().setPion(pi);
			}
		}
	}
	
	public PlateauAI(PlateauAI copie){
		taille = copie.taille;
		tailleplateau = copie.tailleplateau;
		m = new MouvementPion(this);
		joueurs = new Joueur[copie.getNbJoueurs()];
		nbpions = copie.nbpions;
		cases = new Vector<Case>();
		for (int i = 0 ; i < copie.getNbJoueurs() ; i++){
			joueurs[i]=copie.getJoueur(i).copy(tailleplateau);
		}
		for (int i = 0; i < taille ; i ++)
			cases.add(new Case(copie.getCase(i)));
		for (int i = 0; i < copie.getNbJoueurs() ; i ++){
			Joueur j = joueurs[i];
			for(int k =0 ; k < nbpions; k++){
				Pion pi = j.getPion(k);
				pi.setPosition(m.selCase(pi.getPosition().getX(), pi.getPosition().getY()));
				pi.getPosition().setPion(pi);
			}
		}
	}
	public Case getCase(int c){
		try{
			return cases.get(c);
		} catch (Exception e){
			return null;
		}
	}
	
	public int getTaille(){
		return cases.size();
	}

	public int getNbJoueurs() {
		return joueurs.length;
	}
	
	public int getNbPions(){
		return nbpions;
	}
	
	public int etatJoueur(int numJoueur){
		int etat = 0;
		try {
			for (int i = 0 ; i < nbpions ; i++ ){
				int val =joueurs[numJoueur].getPion(i).getPosition().value(joueurs[numJoueur].getNumBrancheDebut(),joueurs[numJoueur].getNumBrancheFin()); 
				etat+=val;
				//System.out.println("Valeur du pion "+i+" du joueur "+numJoueur+" : "+val);
			}
		}
		catch (Exception e){
			etat = 0;
		}
		return etat;
	}

	public Joueur getJoueur(int i) {
		try{
			return joueurs[i];
		} catch (Exception e){
			return null;
		}
	}

	public PlateauAI tourJoueurAI(int joueur, int pion, int prio){
		PlateauAI suivant = new PlateauAI(this);
		Pion p = suivant.joueurs[joueur].getPion(pion);
		Vector<Case> mvt = suivant.m.mvtpossibles(p.getPosition());
		if(mvt.size() != 0){
			int max = 0;
			int maxval = mvt.get(max).value(joueurs[joueur].getNumBrancheDebut(),joueurs[joueur].getNumBrancheFin());
			for(int i = 1 ; i < mvt.size(); i ++){
				int val = mvt.get(i).value(joueurs[joueur].getNumBrancheDebut(),joueurs[joueur].getNumBrancheFin());
				if(val > maxval){
					maxval = val;
					max = i;
				}
			}
			suivant.m.move(p.getPosition(), mvt.get(max));
			return suivant;
		}
		return null;
	}

	public int joueurSuivant(int tourDe) {
		int jS = tourDe;
		jS++;
		jS=jS%getNbJoueurs();
		while(joueurs[jS].fini()){
			jS++;
			jS=jS%getNbJoueurs();
		}
		return jS;
	}
	
	public Vector<Case> changement (PlateauAI p){
		Vector<Case> ret = new Vector<Case>();
		for(int i = 0 ; i < p.getNbJoueurs() ; i ++)
			for(int j = 0 ; j < nbpions ; j++)
				if(!p.joueurs[i].getPion(j).getPosition().equals(joueurs[i].getPion(j).getPosition())){
					ret.add( p.joueurs[i].getPion(j).getPosition());
					ret.add( joueurs[i].getPion(j).getPosition());
					return ret;
				}
		return null;
	}
	
}
