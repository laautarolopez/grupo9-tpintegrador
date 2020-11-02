import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SEM.CentroRegistros;
import SEM.Registro;

import static org.mockito.Mockito.*;



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
		Registro registro1 = mock(Registro.class);
		centroConocido.registrarInicio(registro1);
		when(centroConocido.estaVigente("abc123")).thenReturn(true);
		
		assertTrue(centroConocido.estaVigente("abc123"));
		
	}
}
