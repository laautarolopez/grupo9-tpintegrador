package SEM;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
class ObserverTest {

	
	Observer observer;
	
	@BeforeEach
	void setup() {
		observer = new Observer();
		spyobserver = spy(observer);
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
		assertTrue(observableTesteable.getObservers().contains(obs));
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
