package damechinoises.jeux;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import damechinoises.SD.InterfaceTour;
import damechinoises.SD.Partie;
import damechinoises.SD.Plateau;
import damechinoises.SD.TourEvent;

public class PartieAffichage extends JPanel {
	
	private Partie partie;
	private FenetrePrincipale parent;
	private JMenuBar mb = new JMenuBar();
	private JMenu menu=new JMenu("Menu");
	private JMenuItem quit=new JMenuItem("Quitter");
	private JMenuItem mainmenu=new JMenuItem("Menu Principal");
	private JMenuItem newgame=new JMenuItem("Nouvelle Partie");
	private JPanel panelMenu = new JPanel(new GridLayout(1,0));
	private PlateauAffichage panelJeu;
	private JLabel tourDe = new JLabel("");
	
	public PartieAffichage(FenetrePrincipale p){
		parent = p;
		this.setLayout(new BorderLayout());
		partie = new Partie(4,1);
		Plateau plateau = partie.getPlateau();
		panelMenu.add(mb);
		panelJeu = new PlateauAffichage(partie);
		mb.add(menu);
		menu.add(newgame);
		menu.add(mainmenu);
		menu.add(quit);
		this.add(panelMenu,BorderLayout.NORTH);
		this.add(panelJeu,BorderLayout.CENTER);
		panelMenu.add(new JLabel(""));
		majTour();
		panelMenu.add(tourDe);
		partie.addEventListener(new InterfaceTour() {
			
			@Override
			public void changementDeTour(TourEvent e) {
				majTour();
			}
		});
	}
	
	public PlateauAffichage getPanelJeu(){
		return panelJeu;
	}
	
	public void majTour(){
		String couleur = partie.getJoueur(partie.getTourDe()).getCouleur();
		tourDe.setText("Tour du joueur "+couleur);
	}
}
