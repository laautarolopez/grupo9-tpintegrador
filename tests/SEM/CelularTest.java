package SEM;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
class CelularTest {
	
	public class CelularTesteable extends Celular {

		public CelularTesteable(String numero, String patente, Gps gps, MovementSensor ms) {
			super(numero, patente, gps, ms);
		}
		
		public void setApp(AplicacionCliente app) {
			this.app = app;
		}
		
		public void setCc(CentroCelulares cc) {
			this.centroCelulares = cc;
		}
		
		public void setCz(CentroZonas cz) {
			this.centroZonas = cz;
		}
		
		public void setCr(CelularReal cr) {
			this.real = cr;
		}
		
	}
	
	AplicacionCliente app = mock(AplicacionCliente.class);
	Gps gps = mock(Gps.class);
	MovementSensor ms = mock(MovementSensor.class);
	CelularTesteable celular = new CelularTesteable("11231", "3312", gps, ms);
	CentroCelulares cc = mock(CentroCelulares.class);
	CelularReal cr = mock(CelularReal.class);
	CentroZonas cz = mock(CentroZonas.class);
	@BeforeEach
	void setup() {
		celular.setApp(app);
		celular.setCc(cc);
		celular.setCr(cr);
		celular.setCz(cz);
	}
	@Test
	void testGetters() {
		assertEquals("3312", celular.getPatente());
		assertEquals("11231", celular.getNumero());
	}
	
	@Test
	void testSaldo() {
		when(cc.saldoDe("11231")).thenReturn(120);
		assertEquals(120,celular.getSaldoActual());
	}
	@Test
	void testCambioDeModo() {
		Modo modo = mock(Modo.class);
		celular.cambiarModoApp(modo);
		verify(app, times(1)).cambiarModo(modo);
	}
	@Test
	void zonaEstacionamiento() {
		when(gps.getZona()).thenReturn("Varela");
		when(cz.esZonaDeEstacionamiento("Varela")).thenReturn(true);
		assertTrue(celular.estaEnZonaDeEstacionamiento());
	}
	@Test
	void zonaEstacionamiento2() {
		when(gps.getZona()).thenReturn("Varela");
		when(cz.esZonaDeEstacionamiento("Varela")).thenReturn(false);
		assertFalse(celular.estaEnZonaDeEstacionamiento());
	}
	@Test
	void validarSaldoTest() {
		when(cc.saldoDe("11231")).thenReturn(30);
		assertThrows(Exception.class, () -> celular.validarSaldo(40));
	}
	@Test
	void validarSaldoTest2() {
		when(cc.saldoDe("11231")).thenReturn(60);
		assertDoesNotThrow(() -> celular.validarSaldo(40));
	}
	@Test
	void getZonaTest() {
		when(gps.getZona()).thenReturn("Varela");
		assertEquals("Varela", celular.getZona());
	}
}
