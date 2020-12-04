package SEM;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

class EstadoWalkingTest {
	
	Modo modo = mock(Modo.class);
	EstadoWalking e = new EstadoWalking(modo);
	@Test
	void driving() {
		e.walking();
	}
		
	@Test
	void walking() {
		e.driving();
		verify(modo,times(1)).realizarAccionWalkingADriving();
	}
	
}
