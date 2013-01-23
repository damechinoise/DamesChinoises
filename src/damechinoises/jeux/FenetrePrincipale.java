package damechinoises.jeux;

import javax.swing.*;

public class FenetrePrincipale extends JFrame {
	
	private JPanel main;
	
	public FenetrePrincipale(){
		this.setTitle("Dames chinoises");
		this.setSize(800,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		main= new MenuDemarage(this);
		this.add(main);
		this.setVisible(true);
	}
	
	public JPanel getMain(){
		return main;
	}
	
	public void setMain(JPanel panel){
		this.remove(main);
		main=panel;
		this.add(main);
		this.setVisible(false);
		this.setVisible(true);
	}
}
