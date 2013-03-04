package damechinoises.jeux;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;

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
		charger.addActionListener(new ChargerPartieListener(this));
		
		quitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
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
	
	class ChargerPartieListener implements ActionListener{

		private MenuDemarage m;
		
		public ChargerPartieListener(MenuDemarage m){
			this.m=m;
		}
		
		public void actionPerformed(ActionEvent e) {
			
			JFileChooser choix = new JFileChooser("Sauvegardes");
			FiltreSimple onlyTXT = new FiltreSimple("fichier dames chinoises", ".dc");
			choix.removeChoosableFileFilter(choix.getAcceptAllFileFilter());
			choix.setFileFilter(onlyTXT);
			
			int retour = choix.showOpenDialog(charger);

			if (retour == JFileChooser.APPROVE_OPTION){
				choix.getSelectedFile().getName();

				PartieAffichage p = new PartieAffichage(m.getParent(), choix.getSelectedFile().getAbsolutePath());
				m.getParent().setMain(p);
				m.getParent().validate();
				p.getPanelJeu().updateFirst();

			}	
		}
			
			
		
		
	}
}
