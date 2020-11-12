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

		public RegistroAppTesteable(String patente, String zona, Celular celular) {
			super(patente, zona, celular );
		}
		
		public void setHoraDeInicio(LocalDateTime hora) {
			this.horaDeInicio = hora;
		}
		protected void setClock(Clock clock) {
			this.clock = clock;
		}
		
		public int obtenerCosto() {
			return this.calcularCosto();
		}
		
		public int obtenerHoras() {
			return this.calcularDuracion();
		}
	}
	private String patente;
	private String zona;
	private Celular celular;
	private RegistroAppTesteable registro;
	private Clock clock;
	
	@BeforeEach
	public void setUp() {
		patente = "OJL215";
		zona = "Quilmes Oeste";
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
	@Test
	public void horaDeFinTest1() {
		clock = Clock.fixed(Instant.parse("2020-11-10T19:24:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock);
		registro.setHoraDeInicio(LocalDateTime.now(clock));
		assertEquals(LocalDateTime.parse("2020-11-10T19:24:24.498559900"), registro.getHoraDeFin());
	}
	@Test
	public void horaDeFinTest2() {
		clock = Clock.fixed(Instant.parse("2020-11-10T20:24:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock);
		registro.setHoraDeInicio(LocalDateTime.now(clock));
		assertEquals(LocalDateTime.parse("2020-11-10T20:00"), registro.getHoraDeFin());
	}
	@Test
	public void validez1 () {
		clock = Clock.fixed(Instant.parse("2020-11-10T16:24:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock);
		registro.setHoraDeInicio(LocalDateTime.now(clock));
		Clock clock2 = Clock.fixed(Instant.parse("2020-11-10T19:28:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock2);
		assertFalse(registro.estaVigente());
	}
	@Test
	public void validez2() {
		clock = Clock.fixed(Instant.parse("2020-11-10T19:24:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock);
		registro.setHoraDeInicio(LocalDateTime.now(clock));
		Clock clock2 = Clock.fixed(Instant.parse("2020-11-10T22:10:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock2);
		assertTrue(registro.estaVigente());
	}
	
	@Test
	public void costo1() {
		clock = Clock.fixed(Instant.parse("2020-11-10T18:24:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock);
		registro.setHoraDeInicio(LocalDateTime.now(clock));
		Clock clock2 = Clock.fixed(Instant.parse("2020-11-10T22:10:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock2);
		assertEquals(160, registro.obtenerCosto());
		Clock clock3 = Clock.fixed(Instant.parse("2020-11-10T23:24:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock3);
		assertEquals(200, registro.obtenerCosto());
		Clock clock4 = Clock.fixed(Instant.parse("2020-11-10T22:24:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock4);
		assertEquals(200, registro.obtenerCosto());
	}
	@Test
	public void costo2() {
		clock = Clock.fixed(Instant.parse("2020-11-10T12:24:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock);
		registro.setHoraDeInicio(LocalDateTime.now(clock));
		Clock clock2 = Clock.fixed(Instant.parse("2020-11-10T23:59:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock2);
		assertEquals(440, registro.obtenerCosto());
	}
	
	@Test
	public void calculoHoras() {
		clock = Clock.fixed(Instant.parse("2020-11-10T13:24:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock);
		registro.setHoraDeInicio(LocalDateTime.now(clock));
		Clock clock2 = Clock.fixed(Instant.parse("2020-11-10T21:10:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock2);
		assertEquals(8,registro.obtenerHoras());
		Clock clock3 = Clock.fixed(Instant.parse("2020-11-10T23:30:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock3);
		assertEquals(10,registro.obtenerHoras());
	}
	
}