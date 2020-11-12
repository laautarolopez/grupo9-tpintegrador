package SEM;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import static org.junit.Assert.assertThrows;
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
	private Registro registro3;
	
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
		verify(registro1, times(1)).finalizar();
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
	
	@Test
	void testFinalizarTodos() {
		registro1 = mock(Registro.class);
		when(registro1.getPatente()).thenReturn("abc123");
		registro2 = mock(Registro.class);
		when(registro2.getPatente()).thenReturn("abc124");
		registro3= mock(Registro.class);
		when(registro3.getPatente()).thenReturn("abc125");
		TestCentro centrotest = new TestCentro();
		centrotest.registrarInicio(registro1);
		centrotest.registrarInicio(registro3);
		centrotest.registrarInicio(registro2);
		centrotest.finalizarTodos();
		
		verify(registro1,times(1)).finalizar();
		verify(registro2,times(1)).finalizar();
		verify(registro2,times(1)).finalizar();
		assertTrue(centrotest.getRegistros().isEmpty());
	}
	@Test
	void validarExistenciaDeEstacionamiento() throws Exception {
		assertThrows(Exception.class, () -> centroConocido.validarExistenciaDeEstacionamiento("7123"));
	}
}
