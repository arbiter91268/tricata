package tricata.model;

import java.util.Observable;
import java.util.Observer;

/**
 * Game model. Interacts with observer
 */
public class Tricata extends Observable {

	public Tricata(Observer observer) {
		addObserver(observer);
	}


}
