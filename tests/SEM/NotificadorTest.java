package SEM;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito.Then;

import static org.mockito.Mockito.*;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;



class NotificadorTest {
	Registro registro = mock(Registro.class);
	Celular celular = mock(Celular.class);
	NotificadorTesteable notificador = new NotificadorTesteable();
	Clock clock = Clock.fixed(Instant.parse("2020-11-10T17:24:24.498559900Z"), ZoneId.systemDefault());
	
	public class NotificadorTesteable extends Notificador{
		public void setClock(Clock clock) {
			this.clock = clock;
		}

	}
	@BeforeEach
	void setup() {
		when(registro.getHoraDeInicio()).thenReturn(LocalDateTime.of(2020, 4, 15, 12, 11));
		when(registro.getHoraDeFin()).thenReturn(LocalDateTime.of(2020, 4, 15, 14, 15));
		when(registro.calcularCosto()).thenReturn(160);
		when(registro.calcularDuracion()).thenReturn(4);
		notificador.setClock(clock);
	}
	
	@Test
	void aconsejarInicioTest() {
		notificador.aconsejarInicio(celular);
		verify(celular, times(1)).notificar("Se detectó que estacionaste en una zona de estacionamiento medido,"
				+ " te recomendamos que lo inicies desde la app para evitar multas");
	}
	@Test
	void aconsejarFinalTest() {
		notificador.aconsejarFinal(celular);
		verify(celular, times(1)).notificar("Se detectó que finalizaste un estacionamiento,"
				+ " te recomendamos que lo finalices el mismo desde la app para evitarte gastos adicionales");
	}
	@Test
	void informarInicioTest() {
		notificador.informarInicio(celular, registro);
		verify(celular, times(1)).notificar("Hora de inicio: " + registro.getHoraDeInicio() + 
				 "Hora máxima de fin: " + registro.getHoraDeFin());
	}
	@Test
	void informarFinalTest() {
		notificador.informarFinal(celular, registro);
		verify(celular, times(1)).notificar("Hora de inicio: " + registro.getHoraDeInicio().getHour() + ":" + registro.getHoraDeInicio().getMinute() + "\n" +
				"Hora de fin: " + "14" + ":" + "24" + "\n" +
				"Duración: " + registro.calcularDuracion() + "\n" +
				"Costo: " + registro.calcularCosto());
	}
}
