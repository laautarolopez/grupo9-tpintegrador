package SEM;

import java.time.LocalDateTime;

public class RegistroPuntoDeVenta extends Registro {
	
	
	private LocalDateTime horaDeFin;
	
	public RegistroPuntoDeVenta(String patente, Zona zona, int horas) {
		super(patente, zona);
		this.setHoraDeFin(horas);
	}
	
	
	protected void setHoraDeFin(int horas) {
		this.horaDeFin = this.calcularHoraDeFin(horas);
	}
	
	@Override
	public LocalDateTime getHoraDeFin() {
		return this.horaDeFin;
	}
}