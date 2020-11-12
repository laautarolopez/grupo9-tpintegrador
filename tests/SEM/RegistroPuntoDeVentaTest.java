package SEM;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegistroPuntoDeVentaTest {
	public class RegistroPuntoTesteable extends RegistroPuntoDeVenta{

		public RegistroPuntoTesteable(String patente, String zona, int horas) {
			super(patente, zona, horas);
		}
		
		public void setHoraDeInicio(LocalDateTime hora) {
			this.horaDeInicio = hora;
		}
		
		protected void setClock(Clock clock) {
			this.clock = clock;
		}
		
		public void setHoraDeFinTest(int horas) {
			this.setHoraDeFin(horas);
		}
		
	}
	private String patente;
	private String zona;
	private RegistroPuntoTesteable registro;
	private Clock clock;
	
	
	@BeforeEach
	public void setUp() {
		patente = "AFE105";
		zona = "Quilmes Oeste";
		registro = new RegistroPuntoTesteable(patente, zona, 3);
		clock = Clock.fixed(Instant.parse("2020-11-10T19:24:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock);
		registro.setHoraDeInicio(LocalDateTime.now(clock));
		registro.setHoraDeFinTest(3);
	}
	
	@Test
	public void gettersTest() {
		assertEquals("AFE105", registro.getPatente());
		assertEquals(zona, registro.getZona());
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
		clock = Clock.fixed(Instant.parse("2020-11-10T22:24:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock);
		registro.setHoraDeInicio(LocalDateTime.now(clock));
		registro.setHoraDeFinTest(3);
		assertEquals(LocalDateTime.parse("2020-11-10T20:00"), registro.getHoraDeFin());
	}
	@Test
	public void validez1 () {
		clock = Clock.fixed(Instant.parse("2020-11-10T19:24:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock);
		registro.setHoraDeInicio(LocalDateTime.now(clock));
		Clock clock2 = Clock.fixed(Instant.parse("2020-11-10T22:28:24.498559900Z"), ZoneId.of("GMT-3"));
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
	public void calcularCosto() {
		clock = Clock.fixed(Instant.parse("2020-11-10T19:24:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock);
		assertEquals(3,registro.calcularDuracion());
	}
}