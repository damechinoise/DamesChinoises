package damechinoises.jeux;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuNouvellePartie extends JPanel {

	private FenetrePrincipale parent; 
	private JPanel content;
	private JButton retour;
	private JButton lancer;
	private JButton editeur;
	
	public MenuNouvellePartie(FenetrePrincipale p) {
		//super(p);
		parent=p;
		this.setLayout(new BorderLayout());
		content = new JPanel(new GridLayout(0,1,0,20));
		lancer = new JButton("Lancer");
		editeur = new JButton("Editeur de plateaux");
		retour = new JButton("Retour au menu principal");
		content.add(lancer);
		content.add(editeur);
		content.add(retour);
		this.add(content,BorderLayout.CENTER);
		retour.addActionListener(new RetourListener(this));
		lancer.addActionListener(new NouveauListener(this));
		editeur.addActionListener(new EditeurListener(this));

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
	
	
	class NouveauListener implements ActionListener{

		private MenuNouvellePartie m;
		
		public NouveauListener(MenuNouvellePartie m){
			this.m=m;
		}
		
		public void actionPerformed(ActionEvent e) {
			m.getParentt().setMain(new MenuConfiguration(m.getParentt()));

		}
		
	}
	
	class EditeurListener implements ActionListener{

		private MenuNouvellePartie m;
		
		public EditeurListener(MenuNouvellePartie m){
			this.m=m;
		}
		
		public void actionPerformed(ActionEvent e) {
			PartieAffichage p = new PartieAffichage(m.getParentt(),4,1,true);
			p.getPartie().setEditable(true);
			m.getParentt().setMain(p);
			m.getParentt().validate();
			p.getPanelJeu().updateFirst();
		}
		
	}
	
	public FenetrePrincipale getParentt(){
		return parent;
	}
}
