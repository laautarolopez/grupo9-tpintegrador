package SEM;

import java.util.ArrayList;
import java.util.List;

public class Observable {
	private boolean changed = false;
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
	
	public void setChanged() {
		this.changed = true;
	}
	
	public boolean hasChanged() {
		return this.changed;
	}
	
	public void notifyObservers(Object param) {
		if(this.hasChanged()) {
			for(Observer o : obs) {
				o.update(param);
			}
			this.changed = false;
		}
	}
}