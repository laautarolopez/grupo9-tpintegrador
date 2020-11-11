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
		centroTesteable.agregarSaldo("123", 150);
		assertEquals(150,centroTesteable.getCelularesRegistrados().get("123"));
		centroTesteable.agregarSaldo("123", 350);
		assertEquals(500,centroTesteable.getCelularesRegistrados().get("123"));
		centroTesteable.agregarSaldo("122224", 450);
		assertEquals(450,centroTesteable.getCelularesRegistrados().get("122224"));
	}
	
	@Test
	void testNotificarObservers() {
		Observer observer = mock(Observer.class);
		centroConocido.addObserver(observer);
		centroConocido.agregarSaldo("123",150);
		verify(observer,times(1)).update(Map.entry("123", 150));
	}
	

}
