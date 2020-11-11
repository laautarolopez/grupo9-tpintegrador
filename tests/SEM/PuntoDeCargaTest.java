package SEM;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PuntoDeCargaTesteable extends PuntoDeCarga{
	public CentroCelulares getCentroCelulares() {
		return this.centroCelulares;
	}
	
	public void setCentroCelulares(CentroCelulares centro) {
		this.centroCelulares = centro;
	}
}

class PuntoDeCargaTest {
	
	
	PuntoDeCargaTesteable pc;
	
	@BeforeEach
	void setup() {
		pc = new PuntoDeCargaTesteable();
	}
	
	@Test
	void recargarCelularTestDeMensajesAlCelular() {
		Celular celular = mock(Celular.class);
		when(celular.getNumero()).thenReturn("1234");
		when(celular.getSaldoActual()).thenReturn(120);
		pc.recargarCelular(celular, 120);
		
		verify(celular,times(1)).getNumero();
	}
	
	@Test
	void recargarCelularTestDeMensajesAlCentroDeCelulares() {
		Celular celular = mock(Celular.class);
		when(celular.getNumero()).thenReturn("1234");
		when(celular.getSaldoActual()).thenReturn(120);
		CentroCelulares cc = mock(CentroCelulares.class);
		pc.setCentroCelulares(cc);
		pc.recargarCelular(celular, 120);
		
		verify(pc.getCentroCelulares(), times(1)).agregarSaldo("1234",120);
	}

}
