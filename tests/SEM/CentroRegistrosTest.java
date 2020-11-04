package SEM;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TestCentro extends CentroRegistros{

	protected TestCentro() {
		super();
	}

	public ArrayList<Registro> getRegistros(){
		return this.registros;
	}
}


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
	void testValidacionDeRegistros() {
		registro1 = mock(Registro.class);
		when(registro1.getPatente()).thenReturn("abc123");
		when(registro1.estaVigente()).thenReturn(true);		
		centroConocido.registrarInicio(registro1);
		assertTrue(centroConocido.estaVigente("abc123"));
	}
	
	void agregarRegistros() {
		registro1 = mock(Registro.class);
		TestCentro centrotest = new TestCentro();
		centrotest.registrarInicio(registro1);
		assertTrue(centrotest.getRegistros().contains(registro1));
	}
	
	void finalizarRegistros() {
		registro1 = mock(Registro.class);
		when(registro1.getPatente()).thenReturn("abc123");
		TestCentro centrotest = new TestCentro();
		centrotest.registrarInicio(registro1);
		centrotest.registrarFinal("abc123");
		assertFalse(centrotest.getRegistros().contains(registro1));
	}
	
	
	@Test
	void testNotificarObservers() {
		Observer observer = mock(Observer.class);
		TestCentro centrotest = new TestCentro();
		registro1 = mock(Registro.class);
		centrotest.addObserver(observer);
		centrotest.registrarInicio(registro1);
		verify(observer,times(1)).update(centrotest.getRegistros());
	}
	
	@Test
	void testNotificarObservers2() {
		Observer observer = mock(Observer.class);
		TestCentro centrotest = new TestCentro();
		registro1 = mock(Registro.class);
		when(registro1.getPatente()).thenReturn("abc123");
		centrotest.addObserver(observer);
		centrotest.registrarFinal("abc123");
		verify(observer,times(1)).update(centrotest.getRegistros());
	}
}
