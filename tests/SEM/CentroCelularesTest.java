package SEM;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

class CentroCelularesTesteable extends CentroCelulares {
	public CentroCelularesTesteable() {
		super();
	}
	
	public HashMap<String,Integer> getCelularesRegistrados(){
		return this.celularesRegistrados;
	}
}

public class CentroCelularesTest {
	
	CentroCelulares centroConocido;
	CentroCelularesTesteable centroTesteable;
	
	@BeforeEach
	void setup() {
		centroConocido = CentroCelulares.getCentroCelulares();
		centroTesteable = new CentroCelularesTesteable();
	}
	@Test
	void testSingleton() {
		assertEquals(centroConocido, CentroCelulares.getCentroCelulares());
	}
	
	@Test
	void registrarRecargaDeCelularTest() {
		centroTesteable.registrarCarga("123", 150);
		assertEquals(150,centroTesteable.getCelularesRegistrados().get("123"));
		centroTesteable.registrarCarga("123", 350);
		assertEquals(350,centroTesteable.getCelularesRegistrados().get("123"));
		centroTesteable.registrarCarga("124", 450);
		assertEquals(450,centroTesteable.getCelularesRegistrados().get("124"));
	}
	
	@Test
	void testNotificarObservers() {
		Observer observer = mock(Observer.class);
		centroConocido.addObserver(observer);
		centroConocido.registrarCarga("123",150);
		verify(observer,times(1)).update(Map.entry("123", 150));
	}
	

}
