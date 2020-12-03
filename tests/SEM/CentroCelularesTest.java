package SEM;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CentroCelularesTest {
	
	@Spy
	HashMap<String,Integer> cr;
	@InjectMocks
	public CentroCelulares centroConocido ;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	void saldoDe() {
		assertEquals(0,centroConocido.saldoDe("123122412"));
		centroConocido.agregarSaldo("123", 350);
		assertEquals(350, centroConocido.saldoDe("123"));
		centroConocido.restarSaldo("123", 120);
		assertEquals(230, centroConocido.saldoDe("123"));
	}
	@Test
	void restarSaldo() {
		centroConocido.agregarSaldo("123", 1350);
		centroConocido.restarSaldo("123", 120);
		assertEquals(1230,cr.get("123"));
	}
	
	@Test
	void registrarRecargaDeCelularTest() {
		centroConocido.agregarSaldo("123", 150);
		assertEquals(150,cr.get("123"));
		centroConocido.agregarSaldo("123", 350);
		assertEquals(500,cr.get("123"));
		centroConocido.agregarSaldo("122224", 450);
		assertEquals(450,cr.get("122224"));
	}
	@Test
	void testNotificarObservers() {
		Observer observer = mock(Observer.class);
		centroConocido.addObserver(observer);
		centroConocido.agregarSaldo("123", 150);
		String info = "Se recargó $" + 150 + "al número " + "123" + "\n" +
				"El crédito actual es de: $" + 150 + "\n";
		verify(observer,times(1)).update(info);
	}

}
