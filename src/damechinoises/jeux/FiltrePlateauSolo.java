package damechinoises.jeux;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FiltrePlateauSolo extends FileFilter{
	   //Description et extension accept�e par le filtre
	   private String description;
	   //Constructeur � partir de la description et de l'extension accept�e
	   public FiltrePlateauSolo(){
	      
	      description="Plateau avec 1 joueur";
	   }
	   //Impl�mentation de FileFilter
	   public boolean accept(File file){
	      if(file.isDirectory()) { 
	         return true; 
	      } 
	      String nomFichier = file.getName().toLowerCase(); 
	      return (nomFichier.startsWith("plateau avec 1 joueur")&&nomFichier.endsWith(".pla"));
	      
	   }
	      public String getDescription(){
	      return description;
	   }
	}