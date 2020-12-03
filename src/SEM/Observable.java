package SEM;

import java.util.ArrayList;
import java.util.List;

public class Observable {
	private List<Observer> obs = new ArrayList<Observer>();
	
	public void addObserver(Observer o) {
		this.obs.add(o);
	}
	
	public void deleteObserver(Observer o) {
		if(this.obs.contains(o)) {
			this.obs.remove(o);
		}
	}
	
	public int countObservers() {
		return this.obs.size();
	}	
	
	public void notifyObservers(Object param) {
		for(Observer o : obs) {
			o.update(param);
		}
	}
}