package SEM;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class CentroRegistrosTest {
	
	
	public RegistroDeEstacionamiento registro1;
	public RegistroDeEstacionamiento registro2;
	private RegistroDeEstacionamiento registro3;
	
	@Spy
	ArrayList<RegistroDeEstacionamiento> registros;// = new ArrayList<RegistroDeEstacionamiento>();
	@Spy
	ArrayList<Observer> observers;
	@InjectMocks
	public CentroRegistros centroConocido ;//= new CentroRegistros();
	@BeforeEach
	
	void setup() {
		MockitoAnnotations.initMocks(this);
		
		registro1 = mock(RegistroDeEstacionamiento.class);
		when(registro1.toString()).thenReturn("a");
		when(registro1.getPatente()).thenReturn("abc123");
		registro2 = mock(RegistroDeEstacionamiento.class);
		when(registro2.getPatente()).thenReturn("abc124");
		registro3= mock(RegistroDeEstacionamiento.class);
		when(registro3.getPatente()).thenReturn("abc125");
	}
	@Test
	void testValidacionDeRegistros() {
		when(registro1.estaVigente()).thenReturn(true);
		centroConocido.registrarInicio(registro1);
		assertTrue(centroConocido.estaVigente("abc123"));
	}
	
	@Test
	void agregarRegistros() {
		registro1 = mock(RegistroDeEstacionamiento.class);
		centroConocido.registrarInicio(registro1);
		assertTrue(registros.contains(registro1));
	}
	
	
	
	@Test
	void testNotificarObservers() {
		Observer observer = mock(Observer.class);
		centroConocido.addObserver(observer);
		centroConocido.registrarInicio(registro1);
		verify(observer,times(1)).update("Inicio de: \n" + "a");
	}
	
	@Test
	void testNotificarObservers2() {
		Observer observer = mock(Observer.class);
		centroConocido.addObserver(observer);
		centroConocido.registrarInicio(registro1);
		centroConocido.registrarFinal("abc123");
		verify(observer,times(1)).update("Final de: " + "a");
	}

	@Test
	void testFinalizarTodos() {
		centroConocido.registrarInicio(registro1);
		centroConocido.registrarInicio(registro3);
		centroConocido.registrarInicio(registro2);
		centroConocido.finalizarTodos();
		verify(registro1,times(1)).finalizar();
		verify(registro2,times(1)).finalizar();
		verify(registro3,times(1)).finalizar();
		assertTrue(registros.isEmpty());
	}
	
	
	@Test 
	void registrarFinal(){
		when(registro1.estaVigente()).thenReturn(true);
		centroConocido.registrarInicio(registro1);
		centroConocido.registrarFinal("abc123");
		assertFalse(registros.contains(registro1));
		verify(registro1, times(1)).finalizar();
	}
	
	@Test
	void deleteObserver() {
		Observer observer = mock(Observer.class);
		centroConocido.addObserver(observer);
		assertEquals(1,centroConocido.countObservers());
		centroConocido.deleteObserver(observer);
		centroConocido.deleteObserver(observer);
		assertEquals(0,centroConocido.countObservers());
	}

}
	
