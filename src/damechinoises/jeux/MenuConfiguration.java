package damechinoises.jeux;

import damechinoises.SD.TextLimiter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import damechinoises.SD.Chronometre;
import damechinoises.SD.Joueur;
import damechinoises.SD.JoueurHumain;
import damechinoises.SD.JoueurOrdinateur;
import damechinoises.SD.Partie;
import damechinoises.SD.Util;

import javax.swing.JFileChooser;

public class MenuConfiguration extends JPanel implements ActionListener{

	private FenetrePrincipale parent; 
	private String typePartie;
	
	private int tailleChoisi;
	private int nbJoueur;
	private Joueur lesJoueurs[];
	private String couleurChoisi[];
	
	private boolean chargement;
	private int nbJoueurCharger;
	private boolean bleuChoisi, rougeChoisi, orangeChoisi, vertChoisi, jauneChoisi, violetChoisi;
	private Joueur lesJoueursCharger[];
	private Partie partieCharger;
	private Chronometre c;
	
	private JPanel content;
	private JPanel panelBouton, panelLesJoueurs, panelInformation, panelTaillePlateau, panelChrono;
	private JPanel panelJoueurBleu, panelJoueurRouge, panelJoueurOrange, panelJoueurVert, panelJoueurJaune, panelJoueurViolet;
	private JPanel sousPanelJoueurBleu, sousPanelJoueurRouge, sousPanelJoueurOrange, sousPanelJoueurVert, sousPanelJoueurJaune, sousPanelJoueurViolet;
	private JTextField pseudoBleu, pseudoRouge, pseudoOrange, pseudoVert, pseudoJaune, pseudoViolet;
	private JComboBox listeDifficulteBleu, listeDifficulteRouge, listeDifficulteOrange, listeDifficulteVert, listeDifficulteJaune, listeDifficulteViolet;
	private Object[] lesDifficultes = new Object[]{"1","2"} ;
	private JButton retour, lancer, charger;
	private ButtonGroup taille, choixBleu, choixRouge, choixOrange, choixVert, choixJaune, choixViolet;
	private JRadioButton taille2, taille3, taille4, taille5;
	private JRadioButton chrono2, chrono3, chrono4, chrono5, chrono10, chrono15, chrono30;
	private JRadioButton humainBleu, ordinateurBleu, inactifBleu, actifBleu;
	private JRadioButton humainRouge, ordinateurRouge, inactifRouge, actifRouge;
	private JRadioButton humainOrange, ordinateurOrange, inactifOrange, actifOrange;
	private JRadioButton humainVert, ordinateurVert, inactifVert, actifVert;
	private JRadioButton humainJaune, ordinateurJaune, inactifJaune, actifJaune;
	private JRadioButton humainViolet, ordinateurViolet, inactifViolet, actifViolet;
	private JLabel informations;
	private JLabel joueurBleu, joueurRouge, joueurOrange, joueurVert, joueurJaune, joueurViolet;
	private JLabel compBleu, compRouge, compOrange, compVert, compJaune, compViolet;
	
	private JFileChooser choix;
	
	public MenuConfiguration(FenetrePrincipale p,String typePartie) {
		//super(p);
		parent=p;
		this.typePartie = typePartie;
		this.setLayout(new BorderLayout());
		this.chargement = false;
		
		//LES PANELS
		content = new JPanel(new BorderLayout());
		panelInformation = new JPanel();
		panelTaillePlateau = new JPanel(new FlowLayout());
		panelBouton = new JPanel(new FlowLayout());
		panelLesJoueurs = new JPanel(new GridLayout(6,2));
		panelJoueurBleu = new JPanel(new FlowLayout());
		panelJoueurRouge = new JPanel(new FlowLayout());
		panelJoueurOrange = new JPanel(new FlowLayout());
		panelJoueurVert = new JPanel(new FlowLayout());
		panelJoueurJaune = new JPanel(new FlowLayout());
		panelJoueurViolet = new JPanel(new FlowLayout());
		sousPanelJoueurBleu = new JPanel(new FlowLayout());
		sousPanelJoueurRouge = new JPanel(new FlowLayout());
		sousPanelJoueurOrange = new JPanel(new FlowLayout());
		sousPanelJoueurVert = new JPanel(new FlowLayout());
		sousPanelJoueurJaune = new JPanel(new FlowLayout());
		sousPanelJoueurViolet = new JPanel(new FlowLayout());
		
		//LA TAILLE DU PLATEAU
		JLabel taillePlateau = new JLabel("Taille du plateau:");
		taille = new ButtonGroup();
		taille2 = new JRadioButton("2");
		taille3 = new JRadioButton("3");
		taille4 = new JRadioButton("4", true);
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
		
		if(typePartie.equals("chronometre")){
			//LE CHRONO
			panelChrono = new JPanel(new FlowLayout());
			JLabel leChrono = new JLabel("Chronometre:");
			ButtonGroup chronoGroup = new ButtonGroup();
			chrono2 = new JRadioButton("2 minutes");
			chrono3 = new JRadioButton("3 minutes");
			chrono4 = new JRadioButton("4 minutes");
			chrono5 = new JRadioButton("5 minutes",true);
			chrono10 = new JRadioButton("10 minutes");
			chrono15 = new JRadioButton("15 minutes");
			chrono30 = new JRadioButton("30 minutes");
	
			chronoGroup.add(chrono2);
			chronoGroup.add(chrono3);
			chronoGroup.add(chrono4);
			chronoGroup.add(chrono5);
			chronoGroup.add(chrono10);
			chronoGroup.add(chrono15);
			chronoGroup.add(chrono30);
			
			panelChrono.add(leChrono);
			panelChrono.add(chrono2);
			panelChrono.add(chrono3);
			panelChrono.add(chrono4);
			panelChrono.add(chrono5);
			panelChrono.add(chrono10);
			panelChrono.add(chrono15);
			panelChrono.add(chrono30);
		}
		
		
		//Les erreurs (informations)
		informations = new JLabel("");
		panelInformation.add(informations);
		panelInformation.setVisible(false);
		
		
		//LES BOUTONS
		lancer = new JButton("Lancer");
		retour = new JButton("Retour");
		
		panelBouton.add(lancer);
		if(!typePartie.equals("editeur")){
			charger = new JButton("Charger un plateau");
			panelBouton.add(charger);
		}
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
			
			if(typePartie.equals("normale") || typePartie.equals("chronometre")){
				
				humainBleu = new JRadioButton("Humain", true);
				ordinateurBleu = new JRadioButton("Ordinateur");
				inactifBleu = new JRadioButton("Inactif");
				
				choixBleu.add(humainBleu);
				choixBleu.add(ordinateurBleu);
				choixBleu.add(inactifBleu);
				
				compBleu = new JLabel("Pseudo: ");
				pseudoBleu = new JTextField(15);
				pseudoBleu.setDocument(new TextLimiter(20));
				pseudoBleu.setHorizontalAlignment(JTextField.CENTER);
				
				listeDifficulteBleu = new JComboBox(lesDifficultes);
				listeDifficulteBleu.setPreferredSize(new Dimension(50,25));
				listeDifficulteBleu.setVisible(false);
			
				panelJoueurBleu.add(humainBleu);
				panelJoueurBleu.add(ordinateurBleu);
				panelJoueurBleu.add(inactifBleu);
				sousPanelJoueurBleu.add(compBleu);
				sousPanelJoueurBleu.add(pseudoBleu);
				sousPanelJoueurBleu.add(listeDifficulteBleu);
			}
			
			else if(typePartie.equals("editeur")){
				actifBleu = new JRadioButton("Actif", true);
				inactifBleu = new JRadioButton("Inactif");
				
				choixBleu.add(actifBleu);
				choixBleu.add(inactifBleu);
				
				panelJoueurBleu.add(actifBleu);
				panelJoueurBleu.add(inactifBleu);
			}
	
			//Rouge
			choixRouge = new ButtonGroup();
			
			if(typePartie.equals("normale") || typePartie.equals("chronometre")){
				humainRouge = new JRadioButton("Humain");
				ordinateurRouge = new JRadioButton("Ordinateur", true);
				inactifRouge = new JRadioButton("Inactif");
				
				choixRouge.add(humainRouge);
				choixRouge.add(ordinateurRouge);
				choixRouge.add(inactifRouge);
		
				compRouge = new JLabel("Difficulte: ");
				
				pseudoRouge = new JTextField(15);
				pseudoRouge.setDocument(new TextLimiter(20));
				pseudoRouge.setHorizontalAlignment(JTextField.CENTER);
				pseudoRouge.setVisible(false);
				
				listeDifficulteRouge = new JComboBox(lesDifficultes);
				listeDifficulteRouge.setPreferredSize(new Dimension(50,25));
				
				panelJoueurRouge.add(humainRouge);
				panelJoueurRouge.add(ordinateurRouge);
				panelJoueurRouge.add(inactifRouge);
				sousPanelJoueurRouge.add(compRouge);
				sousPanelJoueurRouge.add(pseudoRouge);
				sousPanelJoueurRouge.add(listeDifficulteRouge);
			}
			
			else if(typePartie.equals("editeur")){
				actifRouge = new JRadioButton("Actif", true);
				inactifRouge = new JRadioButton("Inactif");
				
				choixRouge.add(actifRouge);
				choixRouge.add(inactifRouge);
				
				panelJoueurRouge.add(actifRouge);
				panelJoueurRouge.add(inactifRouge);
			}
		
			//Orange
			choixOrange = new ButtonGroup();
			
			if(typePartie.equals("normale") || typePartie.equals("chronometre")){
				humainOrange = new JRadioButton("Humain");
				ordinateurOrange = new JRadioButton("Ordinateur");
				inactifOrange = new JRadioButton("Inactif", true);
				
				choixOrange.add(humainOrange);
				choixOrange.add(ordinateurOrange);
				choixOrange.add(inactifOrange);
	
				compOrange = new JLabel("");
				pseudoOrange = new JTextField(15);
				pseudoOrange.setDocument(new TextLimiter(20));
				pseudoOrange.setHorizontalAlignment(JTextField.CENTER);
				pseudoOrange.setVisible(false);
				
				listeDifficulteOrange = new JComboBox(lesDifficultes);
				listeDifficulteOrange.setPreferredSize(new Dimension(50,25));
				listeDifficulteOrange.setVisible(false);
				
				panelJoueurOrange.add(humainOrange);
				panelJoueurOrange.add(ordinateurOrange);
				panelJoueurOrange.add(inactifOrange);
				sousPanelJoueurOrange.add(compOrange);
				sousPanelJoueurOrange.add(pseudoOrange);
				sousPanelJoueurOrange.add(listeDifficulteOrange);
			}
		
			else if(typePartie.equals("editeur")){
				actifOrange = new JRadioButton("Actif");
				inactifOrange = new JRadioButton("Inactif", true);
				
				choixOrange.add(actifOrange);
				choixOrange.add(inactifOrange);
				
				panelJoueurOrange.add(actifOrange);
				panelJoueurOrange.add(inactifOrange);
			}
			
			//Vert
			choixVert = new ButtonGroup();
			
			if(typePartie.equals("normale") || typePartie.equals("chronometre")){
				humainVert = new JRadioButton("Humain");
				ordinateurVert = new JRadioButton("Ordinateur");
				inactifVert = new JRadioButton("Inactif", true);
				
				choixVert.add(humainVert);
				choixVert.add(ordinateurVert);
				choixVert.add(inactifVert);
	
				compVert = new JLabel("");
				pseudoVert = new JTextField(15);
				pseudoVert.setDocument(new TextLimiter(20));
				pseudoVert.setHorizontalAlignment(JTextField.CENTER);
				pseudoVert.setVisible(false);
				
				listeDifficulteVert = new JComboBox(lesDifficultes);
				listeDifficulteVert.setPreferredSize(new Dimension(50,25));
				listeDifficulteVert.setVisible(false);
				
				panelJoueurVert.add(humainVert);
				panelJoueurVert.add(ordinateurVert);
				panelJoueurVert.add(inactifVert);
				sousPanelJoueurVert.add(compVert);
				sousPanelJoueurVert.add(pseudoVert);
				sousPanelJoueurVert.add(listeDifficulteVert);
			}
			
			else if(typePartie.equals("editeur")){
				actifVert = new JRadioButton("Actif");
				inactifVert = new JRadioButton("Inactif", true);
				
				choixVert.add(actifVert);
				choixVert.add(inactifVert);
				
				panelJoueurVert.add(actifVert);
				panelJoueurVert.add(inactifVert);
			}
		
			//Jaune
			choixJaune = new ButtonGroup();
			
			if(typePartie.equals("normale") || typePartie.equals("chronometre")){
				humainJaune = new JRadioButton("Humain");
				ordinateurJaune = new JRadioButton("Ordinateur");
				inactifJaune = new JRadioButton("Inactif", true);
				
				choixJaune.add(humainJaune);
				choixJaune.add(ordinateurJaune);
				choixJaune.add(inactifJaune);
	
				compJaune = new JLabel("");
				pseudoJaune = new JTextField(15);
				pseudoJaune.setDocument(new TextLimiter(20));
				pseudoJaune.setHorizontalAlignment(JTextField.CENTER);
				pseudoJaune.setVisible(false);
				
				listeDifficulteJaune = new JComboBox(lesDifficultes);
				listeDifficulteJaune.setPreferredSize(new Dimension(50,25));
				listeDifficulteJaune.setVisible(false);
				
				panelJoueurJaune.add(humainJaune);
				panelJoueurJaune.add(ordinateurJaune);
				panelJoueurJaune.add(inactifJaune);
				sousPanelJoueurJaune.add(compJaune);
				sousPanelJoueurJaune.add(pseudoJaune);
				sousPanelJoueurJaune.add(listeDifficulteJaune);
			}
			
			else if(typePartie.equals("editeur")){
				actifJaune = new JRadioButton("Actif");
				inactifJaune = new JRadioButton("Inactif", true);
				
				choixJaune.add(actifJaune);
				choixJaune.add(inactifJaune);
				
				panelJoueurJaune.add(actifJaune);
				panelJoueurJaune.add(inactifJaune);
			}
		
			//Violet
			choixViolet = new ButtonGroup();
			
			if(typePartie.equals("normale") || typePartie.equals("chronometre")){
				humainViolet = new JRadioButton("Humain");
				ordinateurViolet = new JRadioButton("Ordinateur");
				inactifViolet = new JRadioButton("Inactif", true);
				
				choixViolet.add(humainViolet);
				choixViolet.add(ordinateurViolet);
				choixViolet.add(inactifViolet);
	
				compViolet = new JLabel("");
				pseudoViolet = new JTextField(15);
				pseudoViolet.setDocument(new TextLimiter(20));
				pseudoViolet.setHorizontalAlignment(JTextField.CENTER);
				pseudoViolet.setVisible(false);
				
				listeDifficulteViolet = new JComboBox(lesDifficultes);
				listeDifficulteViolet.setPreferredSize(new Dimension(50,25));
				listeDifficulteViolet.setVisible(false);
				
				panelJoueurViolet.add(humainViolet);
				panelJoueurViolet.add(ordinateurViolet);
				panelJoueurViolet.add(inactifViolet);
				sousPanelJoueurViolet.add(compViolet);
				sousPanelJoueurViolet.add(pseudoViolet);
				sousPanelJoueurViolet.add(listeDifficulteViolet);
			}
			
			else if(typePartie.equals("editeur")){
				actifViolet = new JRadioButton("Actif");
				inactifViolet = new JRadioButton("Inactif", true);
				
				choixViolet.add(actifViolet);
				choixViolet.add(inactifViolet);
				
				panelJoueurViolet.add(actifViolet);
				panelJoueurViolet.add(inactifViolet);
			}
		

		//Ajout des panels joueur au panel LesJoueurs
		panelLesJoueurs.add(panelJoueurBleu);
		panelLesJoueurs.add(panelJoueurRouge);
		panelLesJoueurs.add(sousPanelJoueurBleu);
		panelLesJoueurs.add(sousPanelJoueurRouge);
		panelLesJoueurs.add(panelJoueurOrange);
		panelLesJoueurs.add(panelJoueurVert);
		panelLesJoueurs.add(sousPanelJoueurOrange);
		panelLesJoueurs.add(sousPanelJoueurVert);
		panelLesJoueurs.add(panelJoueurJaune);
		panelLesJoueurs.add(panelJoueurViolet);
		panelLesJoueurs.add(sousPanelJoueurJaune);
		panelLesJoueurs.add(sousPanelJoueurViolet);

		//AJOUT DES PANELS AU PANEL PRINCIPAL
		if(typePartie.equals("chronometre")){
			
			JPanel panelNord = new JPanel();
			panelNord.setLayout(new BoxLayout(panelNord,BoxLayout.Y_AXIS));
			panelNord.add(panelTaillePlateau);
			panelNord.add(panelChrono);
			content.add(panelNord, BorderLayout.NORTH);
		}
		else{
			content.add(panelTaillePlateau, BorderLayout.NORTH);
		}
		
		
		if(!typePartie.equals("personalise")){
			content.add(panelLesJoueurs, BorderLayout.CENTER);
		}

		JPanel panelSud = new JPanel();
		panelSud.setLayout(new BoxLayout(panelSud, BoxLayout.Y_AXIS));
		
		panelSud.add(informations);
		panelSud.add(panelBouton);
		content.add(panelSud, BorderLayout.SOUTH);		
		
		this.add(content,BorderLayout.CENTER);
		
		
		
		//enregistrement à l'écouteur
		retour.addActionListener(new RetourListener(this));
		lancer.addActionListener(new NouveauListener(this));
		
		if(typePartie.equals("personalise")){
			charger.addActionListener(new ChargerPlateauSoloListener(this));
		}
		
		if(typePartie.equals("normale") || typePartie.equals("chronometre")){
			charger.addActionListener(new ChargerPartieListener(this));
			
			humainBleu.addActionListener(this);
			ordinateurBleu.addActionListener(this);
			inactifBleu.addActionListener(this);
			
			humainRouge.addActionListener(this);
			ordinateurRouge.addActionListener(this);
			inactifRouge.addActionListener(this);
			
			humainOrange.addActionListener(this);
			ordinateurOrange.addActionListener(this);
			inactifOrange.addActionListener(this);
			
			humainVert.addActionListener(this);
			ordinateurVert.addActionListener(this);
			inactifVert.addActionListener(this);
			
			humainJaune.addActionListener(this);
			ordinateurJaune.addActionListener(this);
			inactifJaune.addActionListener(this);
			
			humainViolet.addActionListener(this);
			ordinateurViolet.addActionListener(this);
			inactifViolet.addActionListener(this);
		}
	}
	
	
	
	/////////////////////////////////////////////////
	////////////////////////////////////////////////
	///////////////////////////////////////////////
	//////////////////////////////////////////////
	class RetourListener implements ActionListener{

		private MenuConfiguration m;
		
		public RetourListener(MenuConfiguration m){
			this.m=m;
		}
		
		public void actionPerformed(ActionEvent e) {
			if(typePartie.equals("normale") || typePartie.equals("chronometre") || typePartie.equals("personalise")){
				m.getParentt().setMain(new MenuNouvellePartie(m.getParentt()));
			}
			else if(typePartie.equals("editeur")){
				m.getParentt().setMain(new MenuDemarage(m.getParentt()));
			}
		}
		
	}
	
	
	class ChargerPartieListener implements ActionListener{

		private MenuConfiguration m;
		
		public ChargerPartieListener(MenuConfiguration m){
			this.m=m;
		}
		
		public void actionPerformed(ActionEvent e) {
			
			File workDir = Util.getWorkingDirectory();
			File saveDir = new File(workDir.getAbsolutePath()+"/Plateaux");
			JFileChooser choix = new JFileChooser(saveDir);
			FiltrePlateauMultijoueur onlyPlateau = new FiltrePlateauMultijoueur();
			choix.removeChoosableFileFilter(choix.getAcceptAllFileFilter());
			choix.setFileFilter(onlyPlateau);
			
			int retour = choix.showOpenDialog(charger);

			if (retour == JFileChooser.APPROVE_OPTION){
				choix.getSelectedFile().getName();
				chargement = true;
				
				partieCharger = new Partie(choix.getSelectedFile().getAbsolutePath());
				nbJoueurCharger = partieCharger.getNbJoueurs();
				lesJoueursCharger = new Joueur[nbJoueurCharger];
																
				for(int i = 0; i < nbJoueurCharger; i++){
					if (partieCharger.getJoueur(i).getCouleur().equals("bleu")){bleuChoisi = true;}
					else if (partieCharger.getJoueur(i).getCouleur().equals("rouge")){rougeChoisi = true;}
					else if (partieCharger.getJoueur(i).getCouleur().equals("orange")){orangeChoisi = true;}
					else if (partieCharger.getJoueur(i).getCouleur().equals("vert")){vertChoisi = true;}
					else if (partieCharger.getJoueur(i).getCouleur().equals("jaune")){jauneChoisi = true;}
					else if (partieCharger.getJoueur(i).getCouleur().equals("violet")){violetChoisi = true;}
				}
				
				if(bleuChoisi == false){
					panelJoueurBleu.setVisible(false);
					sousPanelJoueurBleu.setVisible(false);
				}
				else{
					inactifBleu.setVisible(false);
					humainBleu.setSelected(true);

				}
				if(rougeChoisi == false){
					panelJoueurRouge.setVisible(false);
					sousPanelJoueurRouge.setVisible(false);
				}
				else{
					inactifRouge.setVisible(false);
					listeDifficulteRouge.setVisible(true);
				}
				if(orangeChoisi == false){
					panelJoueurOrange.setVisible(false);
					sousPanelJoueurOrange.setVisible(false);
				}
				else{
					inactifOrange.setVisible(false);
					humainOrange.setSelected(true);
					pseudoOrange.setVisible(true);
				}
				if(vertChoisi == false){
					panelJoueurVert.setVisible(false);
					sousPanelJoueurVert.setVisible(false);
				}
				else{
					inactifVert.setVisible(false);
					ordinateurVert.setSelected(true);
					listeDifficulteVert.setVisible(true);
				}
				if(jauneChoisi == false){
					panelJoueurJaune.setVisible(false);
					sousPanelJoueurJaune.setVisible(false);
				}
				else{
					inactifJaune.setVisible(false);
					humainJaune.setSelected(true);
					pseudoJaune.setVisible(true);
				}
				if(violetChoisi == false){
					panelJoueurViolet.setVisible(false);
					sousPanelJoueurViolet.setVisible(false);
				}
				else{
					inactifViolet.setVisible(false);
					ordinateurViolet.setSelected(true);
					listeDifficulteViolet.setVisible(true);
				}
				
				panelTaillePlateau.setVisible(false);
	
			}	
		}	
	}
	
	
	class NouveauListener implements ActionListener{

		private MenuConfiguration m;
		
		public NouveauListener(MenuConfiguration m){
			this.m=m;
		}
		
		public void actionPerformed(ActionEvent e) {
			
	
			if(chargement == true){
								
				int numordi = 1;
				int numJoueur = 0;
				int numHum = 0;

				informations.setText("");
				informations.setVisible(false);
	
				if(bleuChoisi == true){
					if(humainBleu.isSelected() == true){
						numHum++;
						if(pseudoBleu.getText().equals("")){
							informations.setText("Les pseudos des joueurs ne peuvent �tre vide !");
							informations.setVisible(true);
						}
						
						else{
							lesJoueursCharger[numJoueur] = new JoueurHumain(partieCharger.getPlateau().getTaille(), partieCharger.getJoueur(numJoueur), pseudoBleu.getText());	
							numJoueur++;
						}
					}
					else if(ordinateurBleu.isSelected() == true){
						lesJoueursCharger[numJoueur] = new JoueurOrdinateur(partieCharger.getPlateau().getTaille(), partieCharger.getJoueur(numJoueur), "Ordinateur "+numordi, listeDifficulteBleu.getSelectedIndex()+1);
						numJoueur++;
						numordi++;
					}
				}
						
				if(rougeChoisi == true){
					if(humainRouge.isSelected() == true){
						numHum++;
						if(pseudoRouge.getText().equals("")){
							informations.setText("Les pseudos des joueurs ne peuvent être vide !");
							informations.setVisible(true);
						}
						
						else{
							lesJoueursCharger[numJoueur] = new JoueurHumain(partieCharger.getPlateau().getTaille(), partieCharger.getJoueur(numJoueur), pseudoRouge.getText());
							numJoueur++;
						}
					}
					else if(ordinateurRouge.isSelected() == true){
						lesJoueursCharger[numJoueur] = new JoueurOrdinateur(partieCharger.getPlateau().getTaille(), partieCharger.getJoueur(numJoueur), "Ordinateur "+numordi, listeDifficulteRouge.getSelectedIndex()+1);
						numJoueur++;
						numordi++;
					}
				}
					
				if(orangeChoisi == true){
					if(humainOrange.isSelected() == true){
						numHum++;
						if(pseudoOrange.getText().equals("")){
							informations.setText("Les pseudos des joueurs ne peuvent �tre vide !");
							informations.setVisible(true);
						}
						
						else{
							lesJoueursCharger[numJoueur] = new JoueurHumain(partieCharger.getPlateau().getTaille(), partieCharger.getJoueur(numJoueur), pseudoOrange.getText());
							numJoueur++;
						}
					}
					else if(ordinateurOrange.isSelected() == true){
						lesJoueursCharger[numJoueur] = new JoueurOrdinateur(partieCharger.getPlateau().getTaille(), partieCharger.getJoueur(numJoueur), "Ordinateur "+numordi, listeDifficulteOrange.getSelectedIndex()+1);
						numJoueur++;
						numordi++;
					}
				}
					
				if(vertChoisi == true){
					if(humainVert.isSelected() == true){
						numHum++;
						if(pseudoVert.getText().equals("")){
							informations.setText("Les pseudos des joueurs ne peuvent être vide !");
							informations.setVisible(true);
						}
						
						else{
							lesJoueursCharger[numJoueur] = new JoueurHumain(partieCharger.getPlateau().getTaille(), partieCharger.getJoueur(numJoueur), pseudoVert.getText());
							numJoueur++;
						}
					}
					else if(ordinateurVert.isSelected() == true){
						lesJoueursCharger[numJoueur] = new JoueurOrdinateur(partieCharger.getPlateau().getTaille(), partieCharger.getJoueur(numJoueur), "Ordinateur "+numordi, listeDifficulteVert.getSelectedIndex()+1);
						numordi++;
						numJoueur++;
					}
				}
					
				if(jauneChoisi == true){
					if(humainJaune.isSelected() == true){
						numHum++;
						if(pseudoJaune.getText().equals("")){
							informations.setText("Les pseudos des joueurs ne peuvent être vide !");
							informations.setVisible(true);
						}
						
						else{
							lesJoueursCharger[numJoueur] = new JoueurHumain(partieCharger.getPlateau().getTaille(), partieCharger.getJoueur(numJoueur), pseudoJaune.getText());
							numJoueur++;
						}
					}
					else if(ordinateurJaune.isSelected() == true){
						lesJoueursCharger[numJoueur] = new JoueurOrdinateur(partieCharger.getPlateau().getTaille(), partieCharger.getJoueur(numJoueur), "Ordinateur "+numordi, listeDifficulteJaune.getSelectedIndex()+1);
						numordi++;
						numJoueur++;
					}
				}
					
				if(violetChoisi == true){
					if(humainViolet.isSelected() == true){
						numHum++;
						if(pseudoViolet.getText().equals("")){
							informations.setText("Les pseudos des joueurs ne peuvent être vide !");
							informations.setVisible(true);
						}
						
						else{
							lesJoueursCharger[numJoueur] = new JoueurHumain(partieCharger.getPlateau().getTaille(), partieCharger.getJoueur(numJoueur), pseudoViolet.getText());
							numJoueur++;

						}
					}
					else if(ordinateurViolet.isSelected() == true){
						lesJoueursCharger[numJoueur] = new JoueurOrdinateur(partieCharger.getPlateau().getTaille(), partieCharger.getJoueur(numJoueur), "Ordinateur "+numordi, listeDifficulteViolet.getSelectedIndex()+1);
						numordi++;
						numJoueur++;
					}
				}
				
				if(numHum == 0){
					informations.setText("Il doit y avoir au moins un humain !");
					informations.setVisible(true);
				}
				
				if(informations.getText().equals("")){
					
					if(typePartie.equals("chronometre")){
						
						if(chrono2.isSelected()){c=new Chronometre(2,0);}
						else if(chrono3.isSelected()){c=new Chronometre(3,0);}
						else if(chrono4.isSelected()){c=new Chronometre(4,0);}
						else if(chrono5.isSelected()){c=new Chronometre(5,0);}						
						
						PartieAffichage p = new PartieAffichage(m.getParentt(),partieCharger.getPlateau().getTaille(),nbJoueurCharger,"chronometre",false,lesJoueursCharger,true,c);
						
						m.getParentt().setMain(p);
						m.getParentt().validate();
						p.getPanelJeu().updateFirst();
					}
					
					else{
						PartieAffichage p = new PartieAffichage(m.getParentt(), partieCharger.getPlateau().getTaille(),nbJoueurCharger,"normale",false,lesJoueursCharger,true);
						m.getParentt().setMain(p);
						m.getParentt().validate();
						p.getPanelJeu().updateFirst();
					}
				}
			}
			
			else{
				//Calcul de la taille
				if (taille2.isSelected()){tailleChoisi = 2;}
				else if (taille3.isSelected()){tailleChoisi = 3;}
				else if (taille4.isSelected()){tailleChoisi = 4;}
				else if (taille5.isSelected()){tailleChoisi = 5;}	
				nbJoueur = 6;
				
				if(typePartie!="personalise"){
					//compter le nb de joueur
					if(inactifBleu.isSelected()){nbJoueur--;}
					if(inactifRouge.isSelected()){nbJoueur--;}
					if(inactifOrange.isSelected()){nbJoueur--;}
					if(inactifVert.isSelected()){nbJoueur--;}
					if(inactifJaune.isSelected()){nbJoueur--;}
					if(inactifViolet.isSelected()){nbJoueur--;}
				}
				
				int numJoueur = 0;
				if(typePartie.equals("editeur")){
					couleurChoisi = new String[nbJoueur];
					
					if(actifBleu.isSelected()){
						couleurChoisi[numJoueur] = new String("bleu");
						numJoueur++;
					}
					if(actifRouge.isSelected()){
						couleurChoisi[numJoueur] = new String("rouge");
						numJoueur++;
					}
					if(actifOrange.isSelected()){
						couleurChoisi[numJoueur] = new String("orange");
						numJoueur++;
					}
					if(actifVert.isSelected()){
						couleurChoisi[numJoueur] = new String("vert");
						numJoueur++;
					}
					if(actifJaune.isSelected()){
						couleurChoisi[numJoueur] = new String("jaune");
						numJoueur++;
					}
					if(actifViolet.isSelected()){
						couleurChoisi[numJoueur] = new String("violet");
						numJoueur++;
					}
					
					PartieAffichage p = new PartieAffichage(m.getParentt(),tailleChoisi,nbJoueur,true, couleurChoisi);
					p.getPartie().setEditable(true);
					m.getParentt().setMain(p);
					m.getParentt().validate();
					p.getPanelJeu().updateFirst();
				
				}
				
				else if(typePartie.equals("personalise")){
					
					PartieAffichage p = new PartieAffichage(m.getParentt(),tailleChoisi,1,"personalise",false);
					m.getParentt().setMain(p);
					m.getParentt().validate();
					p.getPanelJeu().updateFirst();
				
				}
				else if(typePartie.equals("normale") || typePartie.equals("chronometre")){
					
					lesJoueurs = new Joueur[nbJoueur];
	
					int numOrdi = 1;
					int numHumain = 0;
				
					informations.setText("");
					informations.setVisible(false);
										
					//JOUEUR BLEU
					if(humainBleu.isSelected()){
						numHumain++;
						if(pseudoBleu.getText().equals("")){
							informations.setText("Les pseudos des joueurs ne peuvent être vide !");
							informations.setVisible(true);
						}
						
						else{
							lesJoueurs[numJoueur] = new JoueurHumain(tailleChoisi,numJoueur,"bleu",0, pseudoBleu.getText());	
							numJoueur++;
						}
					}
					else if(ordinateurBleu.isSelected()){
						lesJoueurs[numJoueur] = new JoueurOrdinateur(tailleChoisi,numJoueur,"bleu",listeDifficulteBleu.getSelectedIndex()+1,0,"Ordinateur "+numOrdi);
						numOrdi++;
						numJoueur++;
					}
					
					//JOUEUR ROUGE
					if(humainRouge.isSelected()){
						numHumain++;
						if(pseudoRouge.getText().equals("")){
							informations.setText("Les pseudos des joueurs ne peuvent être vide !");
							informations.setVisible(true);
						}
						else{
							lesJoueurs[numJoueur] = new JoueurHumain(tailleChoisi,numJoueur,"rouge",0, pseudoRouge.getText());
							numJoueur++;
						}
						
					}
					else if(ordinateurRouge.isSelected()){
						lesJoueurs[numJoueur] = new JoueurOrdinateur(tailleChoisi,numJoueur,"rouge",listeDifficulteRouge.getSelectedIndex()+1,0,"Ordinateur "+numOrdi);
						numOrdi++;
						numJoueur++;;
					}
					
					//JOUEUR ORANGE
					if(humainOrange.isSelected()){
						numHumain++;
						if(pseudoOrange.getText().equals("")){
							informations.setText("Les pseudos des joueurs ne peuvent être vide !");
							informations.setVisible(true);
						}
						else{
							lesJoueurs[numJoueur] = new JoueurHumain(tailleChoisi,numJoueur,"orange",0, pseudoOrange.getText());
							numJoueur++;
						}
					}
					else if(ordinateurOrange.isSelected()){
						lesJoueurs[numJoueur] = new JoueurOrdinateur(tailleChoisi,numJoueur,"orange",listeDifficulteOrange.getSelectedIndex()+1,0,"Ordinateur "+numOrdi);
						numOrdi++;
						numJoueur++;
					}
					
					//JOUEUR VERT
					if(humainVert.isSelected()){
						numHumain++;
						if(pseudoVert.getText().equals("")){
							informations.setText("Les pseudos des joueurs ne peuvent être vide !");
							informations.setVisible(true);
						}
						else{
							lesJoueurs[numJoueur] = new JoueurHumain(tailleChoisi,numJoueur,"vert",0, pseudoVert.getText());
							numJoueur++;
						}
					}
					else if(ordinateurVert.isSelected()){
						lesJoueurs[numJoueur] = new JoueurOrdinateur(tailleChoisi,numJoueur,"vert",listeDifficulteVert.getSelectedIndex()+1,0,"Ordinateur "+numOrdi);
						numOrdi++;
						numJoueur++;
					}
		
					//JOUEUR JAUNE
					if(humainJaune.isSelected()){
						numHumain++;
						if(pseudoJaune.getText().equals("")){
							informations.setText("Les pseudos des joueurs ne peuvent être vide !");
							informations.setVisible(true);
						}
						else{
							lesJoueurs[numJoueur] = new JoueurHumain(tailleChoisi,numJoueur,"jaune",0, pseudoJaune.getText());
							numJoueur++;
						}
					}
					else if(ordinateurJaune.isSelected()){
						lesJoueurs[numJoueur] = new JoueurOrdinateur(tailleChoisi,numJoueur,"jaune",listeDifficulteJaune.getSelectedIndex()+1,0,"Ordinateur "+numOrdi);
						numOrdi++;
						numJoueur++;
					}
		
					//JOUEUR VIOLET
					if(humainViolet.isSelected()){
						numHumain++;
						if(pseudoViolet.getText().equals("")){
							informations.setText("Les pseudos des joueurs ne peuvent être vide !");
							informations.setVisible(true);
						}
						else{
							lesJoueurs[numJoueur] = new JoueurHumain(tailleChoisi,numJoueur,"violet",0, pseudoViolet.getText());
							numJoueur++;
						}
					}
					else if(ordinateurViolet.isSelected()){
						lesJoueurs[numJoueur] = new JoueurOrdinateur(tailleChoisi,numJoueur,"violet",listeDifficulteViolet.getSelectedIndex()+1,0,"Ordinateur "+numOrdi);
						numOrdi++;
						numJoueur++;
					}
					

					if(numHumain == 0){
						informations.setText("Il doit y avoir au moins un humain !");
						informations.setVisible(true);
					}
					
					//Si ya pas d'erreur (pas d'info) alors on lance
					if(informations.getText().equals("")){
						//normale 
						if(typePartie.equals("normale")){
							PartieAffichage p = new PartieAffichage(m.getParentt(),tailleChoisi,nbJoueur,"normal",false,lesJoueurs);
							
							m.getParentt().setMain(p);
							m.getParentt().validate();
							p.getPanelJeu().updateFirst();
						}
						
						//Chrono
						if(typePartie.equals("chronometre")){
							
							if(chrono2.isSelected()){c=new Chronometre(2,0);}
							else if(chrono3.isSelected()){c=new Chronometre(3,0);}
							else if(chrono4.isSelected()){c=new Chronometre(4,0);}
							else if(chrono5.isSelected()){c=new Chronometre(5,0);}
							else if(chrono10.isSelected()){c=new Chronometre(10,0);}
							else if(chrono15.isSelected()){c=new Chronometre(15,0);}
							else if(chrono30.isSelected()){c=new Chronometre(30,0);}
							
							PartieAffichage p = new PartieAffichage(m.getParentt(),tailleChoisi,nbJoueur,"chronometre",false,lesJoueurs,false,c);
							
							m.getParentt().setMain(p);
							m.getParentt().validate();
							p.getPanelJeu().updateFirst();
						}
					}

					
					// perso : 
					//PartieAffichage p = new PartieAffichage(m.getParent(),taillePlateau,1,"personalise",false);
					
					
				}
			}
		}
		
	}
	
	
	
	// c'est le charger plateau de la partie personalis�.
	class ChargerPlateauSoloListener implements ActionListener{
	
		private MenuConfiguration m;
			
		public ChargerPlateauSoloListener(MenuConfiguration m){
			this.m=m;
		}
			
		public void actionPerformed(ActionEvent e) {
				
			File workDir = Util.getWorkingDirectory();
			File saveDir = new File(workDir.getAbsolutePath()+"Plateaux");
			JFileChooser choix = new JFileChooser(saveDir);
			FiltrePlateauSolo isPlateautaille1 = new FiltrePlateauSolo();
			choix.removeChoosableFileFilter(choix.getAcceptAllFileFilter());
			choix.setFileFilter(isPlateautaille1);
				
			int retour = choix.showOpenDialog(charger);
	
			if (retour == JFileChooser.APPROVE_OPTION){
				choix.getSelectedFile().getName();
				chargement = true;
				PartieAffichage p = new PartieAffichage(m.getParentt(), choix.getSelectedFile().getAbsolutePath());
				m.getParentt().setMain(p);
				m.getParentt().validate();
				p.getPanelJeu().updateFirst();
	
			}	
		}		
	}
		
	
		
	// c'est le charger plateau pour le type de partie chronometre et normale
	class ChargerPlateauMultijoueurListener implements ActionListener{
	
		private MenuConfiguration m;
				
		public ChargerPlateauMultijoueurListener(MenuConfiguration m){
			this.m=m;
		}
				
		public void actionPerformed(ActionEvent e) {


			File workDir = Util.getWorkingDirectory();
			File saveDir = new File(workDir.getAbsolutePath()+"/Plateaux");
			JFileChooser choix = new JFileChooser(saveDir);
			FiltrePlateauMultijoueur isPlateautaillen = new FiltrePlateauMultijoueur();
			choix.removeChoosableFileFilter(choix.getAcceptAllFileFilter());
			choix.setFileFilter(isPlateautaillen);
					
			int retour = choix.showOpenDialog(charger);
	
			if (retour == JFileChooser.APPROVE_OPTION){
				choix.getSelectedFile().getName();
				chargement = true;
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


	
	public void actionPerformed(ActionEvent e) {
		if((e.getSource()) == humainBleu){
			compBleu.setText("Pseudo: ");
			pseudoBleu.setVisible(true);
			listeDifficulteBleu.setVisible(false);
		}
		else if((e.getSource()) == ordinateurBleu){
			compBleu.setText("Difficulte: ");
			pseudoBleu.setVisible(false);
			listeDifficulteBleu.setVisible(true);
		}
		else if((e.getSource()) == inactifBleu){
			compBleu.setText("");
			pseudoBleu.setVisible(false);
			listeDifficulteBleu.setVisible(false);
		}
		else if((e.getSource()) == humainRouge){
			compRouge.setText("Pseudo: ");
			pseudoRouge.setVisible(true);
			listeDifficulteRouge.setVisible(false);
		}
		else if((e.getSource()) == ordinateurRouge){
			compRouge.setText("Difficulte: ");
			pseudoRouge.setVisible(false);
			listeDifficulteRouge.setVisible(true);
		}
		else if((e.getSource()) == inactifRouge){
			compRouge.setText("");
			pseudoRouge.setVisible(false);
			listeDifficulteRouge.setVisible(false);
		}
		else if((e.getSource()) == humainOrange){
			compOrange.setText("Pseudo: ");
			pseudoOrange.setVisible(true);
			listeDifficulteOrange.setVisible(false);
		}
		else if((e.getSource()) == ordinateurOrange){
			compOrange.setText("Difficulte: ");
			pseudoOrange.setVisible(false);
			listeDifficulteOrange.setVisible(true);
		}
		else if((e.getSource()) == inactifOrange){
			compOrange.setText("");
			pseudoOrange.setVisible(false);
			listeDifficulteOrange.setVisible(false);
		}
		else if((e.getSource()) == humainVert){
			compVert.setText("Pseudo: ");
			pseudoVert.setVisible(true);
			listeDifficulteVert.setVisible(false);
		}
		else if((e.getSource()) == ordinateurVert){
			compVert.setText("Difficulte: ");
			pseudoVert.setVisible(false);
			listeDifficulteVert.setVisible(true);
		}
		else if((e.getSource()) == inactifVert){
			compVert.setText("");
			pseudoVert.setVisible(false);
			listeDifficulteVert.setVisible(false);
		}
		else if((e.getSource()) == humainJaune){
			compJaune.setText("Pseudo: ");
			pseudoJaune.setVisible(true);
			listeDifficulteJaune.setVisible(false);
		}
		else if((e.getSource()) == ordinateurJaune){
			compJaune.setText("Difficulte: ");
			pseudoJaune.setVisible(false);
			listeDifficulteJaune.setVisible(true);
		}
		else if((e.getSource()) == inactifJaune){
			compJaune.setText("");
			pseudoJaune.setVisible(false);
			listeDifficulteJaune.setVisible(false);
		}
		else if((e.getSource()) == humainViolet){
			compViolet.setText("Pseudo: ");
			pseudoViolet.setVisible(true);
			listeDifficulteViolet.setVisible(false);
		}
		else if((e.getSource()) == ordinateurViolet){
			compViolet.setText("Difficulte: ");
			pseudoViolet.setVisible(false);
			listeDifficulteViolet.setVisible(true);
		}
		else if((e.getSource()) == inactifViolet){
			compViolet.setText("");
			pseudoViolet.setVisible(false);
			listeDifficulteViolet.setVisible(false);
		}
	}
}
