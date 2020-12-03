package SEM;

public class AplicacionCliente extends Celular implements MovementSensor {
	private Modo modo;
	private Notificador notificador;
	private RegistroAplicacion registro;
	
	public AplicacionCliente(Sistema sistema, String numero, String patente, Gps gps) {
		super(sistema, numero, patente, gps);
		this.modo = new ModoManual(this);
		this.notificador = new NotificacionesActivadas();
		this.registro = null;
	}
	
	public Modo getModo() {
		return this.modo;
	}
	
	public RegistroAplicacion getRegistro() {
		return this.registro;
	}
	
	public void setModo(Modo modo) {
		this.modo = modo;
	}
	
	public void switchNotificaciones() {
		this.notificador.cambiarModo(this);
	}
	
	public void cambiarModo() {
		this.modo.cambiarModo();
	}
	
	public void iniciarEstacionamiento() {
		this.modo.iniciarEstacionamiento();
	}
	
	protected void realizarEstacionamiento() {
		if(this.condicionesDadasParaIniciar()) {
			RegistroAplicacion registro = new RegistroAplicacion(sistema, this);
			sistema.registrarInicio(registro);
			notificador.informarInicio(this, registro);
			this.registro = registro;
		}
	}
	
	private boolean condicionesDadasParaIniciar() {
		return this.estaEnZonaDeEstacionamiento() && this.tieneSaldoSuficiente()
				   && !this.tieneRegistroCreado() && sistema.esHoraDeEstacionamiento();
	}
	
	private boolean tieneSaldoSuficiente() {
		return this.getSaldoActual() >= sistema.getValorDeHora();
	}

	public void finalizarEstacionamiento() {
		this.modo.finalizarEstacionamiento();
	}
	
	protected void realizarFinalizacion() {
		if(this.tieneRegistroCreado()) {
			sistema.registrarFinal(this.registro.getPatente());
			notificador.informarFinal(this, this.registro);
			this.terminarRegistro();
		}
	}
	
	protected void terminarRegistro() {
		this.registro = null;
	}
	
	public boolean tieneRegistroCreado() {
		return this.registro != null;
	}

	@Override
	public void walking() {
		modo.walking();
	}
	
	@Override
	public void driving() {
		modo.driving();
	}
	
	public void aconsejarFinal() {
		notificador.aconsejarFinal(this, this);
	}

	public void aconsejarInicio() {
		notificador.aconsejarInicio(this, this);
	}
	
	public void setNotificador(Notificador n) {
		this.notificador = n;
	}
}