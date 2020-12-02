package SEM;

import java.time.LocalDateTime;

public class RegistroAplicacion extends RegistroDeEstacionamiento {
	private Celular celular;
	
	public RegistroAplicacion(Sistema sistema, String patente, String zona, Celular celular) throws Exception {
		super(sistema, patente, zona);
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
}