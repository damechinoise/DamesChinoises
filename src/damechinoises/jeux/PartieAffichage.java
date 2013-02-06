package damechinoises.jeux;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import damechinoises.SD.Plateau;

public class PartieAffichage extends JPanel {
	
	private Plateau plateau;
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
		plateau = new Plateau();
		panelMenu.add(mb,BorderLayout.NORTH);
		panelJeu = new PlateauAffichage(plateau);
		mb.add(menu);
		menu.add(newgame);
		menu.add(mainmenu);
		menu.add(quit);
		this.add(panelMenu,BorderLayout.EAST);
		this.add(panelJeu,BorderLayout.CENTER);
		JButton button = new JButton("Update");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelJeu.update();
				
			}
		});
		panelMenu.add(button);
	}
	
	public PlateauAffichage getPanelJeu(){
		return panelJeu;
	}
}
