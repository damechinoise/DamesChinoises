package damechinoises.SD;


import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
 
public class Chronometre{
    Timer jolieTimer;
    int minute,seconde;
    
    public Chronometre(int minutes,int secondes) throws HeadlessException
    {
    	super();
    	minute=minutes;
    	seconde=secondes;
    	int delay = 1000; //milliseconds
    	ActionListener tache_timer;
    	
    	/* Action réalisé par le timer */
    	tache_timer = new ActionListener() {
    		public void actionPerformed(ActionEvent e1) {
    			if (seconde == 00) {
    				seconde = 60;
    				minute--;
    			}
    			seconde--;
    			
    		}
    	};
	 
	  jolieTimer = new Timer(delay, tache_timer);
	  jolieTimer.start();
 
    }
    
    public int getMinute (){
    	return minute;
    }
    
    public int getSeconde (){
    	return seconde;
    }
    
    public void setMinute (int min){
    	minute=min;
    }
    public void setSeconde (int sec){
    	seconde=sec;
    }
}
