package SEM;

public class AplicacionCliente implements MovementSensor {
	private Sistema sistema;
	private Modo modo;
	private Notificador notificador;
	private Celular celular;
	private boolean consejosActivados;
	
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
	
	public void generarRegistro() throws Exception{
		sistema.validarZona(celular.getZona());
		celular.validarSaldo(sistema.getValorDeHora());
		RegistroAplicacion registro = new RegistroAplicacion(sistema, celular.getPatente(), celular.getZona(),celular);
		notificador.informarInicio(celular, registro);
		sistema.registrarInicio(registro);
	}
	
	public void finalizarEstacionamiento() {
		this.modo.finalizarEstacionamiento();
	}
	
	public void realizarFinalizacion() throws Exception{
		centroRegistros.validarExistenciaDeEstacionamiento(celular.getPatente());
		notificador.informarFinal(celular, centroRegistros.getRegistro(celular.getPatente()));
		centroRegistros.registrarFinal(celular.getPatente());
	}
	
	@Override
	public void walking() {
		modo.walking();
	}
	
	@Override
	public void driving() {
		//if(celular.estaEnZonaDeEstacionamiento())
		modo.driving();
	}
	
	public void aconsejarFinal() {
		if(consejosActivados && celular.estaEnZonaDeEstacionamiento()) {
			notificador.aconsejarFinal(celular);
		}
	}

	public void aconsejarInicio() {
		if(consejosActivados && celular.estaEnZonaDeEstacionamiento()) {
			notificador.aconsejarInicio(celular);
		}
	}
}