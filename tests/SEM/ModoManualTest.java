package SEM;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ModoManualTest {
	@InjectMocks
	ModoManual modo;
	@Mock
	AplicacionCliente app;
	
	ByteArrayOutputStream b;
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void iniciarE() {
		modo.iniciarEstacionamiento();
		verify(app,times(1)).realizarEstacionamiento();
	}
	@Test
	void finalizarE() {
		modo.finalizarEstacionamiento();
		verify(app,times(1)).realizarFinalizacion();
	}
	@Test
	void WalkingADriving() {
		modo.realizarAccionWalkingADriving();
		verify(app,times(1)).aconsejarFinal();
	}
	
	@Test
	void DrivingAWalking() {
		modo.realizarAccionDrivingAWalking();
		verify(app,times(1)).aconsejarInicio();
	}
}
