package damechinoises.jeux;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MenuDemarage extends Menu {

	private JPanel content,foot;
	private Font font = new Font("DejaVu Sans",Font.PLAIN,12);
	private Font font2 = new Font("DejaVu Sans",Font.PLAIN,18);
	private JButton nouv,charger,quitter;
	private JLabel infos;
	
	public MenuDemarage(FenetrePrincipale p) {
		super(p);
		infos = new JLabel("Dames chinoises, 2013");
		infos.setFont(font);
		foot = new JPanel(new FlowLayout());
		foot.add(infos);
		this.add(foot,BorderLayout.SOUTH);
		nouv = new JButton("Nouvelle partie");
		nouv.setFont(font2);
		charger = new JButton("Charger une ancienne partie");
		charger.setFont(font2);
		quitter = new JButton("Quitter le jeu");
		quitter.setFont(font2);
		content = new JPanel(new GridLayout(0,1,0,10));
		content.add(nouv);
		content.add(charger);
		content.add(quitter);
		this.add(content,BorderLayout.CENTER);
		nouv.addActionListener(new NouvellePartieListener(this));
	}

	
	class NouvellePartieListener implements ActionListener{

		private MenuDemarage m;
		
		public NouvellePartieListener(MenuDemarage m){
			this.m=m;
		}
		
		public void actionPerformed(ActionEvent e) {
			m.getParent().setMain(new MenuNouvellePartie(m.getParent()));
		}
		
	}
}
