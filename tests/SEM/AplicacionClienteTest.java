package SEM;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class AplicacionClienteTest {
	@InjectMocks
	private AplicacionCliente aplicacion;
	
	@Spy
	private Modo modo;
	
	@Spy
	private Notificador notificador;
	
	private Sistema sistema;
	private String numero;
	private String patente;
	private Gps gps;
	
	@BeforeEach
	public void setUp() {
		sistema = mock(Sistema.class);
		numero = "1125458510";
		patente = "ABC123";
		gps = mock(Gps.class);
		modo = mock(Modo.class);
		notificador = mock(Notificador.class);
		aplicacion = new AplicacionCliente(sistema, numero, patente, gps);
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void constructorTest() {
		assertEquals(sistema, aplicacion.sistema);
		assertEquals(numero, aplicacion.getNumero());
		assertEquals(patente, aplicacion.getPatente());
		assertEquals(modo, aplicacion.getModo());
		assertNull(aplicacion.getRegistro());
	}
	
	@Test
	public void setModoTest() {
		Modo modoNuevo = mock(Modo.class);
		assertNotEquals(modoNuevo, aplicacion.getModo());
		
		aplicacion.setModo(modoNuevo);
		assertEquals(modoNuevo, aplicacion.getModo());
	}
	
	@Test
	public void switchNotificacionesTest() {
		verifyNoInteractions(notificador);
		
		aplicacion.switchNotificaciones();
		verify(notificador).cambiarModo(aplicacion);
	}
	
	@Test
	public void cambiarModoTest() {
		verifyNoInteractions(modo);
		
		aplicacion.cambiarModo();
		verify(modo).cambiarModo();
	}
	
	@Test
	public void iniciarEstacionamientoTest() {
		verifyNoInteractions(modo);
		
		aplicacion.iniciarEstacionamiento();
		verify(modo).iniciarEstacionamiento();
	}
	
	@Test
	public void realizarEstacionamiento(){
		when(gps.getZona()).thenReturn("Quilmes");
		when(sistema.esZonaDeEstacionamiento("Quilmes")).thenReturn(true);
		when(sistema.getValorDeHora()).thenReturn(40);
		when(sistema.getSaldo(aplicacion.getNumero())).thenReturn(120);
		when(sistema.esHoraDeEstacionamiento()).thenReturn(true);
		assertNull(aplicacion.getRegistro());
		
		aplicacion.realizarEstacionamiento();
		verify(sistema).esZonaDeEstacionamiento(gps.getZona());
		verify(sistema).getSaldo(aplicacion.getNumero());
		verify(sistema).esHoraDeEstacionamiento();
		verify(sistema).getValorDeHora();
		verify(sistema).registrarInicio(aplicacion.getRegistro());
		verify(notificador).informarInicio(aplicacion, aplicacion.getRegistro());
		assertNotNull(aplicacion.getRegistro());
	}
	
	@Test
	public void realizarEstacionamientoSinSaldoTest(){
		when(gps.getZona()).thenReturn("Quilmes");
		when(sistema.esZonaDeEstacionamiento("Quilmes")).thenReturn(true);
		when(sistema.getValorDeHora()).thenReturn(40);
		when(sistema.getSaldo(aplicacion.getNumero())).thenReturn(30);
		when(sistema.esHoraDeEstacionamiento()).thenReturn(true);
		assertNull(aplicacion.getRegistro());
		
		aplicacion.realizarEstacionamiento();
		verify(sistema).esZonaDeEstacionamiento(gps.getZona());
		verify(sistema).getSaldo(aplicacion.getNumero());
		verify(sistema).getValorDeHora();
		verify(sistema, times(0)).esHoraDeEstacionamiento();
		verify(sistema, times(0)).registrarInicio(aplicacion.getRegistro());
		verify(notificador, times(0)).informarInicio(aplicacion, aplicacion.getRegistro());
		assertNull(aplicacion.getRegistro());
	}
	
	@Test
	public void finalizarEstacionamientoTest() {
		verifyNoInteractions(modo);
		
		aplicacion.finalizarEstacionamiento();
		verify(modo).finalizarEstacionamiento();
	}
	
	@Test
	public void realizarFinalizacionTest() {
		this.realizarEstacionamiento();
		assertNotNull(aplicacion.getRegistro());
		RegistroDeEstacionamiento registro = aplicacion.getRegistro();
		
		aplicacion.realizarFinalizacion();
		verify(sistema).registrarFinal(aplicacion.getPatente());
		verify(notificador).informarFinal(aplicacion, registro);
		assertNull(aplicacion.getRegistro());
	}
	
	@Test
	public void realizarFinalizacionSinRegistroTest() {
		assertNull(aplicacion.getRegistro());
		RegistroDeEstacionamiento registro = aplicacion.getRegistro();
		
		aplicacion.realizarFinalizacion();
		verify(sistema, times(0)).registrarFinal(aplicacion.getPatente());
		verify(notificador, times(0)).informarFinal(aplicacion, registro);
		assertNull(aplicacion.getRegistro());
	}
	
	@Test
	public void terminarRegistroTest() {
		this.realizarEstacionamiento();
		assertNotNull(aplicacion.getRegistro());
		
		aplicacion.terminarRegistro();
		assertNull(aplicacion.getRegistro());
	}
	
	@Test
	public void tieneRegistroCreadoTest() {
		assertFalse(aplicacion.tieneRegistroCreado());
		
		this.realizarEstacionamiento();
		
		assertTrue(aplicacion.tieneRegistroCreado());
	}
	
	@Test
	public void walkingTest() {
		verifyNoInteractions(modo);
		
		aplicacion.walking();
		verify(modo).walking();
	}
	
	@Test
	public void drivingTest() {
		verifyNoInteractions(modo);
		
		aplicacion.driving();
		verify(modo).driving();
	}
	
	@Test
	public void aconsejarInicioTest() {
		verifyNoInteractions(notificador);
		
		aplicacion.aconsejarInicio();
		verify(notificador).aconsejarInicio(aplicacion);
	}
	
	@Test
	public void aconsejarFinalTest() {
		verifyNoInteractions(notificador);
		
		aplicacion.aconsejarFinal();
		verify(notificador).aconsejarFinal(aplicacion);
	}
	
	@Test
	public void setNotificadorTest() {
		Notificador notificadorNuevo = mock(Notificador.class);
		System.out.println(notificadorNuevo.hashCode());
		assertNotEquals(notificadorNuevo, notificador);
		
		aplicacion.setNotificador(notificadorNuevo);
	}
}