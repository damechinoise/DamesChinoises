package damechinoises.jeux;

import java.awt.BorderLayout;

import javax.swing.*;

public abstract class Menu extends JPanel {

	private FenetrePrincipale parent; 
	
	public Menu(FenetrePrincipale p){
		parent=p;
		this.setLayout(new BorderLayout());
	}
	
	public FenetrePrincipale getParent(){
		return parent;
	}

}
