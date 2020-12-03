package SEM;

import java.time.LocalDateTime;

public class RegistroAplicacion extends RegistroDeEstacionamiento {
	private Celular celular;
	private LocalDateTime horaDeFinalizacion = null;
	
	public RegistroAplicacion(Sistema sistema, Celular celular) {
		super(sistema, celular.getPatente(), celular.getZona());
		this.celular = celular;
	}
	
	@Override
	public void finalizar() {
		this.setHorarioDeFinalizacion();
		sistema.restarSaldo(celular.getNumero(),this.calcularCosto());
	}


	@Override
	public int calcularDuracion() {
		if(LocalDateTime.now(clock).isBefore(getHoraDeFin())) {
			LocalDateTime horaDeFin = LocalDateTime.now(clock);
			return horaDeFin.getHour() - this.horaDeInicio.getHour() +
				   (horaDeFin.getMinute() > horaDeInicio.getHour()? 1 : 0);
		}else {
			return this.getHoraDeFin().getHour() - this.horaDeInicio.getHour() +
				   (this.getHoraDeFin().getMinute() > this.horaDeInicio.getHour()? 1 : 0);
		}

	}
	
	private void setHorarioDeFinalizacion() {
		this.horaDeFinalizacion = LocalDateTime.now(clock).isAfter(this.getHoraDeFin())? 
												this.getHoraDeFin() : LocalDateTime.now(clock);
	}
	
	public String getHorarioDeFinalizacion() {
		return horaDeFinalizacion != null? 
				this.horaDeFinalizacion.getHour() + ":" + this.horaDeFinalizacion.getMinute() :
					"Desconocida";
	}
	
	@Override
	public int calcularCosto() {
		return this.calcularDuracion() * sistema.getValorDeHora();
	}


	public String getNumeroCelular() {
		return this.celular.getNumero();
	}
	
	@Override
	public LocalDateTime getHoraDeFin() {
		return this.calcularHoraDeFin(celular.getSaldoActual() / sistema.getValorDeHora());
	}
	
	@Override
	public String toString() {
		String resultado = "Patente: " + this.getPatente() + "\n" +
						   "Hora de inicio: " + this.getHoraDeInicio().getHour() + ":" +
						   					    this.getHoraDeInicio().getMinute() + "\n" +
						   "Hora de finalizacion: " + this.getHorarioDeFinalizacion() + "\n" +
						   "Número de celular: " + this.getNumeroCelular() + "\n" +
						   "Zona: " + this.getZona()+ "\n";
		return resultado;
	}
}