package SEM;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;



public class Notificador {
	
	protected Clock clock = Clock.system(ZoneId.of("GMT-3"));
	
	
	public void informarInicio(Celular celular, Registro registro) {
		celular.notificar("Hora de inicio: " + registro.getHoraDeInicio() + 
				 "Hora máxima de fin: " + registro.getHoraDeFin());
	}
	
	public void aconsejarInicio(Celular celular) {
		celular.notificar("Se detectó que estacionaste en una zona de estacionamiento medido,"
				+ " te recomendamos que lo inicies desde la app para evitar multas");
	}
	
	public void aconsejarFinal(Celular celular) {
		celular.notificar("Se detectó que finalizaste un estacionamiento,"
				+ " te recomendamos que lo finalices el mismo desde la app para evitarte gastos adicionales");
	}

	public void informarFinal(Celular celular, Registro registro) {
		celular.notificar(("Hora de inicio: " + registro.getHoraDeInicio().getHour() + ":" + registro.getHoraDeInicio().getHour() + "\n" +
				"Hora de fin: " + LocalDateTime.now(clock).getHour()+ ":" + LocalDateTime.now(clock).getHour() + "\n" +
				"Duración: " + registro.calcularDuracion() + "\n" +
				"Costo: " + registro.calcularCosto()));
	}
}
