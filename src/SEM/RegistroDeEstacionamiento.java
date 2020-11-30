package SEM;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

public abstract class RegistroDeEstacionamiento {
	Sistema sistema;
	Clock clock = Clock.system(ZoneId.of("GMT-3"));
	LocalDateTime horaDeInicio;
	private String patente;
	private String zona;
	
	
	
	public RegistroDeEstacionamiento(Sistema sistema, String patente, String zona) throws Exception{
		if(LocalDateTime.now(clock).getHour() < 7 || LocalDateTime.now(clock).getHour() >= 20) {
			throw new Exception("No se puede generar un registro de estacionamiento en este horario");
		}
		this.sistema = sistema;
		this.patente = patente;
		this.zona = zona;
		this.horaDeInicio = LocalDateTime.now(clock);
	}
	
	public String getPatente() {
		return this.patente;
	}
	
	public LocalDateTime getHoraDeInicio() {
		return this.horaDeInicio;
	}
	
	protected LocalDateTime calcularHoraDeFin(int horas) {
		if(LocalDateTime.now(clock).plusHours(horas).getHour() >= 20) {
			return (LocalDateTime.of(
					this.horaDeInicio.getYear(), 
					this.horaDeInicio.getMonth(), 
					this.horaDeInicio.getDayOfMonth(), 
					20, 
					0));
		}else {
			return this.getHoraDeInicio().plusHours(horas);
		}
	}
	public boolean estaVigente() {
		return LocalDateTime.now(clock).isBefore(this.getHoraDeFin());
	}
	
	protected abstract int calcularCosto();
	
	public String getZona() {
		return this.zona;
	}

	public abstract LocalDateTime getHoraDeFin();
	
	protected abstract int calcularDuracion();
	
	public void finalizar() {}
	
}