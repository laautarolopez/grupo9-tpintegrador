package SEM;

import java.time.LocalDateTime;

public class RegistroAplicacion extends Registro {
	private Celular celular;
	
	public RegistroAplicacion(String patente, Zona zona, Celular celular) {
		super(patente, zona);
		this.celular = celular;
	}
	
	
	public String getNumeroCelular() {
		return this.celular.getNumero();
	}
	
	@Override
	public LocalDateTime getHoraDeFin() {
		return this.calcularHoraDeFin(celular.getSaldoActual() / 40);
	}
}