package SEM;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

class TestCentroInfracciones extends CentroInfracciones{
	protected static TestCentroInfracciones centro = null;
	
	protected TestCentroInfracciones() {
		super();
	}
	
	public static TestCentroInfracciones getCentro() {
		if(centro == null) {
			centro = new TestCentroInfracciones();
			return centro;
		}else {
			return centro;
		} 
	}
	
	public ArrayList<Infraccion> getInfracciones(){
		return this.infracciones;
	}
}

public class CentroInfraccionesTest {
	public TestCentroInfracciones centroInfracciones;
	public Infraccion infraccion1;
	public Infraccion infraccion2;
	
	@BeforeEach
	public void setUp() {
		centroInfracciones = TestCentroInfracciones.getCentro();
	}
	
	@Test
	public void testSingleton() {
		assertEquals(centroInfracciones, TestCentroInfracciones.getCentro());
	}
	
	@Test
	public void agregarInfraccionTest() {
		infraccion1 = mock(Infraccion.class);	
		centroInfracciones.registrarInfraccion(infraccion1);
		assertTrue(centroInfracciones.getInfracciones().contains(infraccion1));
	}
	
	public void eliminarInfraccionTest() {
		infraccion1 = mock(Infraccion.class);
		infraccion2 = mock(Infraccion.class);
		centroInfracciones.registrarInfraccion(infraccion1);
		assertTrue(centroInfracciones.getInfracciones().contains(infraccion1));
		assertFalse(centroInfracciones.getInfracciones().contains(infraccion2));
		
		assertFalse(centroInfracciones.eliminarInfraccion(infraccion2.getPatente()));
		assertTrue(centroInfracciones.eliminarInfraccion(infraccion1.getPatente()));
		assertFalse(centroInfracciones.getInfracciones().contains(infraccion1));
	}
	
	@Test
	public void testNotificarObservers() {
		Observer observer = mock(Observer.class);
		infraccion1 = mock(Infraccion.class);
		centroInfracciones.addObserver(observer);
		centroInfracciones.registrarInfraccion(infraccion1);
		verify(observer,times(1)).update(centroInfracciones.getInfracciones());
	}
	
	@Test
	public void testNotificarObservers2() {
		Observer observer = mock(Observer.class);
		infraccion1 = mock(Infraccion.class);
		when(infraccion1.getPatente()).thenReturn("abc123");
		centroInfracciones.registrarInfraccion(infraccion1);
		centroInfracciones.addObserver(observer);
		centroInfracciones.eliminarInfraccion("abc123");
		verify(observer,times(1)).update(centroInfracciones.getInfracciones());
	}
}