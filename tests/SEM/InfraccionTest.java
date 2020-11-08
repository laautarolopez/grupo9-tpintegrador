package SEM;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

public class InfraccionTest {
	private String patente;
	private Inspector inspector;
	private Infraccion infraccion;
	
	private LocalDateTime fechaYHora;
	
	@BeforeEach
	public void setUp() {
		patente = "ACF210";
		inspector = mock(Inspector.class);
		infraccion = new Infraccion(patente, inspector);
		fechaYHora = infraccion.getFechaYHora();
	}

	@Test
	public void gettersTest() {
		assertEquals(patente, infraccion.getPatente());
		assertEquals(fechaYHora, infraccion.getFechaYHora());
		assertEquals(inspector, infraccion.getInspector());
		assertEquals(inspector.getZona(), infraccion.getZona());
	}
}
