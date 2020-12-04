package SEM;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import static org.mockito.Mockito.*;

class SistemaTest {
	
	@Spy
	CentroInfracciones ci;
	@Spy
	CentroRegistros cr;
	@Spy
	CentroCelulares cc;
	@Spy
	CentroZonas cz;
	@InjectMocks
	public Sistema sistema ;//= new CentroRegistros();
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test 
	void vigente(){
		when(cr.estaVigente("123")).thenReturn(true);
		assertTrue(sistema.estaVigente("123"));
	}
	
	@Test
	void estacionamientos() {
		RegistroDeEstacionamiento reg = mock(RegistroDeEstacionamiento.class);
		sistema.registrarInicio(reg);
		when(reg.getPatente()).thenReturn("123");
		sistema.registrarFinal("123");
		verify(cr, times(1)).registrarInicio(reg);
		verify(cr, times(1)).registrarFinal("123");
	}
	@Test
	void infraccion() {
		Infraccion inf = mock(Infraccion.class);
		sistema.registrarInfraccion(inf);
		verify(ci,times(1)).registrarInfraccion(inf);
	}
	
	@Test
	void agregarS() {
		sistema.agregarSaldo("123", 700);
		verify(cc, times(1)).agregarSaldo("123", 700);
	}
	
	@Test
	void restarS() {
		sistema.restarSaldo("123", 21);
		verify(cc,times(1)).restarSaldo("123", 21);
	}
	
	@Test
	void getS() {
		when(cc.saldoDe("123")).thenReturn(100);
		assertEquals(100,sistema.getSaldo("123"));
	}
	@Test
	void agregarZ() {
		sistema.agregarZona("a");
		verify(cz,times(1)).agregarZona("a");
	}
	@Test
	void quitarZ() {
		sistema.eliminarZona("a");
		verify(cz,times(1)).eliminarZona("a");
	}
	
	@Test
	void esZonaDeestacionamiento() {
		when(cz.esZonaDeEstacionamiento("a")).thenReturn(true);
		assertTrue(sistema.esZonaDeEstacionamiento("a"));
	}
	@Test
	void horaEstacionamiento() {
		sistema.setClock(Clock.fixed(Instant.parse("2020-11-10T15:24:24.498559900Z"), ZoneId.of("GMT-3")));
		assertTrue(sistema.esHoraDeEstacionamiento());
	}
	@Test
	void horaEstacionamiento2() {
		sistema.setClock(Clock.fixed(Instant.parse("2020-11-10T23:24:24.498559900Z"), ZoneId.of("GMT-3")));
		assertFalse(sistema.esHoraDeEstacionamiento());
	}
	@Test
	void horaEstacionamiento3() {
		sistema.setClock(Clock.fixed(Instant.parse("2020-11-10T05:24:24.498559900Z"), ZoneId.of("GMT-3")));
		assertFalse(sistema.esHoraDeEstacionamiento());
	}
	
	@Test
	void valorDeHora() {
		sistema.setValorDeHora(12);
		assertEquals(12,sistema.getValorDeHora());
	}
	
	@Test
	void finalizartodos() {
		sistema.setClock(Clock.fixed(Instant.parse("2020-11-10T15:24:24.498559900Z"), ZoneId.of("GMT-3")));
		sistema.finalizarEstacionamientos();
		verify(cr, times(0)).finalizarTodos();
	}
	
	@Test
	void finalizartodos2() {
		sistema.setClock(Clock.fixed(Instant.parse("2020-11-10T05:24:24.498559900Z"), ZoneId.of("GMT-3")));
		sistema.finalizarEstacionamientos();
		verify(cr, times(1)).finalizarTodos();
	}
}