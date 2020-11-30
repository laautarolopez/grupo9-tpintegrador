package SEM;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;


public class CentroInfraccionesTest {
	public CentroInfracciones centroInfracciones;
	public Infraccion infraccion1;
	public Infraccion infraccion2;
	
	@BeforeEach
	public void setUp() {
		centroInfracciones = new CentroInfracciones();
	}
	
	@Test
	public void agregarInfraccionTest() {
		infraccion1 = mock(Infraccion.class);
		when(infraccion1.getPatente()).thenReturn("ABC123");
		centroInfracciones.registrarInfraccion(infraccion1);
		assertTrue(centroInfracciones.contieneInfraccion(infraccion1.getPatente()));
	}
}