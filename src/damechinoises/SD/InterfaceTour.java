package damechinoises.SD;

import java.util.EventListener;

public interface InterfaceTour  extends EventListener{
	public void changementDeTour(TourEvent e);

	public void finJoueur(TourEvent e);
}
