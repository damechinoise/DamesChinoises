package damechinoises.jeux;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FiltreSimple extends FileFilter{
	   //Description et extension acceptée par le filtre
	   private String description;
	   private String extension;
	   //Constructeur à partir de la description et de l'extension acceptée
	   public FiltreSimple(){
	      description="Fichiers .dc";
	   }
	   //Implémentation de FileFilter
	   public boolean accept(File file){
	      if(file.isDirectory()) { 
	         return true; 
	      } 
	      String nomFichier = file.getName().toLowerCase(); 
	 
	      return nomFichier.endsWith(".dc");
	   }
	      public String getDescription(){
	      return description;
	   }
	}