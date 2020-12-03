package SEM;

public class AplicacionCliente implements MovementSensor {
	private Sistema sistema;
	private Modo modo;
	private Notificador notificador;
	private Celular celular;
	private RegistroAplicacion registro;
	
	public AplicacionCliente(Sistema sistema, Celular celular) {
		this.sistema = sistema;
		this.modo = new ModoManual(this);
		this.notificador = new NotificacionesActivadas();
		this.celular = celular;
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
			RegistroAplicacion registro = new RegistroAplicacion(sistema, celular);
			sistema.registrarInicio(registro);
			notificador.informarInicio(celular, registro);
			this.registro = registro;
		}
	}
	
	private boolean condicionesDadasParaIniciar() {
		return celular.estaEnZonaDeEstacionamiento() && this.tieneSaldoSuficiente()
				   && !this.tieneRegistroCreado() && sistema.esHoraDeEstacionamiento();
	}
	
	private boolean tieneSaldoSuficiente() {
		return celular.getSaldoActual() >= sistema.getValorDeHora();
	}

	public void finalizarEstacionamiento() {
		this.modo.finalizarEstacionamiento();
	}
	
	protected void realizarFinalizacion() {
		if(this.tieneRegistroCreado()) {
			sistema.registrarFinal(this.registro.getPatente());
			notificador.informarFinal(celular, this.registro);
			this.registro = null;
		}
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
		notificador.aconsejarFinal(celular,this);
	}

	public void aconsejarInicio() {
		notificador.aconsejarInicio(celular,this);
	}



	public void setNotificador(Notificador n) {
		this.notificador = n;
		
	}
}