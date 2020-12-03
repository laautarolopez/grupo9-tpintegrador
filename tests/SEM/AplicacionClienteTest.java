package SEM;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

class AplicacionClienteTest {
	public class AppTesteable extends AplicacionCliente{
		public AppTesteable(Sistema s, Celular celular) {
			super(s, celular);
		}
		
		public void setNotificador(Notificador notificador) {
			this.notificador = notificador;
		}
		
		public CentroRegistros centroRegistros() {
			return this.centroRegistros;
		}

		public void setCentroZonas(CentroZonas cz) {
			this.centroZonas = cz;
		}

		public void centroRegistros(CentroRegistros centro) {
			this.centroRegistros = centro;
			
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
		app.generarRegistro();
		verify(notificador, times(1)).informarInicio(celular,app.centroRegistros().getRegistro(celular.getPatente()));
	}
	
	@Test 
	void finalizarEstacionamiento() throws Exception{
		app.cambiarModo(modo);
		CentroZonas cz = mock(CentroZonas.class);
		app.setCentroZonas(cz);
		CentroRegistros centro = mock(CentroRegistros.class);
		app.centroRegistros(centro);
		app.setNotificador(notificador);
		when(centro.estaVigente("123")).thenReturn(true);
		when(cz.esZonaDeEstacionamiento("Varela")).thenReturn(true);
		when(celular.getZona()).thenReturn("Varela");
		when(celular.getPatente()).thenReturn("123");
		app.generarRegistro();
		app.finalizarEstacionamiento();
		verify(modo,times(1)).finalizacionManual();
		
	}
	
	@Test
	void realizarFinalizacion() throws Exception {
		app.setNotificador(notificador);
		when(celular.getPatente()).thenReturn("1231");
		CentroRegistros centro = mock(CentroRegistros.class);
		app.centroRegistros(centro);
		app.realizarFinalizacion();
		verify(centro, times(1)).validarExistenciaDeEstacionamiento("1231");
		verify(centro, times(1)).registrarFinal("1231");
	}
	
	
}