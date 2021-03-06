package damechinoises.jeux;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import damechinoises.SD.InterfaceTour;
import damechinoises.SD.Joueur;
import damechinoises.SD.JoueurOrdinateur;
import damechinoises.SD.Partie;
import damechinoises.SD.Plateau;
import damechinoises.SD.TourEvent;
import damechinoises.jeux.MenuNouvellePartie.RetourListener;
import damechinoises.SD.Chronometre;
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
	private JLabel chrono;
	
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

			@Override
			public void finJoueur(TourEvent e) {
				getParentt().setMain(new PartieFinie(getParentt(),partie));
				
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
			Joueur lesJoueurs[];
			lesJoueurs = new Joueur[p.getPartie().getNbJoueurs()];
			for (int i=0;i<p.getPartie().getNbJoueurs();i++){
				lesJoueurs[i]=p.getPartie().getJoueur(i);
			}
			PartieAffichage nouv = new PartieAffichage(p.getParentt(),p.getPartie().getPlateau().getTaille(),p.getPartie().getNbJoueurs(),p.getPartie().getType(),p.getPartie().getEditable(),lesJoueurs);
			p.getParentt().setMain(nouv);
			nouv.getParentt().validate();
			nouv.getPanelJeu().updateFirst();
		
		}
		
	}
	
	
	public PartieAffichage(FenetrePrincipale p, int taillePlateau,int nbJoueurs,String type,boolean edit,Joueur[] joueurs,boolean plateauChargé,Chronometre c){
		parent = p;
		this.setLayout(new BorderLayout());
		partie = new Partie(taillePlateau,nbJoueurs,type,edit,joueurs,plateauChargé,c);
		
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
		if (partie.getType().equals("chronometre")){
			panelMenu.add(new PanelChronometre(c.getMinute(),c.getSeconde()));
		}
		if(partie.getEditable()==false){
			majTour();
			panelMenu.add(tourDe);
		}
		partie.addEventListener(new InterfaceTour() {
				
			@Override
			public void changementDeTour(TourEvent e) {
				majTour();
			}
		
			@Override
			public void finJoueur(TourEvent e) {
				getParentt().setMain(new PartieFinie(getParentt(),partie));
				
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
		if (partie.getType().equals("chronometre")){
			panelMenu.add(new PanelChronometre(partie.getChrono().getMinute(),partie.getChrono().getSeconde()));
		}
		panelMenu.add(new JLabel(""));
		majTour();
		panelMenu.add(tourDe);
		
		partie.addEventListener(new InterfaceTour() {
				
			@Override
			public void changementDeTour(TourEvent e) {
				majTour();
			}

			@Override
			public void finJoueur(TourEvent e) {
				getParentt().setMain(new PartieFinie(getParentt(),partie));
				
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

			@Override
			public void finJoueur(TourEvent e) {
				getParentt().setMain(new PartieFinie(getParentt(),partie));
				
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
	
	public PartieAffichage(FenetrePrincipale p, int taillePlateau,int nbJoueurs,boolean editable, String[] couleurChoisi){
		parent = p;
		this.setLayout(new BorderLayout());
		partie = new Partie(taillePlateau,nbJoueurs,editable, couleurChoisi);
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
	
		if(partie.getEditable()==false){
			panelMenu.add(new JLabel(""));
			majTour();
			panelMenu.add(tourDe);
		}
		
		partie.addEventListener(new InterfaceTour() {
				
			@Override
			public void changementDeTour(TourEvent e) {
				majTour();
			}

			@Override
			public void finJoueur(TourEvent e) {
				getParentt().setMain(new PartieFinie(getParentt(),partie));
				
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
	
	public PartieAffichage(FenetrePrincipale p, int taillePlateau,int nbJoueurs,String type,boolean edit,Joueur[] joueurs){
		parent = p;
		this.setLayout(new BorderLayout());
		partie = new Partie(taillePlateau,nbJoueurs,type,edit,joueurs);
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
		if(partie.getEditable()==false){
			panelMenu.add(new JLabel(""));
			majTour();
			panelMenu.add(tourDe);
		}
		partie.addEventListener(new InterfaceTour() {
				
			@Override
			public void changementDeTour(TourEvent e) {
				majTour();
			}
		
			@Override
			public void finJoueur(TourEvent e) {
				getParentt().setMain(new PartieFinie(getParentt(),partie));
				
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
	
	public PartieAffichage(FenetrePrincipale p, int taillePlateau,int nbJoueurs,String type,boolean edit,Joueur[] joueurs,boolean plateauChargé){
		parent = p;
		this.setLayout(new BorderLayout());
		partie = new Partie(taillePlateau,nbJoueurs,type,edit,joueurs,plateauChargé);
		
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
		if(partie.getEditable()==false){
			panelMenu.add(new JLabel(""));
			majTour();
			panelMenu.add(tourDe);
		}
		partie.addEventListener(new InterfaceTour() {
				
			@Override
			public void changementDeTour(TourEvent e) {
				majTour();
			}
		
			@Override
			public void finJoueur(TourEvent e) {
				getParentt().setMain(new PartieFinie(getParentt(),partie));
				
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
	
	public PartieAffichage(FenetrePrincipale p,int taillePlateau,int nbjoueurs,String type,boolean editable){
		parent = p;
		this.setLayout(new BorderLayout());
		partie = new Partie(taillePlateau,nbjoueurs,type,editable);
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
		if(partie.getEditable()==false){
			panelMenu.add(new JLabel(""));
			majTour();
			panelMenu.add(tourDe);
		}
		partie.addEventListener(new InterfaceTour() {
				
			@Override
			public void changementDeTour(TourEvent e) {
				majTour();
			}
		
			@Override
			public void finJoueur(TourEvent e) {
				getParentt().setMain(new PartieFinie(getParentt(),partie));
				
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
		String nom=partie.getJoueur(partie.getTourDe()).getNom();
		tourDe.setText("Tour de "+nom+" ("+couleur+")");
	}
	

	public FenetrePrincipale getParentt(){
		return parent;
	}

	public class PanelChronometre extends JLabel{
	    Timer jolieTimer;
	    int minute,seconde;
	    
	    public PanelChronometre(int minutes,int secondes) throws HeadlessException
	    {
	    	super();
	    	minute=minutes;
	    	seconde=secondes;
	    	int delay = 1000; //milliseconds
	    	ActionListener tache_timer;
	    	
	    	/* Action réalisé par le timer */
	    	tache_timer = new ActionListener() {
	    		public void actionPerformed(ActionEvent e1) {
	    			if (seconde == 0) {
	    					if((minute==0)&&(partie.finPartie()==false)){
	    						int[] nbpionfinis=new int[6];
	    						for(int i=0;i<partie.getNbJoueurs();i++){
	    							nbpionfinis[i]=0;
	    							for(int j=0;j<partie.getPlateau().getNbPionParJoueur();j++){
	    								if((partie.getJoueur(i).getPion(j).getPosition().getBranch())&&(partie.getJoueur(i).getPion(j).getPosition().getAngle() == partie.getJoueur(i).getNumBrancheFin())){
	    									nbpionfinis[i]++;
	    								}
	    							}
	    					}
	    					
	    					getParentt().setMain(new PartieFinie(getParentt(),partie,nbpionfinis));
	    					
	    				}
	    				seconde = 60;
	    				minute--;
	    			}
	    			seconde--;
	    			setText(minute+":"+seconde);
	    		}
	    	};
		 
		  jolieTimer = new Timer(delay, tache_timer);
		  jolieTimer.start();
	 
	    }
	    	
	    public int getMinute(){
	    	return minute;
	    }
	    
	    public int getSeconde(){
	    	return seconde;
	    }
	    
	}
	
	
}
