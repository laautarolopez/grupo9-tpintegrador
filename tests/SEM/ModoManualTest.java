package SEM;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

class ModoManualTest {
	
	ModoManual modo = new ModoManual();
	AplicacionCliente app = mock(AplicacionCliente.class);
	@Test
	void testFinalizacionManual() {
		assertTrue(modo.finalizacionManual());
	}
	@Test
	void testRequiereNotificacionesManual() {
		assertTrue(modo.requiereNotificaciones());
	}
	@Test
	void iniciar() throws Exception {
		modo.iniciarEstacionamiento(app);
		verify(app,times(1)).generarRegistro();
	}
}
