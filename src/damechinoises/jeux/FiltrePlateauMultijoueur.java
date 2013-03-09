package damechinoises.jeux;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FiltrePlateauMultijoueur extends FileFilter{
	   //Description et extension accept�e par le filtre
	   private String description;
	   //Constructeur � partir de la description et de l'extension accept�e
	   public FiltrePlateauMultijoueur(){
	      description = "Plateau avec au moins 2 joueurs";
	   }
	   //Impl�mentation de FileFilter
	   public boolean accept(File file){
	      if(file.isDirectory()) { 
	         return true; 
	      } 
	      String nomFichier = file.getName().toLowerCase(); 
	      char nbjoueur=nomFichier.charAt(13);
	      
	      String nbj=String.valueOf(nbjoueur);
	      int nbjou=Integer.parseInt(nbj);
	      if(nbjou>1){
	    	  String debut=new String("plateau avec ");
		      debut=debut.concat(nbj);
		      System.out.println(debut);
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