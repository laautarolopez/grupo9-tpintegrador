package SEM;

public class AplicacionCliente implements ValorDeHora {
	private Sistema sistema;
	private Modo modo;
	private Notificador notificador;
	private Celular celular;
	private boolean consejosActivados;
	
	public AplicacionCliente(Sistema sistema, Celular celular, MovementSensor ms) {
		this.sistema = sistema;
		this.modo = new ModoManual(this);
		this.notificador = new Notificador();
		this.celular = celular;
		this.consejosActivados = true;
		//ms.addApp(this);
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
	
	public void iniciarEstacionamiento() throws Exception {
		modo.iniciarEstacionamiento(this);
	}
	
	public void generarRegistro() throws Exception{
		sistema.validarZona(celular.getZona());
		celular.validarSaldo(valorDeHora);
		RegistroAplicacion registro = new RegistroAplicacion(sistema, celular.getPatente(), celular.getZona(),celular);
		notificador.informarInicio(celular, registro);
		sistema.registrarInicio(registro);
	}
	
	protected void validarFinalizacionManual() throws Exception {
		if(modo.finalizacionManual()) {
			throw new Exception("El modo actual de la aplicación no permite finalizar estacionamientos de forma manual");
		}
	}
	public void finalizarEstacionamiento() throws Exception{
		this.validarFinalizacionManual();
		this.realizarFinalizacion();
	}
	
	public void realizarFinalizacion() throws Exception{
		centroRegistros.validarExistenciaDeEstacionamiento(celular.getPatente());
		notificador.informarFinal(celular, centroRegistros.getRegistro(celular.getPatente()));
		centroRegistros.registrarFinal(celular.getPatente());
	}
	
	public void walking() throws Exception {
		if(celular.estaEnZonaDeEstacionamiento()){
			modo.walking(this);
		}
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

	public void driving() throws Exception {
		if(celular.estaEnZonaDeEstacionamiento()) {
			modo.driving();
		}
	}
}