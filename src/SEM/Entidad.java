package SEM;

public abstract class Entidad implements Observer {
	
	public void suscribirse(Observable observable) {
		observable.addObserver(this);
	}
	
	public void desuscribirse(Observable observable) {
		observable.deleteObserver(this);
	}
}