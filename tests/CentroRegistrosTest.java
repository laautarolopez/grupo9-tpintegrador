import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SEM.CentroRegistros;
import SEM.Registro;



public class CentroRegistrosTest {
	
	public CentroRegistros centroConocido;
	public Registro registro1;
	public Registro registro2;
	
	@BeforeEach
	public void setUp() {
		centroConocido = CentroRegistros.getCentro();
	}
	@Test
	void testSingleton() {
		assertEquals(centroConocido, CentroRegistros.getCentro());
	}
	
	@Test
	void testAgregadoDeRegistros() {
		registro1 = mock(Registro.class);
		when(registro1.patente()).thenReturn("abc123");
		when(registro1.estaVigente()).thenReturn(true);		
		centroConocido.registrarInicio(registro1);
		assertTrue(centroConocido.estaVigente("abc123"));
	}
}
