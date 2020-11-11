package SEM;

import java.time.LocalDateTime;

public class RegistroAplicacion extends Registro {
	private Celular celular;
	private CentroCelulares centroCelulares = CentroCelulares.getCentroCelulares();
	
	public RegistroAplicacion(String patente, Zona zona, Celular celular) {
		super(patente, zona);
		this.celular = celular;
	}
	
	@Override
	public void finalizar() {
		celular.notificacionDeFin(this.generarResumen());
		centroCelulares.restarSaldo(celular.getNumero(),this.calcularCosto());
	}
	
	private String generarResumen() {
		return ("Hora de inicio: " + this.horaDeInicio.getHour() + ":" + this.getHoraDeInicio().getHour() + "\n" +
				"Hora de fin: " + LocalDateTime.now(clock).getHour()+ ":" + LocalDateTime.now().getHour() + "\n" +
				"Duración: " + this.calcularDuracion() + "\n" +
				"Costo: " + this.calcularCosto());
	}


	private int calcularDuracion() {
		if(LocalDateTime.now(clock).isBefore(getHoraDeFin())) {
			LocalDateTime horaDeFin = LocalDateTime.now(clock);
			return horaDeFin.getHour() - this.horaDeInicio.getHour() +
				   horaDeFin.getMinute() > horaDeInicio.getHour()? 1 : 0;
		}else {
			return this.getHoraDeFin().getHour() - this.horaDeInicio.getHour() +
				   this.getHoraDeFin().getMinute() > horaDeInicio.getHour()? 1 : 0;
		}

	}
	
	private int calcularCosto() {
		return this.calcularDuracion() * 40;
	}


	public String getNumeroCelular() {
		return this.celular.getNumero();
	}
	
	@Override
	public LocalDateTime getHoraDeFin() {
		return this.calcularHoraDeFin(celular.getSaldoActual() / 40);
	}
}