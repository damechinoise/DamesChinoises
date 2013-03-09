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
import damechinoises.SD.JoueurOrdinateur;
import damechinoises.SD.Partie;

public class MenuConfiguration extends JPanel{

	private FenetrePrincipale parent; 
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
		//super(p);
		parent=p;
		this.setLayout(new BorderLayout());
		//LES PANELS
		content = new JPanel(/*new BorderLayout()*/new GridLayout(3,2));
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
		
		panelBouton.add(lancer);
		panelBouton.add(retour);
		
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
		this.add(content,BorderLayout.CENTER);
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
			m.getParentt().setMain(new MenuNouvellePartie(m.getParentt()));
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
			Joueur lesJoueurs[];
			//Plateau chargé + infos remplies
			/*
			
			Partie pa=new Partie("Partie normale du vendredi 8 mars 2013 21 h 05 CET.dc");
			String types[];
			types = new String[3];
			types[0]="humain";
			types[1]="ordinateur";
			types[2]="humain";
			String noms[];
			noms = new String[3];
			noms[0]="lol";
			noms[1]="salut";
			noms[2]="lol";
			int difficultes[];
			difficultes = new int[3];
			difficultes[1]=2;

			int nbjoueurs=pa.getNbJoueurs();
			lesJoueurs = new Joueur[nbjoueurs];
			for (int i=0;i<nbjoueurs;i++){
				if(types[i].equals("humain")){
					lesJoueurs[i] = new JoueurHumain(pa.getPlateau().getTaille(),pa.getJoueur(i),noms[i]);
				}
				else{
					lesJoueurs[i] = new JoueurOrdinateur(pa.getPlateau().getTaille(),pa.getJoueur(i),noms[i],difficultes[i]);
				}
				
			}
			PartieAffichage p = new PartieAffichage(m.getParent(),pa.getPlateau().getTaille(),nbjoueurs,"normal",false,lesJoueurs,true);
			*/
			
			
			// Pour partie normale
			int nbjoueurs=3;
			lesJoueurs = new Joueur[nbjoueurs];
			int taillePlateau=5;
			
			lesJoueurs[0] = new JoueurHumain(taillePlateau,0,"bleu",0);
			lesJoueurs[1] = new JoueurHumain(taillePlateau,1,"rouge",1);
			lesJoueurs[2] = new JoueurHumain(taillePlateau,2,"orange",2);
			
			//normale :
			
			PartieAffichage p = new PartieAffichage(m.getParentt(),taillePlateau,nbjoueurs,"normal",false,lesJoueurs);
			
			// perso : 
			//PartieAffichage p = new PartieAffichage(m.getParent(),taillePlateau,1,"personalise",false);
			m.getParentt().setMain(p);
			m.getParentt().validate();
			p.getPanelJeu().updateFirst();
		}
		
	}
	
	public FenetrePrincipale getParentt(){
		return parent;
	}
}
