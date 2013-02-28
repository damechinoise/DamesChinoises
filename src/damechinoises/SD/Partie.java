package damechinoises.SD;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Partie {
/*###ATTRIBUTS###*/
/*##############*/
	private Plateau plateau;
	private Joueur lesJoueurs[];
	private int tourDe;
	private List _listener = new ArrayList();
	
/*###CONSTRUCTEURS###*/
/*##################*/
public Partie(String nomFichier){
		
		int taillePlateau=5;
		int nbpionparjoueur;
		
	
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
			
			int espacement=0;
			int i;
			for (i = 0; i < this.getNbJoueurs(); i++){
				
				if((lignes.get(6+(4*i)+espacement)).equals("humain")){
					lesJoueurs[i] = new JoueurHumain(plateau.getTaille(),Integer.parseInt(lignes.get(3+(4*i)+espacement)),lignes.get(4+(4*i)+espacement),Integer.parseInt(lignes.get(5+(4*i)+espacement)));
				}
				else{
					lesJoueurs[i] = new JoueurOrdinateur(plateau.getTaille(),Integer.parseInt(lignes.get(3+(4*i)+espacement)),lignes.get(4+(4*i)+espacement),Integer.parseInt(lignes.get(7+(4*i)+espacement)),Integer.parseInt(lignes.get(5+(4*i)+espacement)));
					espacement++;
				}
			}
			int ligneactuelle=3+(4*this.getNbJoueurs())+espacement;
			int x;
			int y;
			String couleur;
			Hashtable<String,Integer> h = new Hashtable<String,Integer>();
			for (i=0;i<this.getNbJoueurs();i++){
				h.put(lesJoueurs[i].getCouleur(),new Integer(0));
			}
			
			for (i=0;i<plateau.getNbPionParJoueur()*this.getNbJoueurs();i++)
			{
				x=Integer.parseInt(lignes.get(ligneactuelle+(i*3)));
				y=Integer.parseInt(lignes.get(ligneactuelle+1+(i*3)));
				couleur=lignes.get(ligneactuelle+2+(i*3));
				System.out.println(x+" "+y+" "+couleur);

				System.out.println(plateau.selCase(x,y));
				plateau.selCase(x,y).setOccupe(true);
				plateau.selCase(x,y).setPion(this.getJoueur(couleur).getPion(h.get(couleur)));
				h.put(couleur,h.get(couleur)+1);
			}
			
			
			
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}

	}

	public Partie(){
		int taillePlateau=5;
		int nbpionparjoueur;
		
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
			// le BufferedWriter bw auquel on donne comme argument un FileWriter
			Date aujourdhui = new Date();
			DateFormat fullDateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL);
			BufferedWriter bw = new BufferedWriter(new FileWriter(fullDateFormat.format(aujourdhui)));
			
			//on marque dans le fichier ou plutot dans le BufferedWriter qui sert comme un tampon(stream)
			int nbJoueur = this.getNbJoueurs();
			int nbPionParJoueur = this.getPlateau().getNbPionParJoueur();
			
			bw.write("Taille du plateau :"+this.getPlateau().getTaille() + "\r\nNombre de joueurs :" + nbJoueur+"\r\nTour de :"+this.getTourDe());
			bw.write("\r\n");
			
			//PARCOURS DES JOUEURS DE LA PARTIE
			for (int i = 0; i < nbJoueur; i++){
				bw.write("Joueur numero:"+this.getJoueur(i).getNumero() + "\r\nJoueur couleur:" + this.getJoueur(i).getCouleur() + "\r\nJoueur branche debut:" + this.getJoueur(i).getNumBrancheDebut()
						+ "\r\n joueur type:"+this.getJoueur(i).getType());
				bw.write("\r\n");
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
							bw.write("X:"+this.getPlateau().getBranche(cases).getLigne(branche-this.getPlateau().getTaille()-1).getCase(i-((branche-taillebranche)/2)-1).getX());
							bw.write("\r\n");
							bw.write("Y:"+this.getPlateau().getBranche(cases).getLigne(branche-this.getPlateau().getTaille()-1).getCase(i-((branche-taillebranche)/2)-1).getY());
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
				bw.write("X:"+this.getPlateau().getAnneau(0).getLigne(1).getCase(0).getX());
				bw.write(" Y:"+this.getPlateau().getAnneau(0).getLigne(0).getCase(0).getY());
				bw.write(" Couleur:"+this.getPlateau().getAnneau(0).getLigne(0).getCase(0).getCouleur());
			}
						
			//Les anneaux
			for (int anneau = 1; anneau < 1+this.getPlateau().getTaille(); anneau ++){
				//On parcours les lignes des anneaux
				for (int ligne = 0 ; ligne < 6 ; ligne ++){
					//On parcours les cases des lignes
					for (int i = 1 ; i < anneau ; i++){					
						if(this.getPlateau().getAnneau(anneau).getLigne(ligne).getCase(i).getPion() != null){
										
							bw.write("X:"+this.getPlateau().getAnneau(anneau).getLigne(ligne).getCase(i).getX());
							bw.write(" Y:"+this.getPlateau().getAnneau(anneau).getLigne(ligne).getCase(i).getY());
							bw.write(" Couleur:"+this.getPlateau().getAnneau(anneau).getLigne(ligne).getCase(i).getCouleur());
							bw.write("\r\n");
						}
					}
				}
			}
						
			//ensuite flush envoi dans le fichier
			bw.flush();
			
			bw.close();
			
			System.out.println("fichier créé");
		}
		catch(IOException ioe){
			System.err.print("Erreur");
		}
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
	
	public void nextJoueur(){
		tourDe++;
		tourDe=tourDe%getNbJoueurs();
		fireEvent();
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
  
}