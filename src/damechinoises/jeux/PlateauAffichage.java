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
		for (int anneau = 1; anneau < 1+taille*2 ; anneau ++)
			for (int cases = 0 ; cases < anneau*6 ; cases ++){
				double angle = 0 + cases * 360 / (anneau * 6);
				double x = xmid + Math.cos(angle) * 30 *  (anneau - cases % anneau);
				double y = ymid + Math.sin(angle) * 30 * (anneau - cases % anneau);
				this.affCase((int)x,(int)y,24);
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
