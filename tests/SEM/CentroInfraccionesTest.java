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
}