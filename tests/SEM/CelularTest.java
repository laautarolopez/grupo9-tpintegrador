package SEM;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CelularTest {
	private Celular celular;
	private Sistema sistema;
	private Gps gps;
	private ByteArrayOutputStream b;
	
	@BeforeEach
	public void setUp() {
		sistema = mock(Sistema.class);
		gps = mock(Gps.class);
		String numero = "1125458510";
		String patente = "ABC123";
		celular = new Celular(sistema, numero, patente, gps);
		b = new ByteArrayOutputStream();
		System.setOut(new PrintStream(b));
	}
	
	@Test
	public void constructorTest() {
		assertEquals(sistema, celular.sistema);
		assertEquals("1125458510", celular.getNumero());
		assertEquals("ABC123", celular.getPatente());
	}
	
	@Test
	public void setPatenteTest() {
		assertEquals("ABC123", celular.getPatente());
		
		String patente = "ABC456";
		celular.setPatente(patente);
		assertEquals(patente, celular.getPatente());
	}
	
	@Test
	public void estaEnZonaDeEstacionamientoTest() {
		when(gps.getZona()).thenReturn("Quilmes");
		
		verifyNoInteractions(sistema);
		verifyNoInteractions(gps);
		
		celular.estaEnZonaDeEstacionamiento();
		verify(sistema).esZonaDeEstacionamiento("Quilmes");
		verify(gps).getZona();
	}
	
	@Test
	public void getSaldoActualTest() {
		verifyNoInteractions(sistema);
		
		celular.getSaldoActual();
		verify(sistema).getSaldo(celular.getNumero());
	}
	
	@Test
	public void getZonaTest() {
		verifyNoInteractions(gps);
		
		celular.getZona();
		verify(gps).getZona();
	}
	
	@Test
	public void notificarTest() {
		celular.notificar("Mensaje a notificar.");
		String s = b.toString().substring(0, b.toString().length()-2);
		
		assertEquals("Mensaje a notificar.", s);
	}
}