package SEM;

import java.time.LocalDate;

public class Registro {
	private String patente;
	private LocalDate horaDeInicio;
	private LocalDate horaDeFin;
	
	public String getPatente() {
		return this.patente;
	}
	
	public LocalDate getHoraDeInicio() {
		return this.horaDeInicio;
	}
	
	public LocalDate getHoraDeFin() {
		return this.horaDeFin;
	}
	
	public boolean estaVigente() {
		return false;
		
	}
}