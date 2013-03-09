package damechinoises.jeux;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;

import javax.swing.*;


public class MenuDemarage extends JPanel {

	private JPanel content,foot;
	private FenetrePrincipale parent; 
	private Font font = new Font("DejaVu Sans",Font.PLAIN,12);
	private Font font2 = new Font("DejaVu Sans",Font.PLAIN,18);
	private JButton nouv,charger,quitter,editeur;
	private JLabel infos;
	
	public MenuDemarage(FenetrePrincipale p) {
		//super(p);
		parent=p;
		this.setLayout(new BorderLayout());
		infos = new JLabel("Dames chinoises, 2013");
		infos.setFont(font);
		foot = new JPanel(new FlowLayout());
		foot.add(infos);
		this.add(foot,BorderLayout.SOUTH);
		nouv = new JButton("Nouvelle partie");
		nouv.setFont(font2);
		charger = new JButton("Charger une ancienne partie");
		charger.setFont(font2);
		editeur = new JButton("Editeur de plateaux");
		editeur.setFont(font2);
		quitter = new JButton("Quitter le jeu");
		quitter.setFont(font2);
		content = new JPanel(new GridLayout(0,1,0,10));
		content.add(nouv);
		content.add(charger);
		content.add(editeur);
		content.add(quitter);
		this.add(content,BorderLayout.CENTER);
		
		nouv.addActionListener(new NouvellePartieListener(this));
		charger.addActionListener(new ChargerPartieListener(this));
		editeur.addActionListener(new EditeurListener(this));
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
			m.getParentt().setMain(new MenuNouvellePartie(m.getParentt()));
		}
		
	}
	
	
	class EditeurListener implements ActionListener{

		private MenuDemarage m;
		
		public EditeurListener(MenuDemarage m){
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

	
	class ChargerPartieListener implements ActionListener{

		private MenuDemarage m;
		
		public ChargerPartieListener(MenuDemarage m){
			this.m=m;
		}
		
		public void actionPerformed(ActionEvent e) {
			
			JFileChooser choix = new JFileChooser("Sauvegardes");
			FiltreSimple onlyTXT = new FiltreSimple();
			choix.removeChoosableFileFilter(choix.getAcceptAllFileFilter());
			choix.setFileFilter(onlyTXT);
			
			int retour = choix.showOpenDialog(charger);

			if (retour == JFileChooser.APPROVE_OPTION){
				choix.getSelectedFile().getName();

				PartieAffichage p = new PartieAffichage(m.getParentt(), choix.getSelectedFile().getAbsolutePath());
				m.getParentt().setMain(p);
				m.getParentt().validate();
				p.getPanelJeu().updateFirst();

			}	
		}
			
	}
	
	public FenetrePrincipale getParentt(){
		return parent;
	}
}
