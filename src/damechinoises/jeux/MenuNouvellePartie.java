package damechinoises.jeux;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuNouvellePartie extends Menu {

	private JPanel content;
	private JButton retour;
	private JButton lancer;
	private JButton editeur;
	
	public MenuNouvellePartie(FenetrePrincipale p) {
		super(p);
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
			m.getParent().setMain(new MenuDemarage(m.getParent()));
		}
		
	}
	
	
	class NouveauListener implements ActionListener{

		private MenuNouvellePartie m;
		
		public NouveauListener(MenuNouvellePartie m){
			this.m=m;
		}
		
		public void actionPerformed(ActionEvent e) {
			PartieAffichage p = new PartieAffichage(m.getParent(),5,4);
			m.getParent().setMain(p);
			m.getParent().validate();
			p.getPanelJeu().updateFirst();
		}
		
	}
	
	class EditeurListener implements ActionListener{

		private MenuNouvellePartie m;
		
		public EditeurListener(MenuNouvellePartie m){
			this.m=m;
		}
		
		public void actionPerformed(ActionEvent e) {
			PartieAffichage p = new PartieAffichage(m.getParent(),4,1);
			p.getPartie().setEditable(true);
			m.getParent().setMain(p);
			m.getParent().validate();
			p.getPanelJeu().updateFirst();
		}
		
	}
}
