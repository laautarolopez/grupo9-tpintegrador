package SEM;

public class Celular /*Puede ser temporal, despues lo resolvemos en el diseño*/{
	private String numero;
	private Gps gps;
	private AplicacionCliente app;
	private String patenteLinkeada;
	private CelularReal real;
	
	
	public Celular(String numero, String patente, Gps gps, MovementSensor ms) {
		this.numero = numero;
		this.gps = gps;
		this.patenteLinkeada = patente;
		
		this.app = new AplicacionCliente(this, ms);
	}
	
	
	String getPatente() {
		return this.patenteLinkeada;
	}
	String getNumero() {
		return this.numero;
	}
	
	
	public String getZona() {
		return this.gps.getZona();
	}

	public void notificar(String mensaje) {
		this.real.recibirNotificacion(mensaje);
	}
	
	public void cambiarModoApp(Modo modo) {
		app.cambiarModo(modo);
	}

	
}