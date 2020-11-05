package SEM;

public class RegistroAplicacion extends Registro {
	private boolean estaVigente;
	private int numeroCelular;
	
	public RegistroAplicacion(String patente, Zona zona, int numeroCelular) {
		super(patente, zona);
		this.numeroCelular = numeroCelular;
		this.estaVigente = true;
	}
	
	@Override
	public boolean estaVigente() {
		return this.estaVigente;
	}
	
	public int getNumeroCelular() {
		return this.numeroCelular;
	}
}