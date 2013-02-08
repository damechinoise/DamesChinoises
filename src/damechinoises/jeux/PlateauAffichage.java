package damechinoises.jeux;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import damechinoises.SD.Plateau;

public class PlateauAffichage extends JPanel {

	private Plateau plateau;
	
	public PlateauAffichage(Plateau p){
		plateau = p;
	}
	
	public void update(){
		Dimension d =this.getSize();
		double height = d.getHeight();
		double width = d.getWidth();
		int taille =plateau.getTaille();
		double xmid = width /2;
		double ymid = height /2;
		this.affCase((int)xmid,(int)ymid,24);
		double[] posx = new double[6];
		double[] posy = new double[6];
		for (int anneau = 1; anneau < 1+taille ; anneau ++){
			for (int cases = 0 ; cases < 6 ; cases ++){
					double angle = 0 + cases * Math.PI / 3 ;
					posx[cases] = xmid + Math.cos(angle) * 30 *  anneau;
					posy[cases] = ymid + Math.sin(angle) * 30 * anneau;
					this.affCase((int)posx[cases],(int)posy[cases],24);
			}
			for (int cases = 0 ; cases < 6 ; cases ++){
				for (int i = 1 ; i < anneau ; i++){
					double x = posx[cases] - i*(posx[cases]-posx[(cases+1)%6])/anneau;
					double y = posy[cases] - i*(posy[cases]-posy[(cases+1)%6])/anneau;
					this.affCase((int)x,(int)y,24);
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
					this.affCase((int)x,(int)y,24);
				}
			}
			taillebranche --;
		}
	}
	
	public void affCase(int x,int y,int d) {
		Graphics g = this.getGraphics();
	    // draw circle (color already set to foreground)
	    g.fillOval(x, y, d, d);
	    g.setColor(Color.black);
	    g.drawOval(x, y, d, d);
	}
}
