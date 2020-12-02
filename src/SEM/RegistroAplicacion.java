package SEM;

import java.time.LocalDateTime;

public class RegistroAplicacion extends RegistroDeEstacionamiento {
	private Celular celular;
	
	public RegistroAplicacion(Sistema sistema, Celular celular) throws Exception {
		super(sistema, celular.getPatente(), celular.getZona());
		this.celular = celular;
	}
	
	@Override
	public void finalizar() {
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
						   "Número de celular: " + this.getNumeroCelular() + "\n" +
						   "Zona: " + this.getZona()+ "\n";
		return resultado;
	}
}