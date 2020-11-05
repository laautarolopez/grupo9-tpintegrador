package SEM;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PuntoDeVentaTest {
	
	class PuntoDeVentaTesteable extends PuntoDeVenta{
		
		public Zona getZona() {
			return this.zona;
		}
		
		public void setCentroRegistros(CentroRegistros centro) {
			this.centroRegistros = centro;
		}
		public void setCentroCelulares(CentroCelulares centro) {
			this.centroCelulares = centro;
		}
		
		public CentroCelulares getCentroCelulares() {
			return this.centroCelulares;
		}
		
		public CentroRegistros getCentroRegistros() {
			return this.centroRegistros;
		}
	}
	
	public PuntoDeVentaTesteable pv;
	@BeforeEach
	void setup() {
		Zona zona = mock(Zona.class);
		CentroRegistros centR = mock(CentroRegistros.class);
		CentroCelulares centC = mock(CentroCelulares.class);
		pv = new PuntoDeVentaTesteable(zona);
		pv.setCentroRegistros(centR);
		pv.setCentroCelulares(centC);
	}
	
	@Test
	void inciarEstacionamientoTest() {
		pv.iniciarEstacionamiento("abc123", 120);
		//TODO verify(pv.getCentroRegistros(),times(1)).registrarInicio();
	}
	
	@Test
	void recargarCelularTestDeMensajesAlCelular() {
		Celular celular = mock(Celular.class);
		when(celular.getNumero()).thenReturn("1234");
		when(celular.getSaldoActual()).thenReturn(120);
		pv.recargarCelular(celular, 120);
		
		verify(celular, times(1)).getSaldoActual();
		verify(celular,times(1)).agregarSaldo(120);
		verify(celular,times(1)).getNumero();
	}
	
	@Test
	void recargarCelularTestDeMensajesAlCentroDeCelulares() {
		Celular celular = mock(Celular.class);
		when(celular.getNumero()).thenReturn("1234");
		when(celular.getSaldoActual()).thenReturn(120);
		pv.recargarCelular(celular, 120);
		
		verify(pv.getCentroCelulares(), times(1)).registrarCarga("1234",120);
	}

}
