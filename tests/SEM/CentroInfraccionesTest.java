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
		
		infraccion1 = mock(Infraccion.class);
		when(infraccion1.getPatente()).thenReturn("ABC123");
		
		infraccion2 = mock(Infraccion.class);
		when(infraccion2.getPatente()).thenReturn("DEF456");
	}
	
	@Test
	public void registrarInfraccionTest() {
		centroInfracciones.registrarInfraccion(infraccion1);
		assertTrue(centroInfracciones.contieneInfraccion(infraccion1.getPatente()));
		
		centroInfracciones.registrarInfraccion(infraccion2);
		assertTrue(centroInfracciones.contieneInfraccion(infraccion2.getPatente()));
	}
	
	@Test
	public void contieneInfraccionTest() {
		assertFalse(centroInfracciones.contieneInfraccion(infraccion1.getPatente()));
		centroInfracciones.registrarInfraccion(infraccion1);
		assertTrue(centroInfracciones.contieneInfraccion(infraccion1.getPatente()));
		
		assertFalse(centroInfracciones.contieneInfraccion(infraccion2.getPatente()));
		centroInfracciones.registrarInfraccion(infraccion2);
		assertTrue(centroInfracciones.contieneInfraccion(infraccion2.getPatente()));
		
		assertFalse(centroInfracciones.contieneInfraccion("CCC111"));
	}
}