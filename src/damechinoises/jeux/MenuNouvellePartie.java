package damechinoises.jeux;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuNouvellePartie extends JPanel {

	private FenetrePrincipale parent; 
	private JPanel content;
	private JButton retour;
	private JButton normale;
	private JButton chronometre;
	private JButton personalise;
	
	
	public MenuNouvellePartie(FenetrePrincipale p) {
		//super(p);
		parent=p;
		this.setLayout(new BorderLayout());
		content = new JPanel(new GridLayout(0,1,0,20));
		normale = new JButton("Partie normale");
		chronometre = new JButton("Partie Chronometre");
		personalise = new JButton("Partie Moins de coups possibles");
		retour = new JButton("Retour au menu principal");
		content.add(normale);
		content.add(chronometre);
		content.add(personalise);
		content.add(retour);
		this.add(content,BorderLayout.CENTER);
		retour.addActionListener(new RetourListener(this));
		normale.addActionListener(new NormaleListener(this));
		chronometre.addActionListener(new ChronoListener(this));
		personalise.addActionListener(new PersoListener(this));

	}

	class RetourListener implements ActionListener{

		private MenuNouvellePartie m;
		
		public RetourListener(MenuNouvellePartie m){
			this.m=m;
		}
		
		public void actionPerformed(ActionEvent e) {
			m.getParentt().setMain(new MenuDemarage(m.getParentt()));
		}
		
	}
	
	
	class NormaleListener implements ActionListener{

		private MenuNouvellePartie m;
		
		public NormaleListener(MenuNouvellePartie m){
			this.m=m;
		}
		
		public void actionPerformed(ActionEvent e) {
			m.getParentt().setMain(new MenuConfiguration(m.getParentt(),"normal"));

		}
		
	}
	
	class ChronoListener implements ActionListener{

		private MenuNouvellePartie m;
		
		public ChronoListener(MenuNouvellePartie m){
			this.m=m;
		}
		
		public void actionPerformed(ActionEvent e) {
			m.getParentt().setMain(new MenuConfiguration(m.getParentt(),"chrono"));

		}
		
	}
	
	class PersoListener implements ActionListener{

		private MenuNouvellePartie m;
		
		public PersoListener(MenuNouvellePartie m){
			this.m=m;
		}
		
		public void actionPerformed(ActionEvent e) {
			m.getParentt().setMain(new MenuConfiguration(m.getParentt(),"personalise"));

		}
		
	}
	
	
	
	public FenetrePrincipale getParentt(){
		return parent;
	}
}
