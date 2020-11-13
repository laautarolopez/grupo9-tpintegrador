package SEM;

import java.time.LocalDateTime;

public class RegistroAplicacion extends Registro {
	private Celular celular;
	private CentroCelulares centroCelulares = CentroCelulares.getCentroCelulares();
	
	public RegistroAplicacion(String patente, String zona, Celular celular) throws Exception {
		super(patente, zona);
		this.celular = celular;
	}
	
	@Override
	public void finalizar() {
		centroCelulares.restarSaldo(celular.getNumero(),this.calcularCosto());
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
		return this.calcularDuracion() * 40;
	}


	public String getNumeroCelular() {
		return this.celular.getNumero();
	}
	
	@Override
	public LocalDateTime getHoraDeFin() {
		return this.calcularHoraDeFin(celular.getSaldoActual()/ 40);
	}
}