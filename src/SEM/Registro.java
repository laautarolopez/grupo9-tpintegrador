package SEM;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

public abstract class Registro {
	protected Clock clock = Clock.system(ZoneId.of("GMT-3"));
	private String patente;
	protected LocalDateTime horaDeInicio;
	private String zona;
	
	
	
	public Registro(String patente, String zona) {
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
	
	public void finalizar() {}
	
}