package SEM;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
class ModoAutomaticoTest {
	@InjectMocks
	ModoAutomatico modo;
	@Mock
	AplicacionCliente app;
	
	ByteArrayOutputStream b;
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
		b = new ByteArrayOutputStream();
		System.setOut(new PrintStream(b));
	}

	@Test
	void iniciar()  {
		modo.iniciarEstacionamiento();
		String r =b.toString().substring(0, b.toString().length()-2);
		assertEquals("El modo automatico de la app no "
				+ "permite iniciar estacionamientos de forma manual", r);
	}
	@Test
	void finalizar() {
		modo.finalizarEstacionamiento();
		String r =b.toString().substring(0, b.toString().length()-2);
		assertEquals("El modo automático de la app no "
				+ "permite finalizar estacionamientos de forma manual",r);
	}
	@Test 
	void driving(){
		modo.realizarAccionWalkingADriving();
		verify(app,times(1)).realizarFinalizacion();
	}
	@Test
	void walking(){
		modo.realizarAccionDrivingAWalking();
		verify(app,times(1)).realizarEstacionamiento();
	}
}
