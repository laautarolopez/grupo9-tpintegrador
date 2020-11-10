package SEM;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class AplicacionInspectorTest {
	private AplicacionInspector aplicacion;
	private Inspector inspector;
	private Registro registro;
	private CentroRegistros centroRegistros = CentroRegistros.getCentro();
	private CentroInfracciones centroInfracciones = CentroInfracciones.getCentro();
	
	@BeforeEach
	public void setUp() {
		inspector = mock(Inspector.class);
		registro = mock(Registro.class);
		aplicacion = new AplicacionInspector(inspector);
	}
	
	@Test
	public void estaVigenteTest() {
		centroRegistros.registrarInicio(registro);
		when(registro.estaVigente()).thenReturn(true);
		when(registro.getPatente()).thenReturn("abc123");
		assertTrue(aplicacion.estaVigente("abc123"));
	}
	
	@Test
	public void altaDeInfraccionTest() {
		when(aplicacion.estaVigente("abc123")).thenReturn(false);
		aplicacion.altaDeInfraccion("abc123");
	}
}