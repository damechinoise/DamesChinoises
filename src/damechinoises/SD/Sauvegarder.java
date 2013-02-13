package damechinoises.SD;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Sauvegarder {
	
	private BufferedWriter bw;
/*
	type de partie
	nb joueur
	info des joueurs
	position des pions
	au tour de..
	taille plateau
*/
	
	public void save(int nbJoueur, Joueur joueur, Pion pion, Plateau plateau){

		try
		{
			// le BufferedWriter bw auquel on donne comme argument un FileWriter
			bw = new BufferedWriter(new FileWriter("sauvegarde.txt"));
			
			//on marque dans le fichier ou plutot dans le BufferedWriter qui sert comme un tampon(stream)
			bw.write("Joueur: "+joueur.getNumero() + " " + joueur.getCouleur() + " " + joueur.getNumBrancheDebut());
			bw.write("\n");
			
			//ensuite flush envoi dans le fichier
			bw.flush();
			
			bw.close();
			
			System.out.println("fichier créé");
		}
		catch(IOException ioe){
			System.err.print("Erreur");
		}

	}
	
	public void save(Partie partie){

		try
		{
			// le BufferedWriter bw auquel on donne comme argument un FileWriter
			bw = new BufferedWriter(new FileWriter("sauvegarde.txt"));
			
			//on marque dans le fichier ou plutot dans le BufferedWriter qui sert comme un tampon(stream)
			int nbjoueurs=partie.getNbJoueurs();
			int nbpionparjoueur=partie.getPlateau().getNbPionParJoueur();
			int i;
			bw.write("Taille du plateau : "+partie.getPlateau().getTaille() + " Nombre de joueurs : " + nbjoueurs );
			bw.write("\n");
			for (i=0;i<nbjoueurs;i++){
				bw.write("Joueur: "+partie.getJoueur(i).getNumero() + " " + partie.getJoueur(i).getCouleur() + " " + partie.getJoueur(i).getNumBrancheDebut());
				bw.write("\n");
			}
			
			bw.write("\n");
			
			//ensuite flush envoi dans le fichier
			bw.flush();
			
			bw.close();
			
			System.out.println("fichier créé");
		}
		catch(IOException ioe){
			System.err.print("Erreur");
		}

	}
	
}
