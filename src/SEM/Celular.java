package SEM;

public class Celular /*Puede ser temporal, despues lo resolvemos en el dise�o*/{
	private String numero;
	private Gps gps;
	private AplicacionCliente app;
	private String patenteLinkeada;
	private CelularReal real;
	private CentroZonas centroZonas = CentroZonas.getCentro();
	private CentroCelulares centroCelulares= CentroCelulares.getCentroCelulares();
	
	
	public Celular(String numero, String patente, Gps gps, MovementSensor ms) {
		this.numero = numero;
		this.gps = gps;
		this.patenteLinkeada = patente;
		
		this.app = new AplicacionCliente(this, ms);
	}
	
	
	public String getPatente() {
		return this.patenteLinkeada;
	} 
	public String getNumero() {
		return this.numero;
	}
	
	public void validarSaldo(int valorDeHora) throws Exception {
		if(!(this.getSaldoActual() >= valorDeHora)) {
			throw new Exception("Saldo insuficiente. Estacionamiento no permitido.");
		}
	}
	
	public boolean estaEnZonaDeEstacionamiento() {
		return centroZonas.esZonaDeEstacionamiento(this.getZona());
	}
	
	public int getSaldoActual() {
		return centroCelulares.saldoDe(this.numero);
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