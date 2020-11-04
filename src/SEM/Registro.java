package SEM;

import java.time.LocalDateTime;

public abstract class Registro {
	private String patente;
	private LocalDateTime horaDeInicio;
	private LocalDateTime horaDeFin;
	private Zona zona;
	
	public Registro(String patente, Zona zona) {
		this.patente = patente;
		this.zona = zona;
		this.horaDeInicio = LocalDateTime.now();
	}
	
	public String getPatente() {
		return this.patente;
	}
	
	public LocalDateTime getHoraDeInicio() {
		return this.horaDeInicio;
	}
	
	// Prec.: debe haber una horaDeFin definida.
	public LocalDateTime getHoraDeFin() {
		return this.horaDeFin;
	}
	
	protected void setHoraDeFin(LocalDateTime horaDeFin) {
		this.horaDeFin = horaDeFin;
	}
	
	public abstract boolean estaVigente();
	
	public Zona getZona() {
		return this.zona;
	}
}