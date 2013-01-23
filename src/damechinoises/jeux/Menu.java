package damechinoises.jeux;

import java.awt.BorderLayout;

import javax.swing.*;

public abstract class Menu extends JPanel {

	private JFrame parent; 
	
	public Menu(JFrame p){
		parent=p;
		this.setLayout(new BorderLayout());
	}
	
}
