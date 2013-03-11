package damechinoises.jeux;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FiltrePlateauMultijoueur extends FileFilter{
	   //Description et extension acceptée par le filtre
	   private String description;
	   private int nbjou;
	   
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
	      
	      try{
	    	  debut=debut.concat(""+nomFichier.charAt(13));
	      
	    	  nbjou=0;
		      
		      if(nomFichier.startsWith(debut)){
		    	
		    	  nbjou=Integer.parseInt(""+nomFichier.charAt(13));
		    	  
		      }
		      
	      }catch(StringIndexOutOfBoundsException e){
	    	  nbjou = 0;
	      }catch(NumberFormatException e){//Si la partie commence par "Plateau avec s joueur" (impossible de parseInt un "s")
    		  nbjou = 0;
    	  }
	      
	      if(nbjou>1){
		      return (nomFichier.startsWith(debut)&&nomFichier.endsWith(".pla"));
	      }

	      return false;

	   }
	   
	   
	   public String getDescription(){
	      return description;
	   }
	}