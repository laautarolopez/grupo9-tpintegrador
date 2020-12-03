package SEM;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;



public class NotificacionesActivadas implements Notificador{
	
	protected Clock clock = Clock.system(ZoneId.of("GMT-3"));
	
	@Override
	public void informarInicio(Celular celular, RegistroDeEstacionamiento registro) {
		celular.notificar("Hora de inicio: " + registro.getHoraDeInicio() + 
				 "Hora máxima de fin: " + registro.getHoraDeFin());
	}
	@Override
	public void aconsejarInicio(Celular celular, AplicacionCliente app) {
		if(celular.estaEnZonaDeEstacionamiento() && app.tieneRegistroCreado()) {
			celular.notificar("Se detectó que estacionaste en una zona de estacionamiento medido,"
					+ " te recomendamos que lo inicies desde la app para evitar multas");			
		}
	}
	@Override
	public void aconsejarFinal(Celular celular, AplicacionCliente app) {
		
		if(celular.estaEnZonaDeEstacionamiento() && app.tieneRegistroCreado()) {
			celular.notificar("Se detectó que finalizaste un estacionamiento,"
					+ " te recomendamos que lo finalices el mismo desde la app para evitarte gastos adicionales");
		}
	}
	@Override
	public void informarFinal(Celular celular, RegistroDeEstacionamiento registro) {
		celular.notificar(("Hora de inicio: " + registro.getHoraDeInicio().getHour() + ":" + registro.getHoraDeInicio().getMinute() + "\n" +
				"Hora de fin: " + LocalDateTime.now(clock).getHour()+ ":" + LocalDateTime.now(clock).getMinute() + "\n" +
				"Duración: " + registro.calcularDuracion() + "\n" +
				"Costo: " + registro.calcularCosto()));
	}
	@Override
	public void cambiarModo(AplicacionCliente app) {
		app.setNotificador(new NotificacionesDesactivadas());
		
	}
}
