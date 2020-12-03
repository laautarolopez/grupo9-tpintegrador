package SEM;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

class AplicacionClienteTest {
	
	
	Celular celular = mock(Celular.class);
	Sistema sistema = mock(Sistema.class);
	AplicacionCliente app;
	Notificador notificador = mock(Notificador.class);
	Modo modo = mock(Modo.class);
	
	
	@BeforeEach
	void setup() {
		app= new AplicacionCliente(sistema,celular);
		app.setModo(modo);

	}
	
	@Test
	void cambiarNotificaciones(){
		app.setNotificador(notificador);
		app.switchNotificaciones();
		verify(notificador, times(1)).cambiarModo(app);
	}

	
	@Test
	void aconsejarInicio() {
		app.setNotificador(notificador);
		app.aconsejarInicio();
		verify(notificador, times(1)).aconsejarInicio(celular, app);
	}
	
	@Test
	void aconsejarFinal() {
		app.setNotificador(notificador);
		app.aconsejarFinal();
		verify(notificador, times(1)).aconsejarFinal(celular, app);
	}

	@Test
	void driving() {
		app.driving();
		verify(modo,times(1)).driving();
	}

	@Test
	void walking(){
		app.walking();
		verify(modo,times(1)).walking();
	}

	@Test
	void iniciarEstacionamiento() {
		app.iniciarEstacionamiento();
		verify(modo, times(1)).iniciarEstacionamiento();
	}
	
	
	@Test
	void realizarEstacionamiento(){
		when(celular.estaEnZonaDeEstacionamiento()).thenReturn(true);
		when(celular.getSaldoActual()).thenReturn(120);
		when(celular.getNumero()).thenReturn("12123");
		when(celular.getPatente()).thenReturn("11111");
		when(celular.getZona()).thenReturn("Varela");
		when(sistema.esZonaDeEstacionamiento("Varela")).thenReturn(true);
		when(sistema.getValorDeHora()).thenReturn(40);
		when(sistema.esHoraDeEstacionamiento()).thenReturn(true);
		app.realizarEstacionamiento();
		assertNotNull(app.getRegistro());
		verify(sistema,times(1)).registrarInicio(app.getRegistro());
	}
	
	@Test
	void realizarEstacionamiento2(){
		when(celular.estaEnZonaDeEstacionamiento()).thenReturn(false);
		when(celular.getSaldoActual()).thenReturn(120);
		when(celular.getNumero()).thenReturn("12123");
		when(celular.getPatente()).thenReturn("11111");
		when(celular.getZona()).thenReturn("Varela");
		when(sistema.esZonaDeEstacionamiento("Varela")).thenReturn(true);
		when(sistema.getValorDeHora()).thenReturn(40);
		when(sistema.esHoraDeEstacionamiento()).thenReturn(true);
		app.realizarEstacionamiento();
		assertNull(app.getRegistro());
		verify(sistema,times(0)).registrarInicio(app.getRegistro());
	}
	
	@Test
	void realizarEstacionamiento3(){
		when(celular.estaEnZonaDeEstacionamiento()).thenReturn(true);
		when(celular.getSaldoActual()).thenReturn(120);
		when(celular.getNumero()).thenReturn("12123");
		when(celular.getPatente()).thenReturn("11111");
		when(celular.getZona()).thenReturn("Varela");
		when(sistema.esZonaDeEstacionamiento("Varela")).thenReturn(true);
		when(sistema.getValorDeHora()).thenReturn(40);
		when(sistema.esHoraDeEstacionamiento()).thenReturn(false);
		app.realizarEstacionamiento();
		assertNull(app.getRegistro());
		verify(sistema,times(0)).registrarInicio(app.getRegistro());
	}
	@Test
	void realizarEstacionamiento4(){
		when(celular.estaEnZonaDeEstacionamiento()).thenReturn(true);
		when(celular.getSaldoActual()).thenReturn(10);
		when(celular.getNumero()).thenReturn("12123");
		when(celular.getPatente()).thenReturn("11111");
		when(celular.getZona()).thenReturn("Varela");
		when(sistema.esZonaDeEstacionamiento("Varela")).thenReturn(true);
		when(sistema.getValorDeHora()).thenReturn(40);
		when(sistema.esHoraDeEstacionamiento()).thenReturn(true);
		app.realizarEstacionamiento();
		assertNull(app.getRegistro());
		verify(sistema,times(0)).registrarInicio(app.getRegistro());
	}
	
	@Test 
	void finalizarEstacionamiento() {
		app.finalizarEstacionamiento();
		verify(modo,times(1)).finalizarEstacionamiento();;
	}
	
	@Test
	void realizarFinalizacion()  {
		when(celular.estaEnZonaDeEstacionamiento()).thenReturn(true);
		when(celular.getSaldoActual()).thenReturn(120);
		when(celular.getNumero()).thenReturn("12123");
		when(celular.getPatente()).thenReturn("11111");
		when(celular.getZona()).thenReturn("Varela");
		when(sistema.esZonaDeEstacionamiento("Varela")).thenReturn(true);
		when(sistema.getValorDeHora()).thenReturn(40);
		when(sistema.esHoraDeEstacionamiento()).thenReturn(true);
		app.realizarEstacionamiento();
		app.realizarFinalizacion();
		verify(sistema,times(1)).registrarFinal("11111");
	}
	
	@Test
	void realizarFinalizacion2() {
		when(celular.getPatente()).thenReturn("11111");
		app.realizarFinalizacion();
		verify(sistema,times(0)).registrarFinal("11111");
	}
	
	@Test 
	void cambiarModo(){
		app.cambiarModo();
		verify(modo,times(1)).cambiarModo();
	}

}