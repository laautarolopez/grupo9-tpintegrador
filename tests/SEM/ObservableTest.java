package SEM;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ObservableTest {
	private Observable observable;
	private Observer observer;
	
	@BeforeEach
	public void setup() {
		observable = new Observable();
		observer = mock(Observer.class);
	}
	
	@Test
	public void addObserverTest() {
		assertEquals(0, observable.countObservers());
		observable.addObserver(observer);
		assertEquals(1, observable.countObservers());
	}
	
	@Test
	public void deleteObserverTest() {
		// No rompe si se quiere eliminar un observer que no está en la lista.
		assertEquals(0, observable.countObservers());
		observable.deleteObserver(observer);
		assertEquals(0, observable.countObservers());
		
		observable.addObserver(observer);
		assertEquals(1, observable.countObservers());
		observable.deleteObserver(observer);
		assertEquals(0, observable.countObservers());
	}
	
	@Test
	public void notifyObserversTest() {
		Observer observer2 = mock(Observer.class);
		observable.addObserver(observer);
		observable.addObserver(observer2);
		
		observable.notifyObservers("a");
		verify(observer).update("a");
		verify(observer2).update("a");
	}
}