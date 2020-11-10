package SEM;

import java.time.LocalDateTime;

public class RegistroAplicacion extends Registro {
	private boolean estaVigente;
	private Celular celular;
	
	public RegistroAplicacion(String patente, Zona zona, Celular celular) {
		super(patente, zona);
		this.celular = celular;
		this.estaVigente = true;
	}
	
	@Override
	public boolean estaVigente() {
		return this.estaVigente;
	}
	
	public String getNumeroCelular() {
		return this.celular.getNumero();
	}
	
	@Override
	public LocalDateTime getHoraDeFin() {
		int saldo = celular.getSaldoActual();
		int horas = saldo / 40;
		return this.getHoraDeInicio().plusHours(horas);
	}
}