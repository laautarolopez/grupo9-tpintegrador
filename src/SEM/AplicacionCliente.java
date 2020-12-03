package SEM;

public class AplicacionCliente implements MovementSensor {
	private Sistema sistema;
	private Modo modo;
	private Notificador notificador;
	private Celular celular;
	private boolean consejosActivados;
	private RegistroAplicacion registro;
	
	public AplicacionCliente(Sistema sistema, Celular celular) {
		this.sistema = sistema;
		this.modo = new ModoManual(this);
		this.notificador = new Notificador();
		this.celular = celular;
		this.consejosActivados = true;
	}
	
	public Modo getModo() {
		return this.modo;
	}
	
	public void setModo(Modo modo) {
		this.modo = modo;
	}
	
	public void activarConsejos() {
		this.consejosActivados = true;
	}
	
	public void desactivarConsejos() {
		this.consejosActivados = false;
	}
	
	public void cambiarModo() {
		this.modo.cambiarModo();
	}
	
	public void iniciarEstacionamiento() {
		this.modo.iniciarEstacionamiento();
	}
	
	protected void realizarEstacionamiento() throws Exception {
		if(celular.estaEnZonaDeEstacionamiento() && this.tieneSaldoSuficiente()
		   && !this.tieneRegistroCreado()) {
			RegistroAplicacion registro = new RegistroAplicacion(sistema, celular);
			sistema.registrarInicio(registro);
			notificador.informarInicio(celular, registro);
			this.registro = registro;
		}
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
	
	private boolean tieneRegistroCreado() {
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
		if(consejosActivados && celular.estaEnZonaDeEstacionamiento()
		   && this.tieneRegistroCreado()) {
			notificador.aconsejarFinal(celular);
		}
	}

	public void aconsejarInicio() {
		if(consejosActivados && celular.estaEnZonaDeEstacionamiento()
		   && !this.tieneRegistroCreado()) {
			notificador.aconsejarInicio(celular);
		}
	}
}