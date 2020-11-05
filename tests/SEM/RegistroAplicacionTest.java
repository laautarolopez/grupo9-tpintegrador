package SEM;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class RegistroAplicacionTest {
	private String patente;
	private Zona zona;
	private int numeroCelular;
	private RegistroAplicacion registro;
	
	
	@BeforeEach
	public void setUp() {
		patente = "OJL215";
		zona = mock(Zona.class);
		numeroCelular = 1123251054;
		registro = new RegistroAplicacion(patente, zona, numeroCelular);
	}
	
	@Test
	public void gettersTest() {
		assertEquals("OJL215", registro.getPatente());
		assertEquals(zona, registro.getZona());
		assertEquals(numeroCelular, registro.getNumeroCelular());
		assertTrue(registro.estaVigente());
		
		int minutos = LocalDateTime.now().getMinute();
		int segundos = LocalDateTime.now().getSecond();
		assertEquals(minutos, registro.getHoraDeInicio().getMinute());
		assertEquals(segundos, registro.getHoraDeInicio().getSecond());
		
		assertNull(registro.getHoraDeFin()); // Tiene hora de fin cuando deja de estar vigente.
	}
}