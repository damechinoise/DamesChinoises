package damechinoises.jeux;

import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.*;

public class FenetrePrincipale extends JFrame {
	
	private JPanel content;
	
	public FenetrePrincipale(){
		super();
		this.setSize(800,600);
		this.setTitle("Dames chinoises");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		content = new JPanel(new GridLayout(0,1,100,100));
		this.setContentPane(content);
		setMain(new MenuDemarage(this));
		this.setVisible(true);
	}
	
	public void setMain(JPanel panel){
		content.removeAll();
		content.add(panel);
		content.validate();
		content.repaint();
	}
}
