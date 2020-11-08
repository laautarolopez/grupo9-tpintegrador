package SEM;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class InspectorTest {
	private int dni;
	private Zona zona;
	private Inspector inspector;
	
	@BeforeEach
	public void setUp() {
		dni = 35125451;
		zona = mock(Zona.class);
		inspector = new Inspector(dni, zona);
	}
	
	@Test
	public void gettersTest() {
		assertEquals(dni, inspector.getDni());
		assertEquals(zona, inspector.getZona());
	}
	
	@Test
	public void setZonaTest() {
		Zona zona2 = mock(Zona.class);
		inspector.setZona(zona2);
		assertEquals(zona2, inspector.getZona());
		assertNotEquals(zona, inspector.getZona());
	}
}