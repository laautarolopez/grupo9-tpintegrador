package SEM;

import java.time.LocalDateTime;

public class RegistroPuntoDeVenta extends RegistroDeEstacionamiento implements ValorDeHora {
	
	private LocalDateTime horaDeFin;
	private int costo;
	
	public RegistroPuntoDeVenta(String patente, String zona, int horas) throws Exception {
		super(patente, zona);
		this.costo = horas * valorDeHora;
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


	@Override
	protected int calcularDuracion() {
		return this.horaDeFin.getHour() - this.getHoraDeInicio().getHour();
	}
}