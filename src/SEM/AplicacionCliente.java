package SEM;

public class AplicacionCliente {
	private int valorDeHora = 40;
	private Modo modo = new ModoManual();
	private Notificador notificador = new Notificador();
	private Celular celular;
	private CentroRegistros centroRegistros = CentroRegistros.getCentro();
	private CentroZonas centroZonas = CentroZonas.getCentro();
	private boolean consejosActivados = true;
	
	public AplicacionCliente(Celular celular, MovementSensor ms) {
		this.celular = celular;
		ms.addApp(this);
	}
	
	public void setValorDeHora(int valor) {
		this.valorDeHora = valor;
	}
	
	public void activarConsejos() {
		this.consejosActivados = true;
	}
	
	public void desactivarConsejos() {
		this.consejosActivados = false;
	}
	
	public void cambiarModo(Modo modo) {
		this.modo = modo;
	}
	
	public void iniciarEstacionamiento() throws Exception {
		modo.iniciarEstacionamiento(this);
	}
	
	public void generarRegistro() throws Exception{
		centroZonas.validarZona(celular.getZona());
		celular.validarSaldo(this.valorDeHora);
		RegistroAplicacion registro = new RegistroAplicacion(celular.getPatente(), celular.getZona(),celular);
		notificador.informarInicio(celular, registro);
		centroRegistros.registrarInicio(registro);
	}
	
	private void validarFinalizacionManual() throws Exception {
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
		modo.walking(this);
	}

	public void aconsejarFinal() {
		if(consejosActivados) {
			notificador.aconsejarFinal(celular);
		}
		
	}

	public void aconsejarInicio() {
		if(consejosActivados) {
			notificador.aconsejarInicio(celular);
		}
		
	}

	public void driving() throws Exception {
		modo.driving(this);
		
	}

}