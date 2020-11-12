package SEM;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InspectorTest {
	private int dni;
	private String zona;
	private Inspector inspector;
	
	@BeforeEach
	public void setUp() {
		dni = 35125451;
		zona = "Quilmes Oeste";
		inspector = new Inspector(dni, zona);
	}
	
	@Test
	public void gettersTest() {
		assertEquals(dni, inspector.getDni());
		assertEquals(zona, inspector.getZona());
	}
	
	@Test
	public void setZonaTest() {
		String zona2 = "Quilmes Este";
		inspector.setZona(zona2);
		assertEquals(zona2, inspector.getZona());
		assertNotEquals(zona, inspector.getZona());
	}
}