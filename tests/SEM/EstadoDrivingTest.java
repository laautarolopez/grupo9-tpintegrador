package SEM;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;


class EstadoDrivingTest {
	Modo modo = mock(Modo.class);
	EstadoDriving e = new EstadoDriving(modo);
	@Test
	void driving() {
		e.driving();
	}
	
	@Test
	void walking() {
		e.walking();
		verify(modo,times(1)).realizarAccionDrivingAWalking();
	}
	
	@Test
	void getModo() {
		assertEquals(modo,e.getModo());
	}
	
	@Test
	void setModo() {
		Modo modo2 = mock(Modo.class);
		e.setModo(modo2);
		assertEquals(modo2 ,e.getModo());
	}
}
