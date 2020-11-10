package SEM;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class RegistroAplicacionTest {
	public class RegistroAppTesteable extends RegistroAplicacion{

		public RegistroAppTesteable(String patente, Zona zona, Celular celular) {
			super(patente, zona, celular );
		}
		
		public void setHoraDeInicio(LocalDateTime hora) {
			this.horaDeInicio = hora;
		}
		protected void setClock(Clock clock) {
			this.clock = clock;
		}
	}
	private String patente;
	private Zona zona;
	private Celular celular;
	private RegistroAppTesteable registro;
	private Clock clock;
	
	@BeforeEach
	public void setUp() {
		patente = "OJL215";
		zona = mock(Zona.class);
		celular = mock(Celular.class);
		registro = new RegistroAppTesteable(patente, zona, celular);
		when(celular.getNumero()).thenReturn("1145251452");
		when(celular.getSaldoActual()).thenReturn(120);
		
	}
	
	@Test
	public void gettersTest() {
		assertEquals("OJL215", registro.getPatente());
		assertEquals(zona, registro.getZona());
		assertEquals("1145251452", registro.getNumeroCelular());
	}
	
	public void horaDeFinTest1() {
		clock = Clock.fixed(Instant.parse("2020-11-10T19:24:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock);
		registro.setHoraDeInicio(LocalDateTime.now(clock));
		assertEquals(LocalDateTime.parse("2020-11-10T19:24:24.498559900"), registro.getHoraDeFin());
	}
	
	public void horaDeFinTest2() {
		clock = Clock.fixed(Instant.parse("2020-11-10T20:24:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock);
		registro.setHoraDeInicio(LocalDateTime.now(clock));
		assertEquals(LocalDateTime.parse("2020-11-10T20:00"), registro.getHoraDeFin());
	}
	
	public void validez1 () {
		clock = Clock.fixed(Instant.parse("2020-11-10T19:24:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock);
		registro.setHoraDeInicio(LocalDateTime.now(clock));
		Clock clock2 = Clock.fixed(Instant.parse("2020-11-10T22:28:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock2);
		assertFalse(registro.estaVigente());
	}
	
	public void validez2() {
		clock = Clock.fixed(Instant.parse("2020-11-10T19:24:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock);
		registro.setHoraDeInicio(LocalDateTime.now(clock));
		Clock clock2 = Clock.fixed(Instant.parse("2020-11-10T22:10:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock2);
		assertTrue(registro.estaVigente());
	}
}