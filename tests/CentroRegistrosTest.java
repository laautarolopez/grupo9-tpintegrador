import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SEM.Sem;

public class SemTest {
	
	public Sem semConocido;
	
	@BeforeEach
	public void setUp() {
		semConocido = Sem.getSem();
	}
	@Test
	void test() {
		assertEquals(semConocido, Sem.getSem());
	}
}
