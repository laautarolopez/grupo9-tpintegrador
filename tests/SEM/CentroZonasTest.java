package SEM;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CentroZonasTest {
	private String zona1;
	private String zona2;
	private CentroZonas centroZonas;
	
	@BeforeEach
	public void setUp() {
		centroZonas = new CentroZonas();
		zona1 = "Quilmes Oeste";
		zona2 = "Quilmes Este";
	}
	
	@Test
	public void agregarZonaTest() {
		centroZonas.agregarZona(zona1);
		centroZonas.agregarZona(zona2);
		assertTrue(centroZonas.esZonaDeEstacionamiento(zona1));
		assertTrue(centroZonas.esZonaDeEstacionamiento(zona2));
	}
	
	@Test
	public void eliminarZonaTest() {
		centroZonas.agregarZona(zona1);
		centroZonas.agregarZona(zona2);
		assertTrue(centroZonas.esZonaDeEstacionamiento(zona1));
		assertTrue(centroZonas.esZonaDeEstacionamiento(zona2));
		
		centroZonas.eliminarZona(zona1);
		assertFalse(centroZonas.esZonaDeEstacionamiento(zona1));
		
		centroZonas.eliminarZona(zona2);
		assertFalse(centroZonas.esZonaDeEstacionamiento(zona2));
	}
	
	@Test
	public void esZonaDeEstacionamientoTest() {
		assertFalse(centroZonas.esZonaDeEstacionamiento(zona1));
		centroZonas.agregarZona(zona1);
		assertTrue(centroZonas.esZonaDeEstacionamiento(zona1));
		
		assertFalse(centroZonas.esZonaDeEstacionamiento(zona2));
		centroZonas.agregarZona(zona2);
		assertTrue(centroZonas.esZonaDeEstacionamiento(zona2));
		
		assertFalse(centroZonas.esZonaDeEstacionamiento("Berazategui"));
	}
}