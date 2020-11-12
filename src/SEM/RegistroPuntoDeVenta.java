package SEM;

import java.time.LocalDateTime;

public class RegistroPuntoDeVenta extends Registro {
	
	private LocalDateTime horaDeFin;
	private int costo;
	
	public RegistroPuntoDeVenta(String patente, String zona, int horas) {
		super(patente, zona);
		this.costo = horas * 40;
		this.setHoraDeFin(horas);
	}
	
	
	protected void setHoraDeFin(int horas) {
		this.horaDeFin = this.calcularHoraDeFin(horas);
	}
	
	@Override
	public LocalDateTime getHoraDeFin() {
		return this.horaDeFin;
	}
	
	@Override
	protected int calcularCosto() {
		return this.costo;
	}
}