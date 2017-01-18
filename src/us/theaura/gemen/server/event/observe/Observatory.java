package us.theaura.gemen.server.event.observe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * too tired.
 * 
 * @since Jan 15, 2017 9:14:28 PM
 * @author Shortninja
 */

public class Observatory {

	private static Map<Observable, List<Observer>> observers = new EnumMap<Observable, List<Observer>>(Observable.class);
	
	/**
	 * Initializes observers map with empty lists.
	 */
	public Observatory() {
		for(Observable observable : Observable.values()) {
			observers.put(observable, new ArrayList<Observer>());
		}
	}
	
	/**
	 * @param observable Event to subscribe to
	 * @param observer Observer to call.
	 */
	public void subscribe(Observable observable, Observer observer) {
		if(observers.containsKey(observable)) {
			observers.get(observable).add(observer);
		}else observers.put(observable, Arrays.asList(observer));
	}
	
	/**
	 * @param observable Observable type to call for.
	 */
	public void call(Observable observable, String key, String value) {
		for(Observer observer : observers.get(observable)) {
			observer.update(key, value);
		}
	}
	
}