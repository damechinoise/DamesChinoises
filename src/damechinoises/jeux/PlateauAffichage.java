package damechinoises.jeux;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import damechinoises.SD.Case;
import damechinoises.SD.NoeudAI;
import damechinoises.SD.Partie;
import damechinoises.SD.Plateau;

public class PlateauAffichage extends JPanel {

	private Plateau plateau;
	private int taille;
	private Partie partie;
	
	public PlateauAffichage(Partie p){
		partie =p;
		plateau = p.getPlateau();
		this.setBackground(Color.BLACK);
		this.addMouseListener(new ClicListener());
	}
	
	public void updateFirst(){
		Dimension d =this.getSize();
		double height = d.getHeight();
		double width = d.getWidth();
		taille =plateau.getTaille();
		double xmid = width /2;
		double ymid = height /2;
		this.affCase((int)xmid,(int)ymid,24,plateau.getAnneau(0).getLigne(0).getCase(0).getCouleur());
		plateau.getAnneau(0).getLigne(0).getCase(0).setX((int)xmid);
		plateau.getAnneau(0).getLigne(0).getCase(0).setY((int)ymid);
		double[] posx = new double[6];
		double[] posy = new double[6];
		for (int anneau = 1; anneau < 1+taille ; anneau ++){
			for (int cases = 0 ; cases < 6 ; cases ++){
					double angle = 0 + cases * Math.PI / 3 ;
					posx[cases] = xmid + Math.cos(angle) * 30 *  anneau;
					posy[cases] = ymid + Math.sin(angle) * 30 * anneau;
					this.affCase((int)posx[cases],(int)posy[cases],24,plateau.getAnneau(anneau).getLigne(cases).getCase(0).getCouleur());
					plateau.getAnneau(anneau).getLigne(cases).getCase(0).setX((int)posx[cases]);
					plateau.getAnneau(anneau).getLigne(cases).getCase(0).setY((int)posy[cases]);
			}
			for (int cases = 0 ; cases < 6 ; cases ++){
				for (int i = 1 ; i < anneau ; i++){
					double x = posx[cases] - i*(posx[cases]-posx[(cases+1)%6])/anneau;
					double y = posy[cases] - i*(posy[cases]-posy[(cases+1)%6])/anneau;
					this.affCase((int)x,(int)y,24,plateau.getAnneau(anneau).getLigne(cases).getCase(i).getCouleur());
					plateau.getAnneau(anneau).getLigne(cases).getCase(i).setX((int)x);
					plateau.getAnneau(anneau).getLigne(cases).getCase(i).setY((int)y);
				}
			}
		}
		int taillebranche = taille;
		for (int branche = taille +1 ; branche < 2*taille + 1 ; branche++){
			for (int cases = 0 ; cases < 6 ; cases ++){
				double angle = 0 + cases * Math.PI / 3 ;
				posx[cases] = xmid + Math.cos(angle) * 30 *  branche;
				posy[cases] = ymid + Math.sin(angle) * 30 * branche;
			}
			for (int cases = 0 ; cases < 6 ; cases ++){
				for (int i = (branche-taillebranche)/2 +1 ; i < (branche+taillebranche)/2 +1 ; i++ ){
					double x = posx[cases] - i*(posx[cases]-posx[(cases+1)%6])/branche;
					double y = posy[cases] - i*(posy[cases]-posy[(cases+1)%6])/branche;
					this.affCase((int)x,(int)y,24,plateau.getBranche(cases).getLigne(branche-taille-1).getCase(i-((branche-taillebranche)/2)-1).getCouleur());
					plateau.getBranche(cases).getLigne(branche-taille-1).getCase(i-((branche-taillebranche)/2)-1).setX((int)x);
					plateau.getBranche(cases).getLigne(branche-taille-1).getCase(i-((branche-taillebranche)/2)-1).setY((int)y);
				}
			}
			taillebranche --;
		}
	}
	
	public void update(){
		Dimension d =this.getSize();
		double height = d.getHeight();
		double width = d.getWidth();
		taille =plateau.getTaille();
		double xmid = plateau.getAnneau(0).getLigne(0).getCase(0).getX();
		double ymid = plateau.getAnneau(0).getLigne(0).getCase(0).getY();
		this.affCase((int)xmid,(int)ymid,24,plateau.getAnneau(0).getLigne(0).getCase(0).getCouleur());
		double[] posx = new double[6];
		double[] posy = new double[6];
		for (int anneau = 1; anneau < 1+taille ; anneau ++){
			for (int cases = 0 ; cases < 6 ; cases ++){
					posx[cases] = plateau.getAnneau(anneau).getLigne(cases).getCase(0).getX();
					posy[cases] = plateau.getAnneau(anneau).getLigne(cases).getCase(0).getY();
					this.affCase((int)posx[cases],(int)posy[cases],24,plateau.getAnneau(anneau).getLigne(cases).getCase(0).getCouleur());
			}
			for (int cases = 0 ; cases < 6 ; cases ++){
				for (int i = 1 ; i < anneau ; i++){
					int x = plateau.getAnneau(anneau).getLigne(cases).getCase(i).getX();
					int y = plateau.getAnneau(anneau).getLigne(cases).getCase(i).getY();
					this.affCase((int)x,(int)y,24,plateau.getAnneau(anneau).getLigne(cases).getCase(i).getCouleur());
				}
			}
		}
		int taillebranche = taille;
		for (int branche = taille +1 ; branche < 2*taille + 1 ; branche++){
			for (int cases = 0 ; cases < 6 ; cases ++){
				for (int i = (branche-taillebranche)/2 +1 ; i < (branche+taillebranche)/2 +1 ; i++ ){
					int x = plateau.getBranche(cases).getLigne(branche-taille-1).getCase(i-((branche-taillebranche)/2)-1).getX();
					int y = plateau.getBranche(cases).getLigne(branche-taille-1).getCase(i-((branche-taillebranche)/2)-1).getY();
					this.affCase((int)x,(int)y,24,plateau.getBranche(cases).getLigne(branche-taille-1).getCase(i-((branche-taillebranche)/2)-1).getCouleur());
				}
			}
			taillebranche --;
		}
	}
	
	public void affCase(int x,int y,int d,String couleur) {
		Graphics g = this.getGraphics();
		if (!(couleur!=null)){
			g.setColor(Color.LIGHT_GRAY);
		}
		else{
			if (couleur.equals("bleu"))
				g.setColor(Color.blue);
			if (couleur.equals("rouge"))
				g.setColor(Color.red);
			if (couleur.equals("orange"))
				g.setColor(Color.orange);
			if (couleur.equals("vert"))
				g.setColor(Color.green);
			if (couleur.equals("jaune"))
				g.setColor(Color.yellow);
			if (couleur.equals("violet"))
				g.setColor(Color.magenta);
			if (couleur.equals("fond"))
				g.setColor(Color.DARK_GRAY);
		}
		g.fillOval(x-d/2, y-d/2, d, d);
	    g.drawOval(x-d/2, y-d/2, d, d);
	}
	
	public void highLight(int x,int y,int d){
		Graphics g = this.getGraphics();
		g.setColor(Color.WHITE);
		g.fillOval(x-4, y-8, 8, 16);
	}
	
	public void affCase(int x,int y){
		int casex =plateau.getAnneau(0).getLigne(0).getCase(0).getX();
		int casey =plateau.getAnneau(0).getLigne(0).getCase(0).getY();
		if(x>= casex-12 && x<=casex +12 && y>= casey-12 && y<=casey +12 ){
			System.out.println("Tu as clique sur la case 0 de la ligne 0 de l'anneau 0");
			return;
		}
		for (int anneau = 1; anneau < 1+taille ; anneau ++){
			for (int cases = 0 ; cases < 6 ; cases ++){
				casex = plateau.getAnneau(anneau).getLigne(cases).getCase(0).getX();
				casey = plateau.getAnneau(anneau).getLigne(cases).getCase(0).getY();
				if(x>= casex-12 && x<=casex +12 && y>= casey-12 && y<=casey +12 ){
					System.out.println("Tu as clique sur la case 0 de la ligne "+ cases +" de l'anneau "+ anneau);
					return;
				}
			}
			for (int cases = 0 ; cases < 6 ; cases ++){
				for (int i = 1 ; i < anneau ; i++){
					casex = plateau.getAnneau(anneau).getLigne(cases).getCase(i).getX();
					casey = plateau.getAnneau(anneau).getLigne(cases).getCase(i).getY();
					if(x>= casex-12 && x<=casex +12 && y>= casey-12 && y<=casey +12 ){
						System.out.println("Tu as clique sur la case "+i+" de la ligne "+ cases +" de l'anneau "+ anneau);
						return;
					}
				}
			}
		}
		int taillebranche = taille;
		for (int branche = taille +1 ; branche < 2*taille + 1 ; branche++){
			for (int cases = 0 ; cases < 6 ; cases ++){
				for (int i = (branche-taillebranche)/2 +1 ; i < (branche+taillebranche)/2 +1 ; i++ ){
					casex = plateau.getBranche(cases).getLigne(branche-taille-1).getCase(i-((branche-taillebranche)/2)-1).getX();
					casey = plateau.getBranche(cases).getLigne(branche-taille-1).getCase(i-((branche-taillebranche)/2)-1).getY();
					if(x>= casex-12 && x<=casex +12 && y>= casey-12 && y<=casey +12 ){
						System.out.println("Tu as clique sur la case "+i+" de la ligne "+ (branche-taille) +" de la branche "+ cases);
						return;
					}
				}
			}
			taillebranche --;
		}
	}
	
	
	public Case selCase(int x,int y){
		int casex =plateau.getAnneau(0).getLigne(0).getCase(0).getX();
		int casey =plateau.getAnneau(0).getLigne(0).getCase(0).getY();
		if(x>= casex-12 && x<=casex +12 && y>= casey-12 && y<=casey +12 ){
			return plateau.getAnneau(0).getLigne(0).getCase(0);
		}
		for (int anneau = 1; anneau < 1+taille ; anneau ++){
			for (int cases = 0 ; cases < 6 ; cases ++){
				casex = plateau.getAnneau(anneau).getLigne(cases).getCase(0).getX();
				casey = plateau.getAnneau(anneau).getLigne(cases).getCase(0).getY();
				if(x>= casex-12 && x<=casex +12 && y>= casey-12 && y<=casey +12 ){
					return plateau.getAnneau(anneau).getLigne(cases).getCase(0);
				}
			}
			for (int cases = 0 ; cases < 6 ; cases ++){
				for (int i = 1 ; i < anneau ; i++){
					casex = plateau.getAnneau(anneau).getLigne(cases).getCase(i).getX();
					casey = plateau.getAnneau(anneau).getLigne(cases).getCase(i).getY();
					if(x>= casex-12 && x<=casex +12 && y>= casey-12 && y<=casey +12 ){
						return plateau.getAnneau(anneau).getLigne(cases).getCase(i);
					}
				}
			}
		}
		int taillebranche = taille;
		for (int branche = taille +1 ; branche < 2*taille + 1 ; branche++){
			for (int cases = 0 ; cases < 6 ; cases ++){
				for (int i = (branche-taillebranche)/2 +1 ; i < (branche+taillebranche)/2 +1 ; i++ ){
					casex = plateau.getBranche(cases).getLigne(branche-taille-1).getCase(i-((branche-taillebranche)/2)-1).getX();
					casey = plateau.getBranche(cases).getLigne(branche-taille-1).getCase(i-((branche-taillebranche)/2)-1).getY();
					if(x>= casex-12 && x<=casex +12 && y>= casey-12 && y<=casey +12 ){
						return plateau.getBranche(cases).getLigne(branche-taille-1).getCase(i-((branche-taillebranche)/2)-1);
					}
				}
			}
			taillebranche --;
		}
		return null;
	}
	
	public void move(Case d, Case a){
		a.move(d);
		int i = 0;
		NoeudAI n = new NoeudAI(partie,i);
		System.out.println("Etat du joueur "+i+" : "+partie.etatJoueur(i));
		System.out.println("Valeur du plateau pour le joueur "+i+" : "+n.getPoids());
	}
	
	public Vector<Case> scan(Case c){
		Vector<Case> v = new Vector<Case>();
		for(double angle = 0 ; angle < 2*Math.PI ; angle+=Math.PI/3){
			v.add(selCase((int)(c.getX()+Math.cos(angle)*30),(int)(c.getY()+Math.sin(angle)*30)));
		}
		return v;
	}
	
	class ClicListener extends MouseAdapter{
		boolean select = false;
		boolean possible = false;
		Case dep, arr,saut;
		@Override
		public void mouseClicked(MouseEvent arg0) {
			System.out.println("tour de "+partie.getJoueur(partie.getTourDe()).getCouleur());
			int x = arg0.getX();
			int y = arg0.getY();
			if (!select){
				dep=selCase(x,y);
				if (dep!=null && dep.getPion()!=null && dep.getPion().getCouleur().equals(partie.getJoueur(partie.getTourDe()).getCouleur())){
					select = true;
					highLight(dep.getX(),dep.getY(),24);
				}
				else
					dep=null;
			}
			else{
				arr=selCase(x,y);
				if (arr != null && arr.getPion()==null){
					// Cas ou ils ont le meme ordonnée ( meme ligne)
					if(arr.getY()==dep.getY()){
						// Directement adjacente
						if((arr.getX()==(dep.getX()+30))||(arr.getX()==(dep.getX()-30))){
							select = false;
							if(!possible)
								move(dep,arr);
							possible = false;
							dep=null;
							arr=null;
							partie.setNextJoueur();
							partie.getJoueur(partie.getTourDe()).setADejaBouger(false);
							update();
						}
						else
						{
							// saut vers la droite ou la gauche
							if((arr.getX()==(dep.getX()+60))||(arr.getX()==(dep.getX()-60))){
								
								saut=selCase(((arr.getX()+dep.getX())/2),arr.getY());
								// si il  a bien un pion par lequel on peut sauter
								if(saut.getPion()!=null)
								{
									move(dep,arr);
									partie.getJoueur(partie.getTourDe()).setADejaBouger(true);
									dep=arr;
									arr=null;
									update();
									Vector<Case> possibilite = scan(dep);
									possible = false;
									for (int i = 0; i < possibilite.size(); i++){
										Case temp = possibilite.get(i);
										if (temp != null && !temp.equals(saut) && temp.getOccupe()){
											Case temp2 = selCase(dep.getX()+2*(temp.getX()-dep.getX()),dep.getY()+2*(temp.getY()-dep.getY()));
											if(temp2 != null && !temp2.getOccupe()){
												possible = true;
											}
										}
									}
									if (!possible){
										dep=null;
										partie.setNextJoueur();
										partie.getJoueur(partie.getTourDe()).setADejaBouger(false);
										select = false;
									}
									else{
										highLight(dep.getX(),dep.getY(),24);
									}
								}
							}
						}
						
					}
					else{
						if((arr.getX()==dep.getX()+15)||(arr.getX()==dep.getX()-15)){
							// cas d'un simple deplacement d'une case
							if((arr.getY()==dep.getY()+26)||(arr.getY()==dep.getY()-26)||(arr.getY()==dep.getY()-25)||(arr.getY()==dep.getY()+25)){
								select = false;
								if(!possible)
									move(dep,arr);
								possible = false;
								dep=null;
								arr=null;
								partie.setNextJoueur();
								partie.getJoueur(partie.getTourDe()).setADejaBouger(false);
								update();
							}
						}
						else if((arr.getX()==dep.getX()+30)||(arr.getX()==dep.getX()-30)){
							
								if((arr.getY()==dep.getY()+52)||(arr.getY()==dep.getY()-52)||(arr.getY()==dep.getY()-51)||(arr.getY()==dep.getY()+51)){
									saut=selCase(((arr.getX()+dep.getX())/2),((arr.getY()+dep.getY())/2));

									// si il  a bien un pion par lequel on peut sauter
									if(saut.getPion()!=null){
										move(dep,arr);
										partie.getJoueur(partie.getTourDe()).setADejaBouger(true);
										dep=arr;
										arr=null;
										update();
										Vector<Case> possibilite = scan(dep);
										possible = false;
										for (int i = 0; i < possibilite.size(); i++){
											Case temp = possibilite.get(i);
											if (temp != null && !temp.equals(saut) && temp.getOccupe()){
												Case temp2 = selCase(dep.getX()+2*(temp.getX()-dep.getX()),dep.getY()+2*(temp.getY()-dep.getY()));
												if(temp2 != null && !temp2.getOccupe()){
													possible = true;
												}
											}
										}
										if (!possible){
											dep=null;
											partie.setNextJoueur();
											partie.getJoueur(partie.getTourDe()).setADejaBouger(false);
											select = false;
										}
										else{
											highLight(dep.getX(),dep.getY(),24);
										}
									}
							}
						}
					}
					
				}else
				if (arr == dep){
					select = false;
					affCase(arr.getX(),arr.getY(),24,arr.getCouleur());
					dep=null;
					arr=null;
					
					if (partie.getJoueur(partie.getTourDe()).getADejaBouger() == true){
						partie.setNextJoueur();
						partie.getJoueur(partie.getTourDe()).setADejaBouger(false);;
					}
					
				}
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			update();
			if (select == true)
				highLight(dep.getX(),dep.getY(),24);
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			update();
			if (select == true)
				highLight(dep.getX(),dep.getY(),24);
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
