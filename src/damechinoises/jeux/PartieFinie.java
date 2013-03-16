package damechinoises.jeux;

import javax.swing.*;

import damechinoises.SD.Partie;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

public class PartieFinie extends JPanel {

	private FenetrePrincipale parent; 
	private JLabel premier=new JLabel("");
	private JLabel deuxieme=new JLabel("");
	private JLabel troisieme=new JLabel("");
	private JLabel quatrieme=new JLabel("");
	private JLabel cinquieme=new JLabel("");
	private JLabel sixieme=new JLabel("");
	private JPanel content;
	private JButton retour;
	
	
	public PartieFinie(FenetrePrincipale p,Partie pa,int[] nbpionsarrives) {
		//super(p);
		parent=p;
		this.setLayout(new BorderLayout());
		content = new JPanel(new GridLayout(0,1,0,20));
		int nbj=pa.getNbJoueurs();
		premier.setText("Premier : ");
		deuxieme.setText("Deuxieme : ");
		troisieme.setText("Troisieme : ");
		quatrieme.setText("Quatrieme : ");
		cinquieme.setText("Cinquieme : ");
		sixieme.setText("Sixieme : ");
		
		int[] tabfini=new int[6];
		int[] tabtemp=new int[6];
		for (int i = 0; i < nbj ; i++) {
			tabtemp[i]=nbpionsarrives[i];
		}
		int max=0;
		int indexmax=0;
		for(int k=0;k<nbj;k++){
			max=0;
			for (int i = 0; i < nbj ; i++) {
				if (tabtemp[i]>=max){
					max=tabtemp[i];
					indexmax=i;
				}
			}
			tabtemp[indexmax]=0;
			tabfini[k]=indexmax;
		}
		
		
		
		if(nbj==1){
			premier.setText(premier.getText()+pa.getJoueur(0).getCouleur()+" avec "+nbpionsarrives[tabfini[0]]+" pions arrivés.");
		}
		
		if(nbj==2){
			premier.setText(premier.getText()+pa.getJoueur(tabfini[0]).getCouleur()+" avec "+nbpionsarrives[tabfini[0]]+" pions arrivés.");
			deuxieme.setText(deuxieme.getText()+pa.getJoueur(tabfini[1]).getCouleur()+" avec "+nbpionsarrives[tabfini[1]]+" pions arrivés.");
		}
		
		if(nbj==3){
			premier.setText(premier.getText()+pa.getJoueur(tabfini[0]).getCouleur()+" avec "+nbpionsarrives[tabfini[0]]+" pions arrivés.");
			deuxieme.setText(deuxieme.getText()+pa.getJoueur(tabfini[1]).getCouleur()+" avec "+nbpionsarrives[tabfini[1]]+" pions arrivés.");
			troisieme.setText(troisieme.getText()+pa.getJoueur(tabfini[2]).getCouleur()+" avec "+nbpionsarrives[tabfini[2]]+" pions arrivés.");
		}
		
		if(nbj==4){
			premier.setText(premier.getText()+pa.getJoueur(tabfini[0]).getCouleur()+" avec "+nbpionsarrives[tabfini[0]]+" pions arrivés.");
			deuxieme.setText(deuxieme.getText()+pa.getJoueur(tabfini[1]).getCouleur()+" avec "+nbpionsarrives[tabfini[1]]+" pions arrivés.");
			troisieme.setText(troisieme.getText()+pa.getJoueur(tabfini[2]).getCouleur()+" avec "+nbpionsarrives[tabfini[2]]+" pions arrivés.");
			quatrieme.setText(quatrieme.getText()+pa.getJoueur(tabfini[3]).getCouleur()+" avec "+nbpionsarrives[tabfini[3]]+" pions arrivés.");
		}
		
		if(nbj==6){
			premier.setText(premier.getText()+pa.getJoueur(tabfini[0]).getCouleur()+" avec "+nbpionsarrives[tabfini[0]]+" pions arrivés.");
			deuxieme.setText(deuxieme.getText()+pa.getJoueur(tabfini[1]).getCouleur()+" avec "+nbpionsarrives[tabfini[1]]+" pions arrivés.");
			troisieme.setText(troisieme.getText()+pa.getJoueur(tabfini[2]).getCouleur()+" avec "+nbpionsarrives[tabfini[2]]+" pions arrivés.");
			quatrieme.setText(quatrieme.getText()+pa.getJoueur(tabfini[3]).getCouleur()+" avec "+nbpionsarrives[tabfini[3]]+" pions arrivés.");
			cinquieme.setText(cinquieme.getText()+pa.getJoueur(tabfini[4]).getCouleur()+" avec "+nbpionsarrives[tabfini[4]]+" pions arrivés.");
			sixieme.setText(sixieme.getText()+pa.getJoueur(tabfini[5]).getCouleur()+" avec "+nbpionsarrives[tabfini[5]]+" pions arrivés.");
		}
		
		
		
		retour = new JButton("Retour au menu principal");
		
		content.add(premier);
		if(nbj>=2)
			content.add(deuxieme);
		if(nbj>=3)
			content.add(troisieme);
		if(nbj>=4)
			content.add(quatrieme);
		if(nbj>=5)
			content.add(cinquieme);
		if(nbj==6)
			content.add(sixieme);
		content.add(retour);
		this.add(content,BorderLayout.CENTER);
		retour.addActionListener(new RetourListener(this));


	}
	
	
	public PartieFinie(FenetrePrincipale p,Partie pa) {
		//super(p);
		parent=p;
		this.setLayout(new BorderLayout());
		content = new JPanel(new GridLayout(0,1,0,20));
		int nbj=pa.getNbJoueurs();
		premier.setText("Premier : ");
		deuxieme.setText("Deuxieme : ");
		troisieme.setText("Troisieme : ");
		quatrieme.setText("Quatrieme : ");
		cinquieme.setText("Cinquieme : ");
		sixieme.setText("Sixieme : ");
		int nbcoups[]=new int[6];
		for(int i=0;i<nbj;i++){
			nbcoups[i]=pa.getJoueur(i).getNbCoup();
			if(!pa.getJoueur(i).fini()){
				nbcoups[i]+=2;
			}
		}
		int[] tabfini=new int[6];
		int[] tabtemp=new int[6];
		
		for (int i = 0; i < nbj ; i++) {
			tabtemp[i]=nbcoups[i];
		}

		int min=1000;
		int indexmin=0;
		for(int k=0;k<nbj;k++){
			min=1000;
			for (int i = 0; i < nbj ; i++) {
				if (tabtemp[i]<=min){
					min=tabtemp[i];
					indexmin=i;
				}
			}
			tabtemp[indexmin]=1000;
			tabfini[k]=indexmin;
		}


		
		retour = new JButton("Retour au menu principal");
		if(nbj==1){
			premier.setText(premier.getText()+pa.getJoueur(0).getCouleur()+" avec "+nbcoups[tabfini[0]]+" coups.");
		}
		
		if(nbj==2){
			premier.setText(premier.getText()+pa.getJoueur(tabfini[0]).getCouleur()+" avec "+nbcoups[tabfini[0]]+" coups.");
			deuxieme.setText(deuxieme.getText()+pa.getJoueur(tabfini[1]).getCouleur()+" avec "+nbcoups[tabfini[1]]+" coups.(+ "+(nbcoups[tabfini[1]]-nbcoups[tabfini[0]])+" coups)");
		}
		
		if(nbj==3){
			premier.setText(premier.getText()+pa.getJoueur(tabfini[0]).getCouleur()+" avec "+nbcoups[tabfini[0]]+" coups.");
			deuxieme.setText(deuxieme.getText()+pa.getJoueur(tabfini[1]).getCouleur()+" avec "+nbcoups[tabfini[1]]+" coups.(+ "+(nbcoups[tabfini[1]]-nbcoups[tabfini[0]])+" coups)");
			troisieme.setText(troisieme.getText()+pa.getJoueur(tabfini[2]).getCouleur()+" avec "+nbcoups[tabfini[2]]+" coups.(+ "+(nbcoups[tabfini[2]]-nbcoups[tabfini[0]])+" coups)");
		}
		
		if(nbj==4){
			premier.setText(premier.getText()+pa.getJoueur(tabfini[0]).getCouleur()+" avec "+nbcoups[tabfini[0]]+" coups.");
			deuxieme.setText(deuxieme.getText()+pa.getJoueur(tabfini[1]).getCouleur()+" avec "+nbcoups[tabfini[1]]+" coups.(+ "+(nbcoups[tabfini[1]]-nbcoups[tabfini[0]])+" coups)");
			troisieme.setText(troisieme.getText()+pa.getJoueur(tabfini[2]).getCouleur()+" avec "+nbcoups[tabfini[2]]+" coups.(+ "+(nbcoups[tabfini[2]]-nbcoups[tabfini[0]])+" coups)");
			quatrieme.setText(quatrieme.getText()+pa.getJoueur(tabfini[3]).getCouleur()+" avec "+nbcoups[tabfini[3]]+" coups.(+ "+(nbcoups[tabfini[3]]-nbcoups[tabfini[0]])+" coups)");
		}
		
		if(nbj==6){
			premier.setText(premier.getText()+pa.getJoueur(tabfini[0]).getCouleur()+" avec "+nbcoups[tabfini[0]]+" coups.");
			deuxieme.setText(deuxieme.getText()+pa.getJoueur(tabfini[1]).getCouleur()+" avec "+nbcoups[tabfini[1]]+" coups.(+ "+(nbcoups[tabfini[1]]-nbcoups[tabfini[0]])+" coups)");
			troisieme.setText(troisieme.getText()+pa.getJoueur(tabfini[2]).getCouleur()+" avec "+nbcoups[tabfini[2]]+" coups.(+ "+(nbcoups[tabfini[2]]-nbcoups[tabfini[0]])+" coups)");
			quatrieme.setText(quatrieme.getText()+pa.getJoueur(tabfini[3]).getCouleur()+" avec "+nbcoups[tabfini[3]]+" coups.(+ "+(nbcoups[tabfini[3]]-nbcoups[tabfini[0]])+" coups)");
			cinquieme.setText(cinquieme.getText()+pa.getJoueur(tabfini[4]).getCouleur()+" avec "+nbcoups[tabfini[4]]+" coups.(+ "+(nbcoups[tabfini[4]]-nbcoups[tabfini[0]])+" coups)");
			sixieme.setText(sixieme.getText()+pa.getJoueur(tabfini[5]).getCouleur()+" avec "+nbcoups[tabfini[5]]+" coups.(+ "+(nbcoups[tabfini[5]]-nbcoups[tabfini[0]])+" coups)");
		}
		
		
		content.add(premier);
		if(nbj>=2)
			content.add(deuxieme);
		if(nbj>=3)
			content.add(troisieme);
		if(nbj>=4)
			content.add(quatrieme);
		if(nbj>=5)
			content.add(cinquieme);
		if(nbj==6)
			content.add(sixieme);
		content.add(retour);
		this.add(content,BorderLayout.CENTER);
		retour.addActionListener(new RetourListener(this));


	}

	class RetourListener implements ActionListener{

		private PartieFinie m;
		
		public RetourListener(PartieFinie m){
			this.m=m;
		}
		
		public void actionPerformed(ActionEvent e) {
			m.getParentt().setMain(new MenuDemarage(m.getParentt()));
		}
		
	}
	
	
	public FenetrePrincipale getParentt(){
		return parent;
	}
}
