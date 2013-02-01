package damechinoises.jeux;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import damechinoises.SD.Plateau;

public class PartieAffichage extends JFrame {
	
	private Plateau plateau;
	private FenetrePrincipale parent;
	private JMenuBar mb = new JMenuBar();
	private JMenu menu=new JMenu("Menu");
	private JMenuItem quit=new JMenuItem("Quitter");
	private JMenuItem mainmenu=new JMenuItem("Menu Principal");
	private JMenuItem newgame=new JMenuItem("Nouvelle Partie");
	
	
	public PartieAffichage(FenetrePrincipale p){
		parent = p;
		plateau = new Plateau();
		this.setJMenuBar(mb);
		mb.add(menu);
		menu.add(newgame);
		menu.add(mainmenu);
		menu.add(quit);
	}
}
