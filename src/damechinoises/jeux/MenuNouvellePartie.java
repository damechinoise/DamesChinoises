package damechinoises.jeux;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuNouvellePartie extends Menu {

	private JPanel content;
	private JButton retour;
	
	public MenuNouvellePartie(FenetrePrincipale p) {
		super(p);
		content = new JPanel(new FlowLayout());
		retour = new JButton("Retour au menu principal");
		content.add(retour);
		this.add(content,BorderLayout.CENTER);
		retour.addActionListener(new RetourListener(this));
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
	
}
