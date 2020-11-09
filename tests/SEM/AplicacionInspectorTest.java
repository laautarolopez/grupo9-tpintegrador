package SEM;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class AplicacionInspectorTest {
	private AplicacionInspector aplicacion;
	private Inspector inspector;
	private Registro registro;
	
	@BeforeEach
	public void setUp() {
		inspector = mock(Inspector.class);
		registro = mock(Registro.class);
		aplicacion = new AplicacionInspector(inspector);
	}
	
	@Test
	public void estaVigenteTest() {
		CentroRegistros centroRegistros = CentroRegistros.getCentro();
		centroRegistros.registrarInicio(registro);
		when(registro.estaVigente()).thenReturn(true);
		when(registro.getPatente()).thenReturn("abc123");
		assertTrue(aplicacion.estaVigente("abc123"));
	}
}