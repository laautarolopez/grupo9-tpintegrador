package SEM;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
class ObserverTest {
	
	class ObservableTesteable extends Observable{
		public ArrayList<Observer> getObservers(){
			return observers;
		}
	}
	
	ObservableTesteable observableTesteable;
	
	@BeforeEach
	void setup() {
		observableTesteable = new ObservableTesteable();
	}
	@Test
	void agregarObserver() {
		Observer obs = mock(Observer.class);
		observableTesteable.addObserver(obs);
		assertTrue(observableTesteable.getObservers().contains(obs));
	}
	@Test
	void quitarObserver() {
		Observer obs = mock(Observer.class);
		observableTesteable.addObserver(obs);
		observableTesteable.deleteObserver(obs);;
		assertFalse(observableTesteable.getObservers().contains(obs));
	}
	@Test
	void notificarObserver() {
		Observer obs = mock(Observer.class);
		observableTesteable.addObserver(obs);
		observableTesteable.notifyObservers("un dato");
		observableTesteable.notifyObservers("un dato");
		observableTesteable.notifyObservers("un dato");
		verify(obs, times(3)).update("un dato");
	}


}