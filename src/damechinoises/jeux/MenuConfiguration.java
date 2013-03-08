package damechinoises.jeux;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import damechinoises.SD.Joueur;
import damechinoises.SD.JoueurHumain;


public class MenuConfiguration extends MenuNouvellePartie{

	private int tailleChoisi;
	private int nbJoueur;
	private String[] typeJoueur;
	private int[] difficulteBots;
	
	private JPanel content;
	private JPanel panelBouton, panelLesJoueurs, panelInformation, panelTaillePlateau;
	private JPanel panelJoueurBleu, panelJoueurRouge, panelJoueurOrange, panelJoueurVert, panelJoueurJaune, panelJoueurViolet;
	private JButton retour, lancer;
	private ButtonGroup taille, choixBleu, choixRouge, choixOrange, choixVert, choixJaune, choixViolet;
	private JRadioButton taille2, taille3, taille4, taille5;
	private JRadioButton humainBleu, ordinateurBleu, inactifBleu;
	private JRadioButton humainRouge, ordinateurRouge, inactifRouge;
	private JRadioButton humainOrange, ordinateurOrange, inactifOrange;
	private JRadioButton humainVert, ordinateurVert, inactifVert;
	private JRadioButton humainJaune, ordinateurJaune, inactifJaune;
	private JRadioButton humainViolet, ordinateurViolet, inactifViolet;
	private JLabel joueurBleu, joueurRouge, joueurOrange, joueurVert, joueurJaune, joueurViolet;
	
	public MenuConfiguration(FenetrePrincipale p) {
		super(p);
		
		//LES PANELS
		content = new JPanel(/*new BorderLayout()*/new GridLayout(3,1));
		panelInformation = new JPanel();
		panelTaillePlateau = new JPanel(new FlowLayout());
		panelBouton = new JPanel(new FlowLayout());
		panelLesJoueurs = new JPanel(new GridLayout(3,2));
		panelJoueurBleu = new JPanel(new FlowLayout());
		panelJoueurRouge = new JPanel(new FlowLayout());
		panelJoueurOrange = new JPanel(new FlowLayout());
		panelJoueurVert = new JPanel(new FlowLayout());
		panelJoueurJaune = new JPanel(new FlowLayout());
		panelJoueurViolet = new JPanel(new FlowLayout());
		
		
		//LA TAILLE DU PLATEAU
		JLabel taillePlateau = new JLabel("Taille du plateau:");
		
		taille = new ButtonGroup();
		taille2 = new JRadioButton("2");
		taille3 = new JRadioButton("3");
		taille4 = new JRadioButton("4");
		taille5 = new JRadioButton("5");

		taille.add(taille2);
		taille.add(taille3);
		taille.add(taille4);
		taille.add(taille5);
		
		panelTaillePlateau.add(taillePlateau);
		panelTaillePlateau.add(taille2);
		panelTaillePlateau.add(taille3);
		panelTaillePlateau.add(taille4);
		panelTaillePlateau.add(taille5);
		
		//LES BOUTONS
		lancer = new JButton("Lancer");
		retour = new JButton("Retour");
		
		/*panelBouton*/content.add(lancer);
		/*panelBouton*/content.add(retour);
		
		//LES JOUEURS
		joueurBleu = new JLabel("Joueur Bleu:");
		joueurRouge = new JLabel("Joueur Rouge:");
		joueurOrange = new JLabel("Joueur Orange:");
		joueurVert = new JLabel("Joueur Vert:");
		joueurJaune = new JLabel("Joueur Jaune:");
		joueurViolet = new JLabel("Joueur Violet:");
		
		panelJoueurBleu.add(joueurBleu);
		panelJoueurRouge.add(joueurRouge);
		panelJoueurOrange.add(joueurOrange);
		panelJoueurVert.add(joueurVert);
		panelJoueurJaune.add(joueurJaune);
		panelJoueurViolet.add(joueurViolet);
				
		//LES CHOIX
			//Bleu
			choixBleu = new ButtonGroup();
			
			humainBleu = new JRadioButton("Humain");
			ordinateurBleu = new JRadioButton("Ordinateur");
			inactifBleu = new JRadioButton("Inactif");
		
			choixBleu.add(humainBleu);
			choixBleu.add(ordinateurBleu);
			choixBleu.add(inactifBleu);
		
			panelJoueurBleu.add(humainBleu);
			panelJoueurBleu.add(ordinateurBleu);
			panelJoueurBleu.add(inactifBleu);
	
			//Rouge
			choixRouge = new ButtonGroup();
			
			humainRouge = new JRadioButton("Humain");
			ordinateurRouge = new JRadioButton("Ordinateur");
			inactifRouge = new JRadioButton("Inactif");
			
			choixRouge.add(humainRouge);
			choixRouge.add(ordinateurRouge);
			choixRouge.add(inactifRouge);
			
			panelJoueurRouge.add(humainRouge);
			panelJoueurRouge.add(ordinateurRouge);
			panelJoueurRouge.add(inactifRouge);
		
			//Orange
			choixOrange = new ButtonGroup();
			
			humainOrange = new JRadioButton("Humain");
			ordinateurOrange = new JRadioButton("Ordinateur");
			inactifOrange = new JRadioButton("Inactif");
			
			choixOrange.add(humainOrange);
			choixOrange.add(ordinateurOrange);
			choixOrange.add(inactifOrange);
			
			panelJoueurOrange.add(humainOrange);
			panelJoueurOrange.add(ordinateurOrange);
			panelJoueurOrange.add(inactifOrange);
		
			//Vert
			choixVert = new ButtonGroup();
			
			humainVert = new JRadioButton("Humain");
			ordinateurVert = new JRadioButton("Ordinateur");
			inactifVert = new JRadioButton("Inactif");
			
			choixVert.add(humainVert);
			choixVert.add(ordinateurVert);
			choixVert.add(inactifVert);
			
			panelJoueurVert.add(humainVert);
			panelJoueurVert.add(ordinateurVert);
			panelJoueurVert.add(inactifVert);
		
			//Jaune
			choixJaune = new ButtonGroup();
			
			humainJaune = new JRadioButton("Humain");
			ordinateurJaune = new JRadioButton("Ordinateur");
			inactifJaune = new JRadioButton("Inactif");
			
			choixJaune.add(humainJaune);
			choixJaune.add(ordinateurJaune);
			choixJaune.add(inactifJaune);
			
			panelJoueurJaune.add(humainJaune);
			panelJoueurJaune.add(ordinateurJaune);
			panelJoueurJaune.add(inactifJaune);
		
			//Violet
			choixViolet = new ButtonGroup();
			
			humainViolet = new JRadioButton("Humain");
			ordinateurViolet = new JRadioButton("Ordinateur");
			inactifViolet = new JRadioButton("Inactif");
			
			choixViolet.add(humainViolet);
			choixViolet.add(ordinateurViolet);
			choixViolet.add(inactifViolet);
			
			panelJoueurViolet.add(humainViolet);
			panelJoueurViolet.add(ordinateurViolet);
			panelJoueurViolet.add(inactifViolet);
		

		//Ajout des panels joueur au panel LesJoueurs
		panelLesJoueurs.add(panelJoueurBleu);
		panelLesJoueurs.add(panelJoueurRouge);
		panelLesJoueurs.add(panelJoueurOrange);
		panelLesJoueurs.add(panelJoueurVert);
		panelLesJoueurs.add(panelJoueurJaune);
		panelLesJoueurs.add(panelJoueurViolet);

		//AJOUT DES PANELS AU PANEL PRINCIPAL
		content.add(panelTaillePlateau, BorderLayout.NORTH);
		content.add(panelLesJoueurs, BorderLayout.CENTER);
		content.add(panelBouton, BorderLayout.SOUTH);
		add(content);
		
		//enregistrement à l'écouteur
		retour.addActionListener(new RetourListener(this));
		lancer.addActionListener(new NouveauListener(this));
	}
	
	
	
	/////////////////////////////////////////////////
	class RetourListener implements ActionListener{

		private MenuConfiguration m;
		
		public RetourListener(MenuConfiguration m){
			this.m=m;
		}
		
		public void actionPerformed(ActionEvent e) {
			m.getParent().setMain(new MenuNouvellePartie(m.getParent()));
		}
		
	}
	
	
	class NouveauListener implements ActionListener{

		private MenuConfiguration m;
		
		public NouveauListener(MenuConfiguration m){
			this.m=m;
		}
		
		public void actionPerformed(ActionEvent e) {
			if (taille2.isSelected()){tailleChoisi = 2;}
			else if (taille3.isSelected()){tailleChoisi = 3;}
			else if (taille4.isSelected()){tailleChoisi = 4;}
			else if (taille5.isSelected()){tailleChoisi = 5;}	
			
			nbJoueur = 6;
			//compter le nb de joueur
			if(inactifBleu.isSelected()){nbJoueur--;}
			if(inactifRouge.isSelected()){nbJoueur--;}
			if(inactifOrange.isSelected()){nbJoueur--;}
			if(inactifVert.isSelected()){nbJoueur--;}
			if(inactifJaune.isSelected()){nbJoueur--;}
			if(inactifViolet.isSelected()){nbJoueur--;}
			
			typeJoueur = new String[nbJoueur];
			difficulteBots = new int[nbJoueur];
			
			int numJoueur = 0;
			//JOUEUR BLEU
			if(humainBleu.isSelected()){
				typeJoueur[numJoueur] = new String("humain");
				numJoueur++;
			}
			else if(ordinateurBleu.isSelected()){
				typeJoueur[numJoueur] = new String("ordinateur");
				numJoueur++;
			}
			
			//JOUEUR ROUGE
			if(humainRouge.isSelected()){
				typeJoueur[numJoueur] = new String("humain");
				numJoueur++;
			}
			else if(ordinateurRouge.isSelected()){
				typeJoueur[numJoueur] = new String("ordinateur");
				numJoueur++;
			}
			
			//JOUEUR ORANGE
			if(humainOrange.isSelected()){
				typeJoueur[numJoueur] = new String("humain");
				numJoueur++;
			}
			else if(ordinateurOrange.isSelected()){
				typeJoueur[numJoueur] = new String("ordinateur");
				numJoueur++;
			}
			
			//JOUEUR VERT
			if(humainVert.isSelected()){
				typeJoueur[numJoueur] = new String("humain");
				numJoueur++;
			}
			else if(ordinateurVert.isSelected()){
				typeJoueur[numJoueur] = new String("ordinateur");
				numJoueur++;
			}

			//JOUEUR JAUNE
			if(humainJaune.isSelected()){
				typeJoueur[numJoueur] = new String("humain");
				numJoueur++;
			}
			else if(ordinateurJaune.isSelected()){
				typeJoueur[numJoueur] = new String("ordinateur");
				numJoueur++;
			}

			
			//JOUEUR VIOLET
			if(humainViolet.isSelected()){
				typeJoueur[numJoueur] = new String("humain");
				numJoueur++;
			}
			else if(ordinateurViolet.isSelected()){
				typeJoueur[numJoueur] = new String("ordinateur");
				numJoueur++;
			}


			
			PartieAffichage p = new PartieAffichage(m.getParent(),5,4);
			m.getParent().setMain(p);
			m.getParent().validate();
			p.getPanelJeu().updateFirst();
		}
		
	}
}
