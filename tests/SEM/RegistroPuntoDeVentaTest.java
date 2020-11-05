package SEM;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegistroPuntoDeVentaTest {
	private String patente;
	private Zona zona;
	private int dinero;
	private RegistroPuntoDeVenta registro;
	
	
	@BeforeEach
	public void setUp() {
		patente = "AFE105";
		zona = mock(Zona.class);
		dinero = 80;
		registro = new RegistroPuntoDeVenta(patente, zona, dinero);
	}
	
	@Test
	public void gettersTest() {
		assertEquals("AFE105", registro.getPatente());
		assertEquals(zona, registro.getZona());
		assertTrue(registro.estaVigente());
		
		int minutos = LocalDateTime.now().getMinute();
		int segundos = LocalDateTime.now().getSecond();
		assertEquals(minutos, registro.getHoraDeInicio().getMinute());
		assertEquals(segundos, registro.getHoraDeInicio().getSecond());
		
		assertEquals(registro.getHoraDeFin(), registro.getHoraDeInicio().plusHours(2));
		//when(!registro.estaVigente()).thenReturn(registro.getHoraDeFin().isAfter(registro.getHoraDeInicio()));
	}
}