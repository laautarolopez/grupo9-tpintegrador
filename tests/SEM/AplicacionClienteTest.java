package SEM;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

class AplicacionClienteTest {
	public class AppTesteable extends AplicacionCliente{
		public AppTesteable(Celular celular, MovementSensor ms) {
			super(celular, ms);
		}

		public boolean consejos() {
			return this.consejosActivados;
		}
		
		public void setNotificador(Notificador notificador) {
			this.notificador = notificador;
		}
		
		public RegistroAplicacion generarRegistroTest() throws Exception {
			centroZonas.validarZona(celular.getZona());
			celular.validarSaldo(this.valorDeHora);
			RegistroAplicacion registro = new RegistroAplicacion(celular.getPatente(), celular.getZona(),celular);
			notificador.informarInicio(celular, registro);
			centroRegistros.registrarInicio(registro);
			return registro;
		}

		public void setCentroZonas(CentroZonas cz) {
			this.centroZonas = cz;
		}
	}
	
	Celular celular = mock(Celular.class);
	MovementSensor ms = mock(MovementSensor.class);
	AppTesteable app = new AppTesteable(celular,ms);
	Notificador notificador = mock(Notificador.class);
	private Modo modo = mock(Modo.class);
	
	@Test
	void activarConsejos(){
		app.activarConsejos();
		assertTrue(app.consejos());	
	}
	
	@Test
	void desactivarCosejos() {
		app.desactivarConsejos();
		assertFalse(app.consejos());
	}
	
	@Test
	void aconsejarInicio() {
		app.setNotificador(notificador);
		app.activarConsejos();
		when(celular.estaEnZonaDeEstacionamiento()).thenReturn(true);
		app.aconsejarInicio();
		verify(notificador, times(1)).aconsejarInicio(celular);
	}
	@Test
	void aconsejarInicio2() {
		app.setNotificador(notificador);
		app.desactivarConsejos();
		when(celular.estaEnZonaDeEstacionamiento()).thenReturn(true);
		app.aconsejarInicio();
		verify(notificador, times(0)).aconsejarInicio(celular);
	}
	
	@Test
	void aconsejarInicio3() {
		app.setNotificador(notificador);
		app.activarConsejos();
		when(celular.estaEnZonaDeEstacionamiento()).thenReturn(false);
		app.aconsejarInicio();
		verify(notificador, times(0)).aconsejarInicio(celular);
	}
	
	@Test
	void aconsejarFinal() {
		app.setNotificador(notificador);
		app.activarConsejos();
		when(celular.estaEnZonaDeEstacionamiento()).thenReturn(true);
		app.aconsejarFinal();
		verify(notificador, times(1)).aconsejarFinal(celular);
	}
	@Test
	void aconsejarFinal2() {
		app.setNotificador(notificador);
		app.desactivarConsejos();
		when(celular.estaEnZonaDeEstacionamiento()).thenReturn(true);
		app.aconsejarFinal();
		verify(notificador, times(0)).aconsejarFinal(celular);
	}
	
	@Test
	void aconsejarFinal3() {
		app.setNotificador(notificador);
		when(celular.estaEnZonaDeEstacionamiento()).thenReturn(false);
		app.activarConsejos();
		app.aconsejarFinal();
		verify(notificador, times(0)).aconsejarFinal(celular);
	}
	@Test
	void driving() throws Exception {
		app.cambiarModo(modo);
		when(celular.estaEnZonaDeEstacionamiento()).thenReturn(true);
		app.driving();
		verify(modo,times(1)).driving(app);
	}
	@Test
	void driving2() throws Exception {
		app.cambiarModo(modo);
		when(celular.estaEnZonaDeEstacionamiento()).thenReturn(false);
		app.driving();
		verify(modo,times(0)).driving(app);
	}
	@Test
	void walking() throws Exception {
		app.cambiarModo(modo);
		when(celular.estaEnZonaDeEstacionamiento()).thenReturn(true);
		app.walking();
		verify(modo,times(1)).walking(app);
	}
	@Test
	void walking2() throws Exception {
		app.cambiarModo(modo);
		when(celular.estaEnZonaDeEstacionamiento()).thenReturn(false);
		app.walking();
		verify(modo,times(0)).walking(app);
	}
	@Test
	void iniciarEstacionamiento() throws Exception {
		app.cambiarModo(modo);
		app.iniciarEstacionamiento();
		verify(modo, times(1)).iniciarEstacionamiento(app);
	}
	@Test
	void validarFinManual() throws Exception {
		app.cambiarModo(modo);
		when(modo.finalizacionManual()).thenReturn(true);
		assertThrows(Exception.class, () -> app.validarFinalizacionManual());
	}
	@Test
	void validarFinManual2() throws Exception {
		app.cambiarModo(modo);
		when(modo.finalizacionManual()).thenReturn(false);
		assertDoesNotThrow(() -> app.validarFinalizacionManual());
	}
	
	@Test
	void generarRegistro() throws Exception {
		when(celular.getSaldoActual()).thenReturn(120);
		when(celular.getNumero()).thenReturn("12123");
		when(celular.getPatente()).thenReturn("11111");
		when(celular.getZona()).thenReturn("Varela");
		CentroZonas cz = mock(CentroZonas.class);
		when(cz.esZonaDeEstacionamiento("Varela")).thenReturn(true);
		app.setCentroZonas(cz);
		app.setNotificador(notificador);
		Registro registro1 = app.generarRegistroTest();
		verify(notificador, times(1)).informarInicio(celular,registro1);
	}
}