package SEM;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
class ModoAutomaticoTest {
	
	public class ModoTesteable extends ModoAutomatico {
		void setEstado(String estado) {
			this.estado = estado;
		}
	}
	ModoTesteable modo = new ModoTesteable();
	AplicacionCliente app = mock(AplicacionCliente.class);
	@Test
	void testFinalizacionManual() {
		assertFalse(modo.finalizacionManual());
	}
	@Test
	void testRequiereNotificacionesManual() {
		assertFalse(modo.requiereNotificaciones());
	}
	@Test
	void iniciar() throws Exception {
		assertThrows(Exception.class,() -> modo.iniciarEstacionamiento(app));
	}
	
	@Test 
	void driving1() throws Exception{
		modo.setEstado("walking");
		modo.walking(app);
		modo.walking(app);
		modo.driving(app);
		verify(app, times(1)).finalizarEstacionamiento();
	}
	@Test
	void driving2() throws Exception{
		modo.setEstado("driving");
		modo.driving(app);
		modo.driving(app);
		modo.driving(app);
		verify(app, times(0)).generarRegistro();
	}
	@Test
	void walking() throws Exception {
		modo.setEstado("driving");
		modo.driving(app);
		modo.driving(app);
		modo.walking(app);
		verify(app, times(1)).generarRegistro();
	}

}
