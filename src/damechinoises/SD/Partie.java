package damechinoises.SD;

import java.io.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class Partie {
/*###ATTRIBUTS###*/
/*##############*/
	
	private Plateau plateau;
	private Joueur lesJoueurs[];
	private int tourDe;
	private List _listener = new ArrayList();
	private boolean editable;
	private String type;
	private Chronometre chrono;
	
/*###CONSTRUCTEURS###*/
/*##################*/
public Partie(String nomFichier){
		

		try{
			InputStream ips=new FileInputStream(nomFichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			Vector<String> lignes = new Vector<String>();
			String[] tab;

			while ((ligne=br.readLine())!=null){
				
				tab=ligne.split(":");
				lignes.add(tab[1]);
				
			}
			
			
			plateau = new Plateau(Integer.parseInt(lignes.get(0)));
			lesJoueurs = new Joueur[Integer.parseInt(lignes.get(1))];
			this.setTourDe(Integer.parseInt(lignes.get(2)));
			
			setPremierJoueur();
			editable=false;
			int espacement=0;
			int i;
			for (i = 0; i < this.getNbJoueurs(); i++){
				
				if((lignes.get(6+(6*i)+espacement)).equals("humain")){
					lesJoueurs[i] = new JoueurHumain(plateau.getTaille(),Integer.parseInt(lignes.get(3+(6*i)+espacement)),lignes.get(4+(6*i)+espacement),Integer.parseInt(lignes.get(5+(6*i)+espacement)));
					lesJoueurs[i].setNbCoup(Integer.parseInt(lignes.get(7+(6*i)+espacement)));
				}
				else{
					lesJoueurs[i] = new JoueurOrdinateur(plateau.getTaille(),Integer.parseInt(lignes.get(3+(6*i)+espacement)),lignes.get(4+(6*i)+espacement),Integer.parseInt(lignes.get(9+(6*i)+espacement)),Integer.parseInt(lignes.get(5+(6*i)+espacement)));
					lesJoueurs[i].setNbCoup(Integer.parseInt(lignes.get(7+(6*i)+espacement)));
					lesJoueurs[i].setNom(lignes.get(8+(6*i)+espacement));
					espacement++;
				}
			}
			int ligneactuelle=3+(6*this.getNbJoueurs())+espacement;
			boolean branche;
			int angle;
			int distance;
			int num;
			String couleur;
			Hashtable<String,Integer> h = new Hashtable<String,Integer>();
			for (i=0;i<this.getNbJoueurs();i++){
				h.put(lesJoueurs[i].getCouleur(),new Integer(0));
			}
			
			for (i=0;i<plateau.getNbPionParJoueur()*this.getNbJoueurs();i++)
			{
				branche=Boolean.parseBoolean(lignes.get(ligneactuelle+(i*5)));
				angle=Integer.parseInt(lignes.get(ligneactuelle+1+(i*5)));
				distance=Integer.parseInt(lignes.get(ligneactuelle+2+(i*5)));
				num=Integer.parseInt(lignes.get(ligneactuelle+3+(i*5)));
				couleur=lignes.get(ligneactuelle+4+(i*5));
				if (branche){
					plateau.getBranche(angle).getLigne(distance-1).getCase(num).setOccupe(true);
					plateau.getBranche(angle).getLigne(distance-1).getCase(num).setPion(this.getJoueur(couleur).getPion(h.get(couleur)));
					h.put(couleur,h.get(couleur)+1);
				}
				else{
					if (distance==0){
						plateau.getAnneau(0).getLigne(0).getCase(0).setOccupe(true);
						plateau.getAnneau(0).getLigne(0).getCase(0).setPion(this.getJoueur(couleur).getPion(h.get(couleur)));
						h.put(couleur,h.get(couleur)+1);
					}
					else{
						plateau.getAnneau(distance).getLigne(angle).getCase(num).setOccupe(true);
						plateau.getAnneau(distance).getLigne(angle).getCase(num).setPion(this.getJoueur(couleur).getPion(h.get(couleur)));
						h.put(couleur,h.get(couleur)+1);
					}
					
				}
			}
			ligneactuelle+=plateau.getNbPionParJoueur()*this.getNbJoueurs()*5;
			type=lignes.get(ligneactuelle);
			ligneactuelle++;
			if(type.equals("chronometre")){
				int minute=Integer.parseInt(lignes.get(ligneactuelle));
				ligneactuelle++;
				int seconde=Integer.parseInt(lignes.get(ligneactuelle));
				chrono=new Chronometre(minute,seconde);
			}
			
			
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}

	}



	public Partie(){
		type="normal";
		int taillePlateau=5;
		int nbpionparjoueur;
		editable=false;
		plateau = new Plateau(taillePlateau);
		
		lesJoueurs = new Joueur[6];
		/*
		nb joueur
		info des joueurs
		position des pions
		au tour de..
		taille plateau
	*/
	
		setPremierJoueur();
		lesJoueurs[0] = new JoueurHumain(taillePlateau,0,"bleu",0);
		lesJoueurs[1] = new JoueurHumain(taillePlateau,1,"rouge",1);
		lesJoueurs[2] = new JoueurHumain(taillePlateau,2,"orange",2);
		lesJoueurs[3] = new JoueurOrdinateur(taillePlateau,3,"vert",2,3);
		lesJoueurs[4] = new JoueurOrdinateur(taillePlateau,4,"jaune",2,4);
		lesJoueurs[5] = new JoueurOrdinateur(taillePlateau,5,"violet",2,5);
		nbpionparjoueur=plateau.getNbPionParJoueur();
		int k=0;
		int numcas=0;
		//parcours de branches
		for (int i=0;i<6;i++)
		{
			k=plateau.getTaille();
			int t=k;
			int j = 0;
			int nbpion= nbpionparjoueur;
			//parcours de pions
			while (k>0){
				if(j>0 && j%k==0){
					k--;
					j=0;
				}
				else{
					plateau.getBranche(i).getLigne(t-k).getCase(j).setPion(lesJoueurs[i].getPion(--nbpion));
					plateau.getBranche(i).getLigne(t-k).getCase(j).setOccupe(true);
					j++;
				}
			}
		}
	}
	
	public Partie(int taillePlateau,int nbjoueurs){
		int nbpionparjoueur;
		editable=false;
		type="normal";
		plateau = new Plateau(taillePlateau);
		
		lesJoueurs = new Joueur[nbjoueurs];
		nbpionparjoueur=plateau.getNbPionParJoueur();
		/*
		nb joueur
		info des joueurs
		position des pions
		au tour de..
		taille plateau
	*/
		int i,j,k,t,nbpion;
		k=plateau.getTaille();
		t=k;
		j=0;
		setPremierJoueur();
		String [] couleursBase={"bleu","rouge","orange","vert","jaune","violet"};
		int[] basesDebut;
		basesDebut = new int[nbjoueurs];
		int numbranche=0;
		
		if(nbjoueurs==1){
			basesDebut[0]=4;
			type="personalise";
		}
		if(nbjoueurs==2){
			basesDebut[0]=4;
			basesDebut[1]=1;
		}
		
		if(nbjoueurs==3){
			basesDebut[0]=4;
			basesDebut[1]=0;
			basesDebut[2]=2;

		}
		
		if(nbjoueurs==4){
			basesDebut[0]=4;
			basesDebut[1]=0;
			basesDebut[2]=1;
			basesDebut[3]=3;
		}
		
		if(nbjoueurs==6){

			basesDebut[0]=4;
			basesDebut[1]=5;
			basesDebut[2]=0;
			basesDebut[3]=1;
			basesDebut[4]=2;
			basesDebut[5]=3;
		}
			lesJoueurs[0]=new JoueurHumain(taillePlateau,0,couleursBase[0],basesDebut[0]);
		for (i=1;i<nbjoueurs;i++){

				lesJoueurs[i] = new JoueurOrdinateur(taillePlateau,i,couleursBase[i],2,basesDebut[i]);
		
		}
			k=0;
			//parcours de branches
			for (i=0;i<nbjoueurs;i++){
				k=plateau.getTaille();
				t=k;
				j = 0;
				
				nbpion= nbpionparjoueur;
				//parcours de pions
				while (k>0){
					if(j>0 && j%k==0){
						k--;
						j=0;
					}
					else{
						numbranche=lesJoueurs[i].getNumBrancheDebut();
						plateau.getBranche(numbranche).getLigne(t-k).getCase(j).setPion(lesJoueurs[i].getPion(--nbpion));
						plateau.getBranche(numbranche).getLigne(t-k).getCase(j).setOccupe(true);
						j++;
					}
				}
			}
		
	}
	
	public Partie(int taillePlateau,int nbjoueurs,boolean editable, String[] couleurChoisi){
		int nbpionparjoueur;
		this.editable=editable;
		type="normal";
		plateau = new Plateau(taillePlateau);
		
		lesJoueurs = new Joueur[nbjoueurs];
		nbpionparjoueur=plateau.getNbPionParJoueur();
		/*
		nb joueur
		info des joueurs
		position des pions
		au tour de..
		taille plateau
	*/
		int i,j,k,t,nbpion;
		k=plateau.getTaille();
		t=k;
		j=0;
		setPremierJoueur();
		int[] basesDebut;
		basesDebut = new int[nbjoueurs];
		int numbranche=0;
		
		if(nbjoueurs==1){
			basesDebut[0]=4;
			type="personalise";
		}
		if(nbjoueurs==2){
			basesDebut[0]=4;
			basesDebut[1]=1;
		}
		
		if(nbjoueurs==3){
			basesDebut[0]=4;
			basesDebut[1]=0;
			basesDebut[2]=2;

		}
		
		if(nbjoueurs==4){
			basesDebut[0]=4;
			basesDebut[1]=0;
			basesDebut[2]=1;
			basesDebut[3]=3;
		}
		
		if(nbjoueurs==6){

			basesDebut[0]=4;
			basesDebut[1]=5;
			basesDebut[2]=0;
			basesDebut[3]=1;
			basesDebut[4]=2;
			basesDebut[5]=3;
		}
			lesJoueurs[0]=new JoueurHumain(taillePlateau,0,couleurChoisi[0],basesDebut[0]);
		for (i=1;i<nbjoueurs;i++){

				lesJoueurs[i] = new JoueurOrdinateur(taillePlateau,i,couleurChoisi[i],2,basesDebut[i]);
		
		}
			k=0;
			//parcours de branches
			for (i=0;i<nbjoueurs;i++){
				k=plateau.getTaille();
				t=k;
				j = 0;
				
				nbpion= nbpionparjoueur;
				//parcours de pions
				while (k>0){
					if(j>0 && j%k==0){
						k--;
						j=0;
					}
					else{
						numbranche=lesJoueurs[i].getNumBrancheDebut();
						plateau.getBranche(numbranche).getLigne(t-k).getCase(j).setPion(lesJoueurs[i].getPion(--nbpion));
						plateau.getBranche(numbranche).getLigne(t-k).getCase(j).setOccupe(true);
						j++;
					}
				}
			}
		
	}
	
	public Partie(int taillePlateau,int nbjoueurs,String type,boolean editable){
		int nbpionparjoueur;
		this.editable=editable;
		this.type=type;
		plateau = new Plateau(taillePlateau);
		
		lesJoueurs = new Joueur[nbjoueurs];
		nbpionparjoueur=plateau.getNbPionParJoueur();
		/*
		nb joueur
		info des joueurs
		position des pions
		au tour de..
		taille plateau
	*/
		int i,j,k,t,nbpion;
		k=plateau.getTaille();
		t=k;
		j=0;
		setPremierJoueur();
		String [] couleursBase={"bleu","rouge","orange","vert","jaune","violet"};
		int[] basesDebut;
		basesDebut = new int[nbjoueurs];
		int numbranche=0;
		
		if(nbjoueurs==1){
			basesDebut[0]=4;
		}
		if(nbjoueurs==2){
			basesDebut[0]=4;
			basesDebut[1]=1;
		}
		
		if(nbjoueurs==3){
			basesDebut[0]=4;
			basesDebut[1]=0;
			basesDebut[2]=2;

		}
		
		if(nbjoueurs==4){
			basesDebut[0]=4;
			basesDebut[1]=0;
			basesDebut[2]=1;
			basesDebut[3]=3;
		}
		
		if(nbjoueurs==6){

			basesDebut[0]=4;
			basesDebut[1]=5;
			basesDebut[2]=0;
			basesDebut[3]=1;
			basesDebut[4]=2;
			basesDebut[5]=3;
		}
			
		for (i=0;i<nbjoueurs;i++){

				lesJoueurs[i] = new JoueurHumain(taillePlateau,i,couleursBase[i],basesDebut[i]);
		
		}
			k=0;
			//parcours de branches
			for (i=0;i<nbjoueurs;i++){
				k=plateau.getTaille();
				t=k;
				j = 0;
				
				nbpion= nbpionparjoueur;
				//parcours de pions
				while (k>0){
					if(j>0 && j%k==0){
						k--;
						j=0;
					}
					else{
						numbranche=lesJoueurs[i].getNumBrancheDebut();
						plateau.getBranche(numbranche).getLigne(t-k).getCase(j).setPion(lesJoueurs[i].getPion(--nbpion));
						plateau.getBranche(numbranche).getLigne(t-k).getCase(j).setOccupe(true);
						j++;
					}
				}
			}
		
	}
	

	
	public Partie(int taillePlateau, int nbjoueurs, String type,boolean editable,Joueur[] joueurs,boolean plateauChargé) {
		int nbpionparjoueur;
		this.editable=editable;
		this.type=type;
		plateau = new Plateau(taillePlateau);
		
		lesJoueurs = new Joueur[nbjoueurs];
		nbpionparjoueur=plateau.getNbPionParJoueur();

		if(plateauChargé){
			for (int i=0;i<nbjoueurs;i++){
				lesJoueurs[i]=joueurs[i];
			}
			for (int i=0;i<nbjoueurs;i++){
				for(int j=0;j<plateau.getNbPionParJoueur();j++){
					if (lesJoueurs[i].getPion(j).getPosition().getBranch()){
						plateau.getBranche(lesJoueurs[i].getPion(j).getPosition().getAngle()).getLigne(lesJoueurs[i].getPion(j).getPosition().getDist()-1).getCase(lesJoueurs[i].getPion(j).getPosition().getNum()).setOccupe(true);
						plateau.getBranche(lesJoueurs[i].getPion(j).getPosition().getAngle()).getLigne(lesJoueurs[i].getPion(j).getPosition().getDist()-1).getCase(lesJoueurs[i].getPion(j).getPosition().getNum()).setPion(lesJoueurs[i].getPion(j));
					}
					else{
						if (lesJoueurs[i].getPion(j).getPosition().getDist()==0){
							plateau.getAnneau(0).getLigne(0).getCase(0).setOccupe(true);
							plateau.getAnneau(0).getLigne(0).getCase(0).setPion(lesJoueurs[i].getPion(j));
						}
						else{
							plateau.getAnneau(lesJoueurs[i].getPion(j).getPosition().getDist()).getLigne(lesJoueurs[i].getPion(j).getPosition().getAngle()).getCase(lesJoueurs[i].getPion(j).getPosition().getNum()).setOccupe(true);
							plateau.getAnneau(lesJoueurs[i].getPion(j).getPosition().getDist()).getLigne(lesJoueurs[i].getPion(j).getPosition().getAngle()).getCase(lesJoueurs[i].getPion(j).getPosition().getNum()).setPion(lesJoueurs[i].getPion(j));
						}
						
					}
				}
			}

		}
		else{
			System.out.println("lol");
			int i,j,k,t,nbpion;
			k=plateau.getTaille();
			t=k;
			j=0;
			setPremierJoueur();
			String [] couleursBase;
			couleursBase = new String[nbjoueurs];
			for(i=0;i<nbjoueurs;i++){
				couleursBase[i]=joueurs[i].getCouleur();
			}
			
			int[] basesDebut;
			basesDebut = new int[nbjoueurs];
			int numbranche=0;
			
			if(nbjoueurs==1){
				basesDebut[0]=4;
			}
			if(nbjoueurs==2){
				basesDebut[0]=4;
				basesDebut[1]=1;
			}
			
			if(nbjoueurs==3){
				basesDebut[0]=4;
				basesDebut[1]=0;
				basesDebut[2]=2;

			}
			
			if(nbjoueurs==4){
				basesDebut[0]=4;
				basesDebut[1]=0;
				basesDebut[2]=1;
				basesDebut[3]=3;
			}
			
			if(nbjoueurs==6){

				basesDebut[0]=4;
				basesDebut[1]=5;
				basesDebut[2]=0;
				basesDebut[3]=1;
				basesDebut[4]=2;
				basesDebut[5]=3;
			}
				
			for (i=0;i<nbjoueurs;i++){
				if(joueurs[i].getType().equals("humain")){
					lesJoueurs[i] = new JoueurHumain(taillePlateau,i,couleursBase[i],basesDebut[i],joueurs[i].getNom());
				}
				else{
					lesJoueurs[i] = new JoueurOrdinateur(taillePlateau,i,couleursBase[i],((JoueurOrdinateur) joueurs[i]).getDifficulte(),basesDebut[i],joueurs[i].getNom());
				}

			}
				k=0;
				//parcours de branches
				for (i=0;i<nbjoueurs;i++){
					k=plateau.getTaille();
					t=k;
					j = 0;
					
					nbpion= nbpionparjoueur;
					//parcours de pions
					while (k>0){
						if(j>0 && j%k==0){
							k--;
							j=0;
						}
						else{
							numbranche=lesJoueurs[i].getNumBrancheDebut();
							plateau.getBranche(numbranche).getLigne(t-k).getCase(j).setPion(lesJoueurs[i].getPion(--nbpion));
							plateau.getBranche(numbranche).getLigne(t-k).getCase(j).setOccupe(true);
							j++;
						}
					}
				}
		}
		
	}
	
	
	
	public Partie(int taillePlateau, int nbjoueurs, String type,boolean editable,Joueur[] joueurs) {
		int nbpionparjoueur;
		this.editable=editable;
		this.type=type;
		plateau = new Plateau(taillePlateau);
		
		lesJoueurs = new Joueur[nbjoueurs];
		nbpionparjoueur=plateau.getNbPionParJoueur();
		/*
		nb joueur
		info des joueurs
		position des pions
		au tour de..
		taille plateau
	*/
		int i,j,k,t,nbpion;
		k=plateau.getTaille();
		t=k;
		j=0;
		setPremierJoueur();
		String [] couleursBase;
		couleursBase = new String[nbjoueurs];
		for(i=0;i<nbjoueurs;i++){
			couleursBase[i]=joueurs[i].getCouleur();
		}
		
		int[] basesDebut;
		basesDebut = new int[nbjoueurs];
		int numbranche=0;
		
		if(nbjoueurs==1){
			basesDebut[0]=4;
		}
		if(nbjoueurs==2){
			basesDebut[0]=4;
			basesDebut[1]=1;
		}
		
		if(nbjoueurs==3){
			basesDebut[0]=4;
			basesDebut[1]=0;
			basesDebut[2]=2;

		}
		
		if(nbjoueurs==4){
			basesDebut[0]=4;
			basesDebut[1]=0;
			basesDebut[2]=1;
			basesDebut[3]=3;
		}
		
		if(nbjoueurs==6){

			basesDebut[0]=4;
			basesDebut[1]=5;
			basesDebut[2]=0;
			basesDebut[3]=1;
			basesDebut[4]=2;
			basesDebut[5]=3;
		}
			
		for (i=0;i<nbjoueurs;i++){
			if(joueurs[i].getType().equals("humain")){
				lesJoueurs[i] = new JoueurHumain(taillePlateau,i,couleursBase[i],basesDebut[i],joueurs[i].getNom());
			}
			else{
				lesJoueurs[i] = new JoueurOrdinateur(taillePlateau,i,couleursBase[i],((JoueurOrdinateur) joueurs[i]).getDifficulte(),basesDebut[i],joueurs[i].getNom());
			}

		}
			k=0;
			//parcours de branches
			for (i=0;i<nbjoueurs;i++){
				k=plateau.getTaille();
				t=k;
				j = 0;
				
				nbpion= nbpionparjoueur;
				//parcours de pions
				while (k>0){
					if(j>0 && j%k==0){
						k--;
						j=0;
					}
					else{
						numbranche=lesJoueurs[i].getNumBrancheDebut();
						plateau.getBranche(numbranche).getLigne(t-k).getCase(j).setPion(lesJoueurs[i].getPion(--nbpion));
						plateau.getBranche(numbranche).getLigne(t-k).getCase(j).setOccupe(true);
						j++;
					}
				}
			}
			
		
	}
	
	public Partie(int taillePlateau, int nbjoueurs, String type,boolean editable,Joueur[] joueurs,boolean plateauChargé,Chronometre chrono) {
		int nbpionparjoueur;
		this.editable=editable;
		this.type=type;
		this.chrono=new Chronometre(chrono.getMinute(),chrono.getSeconde());
		plateau = new Plateau(taillePlateau);
		
		lesJoueurs = new Joueur[nbjoueurs];
		nbpionparjoueur=plateau.getNbPionParJoueur();

		if(plateauChargé){
			for (int i=0;i<nbjoueurs;i++){
				lesJoueurs[i]=joueurs[i];
			}
			for (int i=0;i<nbjoueurs;i++){
				for(int j=0;j<plateau.getNbPionParJoueur();j++){
					if (lesJoueurs[i].getPion(j).getPosition().getBranch()){
						plateau.getBranche(lesJoueurs[i].getPion(j).getPosition().getAngle()).getLigne(lesJoueurs[i].getPion(j).getPosition().getDist()-1).getCase(lesJoueurs[i].getPion(j).getPosition().getNum()).setOccupe(true);
						plateau.getBranche(lesJoueurs[i].getPion(j).getPosition().getAngle()).getLigne(lesJoueurs[i].getPion(j).getPosition().getDist()-1).getCase(lesJoueurs[i].getPion(j).getPosition().getNum()).setPion(lesJoueurs[i].getPion(j));
					}
					else{
						if (lesJoueurs[i].getPion(j).getPosition().getDist()==0){
							plateau.getAnneau(0).getLigne(0).getCase(0).setOccupe(true);
							plateau.getAnneau(0).getLigne(0).getCase(0).setPion(lesJoueurs[i].getPion(j));
						}
						else{
							plateau.getAnneau(lesJoueurs[i].getPion(j).getPosition().getDist()).getLigne(lesJoueurs[i].getPion(j).getPosition().getAngle()).getCase(lesJoueurs[i].getPion(j).getPosition().getNum()).setOccupe(true);
							plateau.getAnneau(lesJoueurs[i].getPion(j).getPosition().getDist()).getLigne(lesJoueurs[i].getPion(j).getPosition().getAngle()).getCase(lesJoueurs[i].getPion(j).getPosition().getNum()).setPion(lesJoueurs[i].getPion(j));
						}
						
					}
				}
			}

		}
		else{
			System.out.println("lol");
			int i,j,k,t,nbpion;
			k=plateau.getTaille();
			t=k;
			j=0;
			setPremierJoueur();
			String [] couleursBase;
			couleursBase = new String[nbjoueurs];
			for(i=0;i<nbjoueurs;i++){
				couleursBase[i]=joueurs[i].getCouleur();
			}
			
			int[] basesDebut;
			basesDebut = new int[nbjoueurs];
			int numbranche=0;
			
			if(nbjoueurs==1){
				basesDebut[0]=4;
			}
			if(nbjoueurs==2){
				basesDebut[0]=4;
				basesDebut[1]=1;
			}
			
			if(nbjoueurs==3){
				basesDebut[0]=4;
				basesDebut[1]=0;
				basesDebut[2]=2;

			}
			
			if(nbjoueurs==4){
				basesDebut[0]=4;
				basesDebut[1]=0;
				basesDebut[2]=1;
				basesDebut[3]=3;
			}
			
			if(nbjoueurs==6){

				basesDebut[0]=4;
				basesDebut[1]=5;
				basesDebut[2]=0;
				basesDebut[3]=1;
				basesDebut[4]=2;
				basesDebut[5]=3;
			}
				
			for (i=0;i<nbjoueurs;i++){
				if(joueurs[i].getType().equals("humain")){
					lesJoueurs[i] = new JoueurHumain(taillePlateau,i,couleursBase[i],basesDebut[i],joueurs[i].getNom());
				}
				else{
					lesJoueurs[i] = new JoueurOrdinateur(taillePlateau,i,couleursBase[i],((JoueurOrdinateur) joueurs[i]).getDifficulte(),basesDebut[i],joueurs[i].getNom());
				}

			}
				k=0;
				//parcours de branches
				for (i=0;i<nbjoueurs;i++){
					k=plateau.getTaille();
					t=k;
					j = 0;
					
					nbpion= nbpionparjoueur;
					//parcours de pions
					while (k>0){
						if(j>0 && j%k==0){
							k--;
							j=0;
						}
						else{
							numbranche=lesJoueurs[i].getNumBrancheDebut();
							plateau.getBranche(numbranche).getLigne(t-k).getCase(j).setPion(lesJoueurs[i].getPion(--nbpion));
							plateau.getBranche(numbranche).getLigne(t-k).getCase(j).setOccupe(true);
							j++;
						}
					}
				}
		}
		
			
		
	}
	


	public Chronometre getChrono() {
		return chrono;
	}


	public Plateau getPlateau(){
		return plateau;
	}
	
	public int getTourDe(){
		return tourDe;
	}
	
	public void setTourDe( int tour){
		tourDe=tour;
	}
	
	public void save(){

		try
		{
			int nbJoueur = this.getNbJoueurs();
			// le BufferedWriter bw auquel on donne comme argument un FileWriter
			Date aujourdhui = new Date();
			DateFormat fullDateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL);
			String path=new String();
			if(this.type.equals("normal")){
				path="Sauvegardes/Partie normale ";
			}
			if(this.type.equals("personalise")){
				path="Sauvegardes/Partie Solo ";
			}
			if(this.editable==true){
				path="Plateaux/Plateau avec "+nbJoueur+" joueur de taille "+this.getPlateau().getTaille()+" ";
			}
			if(this.type.equals("chronometre")){
				path="Sauvegardes/Partie chronometree ";
			}
			path+="du "+fullDateFormat.format(aujourdhui);
			if(this.editable==true){
				path+=".pla";
			}
			else{
				path+=".dc";
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			
			//on marque dans le fichier ou plutot dans le BufferedWriter qui sert comme un tampon(stream)
			
			int nbPionParJoueur = this.getPlateau().getNbPionParJoueur();
			
			bw.write("Taille du plateau :"+this.getPlateau().getTaille() + "\r\nNombre de joueurs :" + nbJoueur+"\r\nTour de :"+this.getTourDe());
			bw.write("\r\n");
			
			//PARCOURS DES JOUEURS DE LA PARTIE
			for (int i = 0; i < nbJoueur; i++){
				bw.write("Joueur numero:"+this.getJoueur(i).getNumero() + "\r\nJoueur couleur:" + this.getJoueur(i).getCouleur() + "\r\nJoueur branche debut:" + this.getJoueur(i).getNumBrancheDebut()
						+ "\r\nJoueur type:"+this.getJoueur(i).getType());
				bw.write("\r\n");
				bw.write("Joueur nbcoup:"+this.getJoueur(i).getNbCoup()+ "\r\n");
				bw.write("Joueur nom:"+this.getJoueur(i).getNom()+ "\r\n");
				if (this.getJoueur(i).getType().equals("ordinateur")){
					bw.write("Joueur difficulte:"+((JoueurOrdinateur) this.getJoueur(i)).getDifficulte());
					bw.write("\r\n");
				}
			}
		
						
				
			//PARCOURS DES BRANCHES DU PLATEAU
			int taillebranche = this.getPlateau().getTaille();
						
			for (int branche = this.getPlateau().getTaille() +1 ; branche < 2*this.getPlateau().getTaille() + 1 ; branche++){
				for (int cases = 0 ; cases < 6 ; cases ++){
					for (int i = (branche-taillebranche)/2 +1 ; i < (branche+taillebranche)/2 +1 ; i++ ){
						if(this.getPlateau().getBranche(cases).getLigne(branche-this.getPlateau().getTaille()-1).getCase(i-((branche-taillebranche)/2)-1).getPion() != null){
							bw.write("branche:"+this.getPlateau().getBranche(cases).getLigne(branche-this.getPlateau().getTaille()-1).getCase(i-((branche-taillebranche)/2)-1).getBranch());
							bw.write("\r\n");
							bw.write("angle:"+this.getPlateau().getBranche(cases).getLigne(branche-this.getPlateau().getTaille()-1).getCase(i-((branche-taillebranche)/2)-1).getAngle());
							bw.write("\r\n");
							bw.write("dist:"+this.getPlateau().getBranche(cases).getLigne(branche-this.getPlateau().getTaille()-1).getCase(i-((branche-taillebranche)/2)-1).getDist());
							bw.write("\r\n");
							bw.write("numero:"+this.getPlateau().getBranche(cases).getLigne(branche-this.getPlateau().getTaille()-1).getCase(i-((branche-taillebranche)/2)-1).getNum());
							bw.write("\r\n");
							bw.write("Couleur:"+this.getPlateau().getBranche(cases).getLigne(branche-this.getPlateau().getTaille()-1).getCase(i-((branche-taillebranche)/2)-1).getCouleur());
							bw.write("\r\n");
						}
					}
				}
				taillebranche --;
			}
						
						
					
			//PARCOURS DES ANNEAUX DU PLATEAU
			//La case du centre
			if(this.getPlateau().getAnneau(0).getLigne(0).getCase(0).getPion() != null){
				bw.write("Branche:"+this.getPlateau().getAnneau(0).getLigne(1).getCase(0).getBranch());
				bw.write("\r\n");
				bw.write("Angle:"+this.getPlateau().getAnneau(0).getLigne(0).getCase(0).getAngle());
				bw.write("\r\n");
				bw.write("Dist:"+this.getPlateau().getAnneau(0).getLigne(0).getCase(0).getDist());
				bw.write("\r\n");
				bw.write("numero:"+this.getPlateau().getAnneau(0).getLigne(0).getCase(0).getNum());
				bw.write("\r\n");
				bw.write("Couleur:"+this.getPlateau().getAnneau(0).getLigne(0).getCase(0).getCouleur());
				bw.write("\r\n");

			}
						
			//Les anneaux
			for (int anneau = 1; anneau < 1+this.getPlateau().getTaille(); anneau ++){
				//On parcours les lignes des anneaux
				for (int ligne = 0 ; ligne < 6 ; ligne ++){
					//On parcours les cases des lignes
					for (int i = 0 ; i < anneau ; i++){					
						if(this.getPlateau().getAnneau(anneau).getLigne(ligne).getCase(i).getPion() != null){
										
							bw.write("Branche:"+this.getPlateau().getAnneau(anneau).getLigne(ligne).getCase(i).getBranch());
							bw.write("\r\n");
							bw.write("Angle:"+this.getPlateau().getAnneau(anneau).getLigne(ligne).getCase(i).getAngle());
							bw.write("\r\n");
							bw.write("Dist:"+this.getPlateau().getAnneau(anneau).getLigne(ligne).getCase(i).getDist());
							bw.write("\r\n");
							bw.write("Numero:"+this.getPlateau().getAnneau(anneau).getLigne(ligne).getCase(i).getNum());
							bw.write("\r\n");
							bw.write("Couleur:"+this.getPlateau().getAnneau(anneau).getLigne(ligne).getCase(i).getCouleur());
							bw.write("\r\n");

						}
					}
				}
			}
			bw.write("Type:"+this.getType());
			bw.write("\r\n");
			if(type.equals("chronometre")){
				bw.write("MinuteChrono:"+this.getChrono().getMinute());
				bw.write("\r\n");
				bw.write("SecondeChrono:"+this.getChrono().getSeconde());
				bw.write("\r\n");
			}
			//ensuite flush envoi dans le fichier
			bw.flush();
			
			bw.close();
			
			System.out.println("Partie sauvegardée");
		}
		catch(IOException ioe){
			System.err.print("Erreur");
		}
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type){
		this.type=type;
	}

	public Joueur getJoueur(int numjoueur){
		try{
			return lesJoueurs[numjoueur];
		}
		catch (Exception e){
			System.err.println("Une erreur est survenue lors de la recuperation du joueur");
			return null;
		}
    }

	public Joueur getJoueur (String couleur){
		
			for (int i=0;i<this.getNbJoueurs();i++){
				if ((this.getJoueur(i).getCouleur()).equals(couleur)){
					return lesJoueurs[i];
				}
			}
			return null;


	}
	
	public void setPremierJoueur(){
		int lower = 0;
		int higher = this.getNbJoueurs();

		int random = (int)(Math.random() * (higher-lower));
		tourDe=random;
	}
	
	public boolean finPartie(){
		for(int i = 0; i < getNbJoueurs() ; i++){
			if(!lesJoueurs[tourDe].fini())
				return false;
		}
		return true;
	}
	
	public void nextJoueur(){
		tourDe++;
		tourDe=tourDe%getNbJoueurs();
		if(!finPartie()){
				while(lesJoueurs[tourDe].fini()){
					tourDe++;
					tourDe=tourDe%getNbJoueurs();
				}
			fireEvent();
		} else {
			fireEventFin();
		}
	}

	public int joueurSuivant(){
		int jS = tourDe;
		jS++;
		jS=jS%getNbJoueurs();
		while(lesJoueurs[jS].fini()){
			jS++;
			jS=jS%getNbJoueurs();
		}
		return jS;
	}
	public void setEditable(boolean edit){
		this.editable=edit;
	}
	
	public boolean getEditable(){
		return editable;
	}
	
	public int getNbJoueurs(){
		int nbj=lesJoueurs.length;
		return nbj;
	}
	
	public int etatJoueur(int numJoueur){
		int etat = 0;
		try {
			for (int i = 0 ; i < lesJoueurs[numJoueur].nbPions() ; i++ ){
				int val =lesJoueurs[numJoueur].getPion(i).getPosition().value(lesJoueurs[numJoueur].getNumBrancheDebut()); 
				etat+=val;
				//System.out.println("Valeur du pion "+i+" du joueur "+numJoueur+" : "+val);
			}
		}
		catch (Exception e){
			etat = 0;
		}
		return etat;
	}
	
	public Partie tourJoueurAI(int joueur, int pion, int prio){
		Partie suivant = this;
		//TODO
		/*
		 * Méthode qui joue l'un des 3 coups décrit pour le joueur donné sur une copie de la partie
		 * Sert juste à alimenter l'algorithme alpha-beta de l'AI
		 * joueur : numéro du joueur
		 * pion : numéro du pion
		 * prio : 
		 * 		0 - meilleur coup
		 * 		1 - pire coup
		 * 		2 - coup aléa
		 */
		return suivant;
	}
	
	 public synchronized void addEventListener(InterfaceTour listener)  {
		     _listener.add(listener);
		   }
	 public synchronized void removeEventListener(InterfaceTour listener)  {
	     _listener.remove(listener);
	   }
	 
	 private synchronized void fireEvent(){
		 TourEvent e = new TourEvent(this);
		 Iterator i = _listener.iterator();
		 while (i.hasNext()){
			 ((InterfaceTour) i.next()).changementDeTour(e);
		 }
	 }
	 
	 private synchronized void fireEventFin(){
		 TourEvent e = new TourEvent(this,this.tourDe);
		 Iterator i = _listener.iterator();
		 while (i.hasNext()){
			 ((InterfaceTour) i.next()).finJoueur(e);
		 }
	 }
  
}