package damechinoises.jeux;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FiltrePlateauMultijoueur extends FileFilter{
	   //Description et extension acceptée par le filtre
	   private String description;
	   //Constructeur à partir de la description et de l'extension acceptée
	   public FiltrePlateauMultijoueur(){
	      description = "Plateau avec au moins 2 joueurs";
	   }
	   //Implémentation de FileFilter
	   public boolean accept(File file){
	      if(file.isDirectory()) { 
	         return true; 
	      } 
	      String nomFichier = file.getName().toLowerCase(); 
	      
	      String debut=new String("plateau avec ");
	      debut=debut.concat(""+nomFichier.charAt(13));
	      System.out.println(debut);
	      
	      int nbjou=0;
	      
	      if(nomFichier.startsWith(debut)){
	    	  try{
	    		  nbjou=Integer.parseInt(""+nomFichier.charAt(13));
	    	  }catch(NumberFormatException e){//Si la partie commence par Plateau avec s joueur (impossible de parseInt un "s")
	    		  nbjou = 0;
	    	  }
	      }

	      if(nbjou>1){
		      return (nomFichier.startsWith(debut)&&nomFichier.endsWith(".pla"));
	      }
	      else{
	    	  return false;
	      }
	      
	   }
	      public String getDescription(){
	      return description;
	   }
	}