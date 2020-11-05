package SEM;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class RegistroAplicacionTest {
	private String patente;
	private Zona zona;
	private int numeroCelular;
	private RegistroAplicacion registro;
	
	
	@BeforeEach
	public void setUp() {
		patente = "OJL215";
		zona = new Zona("Quilmes Oeste");
		numeroCelular = 1123251054;
		registro = new RegistroAplicacion(patente, zona, numeroCelular);
	}
	
	@Test
	public void gettersTest() {
		assertEquals("OJL215", registro.getPatente());
		assertEquals(LocalDateTime.now(), registro.getHoraDeInicio());
		assertEquals(zona, registro.getZona());
		assertEquals(numeroCelular, registro.getNumeroCelular());
		assertTrue(registro.estaVigente());
	}
}