package SEM;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
class NotificacionesDesactivadasTest {
	Celular celular = mock(Celular.class);
	RegistroDeEstacionamiento r = mock(RegistroDeEstacionamiento.class);
	NotificacionesDesactivadas nd = new NotificacionesDesactivadas();
	
	@Test
	void test() {
		AplicacionCliente app = mock(AplicacionCliente.class);
		nd.aconsejarFinal(celular, app);
		nd.aconsejarInicio(celular, app);
		nd.cambiarModo(app);
		nd.informarFinal(celular, r);
		nd.informarInicio(celular, r);
	}

}
