package damechinoises.jeux;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import damechinoises.SD.InterfaceTour;
import damechinoises.SD.JoueurOrdinateur;
import damechinoises.SD.Partie;
import damechinoises.SD.Plateau;
import damechinoises.SD.TourEvent;
import damechinoises.jeux.MenuNouvellePartie.NouveauListener;
import damechinoises.jeux.MenuNouvellePartie.RetourListener;

public class PartieAffichage extends JPanel {
	
	private Partie partie;
	private FenetrePrincipale parent;
	private JMenuBar mb = new JMenuBar();
	private JMenu menu=new JMenu("Menu");
	private JMenuItem quit=new JMenuItem("Quitter");
	private JMenuItem save=new JMenuItem("Sauvegarder");
	private JMenuItem mainmenu=new JMenuItem("Menu Principal");
	private JMenuItem newgame=new JMenuItem("Nouvelle Partie");
	private JPanel panelMenu = new JPanel(new GridLayout(1,0));
	private PlateauAffichage panelJeu;
	private JLabel tourDe = new JLabel("");
	
	public PartieAffichage(FenetrePrincipale p){
		parent = p;
		this.setLayout(new BorderLayout());

		partie = new Partie(4,3);
		Plateau plateau = partie.getPlateau();
		panelMenu.add(mb);
		panelJeu = new PlateauAffichage(partie);
		mb.add(menu);
		menu.add(newgame);
		menu.add(save);
		menu.add(mainmenu);
		menu.add(quit);
		this.add(panelMenu,BorderLayout.NORTH);
		this.add(panelJeu,BorderLayout.CENTER);
		mainmenu.addActionListener(new RetourMenuListener(this));
		newgame.addActionListener(new NewGameListener(this));
		panelMenu.add(new JLabel(""));
		majTour();
		panelMenu.add(tourDe);
		partie.addEventListener(new InterfaceTour() {
			
			@Override
			public void changementDeTour(TourEvent e) {
				majTour();
			}
		});
	
	
		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		
		
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				partie.save();
			}
		});
	
	}
	
	class RetourMenuListener implements ActionListener{

		private PartieAffichage p;
		
		public RetourMenuListener(PartieAffichage p){
			this.p=p;
		}
		
		public void actionPerformed(ActionEvent e) {
			p.getParentt().setMain(new MenuDemarage(p.getParentt()));
		}
		
	}
	
	class NewGameListener implements ActionListener{

		private PartieAffichage p;
		
		public NewGameListener(PartieAffichage p){
			this.p=p;
		}
		
		public void actionPerformed(ActionEvent e) {
			String[] typeBot;
			typeBot=new String[6];
			int[] DifBot;
			DifBot = new int[6];
			for (int i=0;i<p.getPartie().getNbJoueurs();i++){
				typeBot[i]=p.getPartie().getJoueur(i).getType();
				if(typeBot[i].equals("ordinateur")){
					DifBot[i]=((JoueurOrdinateur) p.getPartie().getJoueur(i)).getDifficulte();
				}
			}
			PartieAffichage nouv = new PartieAffichage(p.getParentt(),p.getPartie().getPlateau().getTaille(),p.getPartie().getNbJoueurs(),typeBot,DifBot);
			p.getParentt().setMain(nouv);
			nouv.getParentt().validate();
			nouv.getPanelJeu().updateFirst();
		
		}
		
	}
	
	
	
	public PartieAffichage(FenetrePrincipale p, String absolutePath){
		parent = p;
		this.setLayout(new BorderLayout());
		partie = new Partie(absolutePath);
		Plateau plateau = partie.getPlateau();
		panelMenu.add(mb);
		panelJeu = new PlateauAffichage(partie);
		mb.add(menu);
		menu.add(newgame);
		menu.add(save);
		menu.add(mainmenu);
		menu.add(quit);
		this.add(panelMenu,BorderLayout.NORTH);
		this.add(panelJeu,BorderLayout.CENTER);
		newgame.addActionListener(new NewGameListener(this));
		mainmenu.addActionListener(new RetourMenuListener(this));
		panelMenu.add(new JLabel(""));
		majTour();
		panelMenu.add(tourDe);
		
		partie.addEventListener(new InterfaceTour() {
				
			@Override
			public void changementDeTour(TourEvent e) {
				majTour();
			}
		});
		
		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
			
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				partie.save();
			}
		});
		
	}
	
	public PartieAffichage(FenetrePrincipale p, int taillePlateau,int nbJoueurs){
		parent = p;
		this.setLayout(new BorderLayout());
		partie = new Partie(taillePlateau,nbJoueurs);
		Plateau plateau = partie.getPlateau();
		panelMenu.add(mb);
		panelJeu = new PlateauAffichage(partie);
		mb.add(menu);
		menu.add(newgame);
		menu.add(save);
		menu.add(mainmenu);
		menu.add(quit);
		this.add(panelMenu,BorderLayout.NORTH);
		this.add(panelJeu,BorderLayout.CENTER);
		newgame.addActionListener(new NewGameListener(this));
		mainmenu.addActionListener(new RetourMenuListener(this));
		panelMenu.add(new JLabel(""));
		majTour();
		panelMenu.add(tourDe);
		
		partie.addEventListener(new InterfaceTour() {
				
			@Override
			public void changementDeTour(TourEvent e) {
				majTour();
			}
		});
		
		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
			
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				partie.save();
			}
		});
		
	}
	
	public PartieAffichage(FenetrePrincipale p, int taillePlateau,int nbJoueurs,String[] typeJoueur,int[] difficulteBots){
		parent = p;
		this.setLayout(new BorderLayout());
		partie = new Partie(taillePlateau,nbJoueurs,typeJoueur,difficulteBots);
		Plateau plateau = partie.getPlateau();
		panelMenu.add(mb);
		panelJeu = new PlateauAffichage(partie);
		mb.add(menu);
		menu.add(newgame);
		menu.add(save);
		menu.add(mainmenu);
		menu.add(quit);
		this.add(panelMenu,BorderLayout.NORTH);
		this.add(panelJeu,BorderLayout.CENTER);
		newgame.addActionListener(new NewGameListener(this));
		mainmenu.addActionListener(new RetourMenuListener(this));
		panelMenu.add(new JLabel(""));
		majTour();
		panelMenu.add(tourDe);
		
		partie.addEventListener(new InterfaceTour() {
				
			@Override
			public void changementDeTour(TourEvent e) {
				majTour();
			}
		});
		
		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
			
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				partie.save();
			}
		});
		
	}
	
	public Partie getPartie(){
		return partie;
	}
	
	public PlateauAffichage getPanelJeu(){
		return panelJeu;
	}
	
	public void majTour(){
		String couleur = partie.getJoueur(partie.getTourDe()).getCouleur();
		tourDe.setText("Tour du joueur "+couleur);
	}
	

	public FenetrePrincipale getParentt(){
		return parent;
	}

	
	
}
