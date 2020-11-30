package SEM;

import java.util.ArrayList;

public class Observable {
	protected ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public void addObserver(Observer observer) {
		observers.add(observer);
	}
	
	public void deleteObserver(Observer observer) {
		observers.remove(observer);
	}
	
	public void notifyObservers(Object param) {
		for(Observer i : observers) {
			i.update(param);
		}
	}
}