package damechinoises.SD;


import javax.swing.*;


public class CaseGraphique {
	
	JLabel casegraph;
	String couleur;
	ImageIcon iconefond;
	
	public CaseGraphique() {
		iconefond= new ImageIcon("casevide.jpg","case vide");
		couleur=new String("vide");
		casegraph=new JLabel(iconefond);
		casegraph.setSize(32,32);
	}
	
	public void setCouleur(String couleur){
		this.couleur=couleur;
		if (couleur.equals("vide"))
			iconefond= new ImageIcon("Images/casevide.png","case vide");
		if (couleur.equals("bleu"))
			iconefond= new ImageIcon("Images/casebleu.png","case vide");
		if (couleur.equals("rouge"))
			iconefond= new ImageIcon("Images/caserouge.png","case vide");
		if (couleur.equals("orange"))
			iconefond= new ImageIcon("Images/caseorange.png","case vide");
		if (couleur.equals("vert"))
			iconefond= new ImageIcon("Images/casevert.png","case vide");
		if (couleur.equals("jaune"))
			iconefond= new ImageIcon("Images/casejaune.png","case vide");
		if (couleur.equals("violet"))
			iconefond= new ImageIcon("Images/caseviolet.png","case vide");
		casegraph=new JLabel(iconefond);
		casegraph.setSize(32,32);
	}
}
