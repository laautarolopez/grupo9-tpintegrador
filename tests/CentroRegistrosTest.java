import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SEM.CentroRegistros;
import SEM.Registro;

import org.mockito.*;



public class CentroRegistrosTest {
	
	public CentroRegistros centroConocido;
	public Registro registro1;
	public Registro registro2;
	
	@BeforeEach
	public void setUp() {
		centroConocido = CentroRegistros.getCentro();
		registro1 = Mockito.mock(Registro.class);
		
	}
	@Test
	void testSingleton() {
		assertEquals(centroConocido, CentroRegistros.getCentro());
	}
	
	@Test
	void testAgregadoDeRegistros() {
		
	}
}
