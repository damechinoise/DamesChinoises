package damechinoises.jeux;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import damechinoises.SD.Partie;
import damechinoises.SD.Plateau;

public class PartieAffichage extends JPanel {
	
	private Partie partie;
	private FenetrePrincipale parent;
	private JMenuBar mb = new JMenuBar();
	private JMenu menu=new JMenu("Menu");
	private JMenuItem quit=new JMenuItem("Quitter");
	private JMenuItem mainmenu=new JMenuItem("Menu Principal");
	private JMenuItem newgame=new JMenuItem("Nouvelle Partie");
	private JPanel panelMenu = new JPanel(new BorderLayout());
	private PlateauAffichage panelJeu;
	
	public PartieAffichage(FenetrePrincipale p){
		parent = p;
		this.setLayout(new BorderLayout());
		partie = new Partie();
		Plateau plateau = partie.getPlateau();
		panelMenu.add(mb,BorderLayout.NORTH);
		panelJeu = new PlateauAffichage(plateau);
		mb.add(menu);
		menu.add(newgame);
		menu.add(mainmenu);
		menu.add(quit);
		this.add(panelMenu,BorderLayout.EAST);
		this.add(panelJeu,BorderLayout.CENTER);
	}
	
	public PlateauAffichage getPanelJeu(){
		return panelJeu;
	}
	
	
}
