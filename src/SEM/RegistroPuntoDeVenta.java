package SEM;

import java.time.LocalDateTime;

public class RegistroPuntoDeVenta extends Registro {
	
	public RegistroPuntoDeVenta(String patente, Zona zona, int horas) {
		super(patente, zona);
		this.calcularHoraDeFin(horas);
	}
	
	private void calcularHoraDeFin(int horas) {
		LocalDateTime horaDeFin = this.getHoraDeInicio().plusHours(horas);
		this.setHoraDeFin(horaDeFin);
	}

	@Override
	public boolean estaVigente() {
		return LocalDateTime.now().isBefore(this.getHoraDeFin());
	}
}