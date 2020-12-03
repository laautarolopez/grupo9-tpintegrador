package SEM;

import java.time.LocalDateTime;

public class RegistroPuntoDeVenta extends RegistroDeEstacionamiento {
	
	private LocalDateTime horaDeFin;
	private int costo;
	
	public RegistroPuntoDeVenta(Sistema sistema, String patente, 
								String zona, int horas) {
		super(sistema, patente, zona);
		this.costo = horas * sistema.getValorDeHora();
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
	
	@Override
	public String toString() {
		String resultado = "Patente: " + this.getPatente() + "\n" +
						   "Hora de inicio: " + this.getHoraDeInicio().getHour() + ":" +
						   						this.getHoraDeInicio().getMinute() + "\n" +
						   "Zona: " + this.getZona()+ "\n";
		return resultado;
	}
}