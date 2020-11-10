package SEM;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class RegistroAplicacionTest {
	private String patente;
	private Zona zona;
	private Celular celular;
	private RegistroAplicacion registro;
	
	
	@BeforeEach
	public void setUp() {
		patente = "OJL215";
		zona = mock(Zona.class);
		celular = mock(Celular.class);
		registro = new RegistroAplicacion(patente, zona, celular);
		when(celular.getNumero()).thenReturn("1145251452");
	}
	
	@Test
	public void gettersTest() {
		assertEquals("OJL215", registro.getPatente());
		assertEquals(zona, registro.getZona());
		assertEquals("1145251452", registro.getNumeroCelular());
		assertTrue(registro.estaVigente());
		
		int minutos = LocalDateTime.now().getMinute();
		int segundos = LocalDateTime.now().getSecond();
		assertEquals(minutos, registro.getHoraDeInicio().getMinute());
		assertEquals(segundos, registro.getHoraDeInicio().getSecond());
		
		//assertNull(registro.getHoraDeFin());
		// hacer cuenta con saldo
	}
}