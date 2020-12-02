package SEM;

public class Celular {
	private Sistema sistema;
	private String numero;
	private String patenteLinkeada;
	private Gps gps;
	private AplicacionCliente aplicacion;
	
	public Celular(Sistema sistema, String numero, String patente, Gps gps) {
		this.sistema = sistema;
		this.numero = numero;
		this.patenteLinkeada = patente;
		this.gps = gps;
		this.aplicacion = new AplicacionCliente(sistema, this);
	}
	
	public String getPatente() {
		return this.patenteLinkeada;
	} 
	
	public void setPatente(String patente) {
		this.patenteLinkeada = patente;
	}
	
	public String getNumero() {
		return this.numero;
	}
	
	public boolean estaEnZonaDeEstacionamiento() {
		return sistema.esZonaDeEstacionamiento(this.getZona());
	}
	
	public int getSaldoActual() {
		return sistema.getSaldo(this.numero);
	}
	
	public String getZona() {
		return this.gps.getZona();
	}

	public void notificar(String mensaje) {
		System.out.println(mensaje);
	}
	
	public void cambiarModoApp() {
		this.aplicacion.cambiarModo();
	}
}