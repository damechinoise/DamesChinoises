package damechinoises.SD;

import java.util.Vector;


public class MouvementPion {
	
	private PlateauAI plateau;
	
	public MouvementPion(PlateauAI p){
		this.plateau = p;
	}
	
	public Case selCase(int x,int y){
		int taille = plateau.getTaille();
		int casex,casey;
		for (int c = 1; c < 1+taille ; c ++){
				casex = plateau.getCase(c).getX();
				casey = plateau.getCase(c).getY();
				if(x>= casex-12 && x<=casex +12 && y>= casey-12 && y<=casey +12 ){
					return plateau.getCase(c);
			}
		}
		return null;
	}
	
	public int move(Case d, Case a){
		Vector<Case> poss = mvtpossibles(d);
		for(int i = 0; i < poss.size(); i++)
			if(poss.get(i).equals(a)){
				Vector<Case> tour = scan(d);
				a.move(d);
				for(int j = 0; j < tour.size(); j++)
					if(tour.get(j)!=null && tour.get(j).equals(a))
						return 0;
				return 1;
			}
		return -1;		
	}
	
	public Vector<Case> scan(Case c){
		Vector<Case> v = new Vector<Case>();
		for(double angle = 0 ; angle < 2*Math.PI ; angle+=Math.PI/3){
			v.add(selCase((int)(c.getX()+Math.cos(angle)*30),(int)(c.getY()+Math.sin(angle)*30)));
		}
		return v;
	}
	
	public Vector<Case> mvtpossibles(Case c){
		Vector<Case> v = new Vector<Case>();
		Vector<Case> s = scan(c);
		for(int i = 0 ; i < s.size(); i++)
			if(s.get(i)!=null && !s.get(i).getOccupe())
				v.add(s.get(i));
			else if (s.get(i)!=null){
				Case temp = saut(c,s.get(i));
				if(temp != null && !temp.getOccupe()){
					v.add(temp);
					v.addAll(mvtpossibles(c, temp,v));
				}
			}
		for(int i = 0 ; i < v.size(); i++)
			if(v.get(i).equals(c))
				v.remove(i);
		return v;
	}
	
	public Vector<Case> mvtpossibles(Case dep,Case arr,Vector<Case> vec){
		Vector<Case> s = scan(arr);
		Vector<Case> r = new Vector<Case>();
		for(int i = 0 ; i < s.size(); i++){
			if (s.get(i)!=null && s.get(i).getOccupe()){
				Case temp = saut(arr,s.get(i));
				if(temp != null && !temp.getOccupe() && !temp.equals(dep) && !vec.contains(temp)){
					vec.add(temp);
					r.addAll(mvtpossibles(arr,temp,vec));
				}
			}
		}
		return r;
	}
	
	public Case sautIntermediaire( Case d,Case a){
		return selCase(d.getX()+(a.getX()-d.getX())/2,d.getY()+(a.getY()-d.getY())/2);
	}
	
	public Case saut(Case d,Case s){
		return selCase(d.getX()+2*(s.getX()-d.getX()),d.getY()+2*(s.getY()-d.getY()));
	}
}
