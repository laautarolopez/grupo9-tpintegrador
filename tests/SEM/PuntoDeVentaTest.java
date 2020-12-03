package SEM;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class PuntoDeVentaTest {
	

	private Sistema sistema;
	public PuntoDeVenta pv;
	@BeforeEach
	void setup() {
		String zona = "Quilmes Oeste";
		sistema = spy(Sistema.class);
		pv = new PuntoDeVenta(sistema, zona);
	}
	
	@Test
	void inciarEstacionamientoTest() throws Exception {
		pv.iniciarEstacionamiento("abc123", 120);
		
	}
	
	@Test
	void recargarCelularTestDeMensajesAlCelular() {
		Celular celular = mock(Celular.class);
		when(celular.getNumero()).thenReturn("1234");
		pv.recargarCelular(celular, 120);
		verify(celular,times(1)).getNumero();
	}
	
	@Test
	void recargarCelularTestDeMensajesAlCentroDeCelulares() {
		Celular celular = mock(Celular.class);
		when(celular.getNumero()).thenReturn("1234");
		when(celular.getSaldoActual()).thenReturn(120);
		pv.recargarCelular(celular, 120);
		verify(sistema, times(1)).agregarSaldo("1234",120);
	}

}
