package SEM;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class AplicacionInspectorTest {
	private AplicacionInspector aplicacion;
	private Inspector inspector;
	private RegistroDeEstacionamiento registro;
	private Sistema sistema;
	
	@BeforeEach
	public void setUp() {
		inspector = mock(Inspector.class);
		registro = mock(RegistroDeEstacionamiento.class);
		sistema = mock(Sistema.class);
		aplicacion = new AplicacionInspector(sistema, inspector);
	}
	
	@Test
	public void estaVigenteTest() {
		when(sistema.estaVigente("abc123")).thenReturn(true);
		assertTrue(aplicacion.estaVigente("abc123"));
	}
	
	@Test
	public void altaDeInfraccionTest() {
		when(aplicacion.estaVigente("abc123")).thenReturn(false);
		aplicacion.altaDeInfraccion("abc123");
		verify(sistema,times(1)).registrarInfraccion(new Infraccion("abc123", inspector));
	}
}