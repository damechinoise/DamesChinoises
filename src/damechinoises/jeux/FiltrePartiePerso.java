package damechinoises.jeux;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FiltrePartiePerso extends FileFilter{
	   //Description et extension acceptée par le filtre
	   private String description;
	   private String debut;
	   //Constructeur à partir de la description et de l'extension acceptée
	   public FiltrePartiePerso(String description, String debut){
	      if(description == null ||debut ==null){
	         throw new NullPointerException("La description (ou debut) ne peut être null.");
	      }
	      this.description = description;
	      this.debut = debut;
	   }
	   //Implémentation de FileFilter
	   public boolean accept(File file){
	      if(file.isDirectory()) { 
	         return true; 
	      } 
	      String nomFichier = file.getName().toLowerCase(); 
	      return (nomFichier.startsWith(debut)&&nomFichier.endsWith(".pla"));
	      
	   }
	      public String getDescription(){
	      return description;
	   }
	}