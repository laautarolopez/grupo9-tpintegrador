package SEM;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;


/*Los clocks se setean con 3 horas más que la deseada, ya que al transformarla en LolcaDateTime se le restan 3*/
public class RegistroAplicacionTest {
	public class RegistroAppTesteable extends RegistroAplicacion{

		public RegistroAppTesteable(Sistema sistema, AplicacionCliente app) {
			super(sistema, app );
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
	private String zona;
	private AplicacionCliente app;
	private RegistroAppTesteable registro;
	private Clock clock;
	private String patente;
	private Sistema sistema;
	
	@BeforeEach
	public void setUp() throws Exception {
		patente = "OJL215";
		zona = "Quilmes Oeste";
		app = mock(AplicacionCliente.class);
		sistema = mock(Sistema.class);
		when(app.getNumero()).thenReturn("1145251452");
		when(app.getSaldoActual()).thenReturn(120);
		when(app.getPatente()).thenReturn("OJL215");
		when(app.getZona()).thenReturn(zona);
		when(sistema.getValorDeHora()).thenReturn(40);
		registro = new RegistroAppTesteable(sistema, app);
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
	
	@Test
	public void horarioFinalizacion1() {
		clock = Clock.fixed(Instant.parse("2020-11-10T13:24:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock);
		registro.setHoraDeInicio(LocalDateTime.now(clock));
		clock = Clock.fixed(Instant.parse("2020-11-10T18:24:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock);
		registro.finalizar();
		assertEquals("13:24",registro.getHorarioDeFinalizacion());
	}
	@Test
	public void horarioFinalizacion2() {
		clock = Clock.fixed(Instant.parse("2020-11-10T13:24:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock);
		registro.setHoraDeInicio(LocalDateTime.now(clock));
		clock = Clock.fixed(Instant.parse("2020-11-10T15:24:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock);
		assertEquals("Desconocida", registro.getHorarioDeFinalizacion());
		registro.finalizar();
		assertEquals("12:24",registro.getHorarioDeFinalizacion());
	}
	
	@Test 
	void registroToString() {
		String info = "Patente: " + patente + "\n" +
				   "Hora de inicio: " + "10" + ":" +
				   					    "24" + "\n" +
				   "Hora de finalizacion: " + "Desconocida" + "\n" +
				   "Número de celular: " + "1145251452" + "\n" +
				   "Zona: " + zona + "\n";
		clock = Clock.fixed(Instant.parse("2020-11-10T13:24:24.498559900Z"), ZoneId.of("GMT-3"));
		registro.setClock(clock);
		registro.setHoraDeInicio(LocalDateTime.now(clock));
		assertEquals(info,registro.toString());
	}
}