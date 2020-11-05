package SEM;

import java.time.LocalDateTime;

public class RegistroPuntoDeVenta extends Registro {
	
	public RegistroPuntoDeVenta(String patente, Zona zona, int dinero) {
		super(patente, zona);
		this.calcularHoraDeFin(dinero);
	}
	
	private void calcularHoraDeFin(int dinero) {
		int horas = dinero / 40;
		LocalDateTime horaDeFin = this.getHoraDeInicio().plusHours(horas);
		this.setHoraDeFin(horaDeFin);
	}

	@Override
	public boolean estaVigente() {
		return LocalDateTime.now().isBefore(this.getHoraDeFin());
	}
}