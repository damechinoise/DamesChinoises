package damechinoises.SD;

import java.util.EventObject;

public class TourEvent extends EventObject {
	
	private int playernum;
	public TourEvent(Object arg0) {
		super(arg0);
		playernum=-1;
	}
	
	public TourEvent(Object arg0,int playernum) {
		super(arg0);
	}
	
	public int getPlayerNum(){
		return playernum;
	}

}
